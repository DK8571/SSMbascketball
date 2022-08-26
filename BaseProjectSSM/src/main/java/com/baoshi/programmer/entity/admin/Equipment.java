package com.baoshi.programmer.entity.admin;

public class Equipment {
    private Long id;
    private String equipmentname;
    private String remark;
    private Long userid;
    private Long venuesid;

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", equipmentname='" + equipmentname + '\'' +
                ", remark='" + remark + '\'' +
                ", userid=" + userid +
                ", venuesid=" + venuesid +
                '}';
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentname() {
        return equipmentname;
    }

    public void setEquipmentname(String equipmentname) {
        this.equipmentname = equipmentname;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getVenuesid() {
        return venuesid;
    }

    public void setVenuesid(Long venuesid) {
        this.venuesid = venuesid;
    }
}
