package com.capgemini.wsb.dto;

import java.io.Serializable;

public class MedicalTreatmentTO implements Serializable {
    private Long id;
    private String description;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
