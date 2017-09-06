package com.event.flow.core.model;


/**
 * @author jun
 * @Date 17/9/3 .
 * @des: 每个元素都有from
 */
public abstract class WorkModel extends NodeModel {
    private static final long serialVersionUID = 5886828476606066387L;
    /**
     * form
     */
    private String form;

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }


}
