package com.example.FacProject.dto;

import java.util.List;

public class GetCollectivityDTO {
    private String id;
    private String location;
    private CollectivityStructureDTO structure;
    private List<MemberDTO> members;
    private CreateCollectivityInformationsDTO information;

    public GetCollectivityDTO(String id, String location, CollectivityStructureDTO structure, List<MemberDTO> members, CreateCollectivityInformationsDTO information) {
        this.id = id;
        this.location = location;
        this.structure = structure;
        this.members = members;
        this.information = information;
    }

    public GetCollectivityDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CollectivityStructureDTO getStructure() {
        return structure;
    }

    public void setStructure(CollectivityStructureDTO structure) {
        this.structure = structure;
    }

    public List<MemberDTO> getMembers() {
        return members;
    }

    public void setMembers(List<MemberDTO> members) {
        this.members = members;
    }

    public CreateCollectivityInformationsDTO getInformation() {
        return information;
    }

    public void setInformation(CreateCollectivityInformationsDTO information) {
        this.information = information;
    }
}
