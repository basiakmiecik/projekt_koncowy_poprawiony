package com.application.project.model;

import javax.persistence.*;

@Entity
public class Getting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double debt;
    private String informationGet;
    @ManyToOne
    private Person person;



    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public String getInformationGet() {
        return informationGet;
    }

    public void setInformationGet(String informationGet) {
        this.informationGet = informationGet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }


}
