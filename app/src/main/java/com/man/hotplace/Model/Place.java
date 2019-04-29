package com.man.hotplace.Model;

public class Place {
    private  String id;
    private String tenDiaDiem;
    private String moTa;
    private String sdt;
    private String longitude, latitude;

    public Place() {
    }

    public Place(String id, String tenDiaDiem, String moTa, String sdt, String longitude, String latitude) {
        this.id = id;
        this.tenDiaDiem = tenDiaDiem;
        this.moTa = moTa;
        this.sdt = sdt;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
