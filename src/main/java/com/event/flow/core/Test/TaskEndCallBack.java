package com.event.flow.core.Test;

import com.event.flow.core.JobCallback;

/**
 * @author jun
 * @Date 17/9/5 .
 * @des:
 */
public class TaskEndCallBack implements JobCallback {
    @Override
    public void callback() {
        System.out.println("flow is over...");
    }
}
