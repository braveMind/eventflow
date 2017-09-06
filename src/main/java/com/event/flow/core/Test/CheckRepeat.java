package com.event.flow.core.Test;

import com.event.flow.core.Execution;
import com.event.flow.core.handlers.DecisionHandler;

/**
 * @author jun
 * @Date 17/9/5 .
 * @des:
 */
public class CheckRepeat implements DecisionHandler {
    @Override
    public String decide(Execution execution) {
        execution.getArgs().put("repeat", true);
        System.out.println("简历重复");
        return "";
    }

}
