package org.xinhua.gk.repository.condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 小纸条Condition
 *
 * @author Xu_Pengtao
 */
@ApiModel("小纸条请求实体")
public class NoteInfoCondition extends BaseCondition{

    @ApiModelProperty("活动 actId")
    private String actId;
    @ApiModelProperty("参与人姓名")
    private String name;

    @ApiModelProperty(value = "排序方式", example = "ASC/DESC")
    private String sortNoteAt;
    @ApiModelProperty("排序字段")
    public static final String SORT_NOTE_DATE = "noteAt";

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortNoteAt() {
        return sortNoteAt;
    }

    public void setSortNoteAt(String sortNoteAt) {
        this.sortNoteAt = sortNoteAt;
    }
}
