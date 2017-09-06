package com.event.flow.core.Test;

import com.event.flow.core.Execution;
import com.event.flow.core.handlers.DecisionHandler;

/**
 * @author jun
 * @Date 17/9/5 .
 * @des:
 */
public class SourceDesion implements DecisionHandler {
    @Override
    public String decide(Execution execution) {
        execution.getArgs().put("cover", false);
        System.out.println("不是覆盖渠道");
        return "";
    }
}
