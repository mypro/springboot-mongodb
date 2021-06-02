package org.xinhua.gk.domain.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname CallbackDTO
 * @Description TODO
 * @Date 2021/3/26 9:49
 * @Created by Chen Weichao
 */
public class CallbackDTO  implements Serializable{

    /**
     * data : {"callback":"http://10.244.152.232/act/video/callback","cmdList":[{"args":["ffmpeg","-y","-i","/nasdata/act/actupload/video/202103/20210318/a5c440f6b8834137ade4ff3f4c9c1b69.mp4","-c:a","aac","-b:a","177514","-map","0:1","-use_editlist","1","-r","25","-max_muxing_queue_size","9999","-c:v","libx264","-preset","fast","-profile","main","-level","4.1","-vf","scale=1680:1048,format=yuv420p","-map","0:0","-movflags","+faststart","/nasdata/act/actupload/output/202103/20210319/626ba47bbdf944a3a1d8d1333fcd06e4_preview.mp4"],"bin":"ffmpeg"}],"hlsPath":"/nasdata/act/actupload/output/202103/20210319//626ba47bbdf944a3a1d8d1333fcd06e4","id":"626ba47bbdf944a3a1d8d1333fcd06e4","itemId":"605418f2f65bd035ea0ce7a3","localPath":"/nasdata/act/actupload/video/202103/20210318/a5c440f6b8834137ade4ff3f4c9c1b69.mp4","mp4Path":"/nasdata/act/actupload/output/202103/20210319/626ba47bbdf944a3a1d8d1333fcd06e4","outHls":false,"outHlsPath":"/nasdata/act/actupload/output/202103/20210319//626ba47bbdf944a3a1d8d1333fcd06e4_preview.m3u8","outMp4":true,"outMp4Path":"/nasdata/act/actupload/output/202103/20210319/626ba47bbdf944a3a1d8d1333fcd06e4_preview.mp4","outType":"720P","type":"video"}
     */

    private DataBean data;
    private Integer status;
    private String desc;

    public DataBean getData() {
        return data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * callback : http://10.244.152.232/act/video/callback
         * cmdList : [{"args":["ffmpeg","-y","-i","/nasdata/act/actupload/video/202103/20210318/a5c440f6b8834137ade4ff3f4c9c1b69.mp4","-c:a","aac","-b:a","177514","-map","0:1","-use_editlist","1","-r","25","-max_muxing_queue_size","9999","-c:v","libx264","-preset","fast","-profile","main","-level","4.1","-vf","scale=1680:1048,format=yuv420p","-map","0:0","-movflags","+faststart","/nasdata/act/actupload/output/202103/20210319/626ba47bbdf944a3a1d8d1333fcd06e4_preview.mp4"],"bin":"ffmpeg"}]
         * hlsPath : /nasdata/act/actupload/output/202103/20210319//626ba47bbdf944a3a1d8d1333fcd06e4
         * id : 626ba47bbdf944a3a1d8d1333fcd06e4
         * itemId : 605418f2f65bd035ea0ce7a3
         * localPath : /nasdata/act/actupload/video/202103/20210318/a5c440f6b8834137ade4ff3f4c9c1b69.mp4
         * mp4Path : /nasdata/act/actupload/output/202103/20210319/626ba47bbdf944a3a1d8d1333fcd06e4
         * outHls : false
         * outHlsPath : /nasdata/act/actupload/output/202103/20210319//626ba47bbdf944a3a1d8d1333fcd06e4_preview.m3u8
         * outMp4 : true
         * outMp4Path : /nasdata/act/actupload/output/202103/20210319/626ba47bbdf944a3a1d8d1333fcd06e4_preview.mp4
         * outType : 720P
         * type : video
         */

        private String callback;
        private String hlsPath;
        private String id;
        private String itemId;
        private String localPath;
        private String mp4Path;
        private boolean outHls;
        private String outHlsPath;
        private boolean outMp4;
        private String outMp4Path;
        private String outType;
        private String type;
        private List<CmdListBean> cmdList;

        public String getCallback() {
            return callback;
        }

        public void setCallback(String callback) {
            this.callback = callback;
        }

        public String getHlsPath() {
            return hlsPath;
        }

        public void setHlsPath(String hlsPath) {
            this.hlsPath = hlsPath;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getLocalPath() {
            return localPath;
        }

        public void setLocalPath(String localPath) {
            this.localPath = localPath;
        }

        public String getMp4Path() {
            return mp4Path;
        }

        public void setMp4Path(String mp4Path) {
            this.mp4Path = mp4Path;
        }

        public boolean isOutHls() {
            return outHls;
        }

        public void setOutHls(boolean outHls) {
            this.outHls = outHls;
        }

        public String getOutHlsPath() {
            return outHlsPath;
        }

        public void setOutHlsPath(String outHlsPath) {
            this.outHlsPath = outHlsPath;
        }

        public boolean isOutMp4() {
            return outMp4;
        }

        public void setOutMp4(boolean outMp4) {
            this.outMp4 = outMp4;
        }

        public String getOutMp4Path() {
            return outMp4Path;
        }

        public void setOutMp4Path(String outMp4Path) {
            this.outMp4Path = outMp4Path;
        }

        public String getOutType() {
            return outType;
        }

        public void setOutType(String outType) {
            this.outType = outType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<CmdListBean> getCmdList() {
            return cmdList;
        }

        public void setCmdList(List<CmdListBean> cmdList) {
            this.cmdList = cmdList;
        }

        public static class CmdListBean implements Serializable {
            /**
             * args : ["ffmpeg","-y","-i","/nasdata/act/actupload/video/202103/20210318/a5c440f6b8834137ade4ff3f4c9c1b69.mp4","-c:a","aac","-b:a","177514","-map","0:1","-use_editlist","1","-r","25","-max_muxing_queue_size","9999","-c:v","libx264","-preset","fast","-profile","main","-level","4.1","-vf","scale=1680:1048,format=yuv420p","-map","0:0","-movflags","+faststart","/nasdata/act/actupload/output/202103/20210319/626ba47bbdf944a3a1d8d1333fcd06e4_preview.mp4"]
             * bin : ffmpeg
             */

            private String bin;
            private List<String> args;

            public String getBin() {
                return bin;
            }

            public void setBin(String bin) {
                this.bin = bin;
            }

            public List<String> getArgs() {
                return args;
            }

            public void setArgs(List<String> args) {
                this.args = args;
            }
        }
    }
}
