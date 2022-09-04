package com.baoshi.programmer.entity.admin;

public class Venues {
    private Long id;
    private String venuesname;
    private Long stadiumid;
    private Integer max;
    private Double price;
    private Double allprice;

    public Double getAllprice() {
        return allprice;
    }

    public void setAllprice(Double allprice) {
        this.allprice = allprice;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVenuesname() {
        return venuesname;
    }

    public void setVenuesname(String venuesname) {
        this.venuesname = venuesname;
    }

    public Long getStadiumid() {
        return stadiumid;
    }

    public void setStadiumid(Long stadiumid) {
        this.stadiumid = stadiumid;
    }

    @Override
    public String toString() {
        return "Venues{" +
                "id=" + id +
                ", venuesname='" + venuesname + '\'' +
                ", stadiumid=" + stadiumid +
                ", max=" + max +
                ", price=" + price +
                ", allprice=" + allprice +
                '}';
    }
}
