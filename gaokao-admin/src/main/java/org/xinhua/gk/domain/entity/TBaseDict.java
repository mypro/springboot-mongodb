package org.xinhua.gk.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 字典表
 * @author  chenweichao
 */
@Document(collection = "TBaseDict")
public class TBaseDict implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -142432712625288915L;

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String value;

    private String descp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}
