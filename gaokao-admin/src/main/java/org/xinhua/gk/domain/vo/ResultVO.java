package org.xinhua.gk.domain.vo;

import org.springframework.util.StringUtils;

public class ResultVO<T> {

    public static final Integer SUCCESS_CODE = 200;

    public static final String SUCCESS_MSG = "操作成功!";

    public static final Integer ERROR_CODE = 500;

    public static final String ERROR_MSG = "操作失败!";

    private Integer code = 200;
    private T result;
    private String desc;


    public ResultVO(Integer code, String desc, T result) {
        this.code = code;
        this.desc = desc;
        this.result = result;
    }

    public ResultVO() {
        this.code = 200;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ResultVO success(String msg, Object data){
        return new ResultVO(SUCCESS_CODE, StringUtils.isEmpty(msg) ? SUCCESS_MSG : msg, data);
    }

    public static ResultVO success(String msg){
        return new ResultVO(SUCCESS_CODE, StringUtils.isEmpty(msg) ? SUCCESS_MSG : msg, null);
    }

    public static ResultVO error(String msg, Object data){
        return new ResultVO(ERROR_CODE, StringUtils.isEmpty(msg) ? ERROR_MSG : msg, data);
    }

    public static ResultVO error(String msg){
        return new ResultVO(ERROR_CODE, StringUtils.isEmpty(msg) ? ERROR_MSG : msg, null);
    }

}
