package com.event.flow.core.Test;

import com.event.flow.core.Execution;
import com.event.flow.core.JobCallback;
import com.event.flow.core.entity.Task;
import com.event.flow.core.handlers.IHandler;

import java.util.List;

/**
 * @author jun
 * @Date 17/9/5 .
 * @des:
 */
public class TaskHandler implements JobCallback {


    @Override
    public void callback() {
        System.out.println("===ooddd--");
    }
}
