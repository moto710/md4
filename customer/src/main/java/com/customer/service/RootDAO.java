package com.customer.service;

import java.sql.*;

public class RootDAO {
    protected String jdbcURL = "jdbc:mysql://localhost:3306/users_manager?useSSL=false";
    protected String jdbcUsername = "root";
    protected String jdbcPassword = "123456789";
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet rs;

    protected void closeConnect() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
    protected PreparedStatement startConnect(String SQLQuery) throws SQLException {
        connection = getConnection();
        return connection.prepareStatement(SQLQuery);
    }
    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    protected void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
