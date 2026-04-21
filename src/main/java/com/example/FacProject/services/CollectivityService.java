package com.example.FacProject.services;

import com.example.FacProject.dto.CollectivityDTO;
import com.example.FacProject.dto.CreateCollectivityDTO;
import com.example.FacProject.dto.MemberDTO;
import com.example.FacProject.entities.CollectivityEntity;
import com.example.FacProject.exceptions.BadRequestException;
import com.example.FacProject.exceptions.NotFoundException;
import com.example.FacProject.repositories.CollectivityRepository;
import com.example.FacProject.repositories.CollectivityStructureRepository;
import com.example.FacProject.repositories.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollectivityService {

    private final CollectivityRepository collectivityRepo;
    private final MemberRepository memberRepo;
    private final CollectivityStructureRepository structureRepo;

    public CollectivityService(
            CollectivityRepository collectivityRepo,
            MemberRepository memberRepo,
            CollectivityStructureRepository structureRepo
    ) {
        this.collectivityRepo = collectivityRepo;
        this.memberRepo = memberRepo;
        this.structureRepo = structureRepo;
    }

    public List<CollectivityDTO> create(List<CreateCollectivityDTO> dtos) {
            List<CollectivityDTO> list = new ArrayList<>();

            for (CreateCollectivityDTO dto : dtos) {
                if(!dto.getFederationApproval()|| dto.getStructure() == null){
                    throw new BadRequestException("Collectivity without federation approval or structure missing");
                }
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
}
