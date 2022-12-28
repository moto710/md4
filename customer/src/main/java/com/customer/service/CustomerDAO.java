package com.customer.service;

import com.customer.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerDAO extends RootDAO implements IDAO<Customer> {
    private static final String FIND_MAX_ID = "SELECT MAX(id) from users;";
    private static final String UPDATE_CUSTOMER = "UPDATE users SET name = ?, email = ?, idCountry = ? WHERE id = ?;";
    private static final String DELETE_CUSTOMER = "DELETE FROM users WHERE id = ?;";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM users WHERE id = ?;";

    @Override
    public void insert(Customer customer) {
        
    }

    @Override
    public Customer select(int id) { //SELECT * FROM users WHERE id = ?;
        try {
            preparedStatement = startConnect(SELECT_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                int idCountry = rs.getInt("idCountry");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public List<Customer> selectAll() {
        return null;
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
            preparedStatement.setInt(3, customer.getIdCountry());
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
