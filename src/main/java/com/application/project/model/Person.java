package com.application.project.model;

import javax.persistence.*;
import java.util.List;

@Entity

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String image;
    private Integer age;
    private String about;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy="person")
    private List<Getting> gettingList;

    @OneToMany(mappedBy = "person")
    private List<Giving> givingList;

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

    public List<Getting> getGettingList() {
        return gettingList;
    }

    public void setGettingList(List<Getting> gettingList) {
        this.gettingList = gettingList;
    }

    public List<Giving> getGivingList() {
        return givingList;
    }

    public void setGivingList(List<Giving> givingList) {
        this.givingList = givingList;
    }
}