package com.example.FacProject.services;

import com.example.FacProject.dto.ActivityMemberAttendanceDTO;
import com.example.FacProject.dto.CreateActivityMemberAttendanceDTO;
import com.example.FacProject.exceptions.BadRequestException;
import com.example.FacProject.exceptions.NotFoundException;
import com.example.FacProject.repositories.AttendanceRepository;
import com.example.FacProject.validators.AttendanceValidator;
import com.example.FacProject.validators.CollectivityValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    private AttendanceRepository repository;
    private CollectivityValidator collectivityValidator;
    private AttendanceValidator attendanceValidator;
    AttendanceService(AttendanceRepository repository, CollectivityValidator collectivityValidator, AttendanceValidator attendanceValidator) {
        this.repository = repository;
        this.collectivityValidator = collectivityValidator;
        this.attendanceValidator = attendanceValidator;
    }
        public List<ActivityMemberAttendanceDTO> create(
                List<CreateActivityMemberAttendanceDTO> dtos,
        String id, String activityId) {

            if(dtos.isEmpty() || id == null) {
                throw new BadRequestException("Mandatory query parameters not provided or malformed");
            }
            collectivityValidator.validateExists(id);
            List<String> activityIds = repository.create(dtos, id, activityId);
            List<ActivityMemberAttendanceDTO> list = new ArrayList<>();
            for (String activityIdentifier : activityIds) {
                Optional<ActivityMemberAttendanceDTO> dto = repository.findById(activityIdentifier);
                if(dto.isPresent()) {
                    list.add(dto.get());
                }
            }
            return list;
        }

    public List<ActivityMemberAttendanceDTO> getAllAttendances(String id,String activityId) {
        attendanceValidator.validate(id, activityId);
        return repository.getAllAttendances(id,activityId);
    }
}
