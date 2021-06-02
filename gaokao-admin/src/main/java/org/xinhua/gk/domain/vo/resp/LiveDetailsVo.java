package org.xinhua.gk.domain.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.xinhua.gk.domain.attr.TemplateAttr;
import org.xinhua.gk.domain.vo.PeriodTime;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "直播页详情信息")
public class LiveDetailsVo implements Serializable{

    private static final long serialVersionUID = -4341090523595100516L;

    private String id;

    private String liveId;

    private String title;

    @ApiModelProperty(value = "模板信息")
    private List<TemplateAttr> templates;

    @ApiModelProperty(value = "直播起止时间")
    private PeriodTime liveTime;

    @ApiModelProperty(value = "移动端直播地址")
    private List<PlayerStream> url;

    @ApiModelProperty(value = "移动端视频封面图")
    private String imageUrl;

    @ApiModelProperty(value = "PC端直播地址")
    private List<PlayerStream> pc_url;

    @ApiModelProperty(value = "PC端视频封面图")
    private String pc_imageUrl;

    /**
     * 展示内容 1 展示;  0 不展示
     */
    @ApiModelProperty(value = "是否展示简介 1 展示;  0 不展示;")
    private Integer showContent;

    /**
     * 是否点赞 1 点赞  0 不点赞
     */
    @ApiModelProperty(value = "是否点赞 1 点赞  0 不点赞")
    private Integer showLike;

    @ApiModelProperty(value = "模板类型 1 直播；2 录播")
    private Integer type;

    /**
     * 是否自动播放 1 自动播放;  0 不自动播放
     */
    @ApiModelProperty(value = "是否自动播放 1 自动播放;  0 不自动播放")
    private Integer autoPlay;

    /**
     * 是否开启WebSocket 1开启; 0 关闭
     */
    @ApiModelProperty(value = "是否开启WebSocket 1开启; 0 关闭")
    private Integer wsEnable;

    @ApiModelProperty(value = "是否开启debug 1 开启 0 关闭")
    private Integer debug;
    /**
     * 是否发送小纸条 1 发送; 0 不发送
     */
    @ApiModelProperty(value = "是否小纸条 1 开启小纸条; 0 关闭小纸条")
    private Integer isNote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TemplateAttr> getTemplates() {
        return templates;
    }

    public void setTemplates(List<TemplateAttr> templates) {
        this.templates = templates;
    }

    public PeriodTime getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(PeriodTime liveTime) {
        this.liveTime = liveTime;
    }

    public List<PlayerStream> getUrl() {
        return url;
    }

    public void setUrl(List<PlayerStream> url) {
        this.url = url;
    }

    public List<PlayerStream> getPc_url() {
        return pc_url;
    }

    public void setPc_url(List<PlayerStream> pc_url) {
        this.pc_url = pc_url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPc_imageUrl() {
        return pc_imageUrl;
    }

    public void setPc_imageUrl(String pc_imageUrl) {
        this.pc_imageUrl = pc_imageUrl;
    }

    public Integer getShowContent() {
        return showContent;
    }

    public void setShowContent(Integer showContent) {
        this.showContent = showContent;
    }

    public Integer getShowLike() {
        return showLike;
    }

    public void setShowLike(Integer showLike) {
        this.showLike = showLike;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAutoPlay() {
        return autoPlay;
    }

    public void setAutoPlay(Integer autoPlay) {
        this.autoPlay = autoPlay;
    }

    public Integer getWsEnable() {
        return wsEnable;
    }

    public void setWsEnable(Integer wsEnable) {
        this.wsEnable = wsEnable;
    }

    public Integer getDebug() {
        return debug;
    }

    public void setDebug(Integer debug) {
        this.debug = debug;
    }

    public Integer getIsNote() {
        return isNote;
    }

    public void setIsNote(Integer isNote) {
        this.isNote = isNote;
    }
}
