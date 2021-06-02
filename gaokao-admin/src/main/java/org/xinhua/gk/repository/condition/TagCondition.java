package org.xinhua.gk.repository.condition;

/**
 * @Classname TagCondition
 * @Description TODO
 * @Date 2021/1/27 11:17
 * @Created by Chen Weichao
 */
public class TagCondition extends BaseCondition{

    private Integer type;

    private String name;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
