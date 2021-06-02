package org.xinhua.gk.domain.temp;

import java.io.Serializable;

/**
 * @Classname ActivitiesTemplate
 * @Description TODO
 * @Date 2020/12/9 14:22
 * @Created by chenweichao
 */
public class ActivitiesTemplate implements Serializable {


    private static final long serialVersionUID = 2401133348603679702L;

    /**
     * 活动名称
     */
    private String title;
    /**
     * 主持
     */
    private String presides;
    /**
     * 活动简介
     */
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPresides() {
        return presides;
    }

    public void setPresides(String presides) {
        this.presides = presides;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
