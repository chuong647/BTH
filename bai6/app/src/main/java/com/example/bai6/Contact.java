package com.example.bai6;

import java.util.Objects;

public class Contact {
    private String hoTen;
    private String SDT;
    private String ngaySinh;
    private String gioiTinh;

    public Contact(String hoTen, String SDT, String ngaySinh, String gioiTinh) {
        this.hoTen = hoTen;
        this.SDT = SDT;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return  "" + hoTen + '-' + "" + SDT + '-' + " " + ngaySinh + '-' + "" + gioiTinh;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(SDT, contact.SDT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SDT);
    }
}
