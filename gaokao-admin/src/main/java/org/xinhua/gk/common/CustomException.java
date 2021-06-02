package org.xinhua.gk.common;

public class CustomException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 4410816908582077202L;

    /**
     * 错误编码
     */
    private Integer errorCode;

    public CustomException() {

    }

    public CustomException(String message) {
        super(message);
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode 错误编码
     * @param message   信息描述
     */
    public CustomException(Integer errorCode, String message) {
        super(message);
        this.setErrorCode(errorCode);
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode 错误编码
     * @param message   信息描述
     */
    public CustomException(Integer errorCode, String message, Throwable cause) {
        super(message, cause);
        this.setErrorCode(errorCode);
    }


    /**
     * 构造一个基本异常.
     *
     * @param message 信息描述
     * @param cause   根异常类（可以存入任何异常）
     */
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
