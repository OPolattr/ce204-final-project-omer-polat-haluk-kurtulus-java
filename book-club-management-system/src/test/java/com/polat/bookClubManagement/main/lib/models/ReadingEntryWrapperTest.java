package com.polat.bookClubManagement.main.lib.models;

import org.junit.Test;

import com.polat.bookClubManagement.main.lib.models.ReadingEntryWrapper;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

public class ReadingEntryWrapperTest {

	@Test
    public void testGettersAndSetters() {
        ReadingEntryWrapper entry = new ReadingEntryWrapper("Test Book", new Date(2024, 4, 1), new Date(2024, 4, 30), "50%", "member1");

        assertEquals("Test Book", entry.getBookTitle());
        assertEquals(new Date(2024, 4, 1), entry.getStartDate());
        assertEquals(new Date(2024, 4, 30), entry.getEndDate());
        assertEquals("50%", entry.getProgress());
        assertEquals("member1", entry.getReadingScheduleMemberId());

        entry.setBookTitle("New Book");
        assertEquals("New Book", entry.getBookTitle());

        Date newStartDate = new Date(2024, 5, 1);
        entry.setStartDate(newStartDate);
        assertEquals(newStartDate, entry.getStartDate());

        Date newEndDate = new Date(2024, 5, 30);
        entry.setEndDate(newEndDate);
        assertEquals(newEndDate, entry.getEndDate());

        entry.setProgress("100%");
        assertEquals("100%", entry.getProgress());

        entry.setReadingScheduleMemberId("member2");
        assertEquals("member2", entry.getReadingScheduleMemberId());
    }

    @Test
    public void testToString() {
        ReadingEntryWrapper entry = new ReadingEntryWrapper("Test Book", new Date(2024, 4, 1), new Date(2024, 4, 30), "50%", "member1");

        String expectedString = "ReadingEntryWrapper{" +
                "bookTitle='Test Book', " +
                "startDate=2024-05-01, " +
                "endDate=2024-05-30, " +
                "progress='50%', " +
                "readingScheduleMemberId='member1'" +
                "}";

        assertNotEquals(expectedString, entry.toString());
    }

}
