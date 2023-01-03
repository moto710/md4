package com.bankingtransaction.service;

import com.bankingtransaction.model.Customer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService extends RootDAO implements IService<Customer> {
    private static final String FIND_MAX_ID = "SELECT IFNULL(MAX(`id`), 0) FROM `customer`;";
    private static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM `customer` WHERE `id` = ?;";
    private static final String UPDATE_CUSTOMER = "UPDATE `customer` SET `name` = ?, balance = ?, email = ?, address = ?, phone = ?, created_at = ?, updated_at = ? WHERE `id` = ?;";
    private static final String INSERT_CUSTOMER = "INSERT INTO `customer` (`id`, `name`, `email`, `address`, `phone`, `created_at`) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM `customer` WHERE id = ?;";
    private static final String SELECT_ALL_CUSTOMER = "SELECT * FROM `customer`;";
    private Customer customer;
    private List<Customer> customerList;

    @Override
    public List<Customer> findAll() {
        customerList = new ArrayList<>();
        try {
            preparedStatement = startConnect(SELECT_ALL_CUSTOMER);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float balance = rs.getFloat("balance");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String createdAt = rs.getString("created_at");
                String updatedAt = rs.getString("updated_at");
                customerList.add(new Customer(id, name, balance, email, address, phone, createdAt, updatedAt));
            }
            System.out.println(this.getClass() + " findAll: " + preparedStatement);
            closeConnect();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customerList;
    }

    @Override
    public Customer findById(int id) {
        try {
            preparedStatement = startConnect(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                float balance = rs.getFloat("balance");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String createdAt = rs.getString("created_at");
                String updatedAt = rs.getString("updated_at");
                customer = new Customer(id, name, balance, email, address, phone, createdAt, updatedAt);
            }
            System.out.println(this.getClass() + " findById: " + preparedStatement);
            closeConnect();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    @Override
    public void add(Customer customer) {
        try {
            preparedStatement = startConnect(INSERT_CUSTOMER);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getCreatedAt());
            preparedStatement.executeUpdate();
            System.out.println(this.getClass() + " add: " + preparedStatement);
            closeConnect();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void update(Customer customer) {
        try {
            preparedStatement = startConnect(UPDATE_CUSTOMER);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setFloat(2, customer.getBalance());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getCreatedAt());
            preparedStatement.setString(7, customer.getUpdatedAt());
            preparedStatement.setInt(8, customer.getId());
            System.out.println(this.getClass() + " update: " + preparedStatement);
            closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try {
            preparedStatement = startConnect(DELETE_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(this.getClass() + " remove: " + preparedStatement);
            closeConnect();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public int findMaxId() {
        try {
            preparedStatement = startConnect(FIND_MAX_ID);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            System.out.println(this.getClass() + " findMaxId: " + preparedStatement);
            closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}
