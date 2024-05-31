package com.polat.bookClubManagement.main.lib.models;

import org.junit.Before;
import org.junit.Test;

import com.polat.bookClubManagement.main.lib.models.MemberWrapper;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;

import org.junit.Test;

public class MemberWrapperTest {

	private MemberWrapper member;

    @Before
    public void setUp() {
        member = new MemberWrapper("123", "John Doe", "john@example.com", "Fiction");
    }

    @Test
    public void testGetMemberId() {
        assertEquals("123", member.getMemberId());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", member.getName());
    }

    @Test
    public void testSetName() {
        member.setName("Jane Doe");
        assertEquals("Jane Doe", member.getName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john@example.com", member.getEmail());
    }

    @Test
    public void testSetEmail() {
        member.setEmail("jane@example.com");
        assertEquals("jane@example.com", member.getEmail());
    }

    @Test
    public void testGetReadingPreferences() {
        assertEquals("Fiction", member.getReadingPreferences());
    }

    @Test
    public void testSetReadingPreferences() {
        member.setReadingPreferences("Non-fiction");
        assertEquals("Non-fiction", member.getReadingPreferences());
    }

    @Test
    public void testAddReadingSchedule() {
        member.addReadingSchedule("Monday, 9:00 AM");
        assertEquals(1, member.getReadingSchedules().size());
    }

    @Test
    public void testRemoveReadingSchedule() {
        member.addReadingSchedule("Monday, 9:00 AM");
        member.removeReadingSchedule("Monday, 9:00 AM");
        assertEquals(0, member.getReadingSchedules().size());
    }

    @Test
    public void testAddMeetingPlanner() {
        member.addMeetingPlanner("Bi-weekly");
        assertEquals(1, member.getMeetingPlanners().size());
    }

    @Test
    public void testRemoveMeetingPlanner() {
        member.addMeetingPlanner("Bi-weekly");
        member.removeMeetingPlanner("Bi-weekly");
        assertEquals(0, member.getMeetingPlanners().size());
    }

    @Test
    public void testAddDiscussionForum() {
        member.addDiscussionForum("Book Discussion Group");
        assertEquals(1, member.getDiscussionForums().size());
    }

    @Test
    public void testRemoveDiscussionForum() {
        member.addDiscussionForum("Book Discussion Group");
        member.removeDiscussionForum("Book Discussion Group");
        assertEquals(0, member.getDiscussionForums().size());
    }

    @Test
    public void testUpdateProfile() {
        member.updateProfile("Jane Doe", "jane@example.com", "Non-fiction");
        assertEquals("Jane Doe", member.getName());
        assertEquals("jane@example.com", member.getEmail());
        assertEquals("Non-fiction", member.getReadingPreferences());
    }
    
    @Test
    public void testSetMemberId() {
        member.setMemberId("456");
        assertEquals("456", member.getMemberId());
    }
    
    @Test
    public void testToString() {
        String expectedString = "MemberWrapper{memberId='123', name='John Doe', email='john@example.com', readingPreferences='Fiction'}";
        assertEquals(expectedString, member.toString());
    }

}
