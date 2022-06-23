package com.projekt.PaliwoApp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Receipt> receiptList = new ArrayList<>();

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String pass) {
        this.password = pass;
    }
}
