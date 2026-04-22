package com.example.FacProject.controllers;


import com.example.FacProject.dto.CreateCollectivityDTO;
import com.example.FacProject.dto.CreateMembershipFeeDTO;
import com.example.FacProject.exceptions.BadRequestException;
import com.example.FacProject.exceptions.NotFoundException;
import com.example.FacProject.services.MembershipFeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MembershipFeeController {
    private MembershipFeeService service;

    public MembershipFeeController(MembershipFeeService service) {
        this.service = service;
    }
    @PostMapping("/collectivities/{id}/membershipFees")
    public ResponseEntity<?> create(
            @PathVariable("id") String id,
            @RequestBody List<CreateMembershipFeeDTO> dtos
    ) {
        try{
            return ResponseEntity.status(200).body(service.create(id,dtos));
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
    @GetMapping("/collectivities/{id}/membershipFees")
    public ResponseEntity<?> get(
            @PathVariable("id") String id
    ) {
        try{
            return ResponseEntity.status(200).body(service.getMembershipFees(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
