package org.xinhua.gk.domain.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname 在线人数趋势
 * @Description TODO
 * @Date 2021/5/6 16:03
 * @Created by Chen Weichao
 */
public class TrendDTO implements Serializable{

    /**
     * errcode : 0
     * errmsg : MSG
     * num : 0
     * content : [{"id":31,"liveId":"1119","onlineCount":6,"liveUser":6,"onLineByLiveId":16,"onLineByLiveId_ww":10,"onLineByLiveId_nw":16,"createTime":"2021-04-20 14:50:50"}]
     */

    private String errcode;
    private String errmsg;
    private int num;
    private List<ContentBean> content;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean implements Serializable {
        /**
         * id : 31
         * liveId : 1119
         * onlineCount : 6
         * liveUser : 6
         * onLineByLiveId : 16
         * onLineByLiveId_ww : 10
         * onLineByLiveId_nw : 16
         * createTime : 2021-04-20 14:50:50
         */

        private int id;
        private String liveId;
        private int onlineCount;
        private int liveUser;
        private int onLineByLiveId;
        private int onLineByLiveId_ww;
        private int onLineByLiveId_nw;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLiveId() {
            return liveId;
        }

        public void setLiveId(String liveId) {
            this.liveId = liveId;
        }

        public int getOnlineCount() {
            return onlineCount;
        }

        public void setOnlineCount(int onlineCount) {
            this.onlineCount = onlineCount;
        }

        public int getLiveUser() {
            return liveUser;
        }

        public void setLiveUser(int liveUser) {
            this.liveUser = liveUser;
        }

        public int getOnLineByLiveId() {
            return onLineByLiveId;
        }

        public void setOnLineByLiveId(int onLineByLiveId) {
            this.onLineByLiveId = onLineByLiveId;
        }

        public int getOnLineByLiveId_ww() {
            return onLineByLiveId_ww;
        }

        public void setOnLineByLiveId_ww(int onLineByLiveId_ww) {
            this.onLineByLiveId_ww = onLineByLiveId_ww;
        }

        public int getOnLineByLiveId_nw() {
            return onLineByLiveId_nw;
        }

        public void setOnLineByLiveId_nw(int onLineByLiveId_nw) {
            this.onLineByLiveId_nw = onLineByLiveId_nw;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
