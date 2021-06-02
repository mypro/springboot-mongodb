package org.xinhua.gk.repository.condition;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ActivitiesCondition extends BaseCondition {

    /**
     * 查询字段 begin
     **/
    @ApiModelProperty(value = "标题关键字")
    private String name;

    @ApiModelProperty(value = "发布部门id")
    private String publishDeptId;

    @ApiModelProperty(value = "活动状态 (1, 未开始)(2, 进行中)(3, 已删除)(4, 已截止)(5, 已结束)")
    private Integer status;

    @ApiModelProperty(value = "活动分类id")
    private Integer catId;


    @ApiModelProperty(value = "1 视频直播")
    private Integer liveEnabled;


    @ApiModelProperty(value = "1 开启报名")
    private Integer applyEnabled;

    @ApiModelProperty(value = "1 开启小纸条")
    private Integer noteEnabled;


    @ApiModelProperty(value = "1 开启留言")
    private Integer proposalEnabled;

    @ApiModelProperty(value = "是否有录播上传视频 1：开启  0：关闭")
    private Integer playbackEnable;

    @ApiModelProperty(value = "1 现场活动")
    private Integer sceneEnabled;

    @ApiModelProperty(value = "活动标签")
    private String tag;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "开始时间 long型")
    private Date beginPublishDate;
    @ApiModelProperty(value = "结束时间 long型")
    private Date endPublishDate;

    private Date deadline;
    /** 查询字段 end **/

    /**
     * 排序字段 begin
     **/
    // 排序-创建时间
    private String sortCreatedAt;
    // 发布时间
    public static final String SORT_CREATED_DATE = "createdAt";

    public static final String SORT_ORDER_NUM = "orderNum";

    private String managerUserId;

    public String getPublishDeptId() {
        return publishDeptId;
    }

    public void setPublishDeptId(String publishDeptId) {
        this.publishDeptId = publishDeptId;
    }

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


    public String getManagerUserId() {
        return managerUserId;
    }

    public void setManagerUserId(String managerUserId) {
        this.managerUserId = managerUserId;
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

    public String getSortCreatedAt() {
        return sortCreatedAt;
    }

    public void setSortCreatedAt(String sortCreatedAt) {
        this.sortCreatedAt = sortCreatedAt;
    }

    public Integer getLiveEnabled() {
        return liveEnabled;
    }

    public void setLiveEnabled(Integer liveEnabled) {
        this.liveEnabled = liveEnabled;
    }

    public Integer getApplyEnabled() {
        return applyEnabled;
    }

    public void setApplyEnabled(Integer applyEnabled) {
        this.applyEnabled = applyEnabled;
    }

    public Integer getNoteEnabled() {
        return noteEnabled;
    }

    public void setNoteEnabled(Integer noteEnabled) {
        this.noteEnabled = noteEnabled;
    }

    public Integer getProposalEnabled() {
        return proposalEnabled;
    }

    public void setProposalEnabled(Integer proposalEnabled) {
        this.proposalEnabled = proposalEnabled;
    }

    public Integer getSceneEnabled() {
        return sceneEnabled;
    }

    public void setSceneEnabled(Integer sceneEnabled) {
        this.sceneEnabled = sceneEnabled;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getPlaybackEnable() {
        return playbackEnable;
    }

    public void setPlaybackEnable(Integer playbackEnable) {
        this.playbackEnable = playbackEnable;
    }
}
