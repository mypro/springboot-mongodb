package org.xinhua.gk.domain.attr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 模块信息
 */
@ApiModel(value = "模块信息")
public class ModuleAttr implements Serializable {

    private static final long serialVersionUID = 2262749251812200064L;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 样式
     */
    @ApiModelProperty(value = "内容样式")
    private String style;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
