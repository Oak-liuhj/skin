package com.ps.skin.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台用户请求实体类
 *
 * @author liuhj
 * @date 2020/05/20 14:40
 */
@Data
public class AiSkinAdminReq implements Serializable {

    /**
     * 管理员名称
     */
    @NotBlank(message = "管理员名称不能为空")
    @ApiModelProperty(notes = "管理员名称")
    private String username;
    /**
     * 管理员密码
     */
    @NotBlank(message = "管理员密码不能为空")
    @ApiModelProperty(notes = "管理员密码")
    private String password;
    /**
     * 真实姓名
     */
    @ApiModelProperty(notes = "真实姓名")
    private String realName;
    /**
     * 头像图片
     */
    @ApiModelProperty(notes = "头像图片")
    private String picture;
    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空")
    @ApiModelProperty(notes = "手机号码")
    private String mobile;
    /**
     * 邮箱地址
     */
    @ApiModelProperty(notes = "邮箱地址")
    private String mail;
    /**
     * 状态（0-正常  1-锁定 2-删除）
     */
    @ApiModelProperty(notes = "状态（0-正常  1-锁定 2-删除）")
    private Integer status;
    /**
     * 用户描述
     */
    @ApiModelProperty(notes = "用户描述")
    private String description;
    /**
     * 最近一次登录IP地址
     */
    @ApiModelProperty(notes = "最近一次登录IP地址")
    private String lastLoginIp;
    /**
     * 最近一次登录时间
     */
    @ApiModelProperty(notes = "最近一次登录时间")
    private Date lastLoginTime;
    /**
     * 是否删除（0-未删 1-已删）
     */
    @ApiModelProperty(notes = "是否删除（0-未删 1-已删）")
    private Integer isDeleted;
}
