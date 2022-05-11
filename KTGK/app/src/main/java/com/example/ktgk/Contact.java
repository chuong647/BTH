package com.example.ktgk;

import java.util.Objects;

public class Contact {
    private String hoTen;
    private String MSV;
    private String SoTC;

    public Contact(String hoTen, String MSV, String SoTC) {
        this.hoTen = hoTen;
        this.MSV = MSV;
        this.SoTC = SoTC;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMSV() {
        return MSV;
    }

    public void setMSV(String MSV) {
        this.MSV = MSV;
    }

    public String getSoTC() {
        return SoTC;
    }

    public void setSoTC(String SoTC) { this.SoTC = SoTC;}

    @Override
    public String toString() {
        return  "" + MSV + '-' + "" + hoTen + '-' + " " + SoTC;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(SoTC, contact.SoTC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SoTC);
    }
}

