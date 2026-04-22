package com.example.FacProject.validators;

import com.example.FacProject.dto.CreateCollectivityNameAndNumberDTO;
import com.example.FacProject.exceptions.BadRequestException;
import com.example.FacProject.repositories.CollectivityRepository;
import org.springframework.stereotype.Component;

@Component
public class CollectivityValidator {
    private CollectivityRepository collectivityRepo;
    public CollectivityValidator(CollectivityRepository collectivityRepo){
        this.collectivityRepo = collectivityRepo;
    }
    public void validator(CreateCollectivityNameAndNumberDTO dto){
        if(!collectivityRepo.isExist(dto.getCollectivityId())){
            throw new BadRequestException("Collectivity not found");
        }
        if(collectivityRepo.isExistByNameAndNumber(dto.getName(),dto.getNumber())){
            throw new BadRequestException("Name or Id already exists");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new BadRequestException("Name and number must be provided");
        }
    }
}
