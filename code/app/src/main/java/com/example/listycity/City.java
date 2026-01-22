package com.example.listycity;

public class City {
    private String name;
    private String province;

    //Add setters to your City class so that you can modify its name and province.
    public void setName(String name) {
        this.name = name;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }
    public String getName() {
        return name;
    }
    public String getProvince() {
        return province;
    }
}