package com.javafarmacy.modeadministration.domain.entity;

public class ModeAdministration {
    private int id;
    private String descriptionmode;
    
    public ModeAdministration() {
    }

    
    public ModeAdministration(int id, String descriptionmode) {
        this.id = id;
        this.descriptionmode = descriptionmode;
    }

    public int getId() {
        return id;
    }

    public String getDescriptionmode() {
        return descriptionmode;
    }

    public void setDescriptionmode(String descriptionmode) {
        this.descriptionmode = descriptionmode;
    }

}
