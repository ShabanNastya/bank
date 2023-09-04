package org.example.repository;

import org.example.entity.Bank;

import java.sql.SQLException;
import java.util.List;

public interface BankRepository {
    List<Bank> getAllBanks() throws SQLException;

    String getBankNameById(int id) throws SQLException;

    void createBank(Bank bank) throws SQLException;

    void updateBank(Bank bank) throws SQLException;

    void deleteBank(int bankId) throws SQLException;
}
