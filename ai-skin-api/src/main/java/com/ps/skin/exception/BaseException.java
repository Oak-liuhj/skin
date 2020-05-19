package com.ps.skin.exception;

/**
 * 异常基础类，自定义异常继承此异常
 *
 * @author liuhj
 * @date 2020/05/19 10:42
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -5875371379845226068L;

    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 具体异常码
     */
    protected int code;

    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(int code, String msgFormat, Object... args) {
        this.code = code;
        if (args == null || args.length == 0) {
            this.msg = msgFormat;
            return;
        }
        this.msg = String.format(msgFormat, args);
    }

    public BaseException() {
        super();
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public String getCodeMsg() {
        return code + "," + msg;
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    public BaseException newInstance(String msgFormat, Object... args) {
        return new BaseException(code, msgFormat, args);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message) {
        super(message);
    }

}
