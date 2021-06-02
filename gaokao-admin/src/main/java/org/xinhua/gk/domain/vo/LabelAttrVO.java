package org.xinhua.gk.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import org.xinhua.gk.domain.attr.BaseAttr;

import java.io.Serializable;

/**
 * 主持 讲师 等
 */
@BaseAttr
public class LabelAttrVO implements Serializable {
    /**
     * 称号
     */
    @ApiModelProperty(value = "标签名字")
    private String label;
    /**
     * 名称
     */
    @ApiModelProperty(value = "属性值")
    private String content;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介,描述信息")
    private String descp;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}

