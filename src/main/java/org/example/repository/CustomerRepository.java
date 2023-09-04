package org.example.repository;

import org.example.entity.Customer;

import java.sql.SQLException;

public interface CustomerRepository {

    void createCustomer(Customer customer) throws SQLException;
    Customer getCustomerById(int id) throws SQLException;
    void updateCustomer(Customer customer) throws SQLException;

    void deleteCustomer(int customerId) throws SQLException;
}
