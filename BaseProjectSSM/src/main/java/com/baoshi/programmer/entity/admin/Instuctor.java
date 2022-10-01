package com.baoshi.programmer.entity.admin;

public class Instuctor {
    private Long id;
    private String name;
    private String photo;//头像照片地址
    private int sex;//性别0,代表未知，1代表男，2代表女
    private Integer age;//年龄
    private Long stadiumid;
    private int T;

    public int getT() {
        return T;
    }

    public void setT(int t) {
        T = t;
    }

    @Override
    public String toString() {
        return "Instuctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", stadiumid=" + stadiumid +
                ", T=" + T +
                '}';
    }

    public Long getStadiumid() {
        return stadiumid;
    }

    public void setStadiumid(Long stadiumid) {
        this.stadiumid = stadiumid;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
