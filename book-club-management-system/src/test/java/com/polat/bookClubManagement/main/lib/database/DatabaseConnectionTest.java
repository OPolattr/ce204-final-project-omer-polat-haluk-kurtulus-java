package com.polat.bookClubManagement.main.lib.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DatabaseConnectionTest {

    @Test
    public void testConnection() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            assertNotNull(connection);
            assertTrue(connection.isValid(1));
            connection.close();
        } catch (SQLException e) {
            fail("Connection failed: " + e.getMessage());
        }
    }

    @Test
    public void testTableCreation() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user_credential'");
            assertTrue(resultSet.next()); // Check if user_credential table exists
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='member'");
            assertTrue(resultSet.next()); // Check if member table exists
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='reading_schedule'");
            assertTrue(resultSet.next()); // Check if reading_schedule table exists
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='reading_entry'");
            assertTrue(resultSet.next()); // Check if reading_entry table exists
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='meeting_planner'");
            assertTrue(resultSet.next()); // Check if meeting_planner table exists
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='meeting'");
            assertTrue(resultSet.next()); // Check if meeting table exists
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='discussion_forum'");
            assertTrue(resultSet.next()); // Check if discussion_forum table exists
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='topic'");
            assertTrue(resultSet.next()); // Check if topic table exists
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='response'");
            assertTrue(resultSet.next()); // Check if response table exists
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='like'");
            assertTrue(resultSet.next()); // Check if like table exists
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='follower'");
            assertTrue(resultSet.next()); // Check if follower table exists
        } catch (SQLException e) {
            fail("Table creation test failed: " + e.getMessage());
        }
    }
}
