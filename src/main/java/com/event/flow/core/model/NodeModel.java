package com.event.flow.core.model;


import com.event.flow.core.Action;
import com.event.flow.core.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des: 节点元素（存在输入输出的变迁）
 */
public abstract class NodeModel extends BaseModel implements Action {
    private static final Logger log = LoggerFactory.getLogger(NodeModel.class);
    private static final long serialVersionUID = -4793096713044116712L;
    /**
     * 输入变迁集合
     */
    private List<TransitionModel> inputs = new ArrayList<TransitionModel>();
    /**
     * 输出变迁集合
     */
    private List<TransitionModel> outputs = new ArrayList<TransitionModel>();
    @Override
    public void execute(Execution execution) {
        /*可做拦截*/
        exec(execution);
    }
    protected abstract void exec(Execution execution);
    /**
     * 运行变迁继续执行
     * @param execution 执行对象
     */
    protected void runOutTransition(Execution execution) {
        for (TransitionModel tm : getOutputs()) {
            tm.setEnabled(true);
            tm.execute(execution);
        }
    }
    public List<TransitionModel> getOutputs() {
        return outputs;
    }

    public <T> List<T> getNextModels(Class<T> clazz) {
        List<T> models = new ArrayList<T>();
        for(TransitionModel tm : this.getOutputs()) {
            addNextModels(models, tm, clazz);
        }
        return models;
    }
    protected <T> void addNextModels(List<T> models, TransitionModel tm, Class<T> clazz) {
        if(clazz.isInstance(tm.getTarget())) {
            models.add((T)tm.getTarget());
        } else {
            for(TransitionModel tm2 : tm.getTarget().getOutputs()) {
                addNextModels(models, tm2, clazz);
            }
        }
    }
    public List<TransitionModel> getInputs() {
        return inputs;
    }

}
