package org.xinhua.gk.domain.attr;

import java.io.Serializable;

/**
 * @Classname PlayAddress
 * @Description TODO
 * @Date 2020/12/14 16:33
 * @Created by Chen Weichao
 */

@BaseAttr
public class PlayAddress implements Serializable {

    private static final long serialVersionUID = 74078412613437136L;

    private String rtmp;
    private String flv;
    private String hls;


    public String getRtmp() {
        return rtmp;
    }

    public void setRtmp(String rtmp) {
        this.rtmp = rtmp;
    }

    public String getFlv() {
        return flv;
    }

    public void setFlv(String flv) {
        this.flv = flv;
    }

    public String getHls() {
        return hls;
    }

    public void setHls(String hls) {
        this.hls = hls;
    }
}
