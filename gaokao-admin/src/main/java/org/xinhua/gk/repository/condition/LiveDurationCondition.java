package org.xinhua.gk.repository.condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@ApiModel(value = "直播学时统计信息实体")
public class LiveDurationCondition extends BaseCondition{

    /**
     * 活动actId
     */
    @NotEmpty
    private String actId;

    /**
     * 人员姓名
     */
    @ApiModelProperty(value = "人员姓名")
    private String name;

    /**
     * 人员部门
     */
    private String dept;

    /**
     * 直播结束时间
     */
    private Date checkEndTime;

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

    public Date getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(Date checkEndTime) {
        this.checkEndTime = checkEndTime;
    }
}
