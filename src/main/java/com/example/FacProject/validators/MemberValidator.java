package com.example.FacProject.validators;

import com.example.FacProject.dto.MemberDTO;
import com.example.FacProject.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MemberValidator {
    public void validateSeniorNumber(Integer value){
        if(value < 5){
            throw new BadRequestException(
                    "At least 5 members must have 6 months seniority"
            );
        }
    }
    public void validateMemberCount(Integer value){
        if(value<10){
            throw new BadRequestException("Collectivity needs at least 10 members");
        }
    }
}
