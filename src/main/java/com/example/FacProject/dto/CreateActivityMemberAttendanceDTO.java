package com.example.FacProject.dto;

import com.example.FacProject.entities.AttendanceStatusEnum;

public class CreateActivityMemberAttendanceDTO {
    private String memberIdentifier;
    private AttendanceStatusEnum status;

    public CreateActivityMemberAttendanceDTO() {
    }

    public String getMemberIdentifier() {
        return memberIdentifier;
    }

    public void setMemberIdentifier(String memberIdentifier) {
        this.memberIdentifier = memberIdentifier;
    }

    public AttendanceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatusEnum status) {
        this.status = status;
    }
}
