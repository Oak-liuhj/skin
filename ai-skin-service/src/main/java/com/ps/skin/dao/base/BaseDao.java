package com.ps.skin.dao.base;

import com.ps.skin.common.page.Page;
import com.ps.skin.common.page.PageUtil;
import com.ps.skin.utils.ReflectionUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDao<D extends BaseMyMapper<T>, T> {
    private Logger log = LoggerFactory.getLogger(BaseDao.class);

    @Autowired
    protected D mapper;

    /**
     * 数据库插入 实体
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     *
     * @param record
     * @return 插入成功返回  1
     */
    public int insert(T record) {
        return record != null ? this.mapper.insertSelective(record) : 0;
    }

    /**
     * 通过id查询 实体
     *
     * @param id
     * @return 实体
     */
    public T queryById(Object id) {
        return id != null ? this.mapper.selectByPrimaryKey(id) : null;
    }

    /**
     * 根据实体类不为null的字段进行查询
     *
     * @param record
     * @return
     */
    public T queryOne(T record) {
        return record != null ? this.mapper.selectOne(record) : null;
    }

    /**
     * 根据实体类不为null的字段查询总数
     *
     * @param record
     * @return
     */
    public int queryCount(T record) {
        return record != null ? this.mapper.selectCount(record) : 0;
    }

    /**
     * 根据实体类不为null的字段进行查询
     *
     * @param record
     * @return
     */
    public List<T> query(T record) {
        return record != null ? this.mapper.select(record) : null;
    }

    /**
     * 查询所以数据查询
     *
     * @return
     */
    public List<T> queryAll() {
        return this.mapper.selectAll();
    }

    /**
     * 根据实体类不为null的字段进行分页查询
     *
     * @return
     */
    public Page<T> queryPage(Integer pageIndex, Integer pageSize, T record) {
        List<T> list = record != null ? this.mapper.select(record) : null;
        if (list != null) {
            return new Page<T>(pageIndex, pageSize, list.size(), PageUtil.getPageList(list, pageIndex, pageSize));
        }
        return new Page<T>();
    }

    /**
     * 根据实体的字段名和字段值查询
     *
     * @param fieldName 字段名
     * @param value     字段值
     * @return
     */
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
            return null;
        } catch (Exception e) {
            log.error("根据实体的字段名和字段值查询异常：{}", e);
            return null;
        }
    }


    /**
     * 根据实体的字段名和字段值查询
     *
     * @param fieldName 字段名
     * @param value     字段值
     * @return
     */
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
            log.error("根据实体的字段名和字段值查询List异常：{}", e);
        }
        return null;
    }


    /**
     * 只更新不为null的字段
     *
     * @param record
     * @return
     */
    public int modify(T record) {
        return record != null ? this.mapper.updateByPrimaryKeySelective(record) : 0;
    }

    /**
     * 更新所有字段
     * (建议使用modify(T record))
     *
     * @param record
     * @return
     */
    public int update(T record) {
        return record != null ? this.mapper.updateByPrimaryKey(record) : 0;
    }

    /**
     * 通过主键进行删除
     *
     * @param id
     * @return
     */
    public int deleteById(Object id) {
        return id != null ? this.mapper.deleteByPrimaryKey(id) : 0;
    }

    /**
     * 根据实体类中字段不为null的条件进行删除
     * (条件全部使用=号and条件,建议谨慎使用)
     *
     * @param record
     * @return
     */
    public int delete(T record) {
        return record != null ? this.mapper.delete(record) : 0;
    }

}
