package org.xinhua.gk.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname 起止时间
 * @Description TODO
 * @Date 2020/12/9 15:01
 * @Created by Chen Weichao
 */
public class PeriodTime implements Serializable {

    /**
     * 起始时间
     */
    private Date startDate;
    /**
     * 截止时间
     */
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
