package com.polat.bookClubManagement.main.lib.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCredentialDAL {

    private Connection conn;

    public UserCredentialDAL(Connection conn) {
        this.conn = conn;
    }

    public boolean loginUser(String username, String password) {
        String sql = "SELECT * FROM user_credential WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean registerUser(String username, String password) {
        String sql = "INSERT INTO user_credential(username, password) VALUES(?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
