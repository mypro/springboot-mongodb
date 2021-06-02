package org.xinhua.gk.repository.condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 报名信息Condition
 *
 * @author Xu_Pengtao
 */
@ApiModel("报名信息请求实体")
public class ApplyInfoCondition extends BaseCondition {

    @ApiModelProperty("活动 actId")
    private String actId;
    @ApiModelProperty("参与人姓名")
    private String name;

    @ApiModelProperty(value = "排序方式", example = "ASC/DESC")
    private String sortRegisterAt;

    /**
     * 排序字段
     */
    public static final String SORT_REGISTER_DATE = "registerAt";

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

    public String getSortRegisterAt() {
        return sortRegisterAt;
    }

    public void setSortRegisterAt(String sortRegisterAt) {
        this.sortRegisterAt = sortRegisterAt;
    }
}
