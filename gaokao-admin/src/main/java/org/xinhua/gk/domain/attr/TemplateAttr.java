package org.xinhua.gk.domain.attr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "模板属性")
public class TemplateAttr implements Serializable {
    private static final long serialVersionUID = 235661058577684730L;

    /**
     * 模块序号
     */
    @ApiModelProperty(value = "序号")
    private Integer serNo;

    /**
     * 区块标签
     */
    @ApiModelProperty(value = "模块标签")
    private String label;

    /**
     * 标签是否显示
     */
    @ApiModelProperty(value = "是否显示 1:显示标签名 0:不显示标签名")
    private Integer isDisplay;

    /**
     * 内容
     */
    @ApiModelProperty(value = "模块内容")
    private List<ModuleAttr> text;

    public Integer getSerNo() {
        return serNo;
    }

    public void setSerNo(Integer serNo) {
        this.serNo = serNo;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public List<ModuleAttr> getText() {
        return text;
    }

    public void setText(List<ModuleAttr> text) {
        this.text = text;
    }
}
