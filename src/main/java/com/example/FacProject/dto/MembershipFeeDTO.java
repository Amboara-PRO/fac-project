package com.example.FacProject.dto;

import com.example.FacProject.entities.ActivityStatusEnum;
import com.example.FacProject.entities.FrequencyEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MembershipFeeDTO {

    public String id;
    public LocalDate eligibleFrom;
    public FrequencyEnum frequency;
    public BigDecimal amount;
    public String label;
    public ActivityStatusEnum status;
}