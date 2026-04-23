package com.example.FacProject.controllers;

import com.example.FacProject.dto.CollectivityTransactionDTO;
import com.example.FacProject.dto.CreateCollectivityDTO;
import com.example.FacProject.dto.CreateCollectivityInformationsDTO;
import com.example.FacProject.dto.FinancialAccountDTO;
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
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
    @PutMapping("/collectivities/{id}/informations")
    public ResponseEntity<?> assignIdentity(@PathVariable("id") String collectivityId, @RequestBody CreateCollectivityInformationsDTO request) {

        try{
            return ResponseEntity.status(200).body(service.assignNameAndNumber(collectivityId,request));
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/collectivities/{id}/transactions")
    public ResponseEntity<?> getTransactions(
            @PathVariable String id,
            @RequestParam String from,
            @RequestParam String to
    ) {
        try{
            List<CollectivityTransactionDTO> transactions =
                    service.getTransactions(id, from, to);

            return ResponseEntity.ok(transactions);
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/collectivities/{id}/financialAccounts")
    public ResponseEntity<?> getFinancialAccounts(
            @PathVariable String id,
            @RequestParam String at
    ) {
        try {
            List<FinancialAccountDTO> accounts =
                    service.getFinancialAccounts(id, at);

            return ResponseEntity.ok(accounts);

        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/collectivities/{id}")
    public ResponseEntity<?> getCollectivityById(
            @PathVariable("id") String id
    ) {

        try{
            return ResponseEntity.status(200).body(service.getCollectivityById(id));
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
