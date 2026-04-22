package com.example.FacProject.services;

import com.example.FacProject.dto.CreateMembershipFeeDTO;
import com.example.FacProject.dto.MembershipFeeDTO;
import com.example.FacProject.exceptions.NotFoundException;
import com.example.FacProject.repositories.CollectivityRepository;
import com.example.FacProject.repositories.MemberRepository;
import com.example.FacProject.repositories.MembershipFeeRepository;
import com.example.FacProject.validators.MembershipFeeValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MembershipFeeService {
    private MembershipFeeRepository repo;
    private CollectivityRepository collectivityRepository;
    private MembershipFeeValidator  validator;

    public MembershipFeeService(MembershipFeeRepository repo, MembershipFeeValidator validator,  CollectivityRepository collectivityRepository) {
        this.repo = repo;
        this.validator = validator;
        this.collectivityRepository = collectivityRepository;
    }
    public List<MembershipFeeDTO> create(String id,List<CreateMembershipFeeDTO> dtos){
        if (!collectivityRepository.isExist(id)) {
            throw new NotFoundException("Collectivity not found");
        }
        List<MembershipFeeDTO> list = new ArrayList<>();
        for (CreateMembershipFeeDTO dto : dtos) {
            validator.validate(dto);
            list.add(repo.save(id,dto));
        }
        return list;
    }
}
