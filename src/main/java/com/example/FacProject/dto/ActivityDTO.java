package com.example.FacProject.dto;

import com.example.FacProject.entities.ActivityTypeEnum;
import com.example.FacProject.entities.DayOfWeekEnum;
import com.example.FacProject.entities.MemberOccupationEnum;
import com.example.FacProject.entities.MonthlyRecurrenceRule;

import java.time.LocalDate;
import java.util.List;

public class ActivityDTO {
    private String id;
    private String label;
    private ActivityTypeEnum activityType;
    private List<MemberOccupationEnum> memberOccupationConcerned;
    private MonthlyRecurrenceRule recurrenceRule;
    private LocalDate executiveDate;

    public ActivityDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<MemberOccupationEnum> getMemberOccupationConcerned() {
        return memberOccupationConcerned;
    }

    public void setMemberOccupationConcerned(List<MemberOccupationEnum> memberOccupationConcerned) {
        this.memberOccupationConcerned = memberOccupationConcerned;
    }

    public MonthlyRecurrenceRule getRecurrenceRule() {
        return recurrenceRule;
    }

    public void setRecurrenceRule(MonthlyRecurrenceRule recurrenceRule) {
        this.recurrenceRule = recurrenceRule;
    }

    public LocalDate getExecutiveDate() {
        return executiveDate;
    }

    public void setExecutiveDate(LocalDate executiveDate) {
        this.executiveDate = executiveDate;
    }
}
