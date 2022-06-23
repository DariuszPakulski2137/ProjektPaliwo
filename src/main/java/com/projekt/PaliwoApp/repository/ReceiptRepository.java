package com.projekt.PaliwoApp.repository;

import com.projekt.PaliwoApp.model.Receipt;
import com.projekt.PaliwoApp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
    List<Receipt> findByUser(User user);
}