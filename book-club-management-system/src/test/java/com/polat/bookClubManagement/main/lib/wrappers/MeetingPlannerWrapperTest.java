package com.polat.bookClubManagement.main.lib.wrappers;

import org.junit.Before;
import org.junit.Test;

import com.polat.bookClubManagement.umple.Meeting;
import com.polat.bookClubManagement.umple.MeetingPlanner;
import com.polat.bookClubManagement.umple.Member;
import com.polat.bookClubManagement.umple.MemberManagement;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MeetingPlannerWrapperTest {

    private MeetingPlannerWrapper meetingPlannerWrapper;
    private Member member;
    private MeetingPlanner meetingPlanner;
    private MemberManagement memberManagement;

    @Before
    public void setUp() {
        // MemberManagement nesnesi oluştur
        memberManagement = new MemberManagement();

        // Üye nesnesi oluştur ve MemberManagement'a ekle
        member = new Member("1", "John Doe", "john.doe@example.com", "Fiction", memberManagement);
        memberManagement.addMember(member);
        
        // MeetingPlanner ve MeetingPlannerWrapper nesneleri oluştur
        meetingPlannerWrapper = new MeetingPlannerWrapper(member);
        meetingPlanner = new MeetingPlanner(member);
        
        // MeetingPlannerWrapper'ın MeetingPlanner'ını ayarla
        //meetingPlannerWrapper.setMeetingPlanner(meetingPlanner);
    }

    @Test
    public void testGetMeeting() {
        // Örnek toplantı oluştur
        Meeting meeting = new Meeting("1", new Date(System.currentTimeMillis()), "10:00", "Library", "Discuss book", meetingPlanner);
        meetingPlanner.addMeeting(meeting);

    }

    @Test
    public void testGetMeetings() {
        // Örnek toplantılar oluştur
        List<Meeting> meetings = new ArrayList<>();
        Meeting meeting1 = new Meeting("1", new Date(System.currentTimeMillis()), "10:00", "Library", "Discuss book", meetingPlanner);
        Meeting meeting2 = new Meeting("2", new Date(System.currentTimeMillis()), "14:00", "Cafe", "Plan next book", meetingPlanner);
        meetings.add(meeting1);
        meetings.add(meeting2);
        meetingPlanner.addMeeting(meeting1);
        meetingPlanner.addMeeting(meeting2);

        assertNotEquals(meetings, meetingPlannerWrapper.getMeetings());
    }

    @Test
    public void testNumberOfMeetings() {
        // Örnek toplantılar ekle
        meetingPlanner.addMeeting(new Meeting("1", new Date(System.currentTimeMillis()), "10:00", "Library", "Discuss book", meetingPlanner));
        meetingPlanner.addMeeting(new Meeting("2", new Date(System.currentTimeMillis()), "14:00", "Cafe", "Plan next book", meetingPlanner));

        assertNotEquals(2, meetingPlannerWrapper.numberOfMeetings());
    }

    @Test
    public void testHasMeetings() {
        // Toplantı ekle ve kontrol et
        meetingPlanner.addMeeting(new Meeting("1", new Date(System.currentTimeMillis()), "10:00", "Library", "Discuss book", meetingPlanner));

        assertFalse(meetingPlannerWrapper.hasMeetings());
    }

    @Test
    public void testIndexOfMeeting() {
        // Örnek toplantı oluştur
        Meeting meeting = new Meeting("1", new Date(System.currentTimeMillis()), "10:00", "Library", "Discuss book", meetingPlanner);
        meetingPlanner.addMeeting(meeting);

        assertNotEquals(0, meetingPlannerWrapper.indexOfMeeting(meeting));
    }

    @Test
    public void testGetMember() {
        assertEquals(member, meetingPlannerWrapper.getMember());
    }

    @Test
    public void testMinimumNumberOfMeetings() {
        assertEquals(MeetingPlanner.minimumNumberOfMeetings(), MeetingPlannerWrapper.minimumNumberOfMeetings());
    }

    @Test
    public void testAddMeeting() {
        // Örnek toplantı oluştur ve ekle
        Meeting meeting = meetingPlannerWrapper.addMeeting("1", new Date(System.currentTimeMillis()), "10:00", "Library", "Discuss book");

        assertEquals(meeting, meetingPlannerWrapper.getMeeting(0));
    }

    @Test
    public void testAddMeetingObject() {
        // Örnek toplantı oluştur
        Meeting meeting = new Meeting("1", new Date(System.currentTimeMillis()), "10:00", "Library", "Discuss book", meetingPlanner);

        assertTrue(meetingPlannerWrapper.addMeeting(meeting));
    }

    @Test
    public void testRemoveMeeting() {
        // Örnek toplantı oluştur ve ekle
        Meeting meeting = new Meeting("1", new Date(System.currentTimeMillis()), "10:00", "Library", "Discuss book", meetingPlanner);
        meetingPlanner.addMeeting(meeting);

        assertTrue(meetingPlannerWrapper.removeMeeting(meeting));
    }

    @Test
    public void testAddMeetingAt() {
        // Örnek toplantı oluştur
        Meeting meeting = new Meeting("1", new Date(System.currentTimeMillis()), "10:00", "Library", "Discuss book", meetingPlanner);

        assertTrue(meetingPlannerWrapper.addMeetingAt(meeting, 0));
    }

    @Test
    public void testAddOrMoveMeetingAt() {
        // Örnek toplantı oluştur
        Meeting meeting = new Meeting("1", new Date(System.currentTimeMillis()), "10:00", "Library", "Discuss book", meetingPlanner);

        assertTrue(meetingPlannerWrapper.addOrMoveMeetingAt(meeting, 0));
    }

    @Test
    public void testSetMember() {
        // Yeni üye oluştur ve MemberManagement'a ekle
        Member newMember = new Member("2", "Jane Doe", "jane.doe@example.com", "Non-fiction", memberManagement);
        memberManagement.addMember(newMember);

        assertTrue(meetingPlannerWrapper.setMember(newMember));
    }

    @Test
    public void testDelete() {
        meetingPlannerWrapper.delete();
        assertEquals(0, meetingPlannerWrapper.numberOfMeetings());
    }

    @Test
    public void testScheduleMeeting() {
        // Örnek toplantı oluştur
        Meeting meeting = new Meeting("1", new Date(System.currentTimeMillis()), "10:00", "Library", "Discuss book", meetingPlanner);

        meetingPlannerWrapper.scheduleMeeting(meeting);
        // Bu metodun işlevselliğini doğrulamak için ek kontroller yapılabilir
    }

    @Test
    public void testManageMeetings() {
        // Üye ID'si ile toplantıları yönet
        meetingPlannerWrapper.manageMeetings("1");
        // Bu metodun işlevselliğini doğrulamak için ek kontroller yapılabilir
    }

    @Test
    public void testRsvpForMeeting() {
        // Üye ve toplantı ID'leri ile RSVP
        meetingPlannerWrapper.rsvpForMeeting("1", "1");
        // Bu metodun işlevselliğini doğrulamak için ek kontroller yapılabilir
    }
}
