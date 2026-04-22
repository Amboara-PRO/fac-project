package com.example.FacProject.controllers;

import com.example.FacProject.entities.MemberEntity;
import com.example.FacProject.services.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
