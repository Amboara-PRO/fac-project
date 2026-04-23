package com.example.FacProject.controllers;

import com.example.FacProject.dto.CreateMemberPaymentDTO;
import com.example.FacProject.dto.MemberPaymentDTO;
import com.example.FacProject.entities.MemberEntity;
import com.example.FacProject.exceptions.BadRequestException;
import com.example.FacProject.exceptions.NotFoundException;
import com.example.FacProject.services.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService service;
    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<MemberEntity> input) {
        try{
            return ResponseEntity.status(201).body(service.create(input));
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
    @PostMapping("/{id}/payments")
    public ResponseEntity<?> createPayments(
            @PathVariable String id,
            @RequestBody List<CreateMemberPaymentDTO> payments
    ) {

        List<MemberPaymentDTO> result =
                service.createPayments(id, payments);
        try{
            return ResponseEntity.status(201).body(result);
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
