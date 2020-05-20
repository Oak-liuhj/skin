package com.ps.skin.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 管理端用户实体类
 *
 * @author liuhj
 * @date 2020/05/20 14:16
 */
@Accessors(chain = true)
@Data
@Table(name = "ai_skin_admin")
public class AiSkinAdmin {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 管理员名称
     */
    @Column(name = "username")
    private String username;
    /**
     * 管理员密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;
    /**
     * 头像图片
     */
    @Column(name = "picture")
    private String picture;
    /**
     * 联系电话
     */
    @Column(name = "mobile")
    private String mobile;
    /**
     * 邮箱地址
     */
    @Column(name = "mail")
    private String mail;
    /**
     * 状态（0-正常  1-锁定 2-删除）
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 用户描述
     */
    @Column(name = "description")
    private String description;
    /**
     * 最近一次登录IP地址
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;
    /**
     * 最近一次登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;
    /**
     * 是否删除（0-未删 1-已删）
     */
    @Column(name = "is_deleted")
    private Integer isDeleted;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}
