package com.example.FacProject.validators;

import com.example.FacProject.dto.CreateCollectivityActivityDTO;
import com.example.FacProject.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityValidator {
    public void validate(List<CreateCollectivityActivityDTO> dtos){
        for (CreateCollectivityActivityDTO dto : dtos) {
            if(dto.getExecutiveDate() != null && dto.getRecurrenceRule() != null
                    || dto.getRecurrenceRule() == null && dto.getExecutiveDate() == null) {
                throw new BadRequestException("Both recurrence rule and executive date provided, or provided data malformed inside payload.");
            }
        }
    }
}
