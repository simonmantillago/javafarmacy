package com.javafarmacy.region.domain.entity;

public class Region {
    private String codeRegion;
    private String nameRegion;
    private String codeCountryReg;
    
    
    public Region(String codeRegion, String nameRegion, String codeCountry) {
        this.codeRegion = codeRegion;
        this.nameRegion = nameRegion;
        this.codeCountryReg = codeCountry;
    }

    public Region() {
    }

    public String getCodeRegion() {
        return codeRegion;
    }

    public void setCodeRegion(String codeRegion) {
        this.codeRegion = codeRegion;
    }

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    public String getCodeCountryReg() {
        return codeCountryReg;
    }

    public void setCodeCountryReg(String codeCountry) {
        this.codeCountryReg = codeCountry;
    }

}
