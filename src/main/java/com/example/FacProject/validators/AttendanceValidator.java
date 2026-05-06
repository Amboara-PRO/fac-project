package com.example.FacProject.validators;

import com.example.FacProject.exceptions.BadRequestException;
import com.example.FacProject.repositories.ActivityRepository;
import com.example.FacProject.repositories.CollectivityRepository;
import org.springframework.stereotype.Component;

@Component
public class AttendanceValidator {
    private CollectivityRepository collectivityRepository;
    private ActivityRepository activityRepository;
    public AttendanceValidator(CollectivityRepository collectivityRepository, ActivityRepository activityRepository) {
        this.collectivityRepository = collectivityRepository;
        this.activityRepository = activityRepository;
    }
    public void validate(String collectivityId, String activityId) {
        if(!collectivityRepository.isExist(collectivityId) && !activityRepository.isExist(activityId)){
            throw new BadRequestException("Either collectivity not found or activity not found");
        }
    }
}
