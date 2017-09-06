package com.event.flow.core.impl;



import com.event.flow.core.Context;
import com.event.flow.core.helper.ClassHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jun
 * 17/9/3 下午2:33.
 * des: 引擎上下文信息
 */
public class SimpleContext implements Context {
    /**
     * 上下文注册的配置对象
     */
    private Map<String, Object> contextMap = new HashMap<String, Object>();

    /**
     * put服务
     * @param name 服务名称
     * @param object 服务实例
     */
    @Override
    public void put(String name, Object object) {
        contextMap.put(name, object);
    }

    /**
     *把类转成对象
     * @param name 服务名称
     * @param clazz 类型
     */
    @Override
    public void put(String name, Class<?> clazz) {
        contextMap.put(name, ClassHelper.instantiate(clazz));
    }

    @Override
    public boolean exist(String name) {
         return contextMap.get(name) != null;
    }

    /**
     * 根据Class类型查找
     * @param clazz 类型
     * @param <T>
     * @return
     */
    @Override
    public <T> T find(Class<T> clazz) {
        for (Map.Entry<String, Object> entry : contextMap.entrySet()) {
            if (clazz.isInstance(entry.getValue())) {
                return clazz.cast(entry.getValue());
            }
        }
        return null;
    }

    /**
     * 获取一类型列表
     * @param clazz 类型
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> findList(Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        for (Map.Entry<String, Object> entry : contextMap.entrySet()) {
            if (clazz.isInstance(entry.getValue())) {
                list.add(clazz.cast(entry.getValue()));
            }
        }
        return list;
    }

    /**
     * 对外部提供的查找对象方法，根据名称、class类型查找
     * @param name
     * @param clazz
     * @return
     */
    @Override
    public <T> T findByName(String name, Class<T> clazz) {
        if(contextMap.containsKey(name) && clazz.isInstance(contextMap.get(name))){
            return clazz.cast(contextMap.get(name));
        }
        return null;
    }

}
