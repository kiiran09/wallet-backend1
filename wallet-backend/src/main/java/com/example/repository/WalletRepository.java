package com.example.repository;

import com.example.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t")
    double getBalance();

    @Transactional
    @Modifying
    @Query("UPDATE Transaction t SET t.amount = t.amount + ?1 WHERE t.id = 1")
    void updateBalance(double amount);

    @Query("SELECT t FROM Transaction t ORDER BY t.id")
    List<Transaction> getTransactionHistory();
}
