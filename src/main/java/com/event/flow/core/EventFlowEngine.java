package com.event.flow.core;


import com.event.flow.core.cfg.Configuration;
import com.event.flow.core.entity.Order;

import java.util.Map;

/**
 * Created by jun
 * 17/9/3 下午2:57.
 * des:
 */
public interface EventFlowEngine {
    /**
     * 根据Configuration对象配置实现类
     * @param config 全局配置对象
     * @return EventFlowEngine 流程引擎
     */
    public EventFlowEngine configure(Configuration config);
    /**
     * 获取process服务
     * @return IProcessService 流程定义服务
     */
    public IProcessService process();

    public Order startInstanceById(String id, String operator, Map<String, Object> args);


    public ITaskService task();

}
