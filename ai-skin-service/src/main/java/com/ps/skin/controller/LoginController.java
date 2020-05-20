package com.ps.skin.controller;

import com.ps.skin.constant.AiSkinCodeAndMsgEnum;
import com.ps.skin.model.common.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author
 * @date 2020/05/19 17:19
 */
@RestController
@RequestMapping(value = "skin/v1/login")
@Api(value = "skin/v1/login", tags = "登录控制器")
public class LoginController {

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "登录", notes = "登录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> login(@RequestBody String xx) {
        return new ReturnResult<String>(AiSkinCodeAndMsgEnum.PARAMETER_ISNULL);
    }


}
