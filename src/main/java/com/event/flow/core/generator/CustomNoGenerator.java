package com.event.flow.core.generator;


import com.event.flow.core.INoGenerator;
import com.event.flow.core.model.ProcessModel;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des:ID生成器
 */
public class CustomNoGenerator implements INoGenerator {
    public String generate(ProcessModel model) {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

}
