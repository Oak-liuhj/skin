package com.ps.skin.exception;

/**
 * 服务类异常
 *
 * @author liuhj
 * @date 2020/05/19 10:42
 */
public class ServiceException extends BaseException {

    private static final long serialVersionUID = -2951634658684102707L;

    public ServiceException() {

    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(int code, String message) {
        super(code, message);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
