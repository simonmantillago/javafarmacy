package com.javafarmacy.customer.domain.entity;

public class Customer {
    private String idcustomer;
    private String namecustomer;
    private String lastnamecustomer;
    private String codecitycustomer;
    private String emailcustomer;
    private String birthDate;
    private float lon;
    private float latitud;
    
    public Customer(String idcustomer, String namecustomer, String lastnamecustomer, String codecitycustomer,
            String emailcustomer, String birthDate, float lon, float latitud) {
        this.idcustomer = idcustomer;
        this.namecustomer = namecustomer;
        this.lastnamecustomer = lastnamecustomer;
        this.codecitycustomer = codecitycustomer;
        this.emailcustomer = emailcustomer;
        this.birthDate = birthDate;
        this.lon = lon;
        this.latitud = latitud;
    }

    public Customer() {
    }

    public String getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(String idcustomer) {
        this.idcustomer = idcustomer;
    }

    public String getNamecustomer() {
        return namecustomer;
    }

    public void setNamecustomer(String namecustomer) {
        this.namecustomer = namecustomer;
    }

    public String getLastnamecustomer() {
        return lastnamecustomer;
    }

    public void setLastnamecustomer(String lastnamecustomer) {
        this.lastnamecustomer = lastnamecustomer;
    }

    public String getCodecitycustomer() {
        return codecitycustomer;
    }

    public void setCodecitycustomer(String codecitycustomer) {
        this.codecitycustomer = codecitycustomer;
    }

    public String getEmailcustomer() {
        return emailcustomer;
    }

    public void setEmailcustomer(String emailcustomer) {
        this.emailcustomer = emailcustomer;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }
    
    
    
}
