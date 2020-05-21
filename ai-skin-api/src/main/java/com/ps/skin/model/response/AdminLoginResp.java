package com.ps.skin.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 后台用户登录响应实体
 *
 * @author liuhj
 * @date 2020/05/21 13:38
 */
@Data
public class AdminLoginResp implements Serializable {

    /**
     * 主键di
     */
    private Integer id;
    /**
     * token
     */
    private String token;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    @JsonIgnore
    private String realName;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String picture;
    /**
     * 描述
     */
    private String description;

}
