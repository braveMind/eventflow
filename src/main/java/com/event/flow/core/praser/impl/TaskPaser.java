package com.event.flow.core.praser.impl;

import com.event.flow.core.model.FieldModel;
import com.event.flow.core.model.NodeModel;
import com.event.flow.core.model.TaskModel;
import com.event.flow.core.praser.AbstractNodeParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des: 任务节点解析
 */
public class TaskPaser extends AbstractNodeParser {
    /**
     * 由于任务节点需要解析form、assignee属性，这里覆盖抽象类方法实现
     */
    protected void parseNode(NodeModel node, Element element) {
        TaskModel task = (TaskModel)node;
        task.setForm(element.getAttribute(ATTR_FORM));
        task.setAssignee(element.getAttribute(ATTR_ASSIGNEE));

        task.setCallback(element.getAttribute(ATTR_CALLBACK));

        task.setPerformType(element.getAttribute(ATTR_PERFORMTYPE));
        task.setTaskType(element.getAttribute(ATTR_TASKTYPE));
        task.setAssignmentHandler(element.getAttribute(ATTR_ASSIGNEE_HANDLER));
        NodeList fieldList = element.getElementsByTagName(ATTR_FIELD);
        List<FieldModel> fields = new ArrayList<FieldModel>();
        for(int i = 0; i < fieldList.getLength(); i++) {
            Element item = (Element)fieldList.item(i);
            FieldModel fieldModel = new FieldModel();
            fieldModel.setName(item.getAttribute(ATTR_NAME));
            fieldModel.setDisplayName(item.getAttribute(ATTR_DISPLAYNAME));
            fieldModel.setType(item.getAttribute(ATTR_TYPE));
            NodeList attrList = item.getElementsByTagName(ATTR_ATTR);
            for(int j = 0; j < attrList.getLength(); j++) {
                Node attr = attrList.item(j);
                fieldModel.addAttr(((Element) attr).getAttribute(ATTR_NAME),
                        ((Element) attr).getAttribute(ATTR_VALUE));
            }
            fields.add(fieldModel);
        }
        task.setFields(fields);
    }

    /**
     * 产生TaskModel模型对象
     */
    protected NodeModel newModel() {
        return new TaskModel();
    }


}
