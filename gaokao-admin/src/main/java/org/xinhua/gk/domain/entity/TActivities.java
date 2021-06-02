package org.xinhua.gk.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.xinhua.gk.domain.attr.LabelAttr;
import org.xinhua.gk.domain.attr.User;
import org.xinhua.gk.domain.temp.ProposalTemplate;
import org.xinhua.gk.domain.vo.PeriodTime;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 活动
 *
 * @author chenweichao
 */
@Document(collection = "TActivities")
public class TActivities implements Serializable {

    private static final long serialVersionUID = -5891317228754707954L;

    @Id
    private String id;
    /**
     * 活动ID ，唯一标志
     */
    @Indexed(unique = true)
    private String actId;
    /**
     * 分类id ，不唯一
     */
    @Indexed
    private Integer catId;
    /**
     * 活动名称
     */
    private String title;
    /**
     * 活动地点
     */
    private List<LabelAttr> address;
    /**
     * 封面图
     */
    private String coverImage;
    /**
     * 封面图 _ 手机
     */
    private String mobileCoverImage;
    /**
     * 主持
     */
    private List<LabelAttr> presides;

    /**
     * 活动简介
     */
    private List<LabelAttr> description;
    /**
     * 活动起止时间
     */
    private PeriodTime actTime;
    /**
     * 报名起止时间
     */
    private PeriodTime applyTime;
    /**
     * 小纸条 交流时间
     */
    private PeriodTime noteTime;

    /**
     * 留言起止时间
     */
    private PeriodTime proposalTime;

    private List<ProposalTemplate> proposalTemplates;
    /**
     * 状态
     *
     * @see org.xinhua.gk.common.Constants.ActivityStatusEnum
     */
    private Integer status;

    /**
     * 1 开启直播，0 不开启直播
     */
    private Integer liveEnabled;

    /**
     * 1 开启报名，0 不开启报名
     */
    private Integer applyEnabled;

    /**
     * 1 仅报名可发送小纸条，0 未报名可发送
     */
    private Integer noteFlag;

    /**
     * 1 开启小纸条，0 不开启小纸条
     */
    private Integer noteEnabled;

    /**
     * 1 仅报名可发送，0 未报名可发送
     */
    private Integer proposalFlag;

    /**
     * 1 开启留言，0 不开启留言
     */
    private Integer proposalEnabled;

    /**
     * 1 有现场，0 无现场
     */
    private Integer sceneEnabled;

    /**
     * 是否有录播上传视频 1：开启  0：关闭
     */
    private Integer playbackEnable;

    @DBRef(lazy = true)
    private List<TTag> tags;

    @DBRef(lazy = true)
    private List<TTag> learnTags;
    /**
     * 管理人员
     */
    private List<User> managers;

    /**
     * 蓝信端播放地址
     */
    private String lxPlayAddress;

    /**
     * PC端播放地址
     */
    private String pcPlayAddress;

/**************************************/
    /**
     * 创建时间
     */
    private Date createdAt;


    /**
     * 创建人
     */
    private User createdUser;

    /**
     * 更新时间
     */
    private Date updatedAt;
    /**
     * 修改人
     */
    private User updatedUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LabelAttr> getAddress() {
        return address;
    }

    public void setAddress(List<LabelAttr> address) {
        this.address = address;
    }

    public List<LabelAttr> getPresides() {
        return presides;
    }

    public void setPresides(List<LabelAttr> presides) {
        this.presides = presides;
    }

    public List<LabelAttr> getDescription() {
        return description;
    }

    public void setDescription(List<LabelAttr> description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(User updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLiveEnabled() {
        return liveEnabled;
    }

    public void setLiveEnabled(Integer liveEnabled) {
        this.liveEnabled = liveEnabled;
    }

    public Integer getNoteEnabled() {
        return noteEnabled;
    }

    public void setNoteEnabled(Integer noteEnabled) {
        this.noteEnabled = noteEnabled;
    }

    public Integer getApplyEnabled() {
        return applyEnabled;
    }

    public void setApplyEnabled(Integer applyEnabled) {
        this.applyEnabled = applyEnabled;
    }

    public PeriodTime getProposalTime() {
        return proposalTime;
    }

    public void setProposalTime(PeriodTime proposalTime) {
        this.proposalTime = proposalTime;
    }

    public List<ProposalTemplate> getProposalTemplates() {
        return proposalTemplates;
    }

    public void setProposalTemplates(List<ProposalTemplate> proposalTemplates) {
        this.proposalTemplates = proposalTemplates;
    }

    public Integer getProposalEnabled() {
        return proposalEnabled;
    }

    public void setProposalEnabled(Integer proposalEnabled) {
        this.proposalEnabled = proposalEnabled;
    }

    public PeriodTime getActTime() {
        return actTime;
    }

    public void setActTime(PeriodTime actTime) {
        this.actTime = actTime;
    }

    public PeriodTime getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(PeriodTime noteTime) {
        this.noteTime = noteTime;
    }

    public PeriodTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(PeriodTime applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getSceneEnabled() {
        return sceneEnabled;
    }

    public void setSceneEnabled(Integer sceneEnabled) {
        this.sceneEnabled = sceneEnabled;
    }

    public List<User> getManagers() {
        return managers;
    }

    public void setManagers(List<User> managers) {
        this.managers = managers;
    }

    public String getLxPlayAddress() {
        return lxPlayAddress;
    }

    public void setLxPlayAddress(String lxPlayAddress) {
        this.lxPlayAddress = lxPlayAddress;
    }

    public String getPcPlayAddress() {
        return pcPlayAddress;
    }

    public void setPcPlayAddress(String pcPlayAddress) {
        this.pcPlayAddress = pcPlayAddress;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getMobileCoverImage() {
        return mobileCoverImage;
    }

    public void setMobileCoverImage(String mobileCoverImage) {
        this.mobileCoverImage = mobileCoverImage;
    }

    public Integer getNoteFlag() {
        return noteFlag;
    }

    public void setNoteFlag(Integer noteFlag) {
        this.noteFlag = noteFlag;
    }

    public Integer getProposalFlag() {
        return proposalFlag;
    }

    public void setProposalFlag(Integer proposalFlag) {
        this.proposalFlag = proposalFlag;
    }

    public List<TTag> getTags() {
        return tags;
    }

    public void setTags(List<TTag> tags) {
        this.tags = tags;
    }

    public List<TTag> getLearnTags() {
        return learnTags;
    }

    public void setLearnTags(List<TTag> learnTags) {
        this.learnTags = learnTags;
    }

    public Integer getPlaybackEnable() {
        return playbackEnable;
    }

    public void setPlaybackEnable(Integer playbackEnable) {
        this.playbackEnable = playbackEnable;
    }
}


