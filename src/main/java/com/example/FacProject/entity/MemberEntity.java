package com.example.FacProject.entity;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;

public class MemberEntity extends PersonEntity {
    private MemberOccupationEnum occupation;
    private ArrayList<MemberEntity> referees;

    public MemberEntity(String id, String firstName, String lastName, Instant birthDate, GenderEnum gender, String address, String profession, BigInteger phoneNumber, String email, MemberOccupationEnum occupation, ArrayList<MemberEntity> referees) {
        super(id, firstName, lastName, birthDate, gender, address, profession, phoneNumber, email);
        this.occupation = occupation;
        this.referees = referees;
    }

    public MemberOccupationEnum getOccupation() {
        return occupation;
    }

    public void setOccupation(MemberOccupationEnum occupation) {
        this.occupation = occupation;
    }

    public ArrayList<MemberEntity> getReferees() {
        return referees;
    }

    public void setReferees(ArrayList<MemberEntity> referees) {
        this.referees = referees;
    }
}
