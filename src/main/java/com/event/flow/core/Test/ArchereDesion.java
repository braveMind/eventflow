package com.event.flow.core.Test;

import com.event.flow.core.Execution;
import com.event.flow.core.handlers.DecisionHandler;

/**
 * @author jun
 * @Date 17/9/5 .
 * @des:
 */
public class ArchereDesion implements DecisionHandler {
    @Override
    public String decide(Execution execution) {
        System.out.println("简历归档");
        execution.getArgs().put("operationY",true);
        return "";
    }
}
