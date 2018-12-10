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
    private String information;

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



    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}