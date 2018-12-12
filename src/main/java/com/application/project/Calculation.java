package com.application.project;


import com.application.project.model.Person;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.List;

public class Calculation {


    public BigDecimal saldoAll(List<Person> persons) {
        double saldo=0;
        for (Person person : persons) {
            saldo=saldo+(person.getToGet()-person.getToGive());
        }
        BigDecimal saldo2=BigDecimal.valueOf(saldo).round(new MathContext(4));
        return saldo2;}

    public BigDecimal saldoGet(List<Person> persons) {
        double saldo=0;
        for (Person person : persons) {
            saldo=saldo+(person.getToGet());
        }
        BigDecimal saldo2=BigDecimal.valueOf(saldo).round(new MathContext(4));
        return saldo2;}

    public BigDecimal saldoGive(List<Person> persons) {
        double saldo=0;
        for (Person person : persons) {
            saldo=(saldo+(person.getToGive()));
        }
        BigDecimal saldo2=BigDecimal.valueOf(saldo).round(new MathContext(4));
        return saldo2;}
}
