package com.example.service;

import com.example.entity.Transaction;
import com.example.model.WalletBalance;
import com.example.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public double getBalance() {
        return walletRepository.getBalance();
    }

    @Override
    public void addMoney(double amount) {
        walletRepository.updateBalance(amount);
        walletRepository.save(new Transaction("Deposit", amount));
    }

    @Override
    public boolean withdrawMoney(double amount) {
        if (walletRepository.getBalance() >= amount) {
            walletRepository.updateBalance(-amount);
            walletRepository.save(new Transaction("Withdrawal", amount));
            return true;
        }
        return false;
    }

    @Override
    public List<Transaction> getTransactionHistory() {
        return walletRepository.getTransactionHistory();
    }

    @Override
    public WalletBalance getWalletBalance() {
        WalletBalance walletBalance = new WalletBalance();
        walletBalance.setBalance(walletRepository.getBalance());
        return walletBalance;
    }
}
