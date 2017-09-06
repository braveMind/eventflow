package com.event.flow.core.Test;

import com.event.flow.core.Execution;
import com.event.flow.core.handlers.DecisionHandler;

/**
 * @author jun
 * @Date 17/9/5 .
 * @des:
 */
public class SelfPost implements DecisionHandler {
    @Override
    public String decide(Execution execution) {
        execution.getArgs().put("yes",false);
        System.out.println("不是候选人主动投递");
        return "";
    }
}
