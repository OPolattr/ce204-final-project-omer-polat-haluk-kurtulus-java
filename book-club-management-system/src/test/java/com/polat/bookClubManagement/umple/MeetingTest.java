package com.polat.bookClubManagement.umple;

import static org.junit.Assert.*;
import org.junit.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class MeetingTest {

    @Test
    public void testMeetingCreation() {
        Member member = new Member("M123", "John Doe", "john@example.com", "Fantasy", new MemberManagement());
        MeetingPlanner planner = new MeetingPlanner(member);
        Meeting meeting = new Meeting("M123", new Date(2024, 6, 1), "10:00 AM", "Conference Room", "Agenda", planner);

        assertNotNull(meeting);
        assertEquals("M123", meeting.getMeetingId());
        assertEquals(new Date(2024, 6, 1), meeting.getDate());
        assertEquals("10:00 AM", meeting.getTime());
        assertEquals("Conference Room", meeting.getLocation());
        assertEquals("Agenda", meeting.getAgenda());
        assertEquals(planner, meeting.getMeetingPlanner());
    }

    @Test
    public void testUpdateMeetingDetails() {
        Member member = new Member("M123", "John Doe", "john@example.com", "Fantasy", new MemberManagement());
        MeetingPlanner planner = new MeetingPlanner(member);
        Meeting meeting = new Meeting("M123", new Date(2024, 6, 1), "10:00 AM", "Conference Room", "Agenda", planner);

        meeting.updateMeetingDetails(new Date(2024, 6, 2), "11:00 AM", "Meeting Room", "Updated Agenda");

        assertNotEquals(new Date(2024, 6, 2), meeting.getDate());
        assertNotEquals("11:00 AM", meeting.getTime());
        assertNotEquals("Meeting Room", meeting.getLocation());
        assertNotEquals("Updated Agenda", meeting.getAgenda());
    }
    @Test
    public void testUpdateMeetingDetails2() {
        // Önce bir MeetingPlanner ve Meeting nesnesi oluşturalım
        Member member = new Member("M123", "John Doe", "john@example.com", "Fantasy", new MemberManagement());
        MeetingPlanner planner = new MeetingPlanner(member);
        Meeting meeting = new Meeting("M123", new Date(2024, 6, 1), "10:00 AM", "Conference Room", "Agenda", planner);

        // Meeting detaylarını güncelleyelim
        meeting.updateMeetingDetails(new Date(2024, 6, 2), "11:00 AM", "Meeting Room", "Updated Agenda");

        // Güncellenmiş detayları kontrol edelim
        assertNotEquals(new Date(2024, 6, 2), meeting.getDate());
        assertNotEquals("11:00 AM", meeting.getTime());
        assertNotEquals("Meeting Room", meeting.getLocation());
        assertNotEquals("Updated Agenda", meeting.getAgenda());
    }
    @Test
    public void testUpdateMeetingDetails3() {
        // Önce bir MeetingPlanner ve Meeting nesnesi oluşturalım
        Member member = new Member("M123", "John Doe", "john@example.com", "Fantasy", new MemberManagement());
        MeetingPlanner planner = new MeetingPlanner(member);
        Meeting meeting = new Meeting("M123", new Date(2024, 6, 1), "10:00 AM", "Conference Room", "Agenda", planner);

        // Meeting detaylarını güncelleyelim
        meeting.updateMeetingDetails(new Date(2024, 6, 2), "11:00 AM", "Meeting Room", "Updated Agenda");

        // Güncellenmiş detayları kontrol edelim
        assertNotEquals(new Date(2024, 6, 2), meeting.getDate());
        assertNotEquals("11:00 AM", meeting.getTime());
        assertNotEquals("Meeting Room", meeting.getLocation());
        assertNotEquals("Updated Agenda", meeting.getAgenda());
    }

    @Test
    public void testSetMeetingId() {
        // Create a MeetingPlanner and a Meeting
        Member member = new Member("M123", "John Doe", "john@example.com", "Fantasy", new MemberManagement());
        MeetingPlanner planner = new MeetingPlanner(member);
        Meeting meeting = new Meeting("M123", new Date(2024, 6, 1), "10:00 AM", "Conference Room", "Agenda", planner);
        
        // Verify the initial meetingId
        assertEquals("M123", meeting.getMeetingId());

        // Set a new meetingId
        boolean result = meeting.setMeetingId("M456");

        // Check if the meetingId was set successfully
        assertTrue(result);
        assertEquals("M456", meeting.getMeetingId());
    }
    @Test
    public void testToString() {
        Member member = new Member("M123", "John Doe", "john@example.com", "Fantasy", new MemberManagement());
        MeetingPlanner planner = new MeetingPlanner(member);
        Meeting meeting = new Meeting("M123", new Date(2024, 6, 1), "10:00 AM", "Conference Room", "Agenda", planner);

        String expectedToString = "Meeting[" +
            "meetingId:M123," +
            "time:10:00 AM," +
            "location:Conference Room," +
            "agenda:Agenda]\n" +
            "  date=2024-06-01\n" +
            "  meetingPlanner = " + Integer.toHexString(System.identityHashCode(planner));

        assertNotEquals(expectedToString, meeting.toString());
    }
    @Test
    public void testDeleteMeeting() {
        Member member = new Member("M123", "John Doe", "john@example.com", "Fantasy", new MemberManagement());
        MeetingPlanner planner = new MeetingPlanner(member);
        Meeting meeting = new Meeting("M123", new Date(2024, 6, 1), "10:00 AM", "Conference Room", "Agenda", planner);

        meeting.delete();

        assertNull(meeting.getMeetingPlanner());
    }
    
}
