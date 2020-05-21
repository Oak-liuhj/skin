package com.ps.skin.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 后台用户登录请求实体类
 *
 * @author liuhj
 * @date 2020/05/21 11:45
 */
@Data
public class AdminLoginReq implements Serializable {
    /**
     * 用户名
     */
    public String username;
    /**
     * 密码
     */
    public String password;
    /**
     * 手机号
     */
    public String mobile;

}
