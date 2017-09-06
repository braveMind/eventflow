package com.event.flow.core;

import com.event.flow.core.helper.AssertHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by jun
 * 17/9/3 下午2:54.
 * des:
 */
public abstract class ServiceContext {
    private static final Logger log = LoggerFactory.getLogger(ServiceContext.class);

    /**
     * 流程引擎的引用
     */
    private static EventFlowEngine engine;
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        ServiceContext.context = context;
    }
    public static EventFlowEngine getEngine() {
        AssertHelper.notNull(context, "未注册服务上下文");
        if(engine == null) {
            engine = context.find(EventFlowEngine.class);
        }
        return engine;
    }

    /**
     * 根据服务名称判断是否存在服务实例
     * @param name 服务名称
     * @return
     */
    public static boolean exist(String name) {
        AssertHelper.notNull(context, "未注册服务上下文");
        return context.exist(name);
    }

    /**
     * 对外部提供的查找对象方法，根据class类型查找
     * @param clazz 服务类型
     * @return
     */
    public static <T> T find(Class<T> clazz) {
        AssertHelper.notNull(context, "未注册服务上下文");
        return context.find(clazz);
    }

    /**
     * 对外部提供的查找对象实例列表方法，根据class类型查找集合
     * @param clazz 服务类型
     * @return
     */
    public static <T> List<T> findList(Class<T> clazz) {
        AssertHelper.notNull(context, "未注册服务上下文");
        return context.findList(clazz);
    }

    /**
     * 对外部提供的查找对象方法，根据名称、class类型查找
     * @param name 服务名称
     * @param clazz 服务类型
     * @return
     */
    public static <T> T findByName(String name, Class<T> clazz) {
        AssertHelper.notNull(context, "未注册服务上下文");
        return context.findByName(name, clazz);
    }
    /**
     * 向上下文添加服务实例
     * @param name 服务名称
     * @param clazz 服务类型
     */
    public static void put(String name, Class<?> clazz) {
        AssertHelper.notNull(context, "未注册服务上下文");
        if(log.isInfoEnabled()) {
            log.info("put new instance[name=" + name + "][clazz=" + clazz.getName() + "]");
        }
        context.put(name, clazz);
    }


}
