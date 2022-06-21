package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receipts")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sprzedawca;
    private String miejsce;
    private LocalDate data;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> itemList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public String getSprzedawca(){
        return sprzedawca;
    }
    public String getMiejsce(){
        return miejsce;
    }
    public List<Item> getItemList() {
        return itemList;
    }
    public LocalDate getData() {
        return data;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setSprzedawca(String sprzedawca) {
        this.sprzedawca = sprzedawca;
    }
    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public void dodajItem(Item item){
        itemList.add(item);
    }
}
