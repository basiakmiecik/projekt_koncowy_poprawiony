package com.application.project.model;

import javax.persistence.*;

@Entity
public class Giving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double debt;
    private String informationGive;

    @ManyToOne
    private Person person;



    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public String getInformationGive() {
        return informationGive;
    }

    public void setInformationGive(String informationGive) {
        this.informationGive = informationGive;
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
