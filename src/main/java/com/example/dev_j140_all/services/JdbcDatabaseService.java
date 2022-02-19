package com.example.dev_j140_all.services;

import com.example.dev_j140_all.database.DatabaseStorage;
import com.example.dev_j140_all.models.User;

import java.sql.*;

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
    public boolean saveUser(User user)  {
        String sql = "INSERT INTO devj140.users VALUES (?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("could not save User");
            return false;
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null && connection.isValid(0)) connection.close();
        if (statement != null) statement.close();
        if (resultSet != null) resultSet.close();
        System.out.println("Соединение с БД закрыто");
    }
}
