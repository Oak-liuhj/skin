package com.ps.skin.constant;

import com.ps.skin.model.common.ReturnResult;

/**
 * 统一返回码
 *
 * @author liuhj
 * @date 2020/05/19 10:29
 */
public enum ReturnCodeAndMsgEnum {

    SUCCESS(200, "操作成功"),
    SYSTEM_DB_CONNECT_FAILED(10000, "数据库连接失败"),
    REQUEST_PARAM_LACK(100001, "参数缺失"),
    REQUEST_PARAM_ILLEGAL(100002, "参数不合法"),

    REQUEST_MOBILE_IS_NULL(100003, "用户手机号为空"),
    THIS_USERNAME_HAS_EXIST(100004, "该用户已注册"),
    THIS_MOBILE_NOT_REGISTER(100005, "账号未注册"),
    ADMIN_USER_HAS_BEEN_LOCKED(100006, "用户被锁定"),
    PASSWORD_IS_NOT_CORRECT(100007,"密码不正确"),

    SERVER_EXCEPTION(500001, "服务端异常");

    /**
     * 返回错误码
     */
    private int code;
    /**
     * 返回错误信息
     */
    private String message;

    ReturnCodeAndMsgEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ReturnCodeAndMsgEnum getByCode(int code) {
        for (ReturnCodeAndMsgEnum item : ReturnCodeAndMsgEnum.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }

    /**
     * 将业务状态码封装至 ReturnResult
     *
     * @param codeAndMsgEnum 业务返回值枚举
     * @return ReturnResult
     */
    public static ReturnResult getReturnResult(ReturnCodeAndMsgEnum codeAndMsgEnum) {
        return new ReturnResult(codeAndMsgEnum.getCode(), codeAndMsgEnum.getMessage());
    }

    /**
     * 将业务代码以及数据封装至 ReturnResult
     *
     * @param codeAndMsgEnum 业务返回值枚举
     * @param data           数据
     * @param <T>            数据类型
     * @return ReturnResult
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> ReturnResult getReturnResult(ReturnCodeAndMsgEnum codeAndMsgEnum, T data) {
        return new ReturnResult(codeAndMsgEnum.getCode(), codeAndMsgEnum.getMessage(), data);
    }

    /**
     * 将业务代码重新封装
     *
     * @param returnResult 封装结果值
     * @return ReturnResult
     */
    public static ReturnResult getReturnResult(ReturnResult returnResult) {
        return new ReturnResult(returnResult.getCode(), returnResult.getMessage());
    }

    /**
     * 将业务代码重新封装
     *
     * @param returnResult 封装结果值
     * @return ReturnResult
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> ReturnResult getReturnResult(ReturnResult returnResult, T data) {
        return new ReturnResult(returnResult.getCode(), returnResult.getMessage(), data);
    }
}
