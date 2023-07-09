package com.example.service;

import com.example.entity.Transaction;
import com.example.model.WalletBalance;

import java.util.List;

public interface WalletService {
    double getBalance();
    void addMoney(double amount);
    boolean withdrawMoney(double amount);
    List<Transaction> getTransactionHistory();
    WalletBalance getWalletBalance();
}
