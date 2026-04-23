package com.example.FacProject.dto;

public class CreateCollectivityInformationsDTO {
    private String name;
    private int number;

    public CreateCollectivityInformationsDTO(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public CreateCollectivityInformationsDTO() {
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
