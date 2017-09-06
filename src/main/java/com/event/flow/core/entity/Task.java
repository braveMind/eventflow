package com.event.flow.core.entity;


import com.event.flow.core.model.TaskModel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des:
 */
public class Task implements Serializable {
    private static final long serialVersionUID = -8198130527541363298L;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 版本
     */
    private Integer version = 0;
    /**
     * 流程实例ID
     */
    private String orderId;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务显示名称
     */
    private String displayName;
    /**
     * 参与方式（0：普通任务；1：参与者会签任务）
     */
    private Integer performType;
    /**
     * 任务类型（0：主办任务；1：协办任务）
     */
    private Integer taskType;
    /**
     * 任务处理者ID
     */
    private String operator;
    /**
     * 任务创建时间
     */
    private String createTime;
    /**
     * 任务完成时间
     */
    private String finishTime;
    /**
     * 期望任务完成时间
     */
    private String expireTime;
    /**
     * 期望的完成时间date类型
     */
    private Date expireDate;
    /**
     * 提醒时间date类型
     */
    private Date remindDate;
    /**
     * 任务关联的表单url
     */
    private String actionUrl;
    /**
     * 任务参与者列表
     */
    private String[] actorIds;
    /**
     * 父任务Id
     */
    private String parentTaskId;
    /**
     * 任务附属变量
     */
    private String variable;
    /**
     * 保持模型对象
     */
    private TaskModel model;

    public Task() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getPerformType() {
        return performType;
    }

    public void setPerformType(Integer performType) {
        this.performType = performType;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String[] getActorIds() {
        return actorIds;
    }

    public void setActorIds(String[] actorIds) {
        this.actorIds = actorIds;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public TaskModel getModel() {
        return model;
    }

    public void setModel(TaskModel model) {
        this.model = model;
    }
}
