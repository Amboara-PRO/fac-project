package com.example.FacProject.dto;

import com.example.FacProject.entities.AttendanceStatusEnum;

public class ActivityMemberAttendanceDTO {
    private String id;
    private MemberDescriptionDTO memberDescription;
    private AttendanceStatusEnum attendanceStatus;

    public ActivityMemberAttendanceDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MemberDescriptionDTO getMemberDescription() {
        return memberDescription;
    }

    public void setMemberDescription(MemberDescriptionDTO memberDescription) {
        this.memberDescription = memberDescription;
    }

    public AttendanceStatusEnum getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(AttendanceStatusEnum attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
