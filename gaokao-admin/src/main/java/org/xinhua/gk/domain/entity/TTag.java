package org.xinhua.gk.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Classname 标签
 * @Description TODO
 * @Date 2021/1/27 11:07
 * @Created by Chen Weichao
 */

@Document(collection = "TTag")
@CompoundIndexes({
        @CompoundIndex(name = "tag_idx", def = "{'type': 1, 'name': 1}", unique = true)
})
public class TTag implements Serializable {


    private static final long serialVersionUID = 6768455616407084981L;

    @Id
    private String id;

    /**
     * 1 活动标签; 2 学习标签
     */
    private Integer type;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
