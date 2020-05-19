package com.ps.skin.constant;

import com.ps.skin.model.common.ReturnResult;

/**
 * 统一返回码
 *
 * @author liuhj
 * @date 2020/05/19 10:29
 */
public enum AiSkinCodeAndMsgEnum {

    PARAMETER_ISNULL(100001, "输入参数为空"),
    SERVER_EXCEPTION(500001, "服务端异常");

    /**
     * 返回错误码
     */
    private int code;
    /**
     * 返回错误信息
     */
    private String message;

    AiSkinCodeAndMsgEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static AiSkinCodeAndMsgEnum getByCode(int code) {
        for (AiSkinCodeAndMsgEnum item : AiSkinCodeAndMsgEnum.values()) {
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
    public static ReturnResult getReturnResult(AiSkinCodeAndMsgEnum codeAndMsgEnum) {
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
    public static <T> ReturnResult getReturnResult(AiSkinCodeAndMsgEnum codeAndMsgEnum, T data) {
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
