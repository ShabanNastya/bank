package org.example.repository;

import org.example.entity.Customer;
import org.example.entity.Transaction;

import java.sql.SQLException;
import java.util.UUID;

public interface TransactionRepository {
    void deposite(Customer customer, Double amount) throws SQLException;

    void withdraw(Customer customer, Double amount) throws SQLException;

    void saveTransaction(double amount, int bankFrom, int bankTo) throws SQLException;
}
