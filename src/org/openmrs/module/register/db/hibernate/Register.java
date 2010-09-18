package org.openmrs.module.register.db.hibernate;

import org.openmrs.BaseOpenmrsData;

public class Register extends BaseOpenmrsData{
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
