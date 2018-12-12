package com.application.project;

import com.application.project.model.Person;

import java.math.BigDecimal;
import java.util.List;

public class SaldoCalc {
    private BigDecimal saldoAll;
    private BigDecimal saldoGet;
    private BigDecimal saldoGive;

    public SaldoCalc() {

    }

    public BigDecimal getSaldoAll() {
        return saldoAll;
    }

    public void setSaldoAll(BigDecimal saldoAll) {
        this.saldoAll = saldoAll;
    }

    public BigDecimal getSaldoGet() {
        return saldoGet;
    }

    public void setSaldoGet(BigDecimal saldoGet) {
        this.saldoGet = saldoGet;
    }

    public BigDecimal getSaldoGive() {
        return saldoGive;
    }

    public void setSaldoGive(BigDecimal saldoGive) {
        this.saldoGive = saldoGive;
    }
}
