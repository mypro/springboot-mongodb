package org.xinhua.gk.domain.vo;

import java.io.Serializable;
import java.util.Date;

public class CategoryVO implements Serializable {

    private static final long serialVersionUID = -5891317228754707954L;

    private String id;

    private Integer catId;
    /**
     * 分类名称
     */
    private String name;


    private Integer parent;
    /**
     * 简介
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
    /**
     * 修改人
     */
    private UserVO updatedUser;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserVO getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(UserVO updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }


}


