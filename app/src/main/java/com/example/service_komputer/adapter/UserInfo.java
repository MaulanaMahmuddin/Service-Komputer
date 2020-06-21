package com.example.service_komputer.adapter;

public class UserInfo {

    private  String nama;
    private  String ntelepon;
    private  String email;
    private  String Password;

    public UserInfo() {
    }

    public UserInfo(String nama, String ntelepon, String email, String password) {
        this.nama = nama;
        this.ntelepon = ntelepon;
        this.email = email;
        Password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNtelepon() {
        return ntelepon;
    }

    public void setNtelepon(String ntelepon) {
        this.ntelepon = ntelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
