package org.example.services;

import org.example.db.DbConnection;
import org.example.entity.Customer;
import org.example.repository.TransactionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class TransactionService implements TransactionRepository {
    @Override
    public void deposite(Customer customer, Double amount) throws SQLException {
        try (Connection conn = DbConnection.provideConnection()) {
            String sql = "UPDATE customers SET balance = ? WHERE id = ?";
            customer.getCustomerBalance();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            ps.setDouble(2, customer.getCustomerBalance());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void withdraw(Customer customer, Double amount) throws SQLException {
        try (Connection conn = DbConnection.provideConnection()) {
            if(customer.getCustomerBalance() >= amount){
                String sql ="UPDATE customers SET balance =? WHERE id ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, customer.getId());
                ps.setDouble(2, customer.getCustomerBalance());
                ps.executeUpdate();
            }
        }catch (Exception e){
            System.out.println("Lack of funds");
        }
    }

    @Override
    public void saveTransaction(double amount, int bankFrom, int bankTo) throws SQLException {
        try (Connection conn = DbConnection.provideConnection()) {
            UUID uuid = UUID.randomUUID();
            String sql = "INSERT INTO transactions (uuid, customer_id, amount) VALUES (?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, uuid);
            ps.setInt(2,bankFrom);
            ps.setDouble(3,amount);
            ps.executeUpdate();
            conn.close();

            new CheckService().generationCheck(uuid);

        }catch (Exception e){
            System.out.println("Error of save");
        }

    }
}
