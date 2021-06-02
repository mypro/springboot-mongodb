package org.xinhua.gk.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.xinhua.gk.domain.attr.User;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类
 *
 * @author chenweichao
 */
@Document(collection = "TCategory")
public class TCategory implements Serializable {


    private static final long serialVersionUID = 7494205874553617692L;
    @Id
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
     * 创建人
     */
    private User createdUser;
    /**
     * 更新时间
     */
    private Date updatedAt;
    /**
     * 修改人
     */
    private User updatedUser;

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

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(User updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}


