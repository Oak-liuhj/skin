package com.ps.skin.model.common;

import com.alibaba.fastjson.JSONObject;
import com.ps.skin.constant.AiSkinCodeAndMsgEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 返回值封装
 *
 * @author liuhj
 * @date 2020/05/19 10:42
 */
public class ReturnResult<T> implements Serializable {
    /**
     * 返回code
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 初始化构造函数
     */
    public ReturnResult() {

    }

    public ReturnResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ReturnResult(int code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ReturnResult(AiSkinCodeAndMsgEnum codeAndMsgEnum) {
        this(codeAndMsgEnum.getCode(), codeAndMsgEnum.getMessage(), null);
    }

    public ReturnResult(AiSkinCodeAndMsgEnum codeAndMsgEnum, T data) {
        this(codeAndMsgEnum.getCode(), codeAndMsgEnum.getMessage(), data);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ReturnResult getInstance(String jsonStr) {
        if (StringUtils.isNotEmpty(jsonStr)) {
            try {
                JSONObject json = JSONObject.parseObject(jsonStr);
                if (json.get("code") != null) {
                    return new ReturnResult(json.getIntValue("code"), json.getString("message"), json.get("data"));
                } else {
                    return null;
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
