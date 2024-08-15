package com.javafarmacy.unitmeasurement.domain.entity;

public class UnitMeasurement {
    private int idum;
    private String nameum;
    
    public UnitMeasurement() {
    }

    
    public UnitMeasurement(int idum, String nameum) {
        this.idum = idum;
        this.nameum = nameum;
    }

    public void setIdum(int idum) {
        this.idum = idum;
    }


    public int getIdum() {
        return idum;
    }

    public String getnameum() {
        return nameum;
    }

    public void setnameum(String nameum) {
        this.nameum = nameum;
    }

}
