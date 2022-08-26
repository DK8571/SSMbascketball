package com.baoshi.programmer.entity.admin;

public class Stadium {
    private Long id;
    private String stadiumname;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStadiumname() {
        return stadiumname;
    }

    public void setStadiumname(String stadiumname) {
        this.stadiumname = stadiumname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Stadium{" +
                "id=" + id +
                ", stadiumname='" + stadiumname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
