package com.example.FacProject.dto;

public class CreateCollectivityNameAndNumberDTO {
    private String collectivityId;
    private String name;
    private int number;

    public CreateCollectivityNameAndNumberDTO(String collectivityId, String name, int number) {
        this.collectivityId = collectivityId;
        this.name = name;
        this.number = number;
    }

    public CreateCollectivityNameAndNumberDTO() {
    }

    public String getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
