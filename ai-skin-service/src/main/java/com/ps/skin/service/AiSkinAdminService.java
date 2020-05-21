package com.ps.skin.service;

import com.ps.skin.model.common.ReturnResult;
import com.ps.skin.model.entity.AiSkinAdmin;
import com.ps.skin.model.request.AiSkinAdminReq;
import com.ps.skin.model.response.AdminLoginResp;
import com.ps.skin.service.base.BaseService;

/**
 * 后台用户接口类
 *
 * @author liuhj
 * @date 2020/05/20 14:48
 */
public interface AiSkinAdminService extends BaseService<AiSkinAdmin> {

    /**
     * 注册后台管理用户
     *
     * @param skinAdminReq
     * @return
     */
    int addAdminUser(AiSkinAdminReq skinAdminReq);

    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @param ip
     * @return
     */
    ReturnResult<AdminLoginResp> login(String mobile, String password, String ip);


}
