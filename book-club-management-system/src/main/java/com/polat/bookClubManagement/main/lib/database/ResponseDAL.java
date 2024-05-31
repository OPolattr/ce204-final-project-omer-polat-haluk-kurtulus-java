package com.polat.bookClubManagement.main.lib.database;

import com.polat.bookClubManagement.main.lib.models.ResponseWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResponseDAL {

    public boolean addResponse(ResponseWrapper response) {
        String sql = "INSERT INTO response(response_id, content, member_id, creation_date) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, response.getResponseId());
            pstmt.setString(2, response.getContent());
            pstmt.setString(3, response.getMemberId());
            pstmt.setDate(4, response.getCreationDate());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ResponseWrapper getResponse(String responseId) {
        String sql = "SELECT response_id, content, member_id, creation_date FROM response WHERE response_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, responseId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String content = rs.getString("content");
                String memberId = rs.getString("member_id");
                Date creationDate = rs.getDate("creation_date");

                return new ResponseWrapper(responseId, content, memberId, creationDate);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<ResponseWrapper> getAllResponses() {
        String sql = "SELECT response_id, content, member_id, creation_date FROM response";
        List<ResponseWrapper> responses = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String responseId = rs.getString("response_id");
                String content = rs.getString("content");
                String memberId = rs.getString("member_id");
                Date creationDate = rs.getDate("creation_date");

                responses.add(new ResponseWrapper(responseId, content, memberId, creationDate));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return responses;
    }

    public boolean updateResponse(ResponseWrapper response) {
        String sql = "UPDATE response SET content = ?, member_id = ?, creation_date = ? WHERE response_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, response.getContent());
            pstmt.setString(2, response.getMemberId());
            pstmt.setDate(3, response.getCreationDate());
            pstmt.setString(4, response.getResponseId());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteResponse(String responseId) {
        String sql = "DELETE FROM response WHERE response_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, responseId);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
