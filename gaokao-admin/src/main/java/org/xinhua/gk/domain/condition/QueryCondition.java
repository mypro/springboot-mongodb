package org.xinhua.gk.domain.condition;

import org.xinhua.gk.domain.attr.User;

import java.io.Serializable;
import java.util.List;

public class QueryCondition implements Serializable {


    private static final long serialVersionUID = 1110236016756205507L;

    private String searchKeyword;

    // 排序-最后修改时间
    private String sortModifyTime;

    private String seqId;

    private String userId;

    private String actId;

    private String name;

    private List<String> images;

    private User operator;

    private String trueName;

    private String picPath;

    private Integer pageNo;

    private Integer pageSize;

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSortModifyTime() {
        return sortModifyTime;
    }

    public void setSortModifyTime(String sortModifyTime) {
        this.sortModifyTime = sortModifyTime;
    }

    public Integer getPageNo() {
        if (pageNo == null) {
            pageNo = 1;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            pageSize = 20;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
