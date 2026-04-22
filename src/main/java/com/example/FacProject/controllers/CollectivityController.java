package com.example.FacProject.controllers;

import com.example.FacProject.dto.CollectivityDTO;
import com.example.FacProject.dto.CreateCollectivityDTO;
import com.example.FacProject.dto.CreateCollectivityNameAndNumberDTO;
import com.example.FacProject.exceptions.BadRequestException;
import com.example.FacProject.exceptions.NotFoundException;
import com.example.FacProject.services.CollectivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollectivityController {
    private final CollectivityService service;

    public CollectivityController(CollectivityService service) {
        this.service = service;
    }


    @PostMapping("/collectivities")
    public ResponseEntity<?> create(
            @RequestBody List<CreateCollectivityDTO> dtos
    ) {
        try{
            return ResponseEntity.status(201).body(service.create(dtos));
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
    @PutMapping("/collectivities/{id}/identity")
    public ResponseEntity<?> assignIdentity(@PathVariable("id") String collectivityId,@RequestParam String name, @RequestParam Integer number) {

        try{
            CreateCollectivityNameAndNumberDTO createDTO = new CreateCollectivityNameAndNumberDTO();
            createDTO.setCollectivityId(collectivityId);
            createDTO.setName(name);
            createDTO.setNumber(number);
            return ResponseEntity.status(201).body(service.assignNameAndNumber(createDTO));
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
