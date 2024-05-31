package com.polat.bookClubManagement.umple;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.junit.Test;

public class MemberTest2 {

	private Member member;
    private MemberManagement memberManagement;
    private ReadingSchedule readingSchedule1;
    private ReadingSchedule readingSchedule2;

    @Before
    public void setUp() {
        memberManagement = new MemberManagement();
        member = new Member("1", "Test Member", "test@example.com", "Fiction", memberManagement);
        readingSchedule1 = member.addReadingSchedule();
        readingSchedule2 = member.addReadingSchedule();
    }

    @Test
    public void testIndexOfReadingSchedule() {
        // Okuma takvimindeki ilk girişin doğru indeksi alınıyor mu?
        assertEquals(0, member.indexOfReadingSchedule(readingSchedule1));

        // Okuma takvimindeki ikinci girişin doğru indeksi alınıyor mu?
        assertEquals(1, member.indexOfReadingSchedule(readingSchedule2));
    }
}
