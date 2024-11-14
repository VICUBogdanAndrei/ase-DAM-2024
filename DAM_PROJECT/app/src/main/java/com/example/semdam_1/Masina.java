package com.example.semdam_1;

import java.io.Serializable;
import java.util.Date;

public class Masina implements Serializable {
    private String marca;
    private Date dataFabricatie;
    private float pret;
    private String culoare; //ALB, NEGRU, ROSU, GRI
    private String motorizare; //BENZINA, DISEL, ELECTRIC, HIBRID -> radioBotton

    public Masina(String marca, Date dataFabricatie, float pret, String culoare, String motorizare) {
        this.marca = marca;
        this.dataFabricatie = dataFabricatie;
        this.pret = pret;
        this.culoare = culoare;
        this.motorizare = motorizare;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getDataFabricatie() {
        return dataFabricatie;
    }

    public void setDataFabricatie(Date dataFabricatie) {
        this.dataFabricatie = dataFabricatie;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public String getMotorizare() {
        return motorizare;
    }

    public void setMotorizare(String motorizare) {
        this.motorizare = motorizare;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "marca='" + marca + '\'' +
                ", dataFabricatie=" + dataFabricatie +
                ", pret=" + pret +
                ", culoare='" + culoare + '\'' +
                ", motorizare='" + motorizare + '\'' +
                '}';
    }



}
