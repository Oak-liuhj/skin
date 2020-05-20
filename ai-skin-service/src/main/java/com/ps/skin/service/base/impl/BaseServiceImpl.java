package com.ps.skin.service.base.impl;

import com.ps.skin.common.page.Page;
import com.ps.skin.common.page.PageUtil;
import com.ps.skin.constant.ReturnCodeAndMsgEnum;
import com.ps.skin.dao.base.BaseMyMapper;
import com.ps.skin.exception.ServiceException;
import com.ps.skin.model.common.ReturnResult;
import com.ps.skin.service.base.BaseService;
import com.ps.skin.utils.ReflectionUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 通用接口实现类
 *
 * @author liuhj
 * @date 2020/05/20 14:48
 */
public class BaseServiceImpl<D extends BaseMyMapper<T>, T> implements BaseService<T> {

    private static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    public D mapper;

    @Override
    public int add(T record) {
        return this.mapper.insertSelective(record);
    }

    @Override
    public T queryById(Object id) {
        return id != null ? this.mapper.selectByPrimaryKey(id) : null;
    }

    @Override
    public int queryCount(T record) {
        return record != null ? this.mapper.selectCount(record) : 0;
    }

    @Override
    public List<T> queryList(T record) {
        return record != null ? this.mapper.select(record) : null;
    }

    @Override
    public T queryOne(T record) {
        return record != null ? this.mapper.selectOne(record) : null;
    }

    @Override
    public List<T> queryAll() {
        return this.mapper.selectAll();
    }

    @Override
    public Page<T> queryPage(Integer pageIndex, Integer pageSize, T record) {
        List<T> list = this.mapper.select(record);
        if (list != null) {
            return new Page<T>(pageIndex, pageSize, list.size(), PageUtil.getPageList(list, pageIndex, pageSize));
        }
        return new Page<T>(pageIndex, pageSize, 0, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T queryOneByField(String fieldName, Object value) {
        try {
            if (StringUtils.isNotBlank(fieldName) && value != null) {
                ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
                Class<T> entityClazz = (Class<T>) pt.getActualTypeArguments()[1];
                Object obj = entityClazz.newInstance();
                if (ReflectionUtil.setFieldValue(obj, fieldName, value)) {
                    List<T> list = this.mapper.select((T) obj);
                    if (list != null && list.size() > 0) {
                        return list.get(0);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("根据实体的字段名和字段值查询异常：{}", e);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> queryByField(String fieldName, Object value) {
        try {
            if (StringUtils.isNotBlank(fieldName) && value != null) {
                ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
                Class<T> entityClazz = (Class<T>) pt.getActualTypeArguments()[1];
                Object obj = entityClazz.newInstance();
                if (ReflectionUtil.setFieldValue(obj, fieldName, value)) {
                    return this.mapper.select((T) obj);
                }
            }
        } catch (Exception e) {
            logger.error("根据实体的字段名和字段值查询List异常：{}", e);
        }
        return null;
    }

    @Override
    public int modify(T record) {
        return record != null ? this.mapper.updateByPrimaryKeySelective(record) : 0;
    }

    @Override
    public int update(T record) {
        return record != null ? this.mapper.updateByPrimaryKey(record) : 0;
    }

    @Override
    public int deleteById(Object id) {
        return id != null ? this.mapper.deleteByPrimaryKey(id) : 0;
    }

    @Override
    public int delete(T record) {
        return record != null ? this.mapper.delete(record) : 0;
    }

    /**
     * 更新行数检查
     *
     * @param count 数据库受影响行数
     * @return 成功或失败原因
     */
    protected ReturnResult updateCount(int count) {
        if (count > 0) {
            return new ReturnResult(ReturnCodeAndMsgEnum.SUCCESS);
        }
        return new ReturnResult(ReturnCodeAndMsgEnum.SYSTEM_DB_CONNECT_FAILED);
    }

    /**
     * 事务检查 是否都更新，未同时更新则抛出异常
     *
     * @param exceptionMsg 异常信息
     * @param counts       更新记录数
     * @return 成功或抛出异常
     */
    public ReturnResult transactional(String exceptionMsg, int... counts) {
        boolean isSuccess = false;
        for (int count : counts) {
            if (count > 0) {
                isSuccess = true;
                continue;
            }
            isSuccess = false;
            break;
        }
        if (isSuccess) {
            return new ReturnResult(ReturnCodeAndMsgEnum.SUCCESS);
        }
        // 未同时更新，抛出异常回滚
        throw new ServiceException(exceptionMsg);
    }
}
