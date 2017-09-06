package com.event.flow.core;

import com.event.flow.core.entity.*;

import java.io.InputStream;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des: 流程定义业务类
 */
public interface IProcessService {
    /**
     * 状态；活动状态
     */
    public static final Integer STATE_ACTIVE = 1;
    /**
     * 状态：结束状态
     */
    public static final Integer STATE_FINISH = 0;
    /**
     * 状态：终止状态
     */
    public static final Integer STATE_TERMINATION = 2;
    String deploy(InputStream input);
    /**
     * 根据主键ID获取流程定义对象
     * @param id 流程定义id
     * @return Process 流程定义对象
     */
    com.event.flow.core.entity.Process getProcessById(String id);
}
