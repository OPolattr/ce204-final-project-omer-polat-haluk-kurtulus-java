package com.polat.bookClubManagement.main.lib.database;



import com.polat.bookClubManagement.main.lib.models.ReadingEntryWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadingEntryDAL {

    public boolean addReadingEntry(ReadingEntryWrapper readingEntry) {
        String sql = "INSERT INTO reading_entry(book_title, reading_schedule_member_member_id, start_date, end_date, progress) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, readingEntry.getBookTitle());
            pstmt.setString(2, readingEntry.getReadingScheduleMemberId());
            pstmt.setDate(3, readingEntry.getStartDate());
            pstmt.setDate(4, readingEntry.getEndDate());
            pstmt.setString(5, readingEntry.getProgress());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ReadingEntryWrapper getReadingEntry(String bookTitle) {
        String sql = "SELECT book_title, reading_schedule_member_member_id, start_date, end_date, progress FROM reading_entry WHERE book_title = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bookTitle);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String readingScheduleMemberId = rs.getString("reading_schedule_member_member_id");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                String progress = rs.getString("progress");

                return new ReadingEntryWrapper(bookTitle, startDate, endDate, progress, readingScheduleMemberId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<ReadingEntryWrapper> getAllReadingEntries() {
        String sql = "SELECT book_title, reading_schedule_member_member_id, start_date, end_date, progress FROM reading_entry";
        List<ReadingEntryWrapper> readingEntries = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String bookTitle = rs.getString("book_title");
                String readingScheduleMemberId = rs.getString("reading_schedule_member_member_id");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                String progress = rs.getString("progress");

                readingEntries.add(new ReadingEntryWrapper(bookTitle, startDate, endDate, progress, readingScheduleMemberId));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return readingEntries;
    }

    public boolean updateReadingEntry(ReadingEntryWrapper readingEntry) {
        String sql = "UPDATE reading_entry SET reading_schedule_member_member_id = ?, start_date = ?, end_date = ?, progress = ? WHERE book_title = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, readingEntry.getReadingScheduleMemberId());
            pstmt.setDate(2, readingEntry.getStartDate());
            pstmt.setDate(3, readingEntry.getEndDate());
            pstmt.setString(4, readingEntry.getProgress());
            pstmt.setString(5, readingEntry.getBookTitle());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteReadingEntry(String bookTitle) {
        String sql = "DELETE FROM reading_entry WHERE book_title = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bookTitle);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

 

}
