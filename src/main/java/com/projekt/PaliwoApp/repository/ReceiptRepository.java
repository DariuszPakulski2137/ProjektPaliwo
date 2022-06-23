package com.example.demo.repository;

import com.example.demo.model.Receipt;
import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
    List<Receipt> findByUser(User user);
}
