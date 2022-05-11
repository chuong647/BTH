package com.example.bai_5;

import java.util.Objects;

public class Contact {
    private String hoTen;
    private String Sđt;
    private String email;

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSđt() {
        return Sđt;
    }

    public void setSđt(String sđt) {
        Sđt = sđt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact(String hoTen, String sđt, String email) {
        this.hoTen = hoTen;
        Sđt = sđt;
        this.email = email;
    }

    @Override
    public String toString() {
        return  hoTen +"-"+ Sđt;
    }
}
