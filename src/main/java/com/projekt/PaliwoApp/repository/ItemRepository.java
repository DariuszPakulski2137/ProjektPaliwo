package com.example.demo.repository;

import com.example.demo.model.Item;
import com.example.demo.model.Receipt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Receipt> findByReceipt(Receipt recceipt);
}
