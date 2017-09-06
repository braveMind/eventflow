package com.event.flow.core.praser;

import com.event.flow.core.model.NodeModel;
import org.w3c.dom.Element;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des:dom 元素节点属性
 */
public interface NodeParser {
    /**
     * 变迁节点名称
     */
    public static final String NODE_TRANSITION = "transition";

    /**
     * 节点属性名称
     */
    public static final String ATTR_NAME = "name";
    public static final String ATTR_DISPLAYNAME = "displayName";

    public static final String ATTR_EXPR = "expr";
    public static final String ATTR_HANDLECLASS = "handleClass";
    public static final String ATTR_FORM = "form";
    public static final String ATTR_FIELD = "field";
    public static final String ATTR_VALUE = "value";
    public static final String ATTR_ATTR = "attr";
    public static final String ATTR_TYPE= "type";
    public static final String ATTR_ASSIGNEE = "assignee";
    public static final String ATTR_ASSIGNEE_HANDLER = "assignmentHandler";
    public static final String ATTR_PERFORMTYPE = "performType";
    public static final String ATTR_TASKTYPE = "taskType";
    public static final String ATTR_TO = "to";
    public static final String ATTR_CALLBACK = "callback";




    /**
     * 节点dom元素解析方法，由实现类完成解析
     * @param element dom元素
     */
    public void parse(Element element);

    /**
     * 解析成功后，提供返回NodeModel模型对象
     * @return 节点模型
     */
    public NodeModel getModel();
}
