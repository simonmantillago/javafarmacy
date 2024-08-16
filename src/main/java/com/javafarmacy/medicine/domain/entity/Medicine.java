package com.javafarmacy.medicine.domain.entity;

public class Medicine {
    private int id;
    private String proceedings;
    private String namemedicine;
    private String healthregister;
    private String description;
    private String descriptionshort;
    private int codemodeadmin;
    private int codeap;
    private int codeum;
    private String namerol;
    private int codelab;
    
    public Medicine() {
    }

    public Medicine(int id, String proceedings, String namemedicine, String healthregister, String description,
            String descriptionshort, int codemodeadmin, int codeap, int codeum, String namerol, int codelab) {
        this.id = id;
        this.proceedings = proceedings;
        this.namemedicine = namemedicine;
        this.healthregister = healthregister;
        this.description = description;
        this.descriptionshort = descriptionshort;
        this.codemodeadmin = codemodeadmin;
        this.codeap = codeap;
        this.codeum = codeum;
        this.namerol = namerol;
        this.codelab = codelab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProceedings() {
        return proceedings;
    }

    public void setProceedings(String proceedings) {
        this.proceedings = proceedings;
    }

    public String getNamemedicine() {
        return namemedicine;
    }

    public void setNamemedicine(String namemedicine) {
        this.namemedicine = namemedicine;
    }

    public String getHealthregister() {
        return healthregister;
    }

    public void setHealthregister(String healthregister) {
        this.healthregister = healthregister;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionshort() {
        return descriptionshort;
    }

    public void setDescriptionshort(String descriptionshort) {
        this.descriptionshort = descriptionshort;
    }

    public int getCodemodeadmin() {
        return codemodeadmin;
    }

    public void setCodemodeadmin(int codemodeadmin) {
        this.codemodeadmin = codemodeadmin;
    }

    public int getCodeap() {
        return codeap;
    }

    public void setCodeap(int codeap) {
        this.codeap = codeap;
    }

    public int getCodeum() {
        return codeum;
    }

    public void setCodeum(int codeum) {
        this.codeum = codeum;
    }

    public String getNamerol() {
        return namerol;
    }

    public void setNamerol(String namerol) {
        this.namerol = namerol;
    }

    public int getCodelab() {
        return codelab;
    }

    public void setCodelab(int codelab) {
        this.codelab = codelab;
    }

    

}
