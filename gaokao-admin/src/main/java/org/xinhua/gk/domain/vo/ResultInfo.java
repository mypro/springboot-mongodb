package org.xinhua.gk.domain.vo;

import org.xinhua.gk.util.Lang;

import java.io.Serializable;


public class ResultInfo<T> implements Serializable {

    private static final String SUCCESS_CODE = "0";
    private static final String ERROR_CODE = "500";
    private static final long serialVersionUID = -5299129879990651937L;
    private String errcode;
    private String errmsg;

    private T content;

    public ResultInfo() {
        this.setErrcode(SUCCESS_CODE);
    }

    public static ResultInfo createOKResp(Object object, String msg) {
        ResultInfo result = new ResultInfo();
        result.setContent(object);
        if (Lang.isNotEmpty(msg)) {
            result.setErrmsg(msg);
        }
        return result;
    }

    public static ResultInfo createErrorResp(String errcode, String msg) {
        ResultInfo result = new ResultInfo();
        if (Lang.isNotEmpty(errcode)) {
            result.setErrcode(errcode);
        } else {
            result.setErrcode(ERROR_CODE);
        }
        if (Lang.isNotEmpty(msg)) {
            result.setErrmsg(msg);
        }
        return result;
    }

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

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return errcode == null || errcode.isEmpty() || errcode.equals(SUCCESS_CODE);
    }
}
