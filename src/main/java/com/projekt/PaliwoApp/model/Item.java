package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nazwa;
    private float ilosc;
    private float cena;

    @ManyToOne(fetch = FetchType.EAGER)
    private Receipt receipt;

    public String getNazwa() {
        return nazwa;
    }
    public float getIlosc() {
        return ilosc;
    }
    public float getCena() { return cena; }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public void setIlosc(float ilosc) {
        this.ilosc = ilosc;
    }
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
    public void setCena(float cena) {
        this.cena = cena;
    }
}
