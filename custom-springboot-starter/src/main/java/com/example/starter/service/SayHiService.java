package com.example.starter.service;


public class SayHiService {
    private String age;
    private String name;
    private String address;

    public SayHiService(String age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String hi() {
        return this.toString();
    }


    @Override
    public String toString() {
        return "SayHiService{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}