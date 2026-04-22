package com.example.FacProject.entities;

import java.util.List;

public class CollectivityEntity extends OrganizationEntity {
    private String location ;
    private Boolean federationApproval;
    private CollectivityStructureEntity structure;
    private List<MemberEntity> members;
    private String name;
    private Integer number;

    public CollectivityEntity(String id, String location, Boolean federationApproval, CollectivityStructureEntity structure, List<MemberEntity> members, String name, Integer number) {
        super(id);
        this.location = location;
        this.federationApproval = federationApproval;
        this.structure = structure;
        this.members = members;
        this.name = name;
        this.number = number;
    }

    public CollectivityEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public List<MemberEntity> getMembers() {
        return members;
    }

    public void setMembers(List<MemberEntity> members) {
        this.members = members;
    }
}
