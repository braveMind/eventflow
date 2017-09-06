package com.event.flow.core.impl;

import com.event.flow.core.EventFlowException;
import com.event.flow.core.IProcessService;
import com.event.flow.core.cache.Cache;
import com.event.flow.core.cache.CacheManager;
import com.event.flow.core.cache.CacheManagerAware;
import com.event.flow.core.entity.Process;
import com.event.flow.core.helper.AssertHelper;
import com.event.flow.core.helper.DateHelper;
import com.event.flow.core.helper.StreamHelper;
import com.event.flow.core.helper.StringHelper;
import com.event.flow.core.model.ProcessModel;
import com.event.flow.core.praser.impl.ModelParser;

import java.io.InputStream;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des:
 */
public class ProcessService implements IProcessService,CacheManagerAware {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProcessService.class);


    private static final String DEFAULT_SEPARATOR = ".";
    /**
     * 流程定义对象cache名称
     */
    private static final String CACHE_ENTITY = "evetFlowEngine.process.entity";
    /**
     * 流程id、name的cache名称
     */

    /**
     * 实体cache(key=name,value=entity对象)
     */
    private Cache<String, Process> entityCache;
    /**
     * 名称cache(key=id,value=name对象)
     */
    private Cache<String, String> nameCache;
    private static final String CACHE_NAME = "snaker.process.name";
    /**
     * 根据流程定义xml的输入流解析为字节数组，保存至数据库中，并且put到缓存中
     * @param input 定义输入流
     * @param creator 创建人
     *
     */
    /**
     * cache manager
     */
    private CacheManager cacheManager;
    public String deploy(InputStream input, String creator) {
        AssertHelper.notNull(input);
        try {
            byte[] bytes = StreamHelper.readBytes(input);
            ProcessModel model = ModelParser.parse(bytes);
            Process entity = new Process();
            entity.setId(StringHelper.getPrimaryKey());

            entity.setState(STATE_ACTIVE);
            entity.setModel(model);
            entity.setBytes(bytes);
            entity.setCreateTime(DateHelper.getTime());
            entity.setCreator(creator);
            //saveProcess(entity);
            cache(entity);
            return entity.getId();
        } catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new EventFlowException(e.getMessage(), e.getCause());
        }
    }
    /**
     * 缓存实体
     * @param entity 流程定义对象
     */
    private void cache(Process entity) {
        Cache<String, String> nameCache = ensureAvailableNameCache();
        Cache<String, Process> entityCache = ensureAvailableEntityCache();

        String processName = entity.getName() + DEFAULT_SEPARATOR + "Engine";
        if(nameCache != null && entityCache != null) {
            if(log.isDebugEnabled()) {
                log.debug("cache process id is[{}],name is[{}]", entity.getId(), processName);
            }
            entityCache.put(processName, entity);
            nameCache.put(entity.getId(), processName);
        } else {
            if(log.isDebugEnabled()) {
                log.debug("no cache implementation class");
            }
        }
    }
    private Cache<String, Process> ensureAvailableEntityCache() {
        Cache<String, Process> entityCache = ensureEntityCache();
        if(entityCache == null && this.cacheManager != null) {
            entityCache = this.cacheManager.getCache(CACHE_ENTITY);
        }
        return entityCache;
    }
    public Cache<String, Process> ensureEntityCache() {
        return entityCache;
    }

    @Override
    public String deploy(InputStream input) {
        return this.deploy(input,null);
    }

    @Override
    public Process getProcessById(String id) {
        AssertHelper.notEmpty(id);
        Process entity = null;
        String processName;
        Cache<String, String> nameCache = ensureAvailableNameCache();
        Cache<String, Process> entityCache = ensureAvailableEntityCache();
        if(nameCache != null && entityCache != null) {
            processName = nameCache.get(id);
            if(StringHelper.isNotEmpty(processName)) {
                entity = entityCache.get(processName);
            }
        }
        if(entity != null) {
            if(log.isDebugEnabled()) {
                log.debug("obtain process[id={}] from cache.", id);
            }
            return entity;
        }
/*
        数据库
        //entity = access().getProcess(id);
*/
        if(entity != null) {
            if(log.isDebugEnabled()) {
                log.debug("obtain process[id={}] from database.", id);
            }
            cache(entity);
        }
        return entity;
    }

    private Cache<String, String> ensureAvailableNameCache() {
        Cache<String, String> nameCache = ensureNameCache();
        if(nameCache == null && this.cacheManager != null) {
            nameCache = this.cacheManager.getCache(CACHE_NAME);
        }
        return nameCache;
    }
    public Cache<String, String> ensureNameCache() {
        return nameCache;
    }

    @Override
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
