package org.xinhua.gk.domain.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 学时详情信息Vo (脱产学时、网络学时)
 */
@ApiModel(value = "学时详情信息Vo")
public class DurationDetailVo implements Serializable {

    private static final long serialVersionUID = -212073244060752061L;

    @ApiModelProperty(value = "累计学时 当学习进度小于90%时为空 默认45分钟为一学时 ")
    private float duration;

    @ApiModelProperty(value = "学习进度 当学习进度小于90%时为空")
    private Integer process;

    @ApiModelProperty(value = "学习完成状态 100% = true")
    private Boolean status;

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
