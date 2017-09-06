package com.event.flow.core.handlers.impl;


import com.event.flow.core.Execution;
import com.event.flow.core.entity.Task;
import com.event.flow.core.handlers.IHandler;
import com.event.flow.core.model.TaskModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des: 任务创建操作的处理器
 */
public class CreateTaskHandler implements IHandler {
    private static final Logger log = LoggerFactory.getLogger(CreateTaskHandler.class);
    /**
     * 任务模型
     */
    private TaskModel model;

    /**
     * 调用者需要提供任务模型
     * @param model 模型
     */
    public CreateTaskHandler(TaskModel model) {
        this.model = model;
    }
    @Override
    public void handle(Execution execution) {
        List<Task> tasks = execution.getEngine().task().createTask(model, execution);

    }
}
