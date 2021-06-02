package org.xinhua.gk.dserialNO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ISerialNO {

    @Resource
    private MongoTemplate mongoTemplate;

    public final static String SERIAL_NO_FORMAT = "%2s%s%05d";


    public final static String ACTIVITIES_TABLE_ID = "actId";
    public final static String ACTIVITIES_COLLECTION_NAME = "actDaySerialNo";
    public final static String CATGORY_TABLE_ID = "categoryId";
    public final static String CATGORY_COLLECTION_NAME = "catDaySerialNo";

    private List<String> getItemIdBase(LocalDate date, String tableId, int count, String collectionName) {
        DateTimeFormatter shortFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        List<DSerialNo> daySerialNos = getDayRollingSerialNoBase(date, collectionName, count);
        return daySerialNos.stream().map(d -> String.format(SERIAL_NO_FORMAT, tableId, shortFormat.format(date), d.getSerialNo())).collect(Collectors.toList());
    }

    private List<Integer> getItemIdBaseInteger(LocalDate date, String tableId, int count, String collectionName) {
        List<DSerialNo> daySerialNos = getDayRollingSerialNoBase(date, collectionName, count);
        return daySerialNos.stream().map(d -> d.getSerialNo()).collect(Collectors.toList());
    }

    /**
     * 获取按天流转的流水号公共方法，如果提供collectionName则以此name为准，否则使用DSerialNo对象默认的collection("d_serial")
     *
     * @param date
     * @param collectionName
     * @return
     */
    private List<DSerialNo> getDayRollingSerialNoBase(LocalDate date, String collectionName, int count) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DSerialNo serialNo;
        List<DSerialNo> dSerialNos = new ArrayList<>(count);
        if (!StringUtils.isEmpty(collectionName)) {
            serialNo = mongoTemplate.findAndModify(new Query(Criteria.where("createTime").is(date.format(formatter))),
                    (new Update()).inc("serialNo", count), new FindAndModifyOptions().upsert(true).returnNew(true),
                    DSerialNo.class, collectionName);
        } else {
            serialNo = mongoTemplate.findAndModify(new Query(Criteria.where("createTime").is(date.format(formatter))),
                    (new Update()).inc("serialNo", count), new FindAndModifyOptions().upsert(true).returnNew(true),
                    DSerialNo.class);
        }

        for (int i = count - 1; i >= 0; i--) {
            DSerialNo serialNo1 = new DSerialNo();
            BeanUtils.copyProperties(serialNo, serialNo1);
            serialNo1.setSerialNo(serialNo.getSerialNo() - i);
            dSerialNos.add(serialNo1);
        }
        return dSerialNos;
    }

    /**
     * 生成活动id
     *
     * @return
     */
    public String getRSerialNo() {
        return getRSerialNo(1).get(0);
    }


    /**
     * 批量生成活动id
     *
     * @param count
     * @return
     */
    public List<String> getRSerialNo(int count) {
        return getItemIdBase(LocalDate.now(), ACTIVITIES_TABLE_ID, count, ACTIVITIES_COLLECTION_NAME);
    }


    /**
     * 生成分类id
     */
    public Integer getCategorySerialNo() {
        return getCategorySerialNo(1).get(0);
    }

    /**
     * 批量生成分类id
     *
     * @param count
     * @return
     */
    public List<Integer> getCategorySerialNo(int count) {
        return getItemIdBaseInteger(LocalDate.now(), CATGORY_TABLE_ID, count, CATGORY_COLLECTION_NAME);
    }



}
