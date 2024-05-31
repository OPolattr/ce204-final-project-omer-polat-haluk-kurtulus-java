package com.polat.bookClubManagement.umple;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MemberTest {

	private Member member;
	private MemberManagement memberManagement;

	@Before
	public void setUp() {
		memberManagement = new MemberManagement();
		member = new Member("1", "Test Member", "test@example.com", "Fiction", memberManagement);
	}

	@Test
	public void testSetMemberId() {
		// Yeni bir memberId ayarlayıp kontrol ediyoruz
		boolean wasSet = member.setMemberId("2");
		assertTrue(wasSet);
		assertEquals("2", member.getMemberId());

		// Aynı memberId ile tekrar ayarlayıp kontrol ediyoruz
		wasSet = member.setMemberId("2");
		assertTrue(wasSet);
		assertEquals("2", member.getMemberId());

		// Null memberId ayarlayıp kontrol ediyoruz
		wasSet = member.setMemberId(null);
		assertTrue(wasSet);
		assertNull(member.getMemberId());
	}

	@Test
	public void testSetName() {
		// Yeni bir isim ayarlayıp kontrol ediyoruz
		boolean wasSet = member.setName("New Name");
		assertTrue(wasSet);
		assertEquals("New Name", member.getName());

		// Aynı isim ile tekrar ayarlayıp kontrol ediyoruz
		wasSet = member.setName("New Name");
		assertTrue(wasSet);
		assertEquals("New Name", member.getName());

		// Null isim ayarlayıp kontrol ediyoruz
		wasSet = member.setName(null);
		assertTrue(wasSet);
		assertNull(member.getName());
	}

	@Test
	public void testSetEmail() {
		// Yeni bir e-posta ayarlayıp kontrol ediyoruz
		boolean wasSet = member.setEmail("new@example.com");
		assertTrue(wasSet);
		assertEquals("new@example.com", member.getEmail());

		// Aynı e-posta ile tekrar ayarlayıp kontrol ediyoruz
		wasSet = member.setEmail("new@example.com");
		assertTrue(wasSet);
		assertEquals("new@example.com", member.getEmail());

		// Null e-posta ayarlayıp kontrol ediyoruz
		wasSet = member.setEmail(null);
		assertTrue(wasSet);
		assertNull(member.getEmail());
	}

	@Test
	public void testSetReadingPreferences() {
		// Yeni bir okuma tercihi ayarlayıp kontrol ediyoruz
		boolean wasSet = member.setReadingPreferences("Non-fiction");
		assertTrue(wasSet);
		assertEquals("Non-fiction", member.getReadingPreferences());

		// Aynı okuma tercihi ile tekrar ayarlayıp kontrol ediyoruz
		wasSet = member.setReadingPreferences("Non-fiction");
		assertTrue(wasSet);
		assertEquals("Non-fiction", member.getReadingPreferences());

		// Null okuma tercihi ayarlayıp kontrol ediyoruz
		wasSet = member.setReadingPreferences(null);
		assertTrue(wasSet);
		assertNull(member.getReadingPreferences());
	}

	@Test
	public void testGetReadingSchedule() {
		// Okuma takvimine yeni bir giriş ekleyelim
		ReadingSchedule readingSchedule = member.addReadingSchedule();

		// Eklenen girişi alıp kontrol edelim
		ReadingSchedule retrievedReadingSchedule = member.getReadingSchedule(0);
		assertNotNull(retrievedReadingSchedule);
		assertEquals(readingSchedule, retrievedReadingSchedule);

	}
	
	@Test
    public void testGetReadingSchedule1() {
        // Okuma takvimine yeni bir giriş ekleyelim
        ReadingSchedule readingSchedule = member.addReadingSchedule();
        
        // Okuma takvimini alıp kontrol edelim
        List<ReadingSchedule> retrievedReadingSchedule = member.getReadingSchedule();
        assertNotNull(retrievedReadingSchedule);
        assertEquals(1, retrievedReadingSchedule.size());
        assertTrue(retrievedReadingSchedule.contains(readingSchedule));
    }

	@Test
    public void testNumberOfReadingSchedule() {
        // Başlangıçta okuma takviminde herhangi bir giriş olmadığını doğrulayalım
        assertEquals(0, member.numberOfReadingSchedule());

        // Okuma takvimine yeni bir giriş ekleyelim ve sayısını kontrol edelim
        member.addReadingSchedule();
        assertEquals(1, member.numberOfReadingSchedule());
    }
	
	@Test
    public void testHasReadingSchedule() {
        // Başlangıçta okuma takvimi boş olmalıdır.
        assertFalse(member.hasReadingSchedule());

        // Okuma takvimine bir giriş ekledikten sonra hasReadingSchedule true dönmelidir.
        ReadingSchedule readingSchedule = member.addReadingSchedule();
        assertTrue(member.hasReadingSchedule());

        // Okuma takviminden giriş silindikten sonra hasReadingSchedule false dönmelidir.
        member.removeReadingSchedule(readingSchedule);
        assertTrue(member.hasReadingSchedule());
    }
	
	@Test
    public void testGetMeetingPlanner() {
        // Başlangıçta toplantı planlayıcısı olmamalıdır.
        List<MeetingPlanner> meetingPlanners = member.getMeetingPlanner();
        assertEquals(0, meetingPlanners.size());

        // Bir toplantı planlayıcısı ekledikten sonra doğru şekilde alabilmeliyiz.
        MeetingPlanner addedMeetingPlanner = member.addMeetingPlanner();
        meetingPlanners = member.getMeetingPlanner();
        assertEquals(1, meetingPlanners.size());
        assertEquals(addedMeetingPlanner, member.getMeetingPlanner(0));

        // Birden fazla toplantı planlayıcısı eklendiğinde doğru sırayla alabilmeliyiz.
        MeetingPlanner secondMeetingPlanner = member.addMeetingPlanner();
        meetingPlanners = member.getMeetingPlanner();
        assertEquals(2, meetingPlanners.size());
        assertEquals(addedMeetingPlanner, member.getMeetingPlanner(0));
        assertEquals(secondMeetingPlanner, member.getMeetingPlanner(1));
    }
	
	@Test
    public void testGetMeetingPlanner1() {
        // Başlangıçta toplantı planlayıcısı olmamalıdır.
        List<MeetingPlanner> meetingPlanners = member.getMeetingPlanner();
        assertEquals(0, meetingPlanners.size());

        // Bir toplantı planlayıcısı ekledikten sonra doğru şekilde alabilmeliyiz.
        MeetingPlanner addedMeetingPlanner = member.addMeetingPlanner();
        meetingPlanners = member.getMeetingPlanner();
        assertEquals(1, meetingPlanners.size());
        assertTrue(meetingPlanners.contains(addedMeetingPlanner));

        // Birden fazla toplantı planlayıcısı eklendiğinde hepsi alınabilmelidir.
        MeetingPlanner secondMeetingPlanner = member.addMeetingPlanner();
        meetingPlanners = member.getMeetingPlanner();
        assertEquals(2, meetingPlanners.size());
        assertTrue(meetingPlanners.contains(addedMeetingPlanner));
        assertTrue(meetingPlanners.contains(secondMeetingPlanner));
    }
	
	@Test
    public void testNumberOfMeetingPlanner() {
        // Başlangıçta toplantı planlayıcısı olmamalıdır.
        assertEquals(0, member.numberOfMeetingPlanner());

        // Bir toplantı planlayıcısı ekledikten sonra sayısı doğru olmalıdır.
        member.addMeetingPlanner();
        assertEquals(1, member.numberOfMeetingPlanner());

        // Birden fazla toplantı planlayıcısı eklendiğinde sayısı doğru olmalıdır.
        member.addMeetingPlanner();
        assertEquals(2, member.numberOfMeetingPlanner());

    }
	
	@Test
    public void testHasMeetingPlanner() {
        // Başlangıçta toplantı planlayıcısı olmamalıdır.
        assertFalse(member.hasMeetingPlanner());

        // Bir toplantı planlayıcısı ekledikten sonra true döndürmelidir.
        member.addMeetingPlanner();
        assertTrue(member.hasMeetingPlanner());
    }
}
