package com.javafarmacy.farmacy.domain.entity;

public class Farmacy {
    private int idfarmacy;
    private String namefarmacy;
    private String addressfarmacy;
    private float longitude;
    private float latfarmacy;
    private String codecityfarm;
    private String logofarmacy;
    
    public Farmacy(int idfarmacy, String namefarmacy, String addressfarmacy, float longitude, float latfarmacy,
    String codecityfarm, String logofarmacy) {
        this.idfarmacy = idfarmacy;
        this.namefarmacy = namefarmacy;
        this.addressfarmacy = addressfarmacy;
        this.longitude = longitude;
        this.latfarmacy = latfarmacy;
        this.codecityfarm = codecityfarm;
        this.logofarmacy = logofarmacy;
    }
    
    public void setIdfarmacy(int idfarmacy) {
        this.idfarmacy = idfarmacy;
    }
    public Farmacy() {
    }

    public int getIdfarmacy() {
        return idfarmacy;
    }

    public String getNamefarmacy() {
        return namefarmacy;
    }

    public void setNamefarmacy(String namefarmacy) {
        this.namefarmacy = namefarmacy;
    }

    public String getAddressfarmacy() {
        return addressfarmacy;
    }

    public void setAddressfarmacy(String addressfarmacy) {
        this.addressfarmacy = addressfarmacy;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatfarmacy() {
        return latfarmacy;
    }

    public void setLatfarmacy(float latfarmacy) {
        this.latfarmacy = latfarmacy;
    }

    public String getCodecityfarm() {
        return codecityfarm;
    }

    public void setCodecityfarm(String codecityfarm) {
        this.codecityfarm = codecityfarm;
    }

    public String getLogofarmacy() {
        return logofarmacy;
    }

    public void setLogofarmacy(String logofarmacy) {
        this.logofarmacy = logofarmacy;
    }

    

}
