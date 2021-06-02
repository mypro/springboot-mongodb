package org.xinhua.gk.domain.vo;

public class SystemConfigVO extends BaseAttrVO {

    private String id;
    // 分类 用于区分配置类型
    private String type;
    // key 关键字
    private String key;
    // 设置内容
    private String value;
    // 标签
    private String label;
    // 排序
    private Integer sort;
    // 是否启用
    private Integer isUsed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }
}
