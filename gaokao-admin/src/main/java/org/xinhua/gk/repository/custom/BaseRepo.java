package org.xinhua.gk.repository.custom;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Map;

/**
 * mongoTemplate查询用基类
 * Created by fmy on 2017/8/23.
 */
public interface BaseRepo<T> {

    /**
     * 根据query查询并判断是否存在相应实体
     *
     * @param query
     * @return
     */
    boolean exists(Query query) throws RuntimeException;

    /**
     * 根据query查询并统计结果数
     *
     * @param query
     * @return
     */
    long count(Query query) throws RuntimeException;

    /**
     * 根据query查询，返回实体列表
     *
     * @param query
     * @return
     */
    List<T> find(Query query) throws RuntimeException;

    /**
     * 根据query和sorts进行分页查询，返回实体列表
     *
     * @param query
     * @param sorts
     * @return
     */
    List<T> find(Query query, List<Sort> sorts) throws RuntimeException;

    /**
     * 根据query查询，返回单个实体
     *
     * @param query
     * @return
     */
    T findOne(Query query) throws RuntimeException;

    /**
     * 根据objectid查询，返回单个实体
     *
     * @param id 实体的objectid
     * @return
     */
    T findOneById(String id) throws RuntimeException;

    /**
     * 保存实体对象。分两种情况：
     * 1. 当对象id字段没有值时，是创建
     * 2. 当对象id字段有值时，是更新，将会更新所有指定的object的非空字段
     *
     * @param object
     * @throws Exception
     */
    void save(T object) throws Exception;

    /**
     * 根据query删除
     *
     * @param query
     */
    void remove(Query query) throws Exception;

    /**
     * 删除指定实体对象
     *
     * @param object
     */
    void removeOne(T object) throws Exception;

    /**
     * 根据实体的objectid删除
     *
     * @param id 实体的objectid
     */
    void removeById(String id) throws Exception;

    /**
     * 根据objectid列表批量删除
     *
     * @param ids objectid列表
     */
    void removeByIds(List<String> ids) throws Exception;

    /**
     * 根据query更新update所指定的属性及值
     *
     * @param query
     * @param update
     * @throws Exception
     */
    void update(Query query, Update update) throws Exception;

    T findAndModify(Query query, Update update) throws Exception;

    /**
     * 根据query和sorts更新update所指定的属性及值并返回被更新的对象列表
     *
     * @param query
     * @param sorts
     * @return
     */
    void update(Query query, List<Sort> sorts, Update update) throws Exception;

    /**
     * 根据query和更新update所指定的属性
     * @param query
     * @param update
     * @throws Exception
     */
    void updateMulti(Query query, Update update) throws Exception;

    /**
     * 提供更新字段为空的方法
     * 条件字段只支持等于（=）条件
     * @param queryFields 条件字段
     * @param fields 设置为空的字段列表
     * @return
     * @throws Exception
     */
    T unset(Map<String, Object> queryFields, List<String> fields) throws Exception;

    Class<T> getEntryClass();

    void setEntryClass(Class<T> entryClass);

}
