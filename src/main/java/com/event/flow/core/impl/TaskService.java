package com.event.flow.core.impl;



import com.event.flow.core.Execution;
import com.event.flow.core.ITaskService;
import com.event.flow.core.entity.Task;
import com.event.flow.core.helper.DateHelper;
import com.event.flow.core.helper.JsonHelper;
import com.event.flow.core.helper.StringHelper;
import com.event.flow.core.model.TaskModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des:
 */
public class TaskService  implements ITaskService {




    @Override
    public List<Task> createTask(TaskModel taskModel, Execution execution) {
        List<Task> tasks = new ArrayList<Task>();

        Map<String, Object> args = execution.getArgs();
        if(args == null) args = new HashMap<String, Object>();

        String form = (String)args.get(taskModel.getForm());
        String actionUrl = StringHelper.isEmpty(form) ? taskModel.getForm() : form;

        Task task = createTaskBase(taskModel, execution);
        task.setActionUrl(actionUrl);
        task.setVariable(JsonHelper.toJson(args));

        if(taskModel.isPerformAny()) {
            //任务执行方式为参与者中任何一个执行即可驱动流程继续流转，该方法只产生一个task
            tasks.add(task);
        } else if(taskModel.isPerformAll()){
            //任务执行方式为参与者中每个都要执行完才可驱动流程继续流转，该方法根据参与者个数产生对应的task数量

        }
        return tasks;
    }



    /**
     * 根据模型、执行对象、任务类型构建基本的task对象
     * @param model 模型
     * @param execution 执行对象
     * @return Task任务对象
     */
    private Task createTaskBase(TaskModel model, Execution execution) {
        Task task = new Task();
        task.setTaskName(model.getName());
        task.setDisplayName(model.getDisplayName());
        task.setCreateTime(DateHelper.getTime());
        task.setModel(model);
        return task;
    }
}
