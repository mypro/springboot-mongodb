package org.xinhua.gk.domain.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "播放器流信息")
public class PlayerStream implements Serializable {

    private static final long serialVersionUID = 5902853115908261875L;

    @ApiModelProperty(value = "源地址url")
    protected String src;

    @ApiModelProperty(value = "常用Mime类型")
    protected String type;

    @ApiModelProperty(value = "标签 高清/标清")
    protected String label;

    @ApiModelProperty(value = "画质 720P / 360P")
    protected String res;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
