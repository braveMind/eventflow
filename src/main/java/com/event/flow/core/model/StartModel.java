package com.event.flow.core.model;


import com.event.flow.core.Execution;

import java.util.Collections;
import java.util.List;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des:
 */
public class StartModel extends NodeModel{
    /**
     *
     */
    private static final long serialVersionUID = -4550530562581330477L;

    /**
     * 开始节点无输入变迁
     */
    public List<TransitionModel> getInputs() {
        return Collections.emptyList();
    }

    protected void exec(Execution execution) {
        runOutTransition(execution);
    }
}
