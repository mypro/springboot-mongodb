package org.xinhua.gk.domain.temp;

import java.io.Serializable;

/**
 * @Classname NoteTemplate
 * @Description  模板类
 * @Date 2020/12/11 16:24
 * @Created by Chen Weichao
 */
public class ProposalTemplate implements Serializable {


    private static final long serialVersionUID = -575676355109010059L;
    /**
     * 名称
     */
    private String label;
    /**
     * 存入哪个字段
     */
    private String columnName;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
