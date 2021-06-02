package org.xinhua.gk.domain.attr;

import java.io.Serializable;

/**
 * 主持 讲师 等
 */
@BaseAttr
public class LabelAttr implements Serializable {

    private static final long serialVersionUID = -5464660772784875089L;

    /**
     * 名称（标签名）
     */
    private String label;
    /**
     * @see org.xinhua.gk.common.Constants.LabelTypeEnum
     */
    private Integer type;
    /**
     * 内容
     */
    private String content;
    /**
     * 简介
     */
    private String descp;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

