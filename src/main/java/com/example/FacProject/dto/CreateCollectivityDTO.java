package com.example.FacProject.dto;

import java.util.List;

public class CreateCollectivityDTO {
    private String location;
    private List<String> members;
    private Boolean federationApproval;
    private CollectivityStructureDTO structure;

    public CreateCollectivityDTO() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Boolean getFederationApproval() {
        return federationApproval;
    }

    public void setFederationApproval(Boolean federationApproval) {
        this.federationApproval = federationApproval;
    }

    public CollectivityStructureDTO getStructure() {
        return structure;
    }

    public void setStructure(CollectivityStructureDTO structure) {
        this.structure = structure;
    }
}