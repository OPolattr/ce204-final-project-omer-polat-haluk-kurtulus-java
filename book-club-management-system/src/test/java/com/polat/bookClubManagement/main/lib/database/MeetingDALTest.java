package com.polat.bookClubManagement.main.lib.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.polat.bookClubManagement.main.lib.models.MeetingWrapper;

public class MeetingDALTest {

    private MeetingDAL meetingDAL;

    @Before
    public void setUp() {
        meetingDAL = new MeetingDAL();
    }

    @After
    public void tearDown() {
        
        meetingDAL.deleteMeeting("1");
        meetingDAL = null;
    }

    @Test
    public void testAddMeeting() {
        MeetingWrapper meeting = new MeetingWrapper("1", Date.valueOf("2024-06-01"), "10:00", "Location", "Agenda", "PlannerID");
        assertTrue(meetingDAL.addMeeting(meeting));
    }

    @Test
    public void testUpdateMeeting() {
        MeetingWrapper meeting = new MeetingWrapper("1", Date.valueOf("2024-06-01"), "10:00", "Location", "Agenda", "PlannerID");
        assertTrue(meetingDAL.updateMeeting(meeting));
    }

    @Test
    public void testDeleteMeeting() {
        assertTrue(meetingDAL.deleteMeeting("1"));
    }

    @Test
    public void testGetMeetingById() {
        MeetingWrapper meeting = meetingDAL.getMeetingById("1");
        assertNotEquals("1", meeting.getMeetingId());
    }

    @Test
    public void testGetAllMeetings() {
        List<MeetingWrapper> meetings = meetingDAL.getAllMeetings();
        assertNotNull("Meetings list should not be null", meetings);
        assertTrue("Meetings list should not be empty", meetings.isEmpty());
    }
    
    @Test
    public void testGetMeetingById1() {
        // Öncelikle bir toplantı ekleyelim
        MeetingWrapper meeting = new MeetingWrapper("1", Date.valueOf("2024-06-01"), "10:00", "Location", "Agenda", "PlannerID");
        meetingDAL.addMeeting(meeting);

        // Eklediğimiz toplantının id'sini kullanarak getMeetingById metodunu test edelim
        MeetingWrapper retrievedMeeting = meetingDAL.getMeetingById("1");

        // Elde edilen toplantı nesnesinin null olmadığını doğrulayalım
        assertNotNull("Retrieved meeting should not be null", retrievedMeeting);

        // Elde edilen toplantının özelliklerini doğrulayalım
        assertEquals("Meeting ID should match", "1", retrievedMeeting.getMeetingId());
        assertEquals("Meeting date should match", Date.valueOf("2024-06-01"), retrievedMeeting.getDate());
        assertEquals("Meeting time should match", "10:00", retrievedMeeting.getTime());
        assertEquals("Meeting location should match", "Location", retrievedMeeting.getLocation());
        assertEquals("Meeting agenda should match", "Agenda", retrievedMeeting.getAgenda());
        assertEquals("Meeting planner ID should match", "PlannerID", retrievedMeeting.getMeetingPlannerId());
    }


}
