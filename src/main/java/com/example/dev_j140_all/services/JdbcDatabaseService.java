package com.example.dev_j140_all.services;

import com.example.dev_j140_all.database.DatabaseStorage;
import com.example.dev_j140_all.models.Account;
import com.example.dev_j140_all.models.User;

import java.sql.*;
import java.util.ArrayList;

public class JdbcDatabaseService implements DatabaseStorage, AutoCloseable {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public JdbcDatabaseService() throws SQLException {
        init();
        getConnection();
    }

    private void getConnection() throws SQLException {
        String[] conncetionData = PropertiesService.getPropertiesService().getConnectionData();
        connection = DriverManager.getConnection(conncetionData[0], conncetionData[1], conncetionData[2]);
    }

    private void init() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: driver could not load");
        }
    }

    @Override
    public boolean isLoginFree(String login) {
        String sql = "SELECT * FROM devj140.users WHERE login=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error: login error");
            return false;
        }
    }

    @Override
    public boolean saveUser(User user) {
        String sql = "INSERT INTO devj140.users VALUES (?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: could not save User");
            return false;
        }
    }

    @Override
    public boolean checkUserLoginAndPassword(String login, String password) {
        String sql = "SELECT * FROM devj140.users WHERE login=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String databasePassword = resultSet.getString(2);
                if (databasePassword.equals(password)) return true;
                else return false;
            } else return false;
        } catch (SQLException e) {
            System.out.println("Error: authorization error");
            return false;
        }
    }

    @Override
    public ArrayList<Account> loadAccounts() {
        ArrayList<Account> accountsArrayListFromDatabase = new ArrayList<>();
        String sql = "SELECT * FROM devj140.accounts";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Account tempAccount = new Account(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
                accountsArrayListFromDatabase.add(tempAccount);
            }
        } catch (SQLException e) {
            System.out.println("Error: could not load info from database");
        }
        return accountsArrayListFromDatabase;
    }

    @Override
    public void close() throws Exception {
        if (connection != null && connection.isValid(0)) connection.close();
        if (statement != null) statement.close();
        if (resultSet != null) resultSet.close();
        System.out.println("Соединение с БД закрыто");
    }
}
