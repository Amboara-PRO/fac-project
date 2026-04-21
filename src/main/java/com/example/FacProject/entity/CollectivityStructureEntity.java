package com.example.FacProject.entity;

import java.util.ArrayList;

public class CollectivityStructureEntity {
    private CollectivityEntity collectivity;
    private ArrayList<MemberEntity> members;

    public CollectivityStructureEntity(CollectivityEntity collectivity, ArrayList<MemberEntity> members) {
        this.collectivity = collectivity;
        this.members = members;
    }

    public CollectivityEntity getCollectivity() {
        return collectivity;
    }

    public void setCollectivity(CollectivityEntity collectivity) {
        this.collectivity = collectivity;
    }

    public ArrayList<MemberEntity> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<MemberEntity> members) {
        this.members = members;
    }
}
