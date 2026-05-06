package com.example.FacProject.entities;

public class MonthlyRecurrenceRule {
    private Integer weekOrdinal;
    private DayOfWeekEnum dayOfWeek;

    public MonthlyRecurrenceRule() {
    }

    public Integer getWeekOrdinal() {
        return weekOrdinal;
    }

    public void setWeekOrdinal(Integer weekOrdinal) {
        this.weekOrdinal = weekOrdinal;
    }

    public DayOfWeekEnum getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeekEnum dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
