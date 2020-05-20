package com.ps.skin.controller;

import com.ps.skin.constant.ReturnCodeAndMsgEnum;
import com.ps.skin.model.common.ReturnResult;
import com.ps.skin.model.entity.AiSkinAdmin;
import com.ps.skin.model.request.AiSkinAdminReq;
import com.ps.skin.service.AiSkinAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台登录控制器
 *
 * @author liuhj
 * @date 2020/05/19 17:19
 */
@RestController
@RequestMapping(value = "skin/v1/admin")
@Api(value = "skin/v1/login", tags = "登录控制器")
public class AdminLoginController {

    private final HttpServletRequest request;
    private final AiSkinAdminService aiSkinAdminService;

    @Autowired
    public AdminLoginController(HttpServletRequest request, AiSkinAdminService aiSkinAdminService) {
        this.request = request;
        this.aiSkinAdminService = aiSkinAdminService;
    }

    /**
     * 用户登录
     *
     * @param xx
     * @return
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "用户登录", notes = "用户登录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> login(@RequestBody String xx) {
        return new ReturnResult<String>(ReturnCodeAndMsgEnum.REQUEST_PARAM_LACK);
    }

    /**
     * 用户登出
     *
     * @param xxx
     * @return
     */
    @PostMapping(value = "/loginOut", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "用户登出", notes = "用户登出", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> loginOut(@RequestBody String xxx) {
        return new ReturnResult<String>(ReturnCodeAndMsgEnum.REQUEST_PARAM_LACK);
    }

    /**
     * 用户注册
     *
     * @param skinAdminReq 请求参数实体
     * @return
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "用户注册", notes = "用户注册", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> register(@RequestBody @Validated AiSkinAdminReq skinAdminReq) {
        if (StringUtils.isBlank(skinAdminReq.getUsername()) || StringUtils.isBlank(skinAdminReq.getPassword())) {
            return new ReturnResult<>(ReturnCodeAndMsgEnum.REQUEST_PARAM_LACK);
        }
        if (StringUtils.isBlank(skinAdminReq.getMobile())) {
            return new ReturnResult<>(ReturnCodeAndMsgEnum.REQUEST_MOBILE_IS_NULL);
        }
        // 校验该用户是否存在
        AiSkinAdmin aiSkinAdmin = aiSkinAdminService.queryOneByField("username", skinAdminReq.getUsername());
        if (null != aiSkinAdmin) {
            return new ReturnResult<>(ReturnCodeAndMsgEnum.THIS_USERNAME_HAS_EXIST);
        }
        aiSkinAdminService.registerAdminUser(skinAdminReq);
        return new ReturnResult<>(ReturnCodeAndMsgEnum.SUCCESS);
    }

    /**
     * 更新密码
     *
     * @param xxxx
     * @return
     */
    @PostMapping(value = "/updatePwd", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新密码", notes = "更新密码", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> updatePassWord(@RequestBody String xxxx) {
        return new ReturnResult<String>(ReturnCodeAndMsgEnum.SUCCESS);
    }
}
