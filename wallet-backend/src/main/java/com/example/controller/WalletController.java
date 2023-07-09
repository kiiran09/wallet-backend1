package com.example.controller;

import com.example.entity.Transaction;
import com.example.model.AddMoneyRequest;
import com.example.model.WalletBalance;
import com.example.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/balance")
    public ResponseEntity<WalletBalance> getBalance() {
        WalletBalance walletBalance = walletService.getWalletBalance();
        return ResponseEntity.ok(walletBalance);
    }

    @PostMapping("/add-money")
    public ResponseEntity<Void> addMoney(@RequestBody AddMoneyRequest request) {
        double amount = request.getAmount();
        walletService.addMoney(amount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }




    @PostMapping("/withdraw-money")
    public ResponseEntity<String> withdrawMoney(@RequestBody double amount) {
        boolean success = walletService.withdrawMoney(amount);
        if (success) {
            return ResponseEntity.ok("Amount withdrawn successfully.");
        } else {
            return ResponseEntity.badRequest().body("Insufficient funds.");
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory() {
        List<Transaction> transactionHistory = walletService.getTransactionHistory();
        return ResponseEntity.ok(transactionHistory);
    }
}
