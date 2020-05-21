package com.ps.skin.service.impl;

import com.ps.skin.constant.AiSkinEnum;
import com.ps.skin.constant.ReturnCodeAndMsgEnum;
import com.ps.skin.dao.db.AiSkinAdminDao;
import com.ps.skin.dao.mapper.AiSkinAdminMapper;
import com.ps.skin.model.common.ReturnResult;
import com.ps.skin.model.entity.AiSkinAdmin;
import com.ps.skin.model.request.AiSkinAdminReq;
import com.ps.skin.model.response.AdminLoginResp;
import com.ps.skin.service.AiSkinAdminService;
import com.ps.skin.service.base.impl.BaseServiceImpl;
import com.ps.skin.utils.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    /**
     * 注册
     *
     * @param skinAdminReq
     * @return
     */
    @Override
    public int addAdminUser(AiSkinAdminReq skinAdminReq) {
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

    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @param ip
     * @return
     */
    @Override
    public ReturnResult<AdminLoginResp> login(String mobile, String password, String ip) {
        // 校验用户是否注册账号
        AiSkinAdmin skinAdmin = aiSkinAdminDao.queryOneByField("mobile", mobile);
        if (null == skinAdmin) {
            return new ReturnResult<>(ReturnCodeAndMsgEnum.THIS_MOBILE_NOT_REGISTER);
        }
        if (AiSkinEnum.status.LOCKED.getCode().equals(skinAdmin.getStatus())) {
            return new ReturnResult<>(ReturnCodeAndMsgEnum.ADMIN_USER_HAS_BEEN_LOCKED);
        }
        if (StringUtils.equals(skinAdmin.getPassword(), Md5Util.encodeMD5Hex(password))) {
            // 创建用户登录token
            String token = Md5Util.encodeMD5Hex(mobile + System.currentTimeMillis());
            AdminLoginResp adminLoginResp = new AdminLoginResp();
            BeanUtils.copyProperties(skinAdmin, adminLoginResp);
            adminLoginResp.setToken(token);
            // 更新用户登录信息
            AiSkinAdmin aiSkinAdmin = new AiSkinAdmin();
            BeanUtils.copyProperties(skinAdmin, aiSkinAdmin);
            aiSkinAdmin.setId(skinAdmin.getId());
            aiSkinAdmin.setLastLoginIp(ip);
            aiSkinAdmin.setLastLoginTime(new Date());
            aiSkinAdminDao.update(aiSkinAdmin);
            return new ReturnResult<>(ReturnCodeAndMsgEnum.SUCCESS, adminLoginResp);
        } else {
            return new ReturnResult<>(ReturnCodeAndMsgEnum.PASSWORD_IS_NOT_CORRECT);
        }
    }
}
