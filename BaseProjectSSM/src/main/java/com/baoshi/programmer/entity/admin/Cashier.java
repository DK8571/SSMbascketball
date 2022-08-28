package com.baoshi.programmer.entity.admin;

public class Cashier extends User{
    private Integer stadiumid;
    private String stadiumname;

    @Override
    public String toString() {
        return "Cashier{" +
                "stadiumid=" + stadiumid +
                ", stadiumname='" + stadiumname + '\'' +
                '}';
    }

    public Integer getStadiumid() {
        return stadiumid;
    }

    public void setStadiumid(Integer stadiumid) {
        this.stadiumid = stadiumid;
    }

    public String getStadiumname() {
        return stadiumname;
    }

    public void setStadiumname(String stadiumname) {
        this.stadiumname = stadiumname;
    }
}
