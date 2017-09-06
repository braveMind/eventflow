package com.event.flow.core.model;


import com.event.flow.core.Execution;
import com.event.flow.core.handlers.IHandler;

import java.io.Serializable;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des:
 */
public class BaseModel implements Serializable {
    private static final long serialVersionUID = -8573769136072785802L;
    /**
     * 元素名称
     */
    private String name;
    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 将执行对象execution交给具体的处理器处理
     * @param handler
     * @param execution
     */
    protected void fire(IHandler handler, Execution execution) {
        handler.handle(execution);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
