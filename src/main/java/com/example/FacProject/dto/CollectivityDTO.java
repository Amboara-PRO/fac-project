package com.example.FacProject.dto;

import java.util.List;

public class CollectivityDTO{
    private String id;
    private String location;
    private CollectivityStructureDTO structure;
    private List<MemberDTO> members;

    public CollectivityDTO() {
    }

    public CollectivityDTO(String id, String location, CollectivityStructureDTO structure, List<MemberDTO> members) {
        this.id = id;
        this.location = location;
        this.structure = structure;
        this.members = members;
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
}

