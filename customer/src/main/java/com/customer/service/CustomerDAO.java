package com.customer.service;

import com.customer.model.Country;
import com.customer.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends RootDAO implements IDAO<Customer> {
    private static final String FIND_MAX_ID = "SELECT MAX(id) from users;";
    private static final String UPDATE_CUSTOMER = "UPDATE users SET name = ?, email = ?, idCountry = ? WHERE id = ?;";
    private static final String DELETE_CUSTOMER = "DELETE FROM users WHERE id = ?;";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM users WHERE id = ?;";
    private static final String INSERT_CUSTOMER = "INSERT INTO users VALUES (?, ?, ?, ?);";
    private static final String SELECT_ALL_CUSTOMER = "SELECT * FROM users";
    private Customer customer;
    private List<Customer> customerList;

    @Override
    public void insert(Customer customer) {
        try {
            preparedStatement = startConnect(INSERT_CUSTOMER);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setObject(4, customer.getCountry());
            preparedStatement.executeUpdate();
            System.out.println(this.getClass() + " insert: " + preparedStatement);
            closeConnect();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Customer select(int id) {
        try {
            preparedStatement = startConnect(SELECT_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setId(id);
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setCountry((Country) rs.getObject("Country"));
            }
            System.out.println(this.getClass() + " select: " + preparedStatement);
            closeConnect();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> selectAll() {
        customerList = new ArrayList<>();
        try {
            preparedStatement = startConnect(SELECT_ALL_CUSTOMER);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                customerList.add(getCustomerFromRS(rs));
            }
            System.out.println(this.getClass() + " selectAll: " + preparedStatement);
            closeConnect();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customerList;
    }
    public Customer getCustomerFromRS(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        Country country = (Country) rs.getObject("country");
        return new Customer(id, name, email, country);
    }

    @Override
    public boolean delete(int id){
        boolean rowDelete = false;
        try {
            preparedStatement = startConnect(DELETE_CUSTOMER);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
            System.out.println(this.getClass() + " delete: " + preparedStatement);
            closeConnect();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowDelete;
    }

    @Override
    public boolean update(Customer customer) {
        boolean rowUpdated;
        try {
            preparedStatement = startConnect(UPDATE_CUSTOMER);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setObject(3, customer.getCountry());
            preparedStatement.setInt(4, customer.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
            System.out.println(this.getClass() + " update: " + preparedStatement);
            closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
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
