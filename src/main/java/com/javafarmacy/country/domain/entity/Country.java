package com.javafarmacy.country.domain.entity;

public class Country {
    private String codeCountry;
    private String nameCountry;
    
    public Country() {
    }

    public Country(String codeCountry, String nameCountry) {
        this.codeCountry = codeCountry;
        this.nameCountry = nameCountry;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    
}
