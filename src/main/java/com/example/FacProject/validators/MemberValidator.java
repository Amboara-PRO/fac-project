package com.example.FacProject.validators;

import com.example.FacProject.entities.MemberEntity;
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
    public void validate(MemberEntity m) {

        if (!m.getRegistrationFeePaid() || !m.getMembershipDuesPaid()) {
            throw new IllegalArgumentException("Payment not complete");
        }

        if (m.getReferees() == null || m.getReferees().isEmpty()) {
            throw new IllegalArgumentException("Referees are required");
        }

        if (m.getFirstName() == null || m.getLastName() == null) {
            throw new IllegalArgumentException("Name is required");
        }
    }
}
