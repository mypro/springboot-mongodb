package org.xinhua.gk.repository.condition;

import java.util.Date;
import java.util.List;

public class LiveInfoCondition extends BaseCondition {

    /**
     * 查询字段 begin
     **/


    private String name;

    private Integer status;

    private Integer catId;

    private String liveId;

    private List<String> addrIds;

    private String bakId;

    private Integer type;
    // 直播协议，1推流，2拉流
    private Integer protocol;
    //云直播类型：5-阿里云，7-新华社
    private String url;

    // 发布时间
    private Date beginPublishDate;

    private Date endPublishDate;
    //截止时间
    private Date deadline;
    /** 查询字段 end **/

    /**
     * 排序字段 begin
     **/


    private String publishUserId;
    // 排序-创建时间
    private String sortCreatedAt;
    // 发布时间
    public static final String SORT_CREATED_DATE = "createdAt";


    public Date getBeginPublishDate() {
        return beginPublishDate;
    }

    public void setBeginPublishDate(Date beginPublishDate) {
        this.beginPublishDate = beginPublishDate;
    }

    public Date getEndPublishDate() {
        return endPublishDate;
    }

    public void setEndPublishDate(Date endPublishDate) {
        this.endPublishDate = endPublishDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }



    public String getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(String publishUserId) {
        this.publishUserId = publishUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProtocol() {
        return protocol;
    }

    public void setProtocol(Integer protocol) {
        this.protocol = protocol;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSortCreatedAt() {
        return sortCreatedAt;
    }

    public void setSortCreatedAt(String sortCreatedAt) {
        this.sortCreatedAt = sortCreatedAt;
    }

    public static String getSortCreatedDate() {
        return SORT_CREATED_DATE;
    }

    public String getBakId() {
        return bakId;
    }

    public void setBakId(String bakId) {
        this.bakId = bakId;
    }

    public List<String> getAddrIds() {
        return addrIds;
    }

    public void setAddrIds(List<String> addrIds) {
        this.addrIds = addrIds;
    }
}
