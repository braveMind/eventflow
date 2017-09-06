package com.event.flow.core.impl;

import com.event.flow.core.*;
import com.event.flow.core.cache.CacheManager;
import com.event.flow.core.cache.CacheManagerAware;
import com.event.flow.core.cache.memory.MemoryCacheManager;
import com.event.flow.core.cfg.Configuration;
import com.event.flow.core.entity.Order;
import com.event.flow.core.generator.CustomNoGenerator;
import com.event.flow.core.helper.AssertHelper;
import com.event.flow.core.helper.DateHelper;
import com.event.flow.core.helper.JsonHelper;
import com.event.flow.core.helper.StringHelper;
import com.event.flow.core.model.ProcessModel;
import com.event.flow.core.model.StartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.event.flow.core.entity.Process;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des:
 */
public class EventFlowEngineImpl implements EventFlowEngine{
    private static final Logger log = LoggerFactory.getLogger(EventFlowEngine.class);
    /**
     * Snaker配置对象
     */
    protected Configuration configuration;
    /**
     * 流程定义业务类
     */
    protected IProcessService processService;

    /**
     * 任务业务类
     */
    protected ITaskService taskService;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public IProcessService getProcessService() {
        return processService;
    }

    public void setProcessService(IProcessService processService) {
        this.processService = processService;
    }

    public ITaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public EventFlowEngine configure(Configuration config) {
        this.configuration = config;
        processService = ServiceContext.find(IProcessService.class);

        taskService = ServiceContext.find(ITaskService.class);

        if (!this.configuration.isCMB()) {

            CacheManager cacheManager = ServiceContext.find(CacheManager.class);
            if (cacheManager == null) {
                //默认使用内存缓存管理器
                cacheManager = new MemoryCacheManager();
            }
            List<CacheManagerAware> cacheServices = ServiceContext.findList(CacheManagerAware.class);
            for (CacheManagerAware cacheService : cacheServices) {
                cacheService.setCacheManager(cacheManager);
            }
            return this;
        }
        return null;
    }

    @Override
    public IProcessService process() {
        return processService;
    }

    @Override
    public Order startInstanceById(String id, String operator, Map<String, Object> args) {
        if(args == null) args = new HashMap<String, Object>();
        Process process = process().getProcessById(id);
        return startProcess(process, operator, args);
    }
    private Order startProcess(Process process, String operator, Map<String, Object> args) {
        Execution execution = execute(process, operator, args, null, null);
        if(process.getModel() != null) {
            StartModel start = process.getModel().getStart();
            AssertHelper.notNull(start, "流程定义[name=" + process.getName() + ", version=" + process.getId() + "]没有开始节点");
            start.execute(execution);
        }

        return execution.getOrder();
    }

    @Override
    public ITaskService task() {
        return taskService;
    }

    /**
     * 创建流程实例，并返回执行对象
     * @param process 流程定义
     * @param operator 操作人
     * @param args 参数列表
     * @param parentId 父流程实例id
     * @param parentNodeName 启动子流程的父流程节点名称
     * @return Execution
     */
    private Execution execute(Process process, String operator, Map<String, Object> args,
                              String parentId, String parentNodeName) {
        Order order =createOrder(process, operator, args, parentId, parentNodeName);
        if(log.isDebugEnabled()) {
            log.debug("创建流程实例对象:" + order);
        }
        Execution current = new Execution(this, process, order, args);
        current.setOperator(operator);

        return current;
    }

    private Order createOrder(Process process, String operator, Map<String, Object> args,
                             String parentId, String parentNodeName) {
        Order order = new Order();
        order.setId(StringHelper.getPrimaryKey());
        order.setParentId(parentId);
        order.setParentNodeName(parentNodeName);
        order.setCreateTime(DateHelper.getTime());
        order.setLastUpdateTime(order.getCreateTime());
        order.setCreator(operator);
        order.setLastUpdator(order.getCreator());
        order.setProcessId(process.getId());
        ProcessModel model = process.getModel();
        model.setGenerator(new CustomNoGenerator());
        if(model != null && args != null) {
            if(StringHelper.isNotEmpty(model.getExpireTime())) {
                String expireTime = DateHelper.parseTime(args.get(model.getExpireTime()));
                order.setExpireTime(expireTime);
            }
            String orderNo = (String)args.get("eventFlow.ID");
            if(StringHelper.isNotEmpty(orderNo)) {
                order.setOrderNo(orderNo);
            } else {
                order.setOrderNo(model.getGenerator().generate(model));
            }
        }

        order.setVariable(JsonHelper.toJson(args));
        return order;
    }
}
