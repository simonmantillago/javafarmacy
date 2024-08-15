package com.javafarmacy.labatory.domain.entity;

public class Labatory {
    private String namelab;
    private String codecityreg;
    private int id;
    
    public int getId() {
        return id;
    }

    public Labatory(String namelab, String codecityreg, int id) {
        this.namelab = namelab;
        this.codecityreg = codecityreg;
        this.id = id;
    }

    public String getNamelab() {
        return namelab;
    }

    public void setNamelab(String namelab) {
        this.namelab = namelab;
    }

    public String getCodecityreg() {
        return codecityreg;
    }

    public void setCodecityreg(String codecityreg) {
        this.codecityreg = codecityreg;
    }

    public Labatory() {
    }
}
