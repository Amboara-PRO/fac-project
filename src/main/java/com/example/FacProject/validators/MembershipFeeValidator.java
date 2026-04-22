package com.example.FacProject.validators;

import com.example.FacProject.dto.CreateMembershipFeeDTO;
import com.example.FacProject.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MembershipFeeValidator {
    public void validate(CreateMembershipFeeDTO createmembershipFeeDTO){
        if(createmembershipFeeDTO.getFrequency() == null || createmembershipFeeDTO.getAmount().compareTo(BigDecimal.ZERO) < 0){
            throw new BadRequestException("Either unrecognized frequency or amount under 0.");
        }
    }
}
