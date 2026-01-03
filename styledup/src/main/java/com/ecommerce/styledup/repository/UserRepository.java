package com.ecommerce.styledup.repository;

import java.sql.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ecommerce.styledup.model.Product;
import com.ecommerce.styledup.model.User;


@Repository
public class UserRepository {

    //Database credentials 
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;


    //This method allows you to enter the database and use credentials to create new user objects
    public User findByUsername(String username) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE username=?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
        }
        return user;
    }

    public void AddUserToDataBase(User newUser) throws SQLException {
        String sql = "INSERT INTO USERS (username, password, email, role) VALUES (?, ?, ?, ?)";

        try(Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1,newUser.getUsername());
                stmt.setString(2, newUser.getPassword());
                stmt.setString(3, newUser.getEmail());
                stmt.setString(4, newUser.getRole());
                stmt.executeUpdate();
            }
    }


    public void AddProductToDataBase(Product newProduct) throws SQLException {
        String sql = "INSERT INTO PRODUCTS (name, description, price, stock_quantity) VALUES (?, ?, ?, ?)";

        try(Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1,newProduct.getName());
                stmt.setString(2, newProduct.getDescription());
                stmt.setBigDecimal(3, newProduct.getPrice());
                stmt.setInt(4, newProduct.getStockQuantity());
                stmt.executeUpdate();
            }
    }
  
}
