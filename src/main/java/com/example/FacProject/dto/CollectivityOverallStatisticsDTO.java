package com.example.FacProject.dto;

public class CollectivityOverallStatisticsDTO {

    private CreateCollectivityInformationsDTO collectivityInformation;

    private Integer newMembersNumber;
    private Double overallMemberCurrentDuePercentage;

    public CollectivityOverallStatisticsDTO() {}

    public CreateCollectivityInformationsDTO getCollectivityInformation() {
        return collectivityInformation;
    }

    public void setCollectivityInformation(CreateCollectivityInformationsDTO collectivityInformation) {
        this.collectivityInformation = collectivityInformation;
    }

    public Integer getNewMembersNumber() {
        return newMembersNumber;
    }

    public void setNewMembersNumber(Integer newMembersNumber) {
        this.newMembersNumber = newMembersNumber;
    }

    public Double getOverallMemberCurrentDuePercentage() {
        return overallMemberCurrentDuePercentage;
    }

    public void setOverallMemberCurrentDuePercentage(Double overallMemberCurrentDuePercentage) {
        this.overallMemberCurrentDuePercentage = overallMemberCurrentDuePercentage;
    }
}