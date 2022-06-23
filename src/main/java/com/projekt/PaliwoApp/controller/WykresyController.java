package com.projekt.PaliwoApp.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.projekt.PaliwoApp.config.UserPrincipal;
import com.projekt.PaliwoApp.model.Receipt;
import com.projekt.PaliwoApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class WykresyController {
    
    @Autowired
    private com.example.demo.repository.ReceiptRepository receiptRepository;

    public JsonObject RachunekToJson(Receipt rachunek){
        JsonObject rachunekJson = new JsonObject();
        JsonArray jsonItems = new JsonArray();
        rachunek.getItemList().forEach(item -> {
            JsonObject jsonItemObject = new JsonObject();
            jsonItemObject.addProperty("item", item.getNazwa());
            jsonItemObject.addProperty("amount", item.getIlosc());
            jsonItemObject.addProperty("cena", item.getCena());
            jsonItems.add(jsonItemObject);
        });
        rachunekJson.addProperty("seller", rachunek.getSprzedawca());
        rachunekJson.addProperty("address", rachunek.getMiejsce());
        rachunekJson.addProperty("date", rachunek.getData().toString());
        rachunekJson.add("items", jsonItems);
        return rachunekJson;
    }

    @PostMapping("/getall")
    @ResponseBody
    public String pobierzWszystkieJSONy(){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        JsonObject rachunek = new JsonObject();
        JsonArray receipts = new JsonArray();
        List<Receipt> rachunkiOdUsera = receiptRepository.findByUser(user);
        if (rachunkiOdUsera.size() != 0) {
            rachunkiOdUsera.forEach(receipt -> {
                receipts.add(RachunekToJson(receipt));
            });
        }
        rachunek.add("rachunki", receipts);
        rachunek.addProperty("email", user.getEmail());
        return rachunek.toString();
    }
}
