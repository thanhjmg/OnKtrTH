package com.example.onktrth.model;

public class ChiTieu {

    private  int id;
    private String tenChiTieu;
    private String soTien;

    public ChiTieu() {
    }

    public ChiTieu(int id, String tenChiTieu, String soTien) {
        this.id = id;
        this.tenChiTieu = tenChiTieu;
        this.soTien = soTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenChiTieu() {
        return tenChiTieu;
    }

    public void setTenChiTieu(String tenChiTieu) {
        this.tenChiTieu = tenChiTieu;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }
}
