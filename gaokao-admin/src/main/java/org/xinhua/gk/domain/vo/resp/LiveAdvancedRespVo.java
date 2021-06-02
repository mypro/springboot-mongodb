package org.xinhua.gk.domain.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 直播记学时高级操作面板
 */
@ApiModel(value = "直播学时统计高级操作面板信息")
public class LiveAdvancedRespVo implements Serializable {
    private static final long serialVersionUID = 235960878811832852L;

    /**
     * 执行状态 1：执行中
     */
    @ApiModelProperty(value = "执行状态 1：执行中")
    private Integer status;

    /**
     * 执行进度
     */
    @ApiModelProperty(value = "执行进度")
    private Integer progress;

    /**
     * 执行速率
     */
    @ApiModelProperty(value = "执行速率")
    private Double rate;

    /**
     * 预估剩余执行时长
     */
    @ApiModelProperty(value = "预估剩余执行时长")
    private Integer restTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getRestTime() {
        return restTime;
    }

    public void setRestTime(Integer restTime) {
        this.restTime = restTime;
    }
}
