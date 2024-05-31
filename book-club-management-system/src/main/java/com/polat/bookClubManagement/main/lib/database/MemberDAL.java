package com.polat.bookClubManagement.main.lib.database;



import com.polat.bookClubManagement.main.lib.models.MemberWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAL {

    public boolean addMember(MemberWrapper memberWrapper) {
        String sql = "INSERT INTO member(member_id, name, email, reading_preferences) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberWrapper.getMemberId());
            pstmt.setString(2, memberWrapper.getName());
            pstmt.setString(3, memberWrapper.getEmail());
            pstmt.setString(4, memberWrapper.getReadingPreferences());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public MemberWrapper getMember(String memberId) {
        String sql = "SELECT member_id, name, email, reading_preferences FROM member WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String readingPreferences = rs.getString("reading_preferences");

                return new MemberWrapper(memberId, name, email, readingPreferences);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<MemberWrapper> getAllMembers() {
        String sql = "SELECT member_id, name, email, reading_preferences FROM member";
        List<MemberWrapper> members = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String memberId = rs.getString("member_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String readingPreferences = rs.getString("reading_preferences");

                members.add(new MemberWrapper(memberId, name, email, readingPreferences));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }

    public boolean updateMember(MemberWrapper memberWrapper) {
        String sql = "UPDATE member SET name = ?, email = ?, reading_preferences = ? WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberWrapper.getName());
            pstmt.setString(2, memberWrapper.getEmail());
            pstmt.setString(3, memberWrapper.getReadingPreferences());
            pstmt.setString(4, memberWrapper.getMemberId());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteMember(String memberId) {
        String sql = "DELETE FROM member WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
