package com.event.flow.core;


import com.event.flow.core.entity.Task;
import com.event.flow.core.model.TaskModel;

import java.util.List;
import java.util.Map;

/**
 * @author jun
 * @Date 17/9/3 .
*/
public interface ITaskService {






    List<Task> createTask(TaskModel taskModel, Execution execution);



}
