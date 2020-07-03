package com.lenovo.smarttraffic.cehua2;

public class Car17  {
    private String sex;
    private String name1;
    private String name2;
    private String tel;
    private String date;

    public Car17(String sex, String name1, String name2, String tel, String date) {
        this.sex = sex;
        this.name1 = name1;
        this.name2 = name2;
        this.tel = tel;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Car17(String sex, String name1, String name2, String tel) {
        this.sex = sex;
        this.name1 = name1;
        this.name2 = name2;
        this.tel = tel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
