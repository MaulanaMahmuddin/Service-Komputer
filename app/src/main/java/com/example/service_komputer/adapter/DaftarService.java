package com.example.service_komputer.adapter;

public class DaftarService {
    private String nomorhp;
    private String username;
    private String alamat;
    private String perangkat;
    private String tipe;
    private String kerusakan;

    public DaftarService() {
    }

    public DaftarService(String nomorhp, String username, String alamat, String perangkat, String tipe, String kerusakan) {
        this.nomorhp = nomorhp;
        this.username = username;
        this.alamat = alamat;
        this.perangkat = perangkat;
        this.tipe = tipe;
        this.kerusakan = kerusakan;
    }

    public String getNomorhp() {
        return nomorhp;
    }

    public void setNomorhp(String nomorhp) {
        this.nomorhp = nomorhp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPerangkat() {
        return perangkat;
    }

    public void setPerangkat(String perangkat) {
        this.perangkat = perangkat;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getKerusakan() {
        return kerusakan;
    }

    public void setKerusakan(String kerusakan) {
        this.kerusakan = kerusakan;
    }
}
