package com.polat.bookClubManagement.main.lib.database;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserCredentialDALTest {

    private Connection conn;
    private UserCredentialDAL userCredentialDAL;

    @Before
    public void setUp() {
        String url = "jdbc:sqlite:bookclub.db";
        
        try {
            conn = DriverManager.getConnection(url);
            userCredentialDAL = new UserCredentialDAL(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        userCredentialDAL.registerUser("newuser", "newpassword");
        
    }

    @After
    public void tearDown() {
        try {
            // Veritabanındaki değişiklikleri sil
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM follower")) {
                statement.executeUpdate();
            }

            // "omer" ve "haluk" kullanıcılarını yeniden kaydet
            userCredentialDAL.registerUser("omer", "omer");
            userCredentialDAL.registerUser("haluk", "12345");

            // Bağlantıyı kapat
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoginUser() {
        assertTrue(userCredentialDAL.loginUser("newuser", "newpassword"));
    }
}
