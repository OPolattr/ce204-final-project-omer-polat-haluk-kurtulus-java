package com.polat.bookClubManagement.umple;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.junit.Test;

public class Member3Test {

	private Member member;
    private MemberManagement memberManagement;
    private MeetingPlanner meetingPlanner;

    @Before
    public void setUp() {
        memberManagement = new MemberManagement();
        member = new Member("1", "Test Member", "test@example.com", "Fiction", memberManagement);
        meetingPlanner = member.addMeetingPlanner();
    }

    @Test
    public void testIndexOfMeetingPlanner() {
        // Oluşturduğumuz toplantı planlayıcısının index'i doğru olmalı
        assertEquals(0, member.indexOfMeetingPlanner(meetingPlanner));
    }

}
