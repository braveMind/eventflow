package com.event.flow.core;

import java.util.List;

/**
 * Created by jun
 * 17/9/3 下午2:29.
 * des:存放上下文信息
 */
public interface Context {
    /**
     * 根据服务名称、实例向服务工厂注册
     * @param name 服务名称
     * @param object 服务实例
     */
    void put(String name, Object object);

    /**
     * 根据服务名称、类型向服务工厂注册
     * @param name 服务名称
     * @param clazz 类型
     */
    void put(String name, Class<?> clazz);

    /**
     * 判断是否存在给定的服务名称
     * @param name 服务名称
     * @return
     */
    boolean exist(String name);

    /**
     * 根据给定的类型查找服务实例
     * @param clazz 类型
     * @return
     */
    <T> T find(Class<T> clazz);

    /**
     * 根据给定的类型查找所有此类型的服务实例
     * @param clazz 类型
     * @return
     */
    <T> List<T> findList(Class<T> clazz);

    /**
     * 根据给定的服务名称、类型查找服务实例
     * @param name 服务名称
     * @param clazz 类型
     * @return
     */
    <T> T findByName(String name, Class<T> clazz);
}
