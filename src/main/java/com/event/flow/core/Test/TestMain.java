package com.event.flow.core.Test;


import com.event.flow.core.EventFlowEngine;
import com.event.flow.core.Expression;
import com.event.flow.core.IProcessService;
import com.event.flow.core.cfg.Configuration;
import com.event.flow.core.entity.Order;
import com.event.flow.core.helper.StreamHelper;
import com.event.flow.core.impl.JuelExpression;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des:
 */
public class TestMain {
    protected String processId;
    protected EventFlowEngine engine = getEngine();
    protected IProcessService processService = engine.process();
    protected EventFlowEngine getEngine() {
        return new Configuration().buildEventFlowEngine();
    }

    public static void main(String[] args) {
        TestMain testMain=new TestMain();
        EventFlowEngine engine=testMain.getEngine();
        String processId = engine.process().deploy(StreamHelper
                .getStreamFromClasspath("resumeEvent.xml"));
        Map<String, Object> arg = new HashMap<String, Object>();

        //args.put("content", 200);
        Order order = engine.startInstanceById(processId, "1", arg);

    }
}
