package com.event.flow.core.entity;


import com.event.flow.core.model.ProcessModel;

import java.io.Serializable;
import java.sql.Blob;

/**
 * @author jun
 * @Date 17/9/3 .
 * @des: 流程定义实体类
 */
public class Process implements Serializable{

    private static final long serialVersionUID = -3247501842995540877L;
    /**
     * 主键ID
     */
    private String id;

    /**
     * 流程定义名称
     */
    private String name;
    /**
     * 流程定义显示名称
     */
    private String displayName;


    /**
     * 是否可用的开关
     */
    private Integer state;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 流程定义模型
     */
    private ProcessModel model;
    /**
     * 流程定义xml
     */
    private Blob content;
    /**
     * 流程定义字节数组
     */
    private byte[] bytes;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public ProcessModel getModel() {
        return model;
    }

    public void setModel(ProcessModel model) {
        this.model = model;
    }

    public Blob getContent() {
        return content;
    }

    public void setContent(Blob content) {
        this.content = content;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
