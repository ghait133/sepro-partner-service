package com.sepro.partnerservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "adresse")
public class Adresse extends BaseIdEntity{

    String StreetName;
    String HouseNumbre;
    int plz;
    String cityName;
    String countryName;

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public String getHouseNumbre() {
        return HouseNumbre;
    }

    public void setHouseNumbre(String houseNumbre) {
        HouseNumbre = houseNumbre;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
