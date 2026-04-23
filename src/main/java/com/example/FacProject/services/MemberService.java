package com.example.FacProject.services;

import com.example.FacProject.dto.CreateMemberPaymentDTO;
import com.example.FacProject.dto.MemberPaymentDTO;
import com.example.FacProject.entities.IdGenerator;
import com.example.FacProject.entities.MemberEntity;
import com.example.FacProject.entities.MemberRefereeEntity;
import com.example.FacProject.exceptions.BadRequestException;
import com.example.FacProject.exceptions.NotFoundException;
import com.example.FacProject.repositories.CollectivityRepository;
import com.example.FacProject.repositories.MemberRepository;
import com.example.FacProject.validators.MemberValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepository repo;
    private final MemberValidator validator;
    private final CollectivityRepository collectivityRepository;

    public MemberService(MemberRepository repo, MemberValidator validator, CollectivityRepository collectivityRepository) {
        this.repo = repo;
        this.validator = validator;
        this.collectivityRepository = collectivityRepository;
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
    public List<MemberPaymentDTO> createPayments(String memberId, List<CreateMemberPaymentDTO> payments) {

        if (repo.findById(memberId).isEmpty()) {
            throw new NotFoundException("Member not found");
        }

        if (payments == null || payments.isEmpty()) {
            throw new BadRequestException("Payments list is empty");
        }

        List<MemberPaymentDTO> result = new ArrayList<>();

        for (CreateMemberPaymentDTO p : payments) {

            if (p.getAmount() <= 0) {
                throw new BadRequestException("Amount must be > 0");
            }

            String paymentId = UUID.randomUUID().toString().substring(0, 9);

            repo.savePayment(memberId, paymentId, p);

            collectivityRepository.saveTransactionFromPayment(
                    memberId,
                    p.getAmount(),
                    p.getPaymentMode()
            );

            MemberPaymentDTO dto = new MemberPaymentDTO();
            dto.setId(paymentId);
            dto.setAmount(p.getAmount());
            dto.setPaymentMode(p.getPaymentMode());
            dto.setCreationDate(LocalDate.now());

            result.add(dto);
        }

        return result;
    }
}
