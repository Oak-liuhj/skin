package com.ps.skin.service;

import com.ps.skin.model.entity.AiSkinAdmin;
import com.ps.skin.model.request.AiSkinAdminReq;
import com.ps.skin.service.base.BaseService;

/**
 * 后台用户接口类
 *
 * @author liuhj
 * @date 2020/05/20 14:48
 */
public interface AiSkinAdminService extends BaseService<AiSkinAdmin> {

    int registerAdminUser(AiSkinAdminReq skinAdminReq);
}
