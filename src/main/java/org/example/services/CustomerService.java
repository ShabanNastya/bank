package org.example.services;

import org.example.db.DbConnection;
import org.example.entity.Customer;
import org.example.repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService implements CustomerRepository {
    @Override
    public void createCustomer(Customer customer) throws SQLException {
        try (Connection conn = DbConnection.provideConnection()) {
            String sql = "INSERT into customers (customer_id, firstname, lastname, customerBalance) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getLastName());
            ps.setDouble(4, customer.getCustomerBalance());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Customer getCustomerById(int id) throws SQLException {
        Connection conn = DbConnection.provideConnection();
        String sql = "SELECT * FROM customers WHERE customer_id = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Customer getCustomer = null;
        if (rs.isBeforeFirst()) {
            while (rs.next()) {
                getCustomer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
            }
        } else {
            System.out.println("Customer not found");
        }
        return getCustomer;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        try (Connection conn = DbConnection.provideConnection()) {
            String sql = "UPDATE customers SET firstname = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            ps.setString(2, customer.getFirstName());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        try (Connection conn = DbConnection.provideConnection()) {
            String sql = "DELETE FROM customers WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
