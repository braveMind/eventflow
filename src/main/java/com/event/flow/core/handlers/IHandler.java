package com.event.flow.core.handlers;


import com.event.flow.core.Execution;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des: 流程引擎中需要执行的接口
 */
public interface IHandler {
    /**
     * 子类需要实现的方法，来处理具体的操作
     * @param execution 执行对象
     */
    public void handle(Execution execution);
}
