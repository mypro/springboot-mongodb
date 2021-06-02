package org.xinhua.gk.domain.attr;

import java.io.Serializable;

/**
 * @Classname VideoAttr
 * @Description TODO
 * @Date 2021/3/19 9:51
 * @Created by Chen Weichao
 */
public class VideoAttr implements Serializable {

    private static final long serialVersionUID = -3224917612853818730L;

    /**
     * 视频id ,来自VideoInfo 对象id
     */
    private String itemId;
    private Boolean outHls;
    private String hlsPath;
    private String outHlsPath;
    private String outHlsUrl;
    private Boolean outMp4;
    private String mp4Path;
    private String outMp4Path;
    private String outMp4Url;
    private String localPath;
    private String localMD5;
    private String outType;
    private String type;
    private String callback;

    public Boolean getOutHls() {
        return outHls;
    }

    public void setOutHls(Boolean outHls) {
        this.outHls = outHls;
    }

    public String getHlsPath() {
        return hlsPath;
    }

    public void setHlsPath(String hlsPath) {
        this.hlsPath = hlsPath;
    }

    public Boolean getOutMp4() {
        return outMp4;
    }

    public void setOutMp4(Boolean outMp4) {
        this.outMp4 = outMp4;
    }

    public String getMp4Path() {
        return mp4Path;
    }

    public void setMp4Path(String mp4Path) {
        this.mp4Path = mp4Path;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getLocalMD5() {
        return localMD5;
    }

    public void setLocalMD5(String localMD5) {
        this.localMD5 = localMD5;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOutHlsPath() {
        return outHlsPath;
    }

    public void setOutHlsPath(String outHlsPath) {
        this.outHlsPath = outHlsPath;
    }

    public String getOutMp4Path() {
        return outMp4Path;
    }

    public void setOutMp4Path(String outMp4Path) {
        this.outMp4Path = outMp4Path;
    }

    public String getOutHlsUrl() {
        return outHlsUrl;
    }

    public void setOutHlsUrl(String outHlsUrl) {
        this.outHlsUrl = outHlsUrl;
    }

    public String getOutMp4Url() {
        return outMp4Url;
    }

    public void setOutMp4Url(String outMp4Url) {
        this.outMp4Url = outMp4Url;
    }
}
