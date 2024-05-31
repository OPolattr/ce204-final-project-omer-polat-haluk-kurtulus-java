package com.polat.bookClubManagement.main.lib.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:sqlite:bookclub.db";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                System.out.println("Connection to SQLite has been established.");

                try (Statement stmt = conn.createStatement()) {
                    // SQL script to create tables
                    String sql = "CREATE TABLE IF NOT EXISTS `user_credential` (" +
                            "username VARCHAR(255) PRIMARY KEY, " +
                            "password VARCHAR(255)" +
                            "); " +

                            "CREATE TABLE IF NOT EXISTS `member` (" +
                            "member_id VARCHAR(255) PRIMARY KEY, " +
                            "name VARCHAR(255), " +
                            "email VARCHAR(255), " +
                            "reading_preferences VARCHAR(255)" +
                            "); " +

                            "CREATE TABLE IF NOT EXISTS `reading_schedule` (" +
                            "member_member_id VARCHAR(255) PRIMARY KEY, " +
                            "FOREIGN KEY (member_member_id) REFERENCES `member`(member_id)" +
                            "); " +

                            "CREATE TABLE IF NOT EXISTS `reading_entry` (" +
                            "book_title VARCHAR(255) PRIMARY KEY, " +
                            "reading_schedule_member_member_id VARCHAR(255), " +
                            "start_date DATE, " +
                            "end_date DATE, " +
                            "progress VARCHAR(255), " +
                            "FOREIGN KEY (reading_schedule_member_member_id) REFERENCES `reading_schedule`(member_member_id)" +
                            "); " +

                            "CREATE TABLE IF NOT EXISTS `meeting_planner` (" +
                            "member_member_id VARCHAR(255) PRIMARY KEY, " +
                            "FOREIGN KEY (member_member_id) REFERENCES `member`(member_id)" +
                            "); " +

                            "CREATE TABLE IF NOT EXISTS `meeting` (" +
                            "meeting_id VARCHAR(255) PRIMARY KEY, " +
                            "meeting_planner_member_member_id VARCHAR(255), " +
                            "date DATE, " +
                            "time VARCHAR(255), " +
                            "location VARCHAR(255), " +
                            "agenda VARCHAR(255), " +
                            "FOREIGN KEY (meeting_planner_member_member_id) REFERENCES `meeting_planner`(member_member_id)" +
                            "); " +

                            "CREATE TABLE IF NOT EXISTS `discussion_forum` (" +
                            "member_member_id VARCHAR(255) PRIMARY KEY, " +
                            "FOREIGN KEY (member_member_id) REFERENCES `member`(member_id)" +
                            "); " +

                            "CREATE TABLE IF NOT EXISTS `topic` (" +
                            "topic_id VARCHAR(255) PRIMARY KEY, " +
                            "discussion_forum_member_member_id VARCHAR(255), " +
                            "title VARCHAR(255), " +
                            "content VARCHAR(255), " +
                            "author_id VARCHAR(255), " +
                            "creation_date DATE, " +
                            "FOREIGN KEY (discussion_forum_member_member_id) REFERENCES `discussion_forum`(member_member_id)" +
                            "); " +

                            "CREATE TABLE IF NOT EXISTS `response` (" +
                            "response_id VARCHAR(255) PRIMARY KEY, " +
                            "content VARCHAR(255), " +
                            "member_id VARCHAR(255), " +
                            "creation_date DATE" +
                            "); " +

                            "CREATE TABLE IF NOT EXISTS `like` (" +
                            "like_id VARCHAR(255) PRIMARY KEY, " +
                            "member_id VARCHAR(255), " +
                            "creation_date DATE" +
                            "); " +

                            "CREATE TABLE IF NOT EXISTS `follower` (" +
                            "follower_id VARCHAR(255) PRIMARY KEY, " +
                            "member_id VARCHAR(255), " +
                            "creation_date DATE" +
                            ");";

                    stmt.executeUpdate(sql);
                    System.out.println("Tables have been created.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
