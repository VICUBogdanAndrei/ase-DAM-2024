package com.example.test2_var1;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "customers")
public class Customer {
    private String nume;
    private Date dataContract;
    private String tipAbonament;
    private float pret;
    private boolean extraOptiuni;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @Ignore
    public Customer() {
    }

    public Customer(String nume, Date dataContract, String tipAbonament, float pret, boolean extraOptiuni) {
        this.nume = nume;
        this.dataContract = dataContract;
        this.tipAbonament = tipAbonament;
        this.pret = pret;
        this.extraOptiuni = extraOptiuni;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Date getDataContract() {
        return dataContract;
    }

    public void setDataContract(Date dataContract) {
        this.dataContract = dataContract;
    }

    public String getTipAbonament() {
        return tipAbonament;
    }

    public void setTipAbonament(String tipAbonament) {
        this.tipAbonament = tipAbonament;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public boolean isExtraOptiuni() {
        return extraOptiuni;
    }

    public void setExtraOptiuni(boolean extraOptiuni) {
        this.extraOptiuni = extraOptiuni;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "nume='" + nume + '\'' +
                ", dataContract=" + dataContract +
                ", tipAbonament='" + tipAbonament + '\'' +
                ", pret=" + pret +
                ", extraOptiuni=" + extraOptiuni +
                '}';
    }

}
