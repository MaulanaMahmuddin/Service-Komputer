package com.example.service_komputer.adapter;

public class Menu {

    private String nama;
    private int img;

    public Menu() {
    }

    public Menu(String nama, int img) {
        this.nama = nama;
        this.img = img;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


}
