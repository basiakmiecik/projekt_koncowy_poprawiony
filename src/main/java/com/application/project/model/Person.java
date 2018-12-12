package com.application.project.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private Double toGive;
    private Double toGet;
    private String informationGet;
    private String informationGive;
    private String image;
    private Integer age;
    private String about;


    @ManyToOne
    private User user;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getToGive() {
        return toGive;
    }

    public void setToGive(Double toGive) {
        this.toGive = toGive;
    }

    public Double getToGet() {
        return toGet;
    }

    public void setToGet(Double toGet) {
        this.toGet = toGet;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getInformationGet() {
        return informationGet;
    }

    public void setInformationGet(String informationGet) {
        this.informationGet = informationGet;
    }

    public String getInformationGive() {
        return informationGive;
    }

    public void setInformationGive(String informationGive) {
        this.informationGive = informationGive;
    }
}