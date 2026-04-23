package com.example.FacProject.services;

import com.example.FacProject.dto.*;
import com.example.FacProject.exceptions.BadRequestException;
import com.example.FacProject.exceptions.NotFoundException;
import com.example.FacProject.repositories.CollectivityRepository;
import com.example.FacProject.repositories.CollectivityStructureRepository;
import com.example.FacProject.repositories.MemberRepository;
import com.example.FacProject.validators.CollectivityValidator;
import com.example.FacProject.validators.MemberValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollectivityService {

    private  CollectivityRepository collectivityRepo;
    private  MemberRepository memberRepo;
    private  CollectivityStructureRepository structureRepo;
    private  MemberValidator memberValidator;
    private CollectivityValidator collectivityValidator;
    public CollectivityService(
            CollectivityRepository collectivityRepo,
            MemberRepository memberRepo,
            CollectivityStructureRepository structureRepo,
            MemberValidator memberValidator,
            CollectivityValidator collectivityValidator
    ) {
        this.collectivityRepo = collectivityRepo;
        this.memberRepo = memberRepo;
        this.structureRepo = structureRepo;
        this.memberValidator = memberValidator;
        this.collectivityValidator = collectivityValidator;
    }

    public List<CollectivityDTO> create(List<CreateCollectivityDTO> dtos) {
            List<CollectivityDTO> list = new ArrayList<>();

            for (CreateCollectivityDTO dto : dtos) {
                if(!dto.getFederationApproval()|| dto.getStructure() == null){
                    throw new BadRequestException("Collectivity without federation approval or structure missing");
                }
                if(dto.getLocation() == null){
                    throw new BadRequestException("Collectivity without location missing");
                }
                memberValidator.validateMemberCount(dto.getMembers().size());
                memberValidator.validateSeniorNumber(memberRepo.countSeniorMembers(dto.getMembers()));
                List<MemberDTO>  listMembers = new ArrayList<>();
                for (String id : dto.getMembers()) {
                    Optional<MemberDTO> memberDTO = memberRepo.findById(id);
                    if(memberDTO.isPresent()){
                        listMembers.add(memberDTO.get());
                    }
                    else{
                        throw new NotFoundException("Member not found");
                    }

                }
                String CollectivityDTOId = collectivityRepo.create(dto);
                CollectivityDTO collectivityDTO = new CollectivityDTO();
                collectivityDTO.setId(CollectivityDTOId);
                collectivityDTO.setMembers(listMembers);
                collectivityDTO.setLocation(dto.getLocation());
                collectivityDTO.setStructure(dto.getStructure());
                list.add(collectivityDTO);
                structureRepo.createCollectivityStructure(collectivityDTO);
            }
            return list;

    }
    public String assignNameAndNumber(String id,CreateCollectivityInformationsDTO dto){
        collectivityValidator.validator(id,dto);
        collectivityRepo.assignNamAndNumber(id,dto);
        return "Collectivity name and number assigned";
    }
    public List<CollectivityTransactionDTO> getTransactions(String id, String from, String to) {

        if (!collectivityRepo.isExist(id)) {
            throw new NotFoundException("Collectivity not found");
        }

        if (from == null || to == null) {
            throw new BadRequestException("from and to are required");
        }

        LocalDate fromDate;
        LocalDate toDate;

        try {
            fromDate = LocalDate.parse(from);
            toDate = LocalDate.parse(to);
        } catch (Exception e) {
            throw new BadRequestException("Invalid date format");
        }

        return collectivityRepo.findTransactions(id, fromDate, toDate);
    }
}
