package com.javafarmacy.farmacymedicine.domain.entity;

public class FarmacyMedicine {
    private int idfarmacy;
    private int idmedicine;
    private float price;
    
    public FarmacyMedicine() {
    }

    public FarmacyMedicine(int idfarmacy, int idmedicine, float price) {
        this.idfarmacy = idfarmacy;
        this.idmedicine = idmedicine;
        this.price = price;
    }

    public int getIdfarmacy() {
        return idfarmacy;
    }

    public void setIdfarmacy(int idfarmacy) {
        this.idfarmacy = idfarmacy;
    }

    public int getIdmedicine() {
        return idmedicine;
    }

    public void setIdmedicine(int idmedicine) {
        this.idmedicine = idmedicine;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
}
