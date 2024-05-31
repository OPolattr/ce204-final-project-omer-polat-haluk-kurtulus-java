package com.polat.bookClubManagement.main.lib.models;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import com.polat.bookClubManagement.main.lib.models.MeetingWrapper;

public class MeetingWrapperTest {

	@Test
    public void testGettersAndSetters() {
        String meetingId = "123";
        String time = "10:00";
        String location = "Conference Room";
        String agenda = "Discuss upcoming events";
        String meetingPlannerId = "456";

        MeetingWrapper meeting = new MeetingWrapper(meetingId, null, time, location, agenda, meetingPlannerId);

        // Test getters
        assertEquals(meetingId, meeting.getMeetingId());
        assertEquals(time, meeting.getTime());
        assertEquals(location, meeting.getLocation());
        assertEquals(agenda, meeting.getAgenda());
        assertEquals(meetingPlannerId, meeting.getMeetingPlannerId());

        // Test setters
        String newMeetingId = "789";
        String newTime = "11:00";
        String newLocation = "Meeting Room";
        String newAgenda = "Plan next quarter events";
        String newMeetingPlannerId = "101";

        meeting.setMeetingId(newMeetingId);
        meeting.setTime(newTime);
        meeting.setLocation(newLocation);
        meeting.setAgenda(newAgenda);
        meeting.setMeetingPlannerId(newMeetingPlannerId);

        assertEquals(newMeetingId, meeting.getMeetingId());
        assertEquals(newTime, meeting.getTime());
        assertEquals(newLocation, meeting.getLocation());
        assertEquals(newAgenda, meeting.getAgenda());
        assertEquals(newMeetingPlannerId, meeting.getMeetingPlannerId());
    }

    @Test
    public void testToString() {
        String meetingId = "123";
        String time = "10:00";
        String location = "Conference Room";
        String agenda = "Discuss upcoming events";
        String meetingPlannerId = "456";

        MeetingWrapper meeting = new MeetingWrapper(meetingId, null, time, location, agenda, meetingPlannerId);

        String expectedToString = "MeetingWrapper{meetingId='123', date=null, time='10:00', location='Conference Room', agenda='Discuss upcoming events', meetingPlannerId='456'}";
        assertEquals(expectedToString, meeting.toString());
    }
    
    @Test
    public void testGetDate() {
        // Örnek bir tarih oluştur
        Date sampleDate = Date.valueOf("2024-05-30");

        // MeetingWrapper nesnesi oluştur ve setDate() kullanarak tarihi ayarla
        MeetingWrapper meeting = new MeetingWrapper("123", sampleDate, "10:00", "Conference Room", "Discuss upcoming events", "456");

        // getDate() kullanarak ayarlanan tarihi al
        Date retrievedDate = meeting.getDate();

        // Assert: Alınan tarihin beklenen tarih ile aynı olup olmadığını kontrol et
        assertEquals(sampleDate, retrievedDate);
    }

    @Test
    public void testSetDate() {
        // Örnek bir tarih oluştur
        Date sampleDate = Date.valueOf("2024-05-30");

        // MeetingWrapper nesnesi oluştur
        MeetingWrapper meeting = new MeetingWrapper("123", null, "10:00", "Conference Room", "Discuss upcoming events", "456");

        // setDate() kullanarak tarihi ayarla
        meeting.setDate(sampleDate);

        // Assert: getDate() ile alınan tarihin beklenen tarih ile aynı olup olmadığını kontrol et
        assertEquals(sampleDate, meeting.getDate());
    }

}
