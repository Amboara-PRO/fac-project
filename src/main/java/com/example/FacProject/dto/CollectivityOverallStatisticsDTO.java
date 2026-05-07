package com.example.FacProject.dto;

public class CollectivityOverallStatisticsDTO {

    private CreateCollectivityInformationsDTO collectivityInformation;

    private Integer newMembersNumber;
    private Double overallMemberCurrentDuePercentage;

    private Double overallMemberAssiduityPercentage;

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
    public Double getOverallMemberAssiduityPercentage() {
        return overallMemberAssiduityPercentage;
    }

    public void setOverallMemberAssiduityPercentage(
            Double overallMemberAssiduityPercentage
    ) {
        this.overallMemberAssiduityPercentage =
                overallMemberAssiduityPercentage;
    }
}