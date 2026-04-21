package com.example.FacProject.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class MemberEntity extends PersonEntity {
    private MemberOccupationEnum occupation;
    private ArrayList<MemberRefereeEntity> referees;
    private Boolean registrationFeePaid;
    private Boolean membershipDuesPaid;
    private CollectivityEntity collectivity;
    private LocalDate federationJoinDate;

    public MemberEntity(String id, String firstName, String lastName, LocalDate birthDate, GenderEnum gender, String address, String profession, Long phoneNumber, String email, MemberOccupationEnum occupation, ArrayList<MemberRefereeEntity> referees, Boolean registrationFeePaid, Boolean membershipDuesPaid, CollectivityEntity collectivity, LocalDate federationJoinDate) {
        super(id, firstName, lastName, birthDate, gender, address, profession, phoneNumber, email);
        this.occupation = occupation;
        this.referees = referees;
        this.registrationFeePaid = registrationFeePaid;
        this.membershipDuesPaid = membershipDuesPaid;
        this.collectivity = collectivity;
        this.federationJoinDate = federationJoinDate;
    }


    public MemberEntity() {
    }

    public LocalDate getFederationJoinDate() {
        return federationJoinDate;
    }

    public void setFederationJoinDate(LocalDate federationJoinDate) {
        this.federationJoinDate = federationJoinDate;
    }

    public MemberOccupationEnum getOccupation() {
        return occupation;
    }

    public void setOccupation(MemberOccupationEnum occupation) {
        this.occupation = occupation;
    }

    public ArrayList<MemberRefereeEntity> getReferees() {
        return referees;
    }

    public void setReferees(ArrayList<MemberRefereeEntity> referees) {
        this.referees = referees;
    }

    public Boolean getRegistrationFeePaid() {
        return registrationFeePaid;
    }

    public void setRegistrationFeePaid(Boolean registrationFeePaid) {
        this.registrationFeePaid = registrationFeePaid;
    }

    public Boolean getMembershipDuesPaid() {
        return membershipDuesPaid;
    }

    public void setMembershipDuesPaid(Boolean membershipDuesPaid) {
        this.membershipDuesPaid = membershipDuesPaid;
    }

    public CollectivityEntity getCollectivity() {
        return collectivity;
    }

    public void setCollectivity(CollectivityEntity collectivity) {
        this.collectivity = collectivity;
    }
}
