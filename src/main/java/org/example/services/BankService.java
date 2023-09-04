package org.example.services;

import org.example.db.DbConnection;
import org.example.entity.Bank;
import org.example.repository.BankRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankService implements BankRepository {
    @Override
    public List<Bank> getAllBanks() throws SQLException {
        try (Connection conn = DbConnection.provideConnection()) {
            String sql = "SELECT * FROM banks";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Bank> list = new ArrayList<>();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Bank bank = new Bank(rs.getInt(1), rs.getString(2));
                    list.add(bank);
                }
            } else {
                System.out.println("List of banks is empty");
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public String getBankNameById(int id) throws SQLException {
        try (Connection conn = DbConnection.provideConnection()) {
            String sql = "SELECT * FROM banks WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String bankName = null;
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    bankName = (rs.getString(2));
                }
            } else {
                System.out.println("Bank not found");
            }
            return bankName;
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

    @Override
    public void createBank(Bank bank) throws SQLException {
        try (Connection conn = DbConnection.provideConnection()) {
            String sql = "INSERT INTO banks(id, bank_name) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bank.getId());
            ps.setString(2, bank.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void updateBank(Bank bank) throws SQLException {
        try (Connection conn = DbConnection.provideConnection()) {
            String sql = "UPDATE banks SET bank_name = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bank.getId());
            ps.setString(2, bank.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteBank(int bankId) throws SQLException {
        try (Connection conn = DbConnection.provideConnection()) {
            String sql = "DELETE FROM banks WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bankId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
