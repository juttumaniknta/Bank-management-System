package bank_management_System;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
	
	    private String accountHolder;
	    private double balance;
	    private List<Transaction> transactionHistory;

	    // Inner class to store transaction details
	    private static class Transaction {
	        String type;
	        double amount;
	        double balance;
	        LocalDateTime date;

	        Transaction(String type, double amount, double balance, LocalDateTime date) {
	            this.type = type;
	            this.amount = amount;
	            this.balance = balance;
	            this.date = date;
	        }

	        @Override
	        public String toString() {
	            return String.format("%s: %s $%.2f, Balance: $%.2f, Date: %s",
	                    type, type.equals("deposit") ? "Deposited" : "Withdrew",
	                    amount, balance, date);
	        }
	    }

	    // Constructor
	    public Account(String accountHolder, double initialBalance) {
	        this.accountHolder = accountHolder;
	        this.balance = initialBalance;
	        this.transactionHistory = new ArrayList<>();
	    }

	    // Deposit method
	    public String deposit(double amount) {
	        if (amount > 0) {
	            balance += amount;
	            transactionHistory.add(new Transaction("deposit", amount, balance, LocalDateTime.now()));
	            return String.format("Deposited $%.2f. New balance: $%.2f", amount, balance);
	        }
	        return "Invalid deposit amount";
	    }

	    // Withdraw method
	    public String withdraw(double amount) {
	        if (amount > 0) {
	            if (amount <= balance) {
	                balance -= amount;
	                transactionHistory.add(new Transaction("withdrawal", amount, balance, LocalDateTime.now()));
	                return String.format("Withdrew $%.2f. New balance: $%.2f", amount, balance);
	            }
	            return "Insufficient funds";
	        }
	        return "Invalid withdrawal amount";
	    }

	    // Get balance
	    public String getBalance() {
	        return String.format("Current balance: $%.2f", balance);
	    }

	    // Get transaction history
	    public String getTransactionHistory() {
	        if (transactionHistory.isEmpty()) {
	            return "No transactions yet";
	        }
	        StringBuilder history = new StringBuilder("Transaction History:\n");
	        for (Transaction transaction : transactionHistory) {
	            history.append(transaction).append("\n");
	        }
	        return history.toString();
	    }

	    // Main method for testing
	    public static void main(String[] args) {
	        Account account = new Account("manikanta", 1111);
	        System.out.println(account.deposit(501.00));
	        System.out.println(account.withdraw(201.00));
	        System.out.println(account.withdraw(2000.00)); // Should fail
	        System.out.println(account.getBalance());
	        System.out.println(account.getTransactionHistory());
	    }
	}


