package org.xinhua.gk.repository.condition;

/**
 * @Classname VideoInfoCondition
 * @Description TODO
 * @Date 2021/1/27 11:17
 * @Created by Chen Weichao
 */
public class VideoInfoCondition extends BaseCondition {

    private String title;

    private String name;

    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
