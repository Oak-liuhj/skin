package com.ps.skin.service.base;

import com.ps.skin.common.page.Page;

import java.util.List;

/**
 * 基础接口类
 *
 * @author liuhj
 * @date 2020/05/20 14:48
 */
public interface BaseService<T> {

    /**
     * 数据库插入 实体
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     *
     * @param record
     * @return 插入 成功返回实体,否则返回：1-成功,0-失败
     */
    int add(T record);

    /**
     * 根据实体类不为null的属性查询总数
     *
     * @param record
     * @return
     */
    int queryCount(T record);

    /**
     * 通过id查询 实体
     *
     * @param id
     * @return 实体
     */
    T queryById(Object id);

    /**
     * 根据实体类不为null的属性进行查询，有多个结果是抛出异常
     *
     * @param record
     * @return
     */
    T queryOne(T record);

    /**
     * 根据实体类不为null的属性进行查询
     *
     * @param record
     * @return
     */
    List<T> queryList(T record);

    /**
     * 查询所以数据查询
     *
     * @return
     */
    List<T> queryAll();

    /**
     * 根据实体类不为null的属性进行分页查询
     * (数据过多不建议使用)
     *
     * @return
     */
    Page<T> queryPage(Integer pageIndex, Integer pageSize, T record);

    /**
     * 根据实体的属性名和属性值查询
     *
     * @param fieldName  属性名
     * @param value 属性值
     * @return
     */
    T queryOneByField(String fieldName, Object value);

    /**
     * 根据实体的属性名和属性值查询
     *
     * @param fieldName  属性名
     * @param value 属性值
     * @return
     */
    List<T> queryByField(String fieldName, Object value);

    /**
     * 只更新不为null的属性
     *
     * @param record
     * @return
     */
    int modify(T record);

    /**
     * 更新所有属性
     * (建议使用modify(T record))
     *
     * @param record
     * @return
     */
    int update(T record);

    /**
     * 通过主键进行删除
     *
     * @param id
     * @return
     */
    int deleteById(Object id);

    /**
     * 根据实体类中属性不为null的条件进行删除
     * (条件全部使用=号and条件,建议谨慎使用)
     *
     * @param record
     * @return
     */
    int delete(T record);


}
