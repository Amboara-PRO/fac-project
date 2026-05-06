package com.example.FacProject.services;

import com.example.FacProject.dto.ActivityDTO;
import com.example.FacProject.dto.CreateCollectivityActivityDTO;
import com.example.FacProject.exceptions.NotFoundException;
import com.example.FacProject.repositories.ActivityRepository;
import com.example.FacProject.validators.CollectivityValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    private final ActivityRepository repository;
    private CollectivityValidator collectivityValidator;
    public ActivityService(ActivityRepository repository, CollectivityValidator collectivityValidator) {
        this.repository = repository;
        this.collectivityValidator = collectivityValidator;
    }
    public List<ActivityDTO> getAllActivities(String id){
        collectivityValidator.validateExists(id);
        return repository.getAllActivities(id);
    }
    public List<ActivityDTO> createActivity(List<CreateCollectivityActivityDTO> dtos, String id){
        collectivityValidator.validateExists(id);
        List<String> activityIds = repository.createCollectivityActivity(dtos, id);
        List<ActivityDTO> list = new ArrayList<>();
        for (String activityId : activityIds) {
            ActivityDTO dto = repository.findById(activityId)
                    .orElseThrow(() -> new NotFoundException("Activity not found"));

            list.add(dto);
        }
        return list;
    }
}
