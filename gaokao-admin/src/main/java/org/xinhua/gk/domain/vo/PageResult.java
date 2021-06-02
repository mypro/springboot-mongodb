package org.xinhua.gk.domain.vo;

import java.util.List;

public class PageResult {

    private Integer pageNumber;
    private Integer pageSize;
    private Integer recordCount;
    private Integer pageCount;
    private List<Object> elements;

    public PageResult() {

    }

    public PageResult(Integer pageNumber, Integer pageSize, Integer recordCount, Integer pageCount, List<Object> elements) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
        this.pageCount = pageCount;
        this.elements = elements;
    }

    public List<Object> getElements() {
        return elements;
    }

    public void setElements(List<Object> elements) {
        this.elements = elements;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

}
