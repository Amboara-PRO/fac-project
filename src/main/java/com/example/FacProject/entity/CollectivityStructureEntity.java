package com.example.FacProject.entity;

import java.util.ArrayList;

public class CollectivityStructureEntity {
    private CollectivityEntity collectivity;
    private MemberEntity president;
    private MemberEntity vicePresident;
    private MemberEntity treasurer;
    private MemberEntity secretary;

    public CollectivityStructureEntity(CollectivityEntity collectivity, MemberEntity president, MemberEntity vicePresident, MemberEntity treasurer, MemberEntity secretary) {
        this.collectivity = collectivity;
        this.president = president;
        this.vicePresident = vicePresident;
        this.treasurer = treasurer;
        this.secretary = secretary;
    }

    public CollectivityStructureEntity() {
    }

    public CollectivityEntity getCollectivity() {
        return collectivity;
    }

    public void setCollectivity(CollectivityEntity collectivity) {
        this.collectivity = collectivity;
    }

    public MemberEntity getPresident() {
        return president;
    }

    public void setPresident(MemberEntity president) {
        this.president = president;
    }

    public MemberEntity getVicePresident() {
        return vicePresident;
    }

    public void setVicePresident(MemberEntity vicePresident) {
        this.vicePresident = vicePresident;
    }

    public MemberEntity getTreasurer() {
        return treasurer;
    }

    public void setTreasurer(MemberEntity treasurer) {
        this.treasurer = treasurer;
    }

    public MemberEntity getSecretary() {
        return secretary;
    }

    public void setSecretary(MemberEntity secretary) {
        this.secretary = secretary;
    }
}
