package com.example.FacProject.dto;

import com.example.FacProject.entities.FrequencyEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateMembershipFeeDTO {

    public LocalDate eligibleFrom;
    public FrequencyEnum frequency;
    public BigDecimal amount;
    public String label;
}