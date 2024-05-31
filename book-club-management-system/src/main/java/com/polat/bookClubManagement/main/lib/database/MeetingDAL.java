package com.polat.bookClubManagement.main.lib.database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.polat.bookClubManagement.main.lib.models.MeetingWrapper;

public class MeetingDAL {

    public boolean addMeeting(MeetingWrapper meetingWrapper) {
        String sql = "INSERT INTO meeting (meeting_id, meeting_planner_member_member_id, date, time, location, agenda) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, meetingWrapper.getMeetingId());
            pstmt.setString(2, meetingWrapper.getMeetingPlannerId());
            pstmt.setDate(3, meetingWrapper.getDate());
            pstmt.setString(4, meetingWrapper.getTime());
            pstmt.setString(5, meetingWrapper.getLocation());
            pstmt.setString(6, meetingWrapper.getAgenda());
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateMeeting(MeetingWrapper meetingWrapper) {
        String sql = "UPDATE meeting SET date = ?, time = ?, location = ?, agenda = ? WHERE meeting_id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, meetingWrapper.getDate());
            pstmt.setString(2, meetingWrapper.getTime());
            pstmt.setString(3, meetingWrapper.getLocation());
            pstmt.setString(4, meetingWrapper.getAgenda());
            pstmt.setString(5, meetingWrapper.getMeetingId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteMeeting(String meetingId) {
        String sql = "DELETE FROM meeting WHERE meeting_id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, meetingId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public MeetingWrapper getMeetingById(String meetingId) {
        String sql = "SELECT * FROM meeting WHERE meeting_id = ?";
        MeetingWrapper meetingWrapper = null;

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, meetingId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                meetingWrapper = new MeetingWrapper(
                        rs.getString("meeting_id"),
                        rs.getDate("date"),
                        rs.getString("time"),
                        rs.getString("location"),
                        rs.getString("agenda"),
                        rs.getString("meeting_planner_member_member_id")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // Eğer meetingWrapper null ise, yeni bir MeetingWrapper nesnesi oluşturarak null hatasını önleyebiliriz.
        if (meetingWrapper == null) {
            meetingWrapper = new MeetingWrapper("", null, "", "", "", "");
        }
        
        return meetingWrapper;
    }


    public List<MeetingWrapper> getAllMeetings() {
        String sql = "SELECT * FROM meeting";
        List<MeetingWrapper> meetings = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                MeetingWrapper meetingWrapper = new MeetingWrapper(
                        rs.getString("meeting_id"),
                        rs.getDate("date"),
                        rs.getString("time"),
                        rs.getString("location"),
                        rs.getString("agenda"),
                        rs.getString("meeting_planner_member_member_id")
                );
                meetings.add(meetingWrapper);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return meetings;
    }
}
