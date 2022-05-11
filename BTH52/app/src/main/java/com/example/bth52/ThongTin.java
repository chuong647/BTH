package com.example.bth52;

import java.util.Objects;

public class ThongTin {
    private String hoTen;
    private String soDT;
    private String Email;

    public ThongTin()
    {
        hoTen = "";
        soDT = "";
        Email = "";
    }
    public ThongTin(String ht, String sdt, String email)
    {
        this.hoTen = ht;
        this.soDT = sdt;
        this.Email = email;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return  hoTen + "-" + soDT + "-" + Email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThongTin thongTin = (ThongTin) o;
        return soDT.equals(thongTin.soDT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(soDT);
    }
}
