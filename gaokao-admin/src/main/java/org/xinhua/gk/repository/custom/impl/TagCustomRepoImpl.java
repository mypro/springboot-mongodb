package org.xinhua.gk.repository.custom.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.xinhua.gk.domain.entity.TTag;
import org.xinhua.gk.repository.condition.TagCondition;
import org.xinhua.gk.repository.custom.TagCustomRepo;
import org.xinhua.gk.util.Lang;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TagCustomRepoImpl extends BaseRepoImpl<TTag> implements TagCustomRepo {

    @Override
    public List<Object> distinctByField(TagCondition condition, String field) {
        Query query = new Query();
        generateQuery(query, condition);
        return super.distinct(query, field);
    }

    @Override
    public Page getPageByCondition(TagCondition condition) {
        // 设定返回字段
        Query query = super.genFields(condition);
        // 生成列表查询条件
        generateQuery(query, condition);
        // 返回分页查询结果
        return super.pageFind(condition, query);
    }

    @Override
    public List<TTag> getListByCondition(TagCondition condition) {
        Query query = super.genFields(condition);
        // 生成列表查询条件
        generateQuery(query, condition);
        super.addSortFields(query, condition);
        List<TTag> resultList = super.find(query);
        return resultList;
    }

    /**
     * 拼接查询条件
     *
     * @param query
     * @param condition
     */
    @SuppressWarnings("static-access")
    private void generateQuery(Query query, TagCondition condition) {
        Criteria criteria = new Criteria();
        List<Criteria> andCriteriaList = new ArrayList<>();
        List<Criteria> orCriteriaList = new ArrayList<>();
        if (condition != null) {


            if (Lang.isNotEmpty(condition.getType())) {
                query.addCriteria(criteria.where("type").is(condition.getType()));
            }



            if (Lang.isNotEmpty(condition.getName())) {
                query.addCriteria(criteria.where("name").regex(condition.getName()));
            }


            //  添加or条件
            if (!CollectionUtils.isEmpty(orCriteriaList)) {
                Criteria orCriteria = new Criteria();
                orCriteria.orOperator(orCriteriaList.toArray(new Criteria[orCriteriaList.size()]));
                andCriteriaList.add(orCriteria);
            }

            // 添加and条件
            if (!CollectionUtils.isEmpty(andCriteriaList)) {
                Criteria andCriteria = new Criteria();
                query.addCriteria(andCriteria.andOperator(andCriteriaList.toArray(new Criteria[andCriteriaList.size()])));
            }
        }
    }

}
