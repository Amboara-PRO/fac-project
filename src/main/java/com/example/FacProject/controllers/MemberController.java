package com.example.FacProject.controllers;

import com.example.FacProject.dto.CreateMemberPaymentDTO;
import com.example.FacProject.dto.MemberPaymentDTO;
import com.example.FacProject.entities.MemberEntity;
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
    public ResponseEntity<List<MemberEntity>> create(@RequestBody List<MemberEntity> input) {
        return ResponseEntity.status(201).body(service.create(input));
    }
    @PostMapping("/{id}/payments")
    public ResponseEntity<List<MemberPaymentDTO>> createPayments(
            @PathVariable String id,
            @RequestBody List<CreateMemberPaymentDTO> payments
    ) {

        List<MemberPaymentDTO> result =
                service.createPayments(id, payments);

        return ResponseEntity.status(201).body(result);
    }
}
