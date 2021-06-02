package org.xinhua.gk.repository.condition;

import org.springframework.data.domain.Sort;
import org.xinhua.gk.common.PageObject;

import java.util.ArrayList;
import java.util.List;

public class BaseCondition {

    // 返回字段 需BO层显示设置，如不设置则返回所有字段
    protected List<String> fields = new ArrayList<>();

    // 排序字段
    protected List<Sort.Order> orders = new ArrayList<>();

    protected Sort sort;

    // 分页实体
    protected PageObject page;

    protected Integer pageNo;
    protected Integer pageSize;

    protected Boolean ifPaged;
    protected Boolean allField;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getIfPaged() {
        return ifPaged;
    }

    public void setIfPaged(Boolean ifPaged) {
        this.ifPaged = ifPaged;
    }

    public Boolean getAllField() {
        return allField;
    }

    public void setAllField(Boolean allField) {
        this.allField = allField;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<Sort.Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Sort.Order> orders) {
        this.orders = orders;
    }

    public PageObject getPage() {
        return page;
    }

    public void setPage(PageObject page) {
        this.page = page;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
