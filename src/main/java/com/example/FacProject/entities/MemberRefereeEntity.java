package com.example.FacProject.entities;

public class MemberRefereeEntity {
    private MemberEntity member;
    private MemberEntity referee;

    public MemberRefereeEntity(MemberEntity member, MemberEntity referee) {
        this.member = member;
        this.referee = referee;
    }

    public MemberRefereeEntity() {
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public MemberEntity getReferee() {
        return referee;
    }

    public void setReferee(MemberEntity referee) {
        this.referee = referee;
    }
}
