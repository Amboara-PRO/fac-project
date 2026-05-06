package com.example.FacProject.controllers;

import com.example.FacProject.dto.CreateCollectivityActivityDTO;
import com.example.FacProject.exceptions.BadRequestException;
import com.example.FacProject.exceptions.NotFoundException;
import com.example.FacProject.repositories.ActivityRepository;
import com.example.FacProject.services.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityController {
    private final ActivityService service;
    public ActivityController(ActivityService service) {
        this.service = service;
    }
    @GetMapping("/collectivities/{id}/activities")
    public ResponseEntity<?> getAllActivities(
            @PathVariable("id") String id
    ) {

        try{
            return ResponseEntity.status(200).body(service.getAllActivities(id));
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @PostMapping("/collectivities/{id}/activities")
    public ResponseEntity<?> createActivity(
            @PathVariable("id") String id,
            @RequestBody List<CreateCollectivityActivityDTO> dtos
    ) {

        try{
            return ResponseEntity.status(201).body(service.createActivity(dtos,id));
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
