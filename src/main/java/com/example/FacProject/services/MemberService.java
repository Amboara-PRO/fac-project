package com.example.FacProject.services;

import com.example.FacProject.entities.IdGenerator;
import com.example.FacProject.entities.MemberEntity;
import com.example.FacProject.entities.MemberRefereeEntity;
import com.example.FacProject.repositories.MemberRepository;
import com.example.FacProject.validators.MemberValidator;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private final MemberRepository repo;
    private final MemberValidator validator;

    public MemberService(MemberRepository repo, MemberValidator validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public List<MemberEntity> create(List<MemberEntity> members) {
        List<MemberEntity> result = new ArrayList<>();
        for (MemberEntity m : members) {
            validator.validate(m);
            String id;
            do {
                id = IdGenerator.generateId();
            } while (repo.existsById(id));
            m.setId(id);
            repo.save(m);
            for (MemberRefereeEntity ref : m.getReferees()) {
                if (!repo.existsById(ref.toString())) {
                    throw new RuntimeException("Referee not found: " + ref);
                }
                repo.saveReferee(m.getId(), ref.toString());
            }
            result.add(m);
        }
        return result;
    }
}
