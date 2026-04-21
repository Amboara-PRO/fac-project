package com.example.FacProject.entity;

public class CollectivityEntity extends OrganizationEntity {
    private String location ;
    private Boolean federationApproval;
    private CollectivityStructureEntity structure;

    public CollectivityEntity(String id, String location, Boolean federationApproval, CollectivityStructureEntity structure) {
        super(id);
        this.location = location;
        this.federationApproval = federationApproval;
        this.structure = structure;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getFederationApproval() {
        return federationApproval;
    }

    public void setFederationApproval(Boolean federationApproval) {
        this.federationApproval = federationApproval;
    }

    public CollectivityStructureEntity getStructure() {
        return structure;
    }

    public void setStructure(CollectivityStructureEntity structure) {
        this.structure = structure;
    }
}
