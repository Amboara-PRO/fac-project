package com.example.FacProject.controllers;

import com.example.FacProject.dto.CollectivityTransactionDTO;
import com.example.FacProject.exceptions.BadRequestException;
import com.example.FacProject.exceptions.NotFoundException;
import com.example.FacProject.services.StatisticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class StatisticsController {
    private StatisticsService service;
    public StatisticsController(StatisticsService service){
        this.service = service;
    }
    @GetMapping("/collectivities/{id}/statistics")
    public ResponseEntity<?> getCollectivityStatistics(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        try{
            return ResponseEntity.ok(service.getCollectivityStatisticsById(id, from, to));
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
