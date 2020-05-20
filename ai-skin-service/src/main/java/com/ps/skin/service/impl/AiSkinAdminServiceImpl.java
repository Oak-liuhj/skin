package com.ps.skin.service.impl;

import com.ps.skin.dao.db.AiSkinAdminDao;
import com.ps.skin.dao.mapper.AiSkinAdminMapper;
import com.ps.skin.model.entity.AiSkinAdmin;
import com.ps.skin.model.request.AiSkinAdminReq;
import com.ps.skin.service.AiSkinAdminService;
import com.ps.skin.service.base.impl.BaseServiceImpl;
import com.ps.skin.utils.Md5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后台用户接口实现类
 *
 * @author liuhj
 * @date 2020/05/20 14:49
 */
@Service
public class AiSkinAdminServiceImpl extends BaseServiceImpl<AiSkinAdminMapper, AiSkinAdmin> implements AiSkinAdminService {

    private final AiSkinAdminDao aiSkinAdminDao;

    @Autowired
    public AiSkinAdminServiceImpl(AiSkinAdminDao aiSkinAdminDao) {
        this.aiSkinAdminDao = aiSkinAdminDao;
    }

    @Override
    public int registerAdminUser(AiSkinAdminReq skinAdminReq) {
        if (null == skinAdminReq) {
            return 0;
        }
        AiSkinAdmin skinAdmin = new AiSkinAdmin();
        BeanUtils.copyProperties(skinAdminReq, skinAdmin);
        // 密码使用MD5加密处理
        String password = Md5Util.encodeMD5Hex(skinAdminReq.getPassword());
        skinAdmin.setPassword(password);
        return aiSkinAdminDao.insert(skinAdmin);
    }
}
