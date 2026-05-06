package com.example.FacProject.entities;


import java.time.LocalDate;
import java.util.List;

public class ActivityEntity {

    private String id;
    private String collectivityId;

    private String label;
    private ActivityTypeEnum activityType;

    private boolean mandatory;

    private List<MemberOccupationEnum> memberOccupations;

    private LocalDate executiveDate;

    private Integer recurrenceWeekOrdinal;
    private DayOfWeekEnum recurrenceDayOfWeek;

    public ActivityEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ActivityTypeEnum getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityTypeEnum activityType) {
        this.activityType = activityType;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public List<MemberOccupationEnum> getMemberOccupations() {
        return memberOccupations;
    }

    public void setMemberOccupations(List<MemberOccupationEnum> memberOccupations) {
        this.memberOccupations = memberOccupations;
    }

    public LocalDate getExecutiveDate() {
        return executiveDate;
    }

    public void setExecutiveDate(LocalDate executiveDate) {
        this.executiveDate = executiveDate;
    }

    public Integer getRecurrenceWeekOrdinal() {
        return recurrenceWeekOrdinal;
    }

    public void setRecurrenceWeekOrdinal(Integer recurrenceWeekOrdinal) {
        this.recurrenceWeekOrdinal = recurrenceWeekOrdinal;
    }

    public DayOfWeekEnum getRecurrenceDayOfWeek() {
        return recurrenceDayOfWeek;
    }

    public void setRecurrenceDayOfWeek(DayOfWeekEnum recurrenceDayOfWeek) {
        this.recurrenceDayOfWeek = recurrenceDayOfWeek;
    }
}
