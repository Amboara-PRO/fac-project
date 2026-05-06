package com.example.FacProject.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ActivityAttendanceEntity {
    private String id;
    private String activityId;
    private String memberId;

    private AttendanceStatusEnum attendanceStatus;

    private LocalDateTime markedAt;

    public ActivityAttendanceEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public AttendanceStatusEnum getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(AttendanceStatusEnum attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public LocalDateTime getMarkedAt() {
        return markedAt;
    }

    public void setMarkedAt(LocalDateTime markedAt) {
        this.markedAt = markedAt;
    }
}
