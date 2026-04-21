package com.example.FacProject.entity;

public class OrganizationEntity {
    protected String id;

    public OrganizationEntity(String id) {
        this.id = id;
    }

    public OrganizationEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
