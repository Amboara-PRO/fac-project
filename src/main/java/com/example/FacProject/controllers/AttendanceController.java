package com.example.FacProject.controllers;

import com.example.FacProject.dto.CreateActivityMemberAttendanceDTO;
import com.example.FacProject.exceptions.BadRequestException;
import com.example.FacProject.exceptions.NotFoundException;
import com.example.FacProject.services.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AttendanceController {
    private final AttendanceService attendanceService;
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
    @PostMapping("/collectivities/{id}/activities/{activityId}/attendance")
    public ResponseEntity<?> createActivityAttendance(
            @PathVariable("id") String id,
            @PathVariable("activityId") String activityId,
            @RequestBody List<CreateActivityMemberAttendanceDTO> dtos
    ) {

        try{
            return ResponseEntity.status(201).body(attendanceService.create(dtos,id,activityId));
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/collectivities/{id}/activities/{activityId}/attendance")
    public ResponseEntity<?> getAllAttendances(
            @PathVariable("id") String id,
            @PathVariable("activityId") String activityId
    ) {

        try{
            return ResponseEntity.status(200).body(attendanceService.getAllAttendances(id,activityId));
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
