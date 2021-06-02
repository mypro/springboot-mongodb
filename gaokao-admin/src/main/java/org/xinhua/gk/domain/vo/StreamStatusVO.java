package org.xinhua.gk.domain.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname StreamStatusVO
 * @Description TODO
 * @Date 2021/4/20 10:08
 * @Created by Chen Weichao
 */
public class StreamStatusVO implements Serializable {


    private static final long serialVersionUID = 5309017614575144653L;
    private String liveId;
    private String title;
    private List<StreamBean> streams;

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public List<StreamBean> getStreams() {
        return streams;
    }

    public void setStreams(List<StreamBean> streams) {
        this.streams = streams;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class StreamBean implements Serializable {
        /**
         * 云直播类型：2021-阿里云，2024-新华社
         *
         * @see org.xinhua.gk.common.Constants.StreamTypeEnum
         */
        @ApiModelProperty(value = "云直播类型：2021-阿里云，2024-新华社")
        private Integer type;
        /**
         * 在线人数
         */
        @ApiModelProperty(value = "在线人数")
        private Integer onlineNum;
        /**
         * 码率
         */
        @ApiModelProperty(value = "码率")
        private Double BitRate;

        /**
         * 1正常，0无推流
         */
        @ApiModelProperty(value = "1正常，0无推流")
        private Integer status;

        private String playAddress;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getOnlineNum() {
            return onlineNum;
        }

        public void setOnlineNum(Integer onlineNum) {
            this.onlineNum = onlineNum;
        }

        public Double getBitRate() {
            return BitRate;
        }

        public void setBitRate(Double bitRate) {
            BitRate = bitRate;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getPlayAddress() {
            return playAddress;
        }

        public void setPlayAddress(String playAddress) {
            this.playAddress = playAddress;
        }
    }

}

