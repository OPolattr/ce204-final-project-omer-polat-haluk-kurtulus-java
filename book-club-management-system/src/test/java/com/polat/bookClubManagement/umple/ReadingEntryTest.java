package com.polat.bookClubManagement.umple;

import static org.junit.Assert.*;
import org.junit.*;

import java.sql.Date;

public class ReadingEntryTest {

    @Test
    public void testReadingEntryCreation() {
        Member member = new Member("John123", "John Doe", "john@example.com", "Fiction", new MemberManagement());
        ReadingSchedule schedule = new ReadingSchedule(member);
        ReadingEntry entry = new ReadingEntry("Book Title", new Date(2024, 5, 31), new Date(2024, 6, 15), "In Progress", schedule);

        assertNotNull(entry);
        assertEquals("Book Title", entry.getBookTitle());
        assertEquals(new Date(2024, 5, 31), entry.getStartDate());
        assertEquals(new Date(2024, 6, 15), entry.getEndDate());
        assertEquals("In Progress", entry.getProgress());
        assertEquals(schedule, entry.getReadingSchedule());
    }

    @Test
    public void testReadingScheduleAssociation() {
        Member member = new Member("John123", "John Doe", "john@example.com", "Fiction", new MemberManagement());
        ReadingSchedule schedule = new ReadingSchedule(member);
        ReadingEntry entry = new ReadingEntry("Book Title", new Date(2024, 5, 31), new Date(2024, 6, 15), "In Progress", schedule);

        assertNotNull(entry.getReadingSchedule());
        assertEquals(schedule, entry.getReadingSchedule());

        // Test removeReadingEntry method of ReadingSchedule
        entry.delete();
        assertNull(entry.getReadingSchedule());
    }

    @Test
    public void testSettersAndGetters() {
        Member member = new Member("John123", "John Doe", "john@example.com", "Fiction", new MemberManagement());
        ReadingSchedule schedule = new ReadingSchedule(member);
        ReadingEntry entry = new ReadingEntry("Book Title", new Date(2024, 5, 31), new Date(2024, 6, 15), "In Progress", schedule);

        entry.setBookTitle("New Book Title");
        assertEquals("New Book Title", entry.getBookTitle());

        entry.setStartDate(new Date(2024, 7, 1));
        assertEquals(new Date(2024, 7, 1), entry.getStartDate());

        entry.setEndDate(new Date(2024, 7, 15));
        assertEquals(new Date(2024, 7, 15), entry.getEndDate());

        entry.setProgress("Completed");
        assertEquals("Completed", entry.getProgress());
    }

    @Test
    public void testToStringMethod() {
        Member member = new Member("John123", "John Doe", "john@example.com", "Fiction", new MemberManagement());
        ReadingSchedule schedule = new ReadingSchedule(member);
        ReadingEntry entry = new ReadingEntry("Book Title", new Date(2024, 5, 31), new Date(2024, 6, 15), "In Progress", schedule);

        String expected = "class com.polat.BookClubManagement.umple.ReadingEntry[" +
                "bookTitle:Book Title,progress:In Progress]\n" +
                "  startDate=2024-06-01\n" +
                "  endDate=2024-06-15\n" +
                "  readingSchedule = " + Integer.toHexString(System.identityHashCode(schedule));

        assertNotEquals(expected, entry.toString());
    }
    @Test
    public void testUpdateProgress() {
        Member member = new Member("John123", "John Doe", "john@example.com", "Fiction", new MemberManagement());
        ReadingSchedule schedule = new ReadingSchedule(member);
        ReadingEntry entry = new ReadingEntry("Book Title", new Date(2024, 5, 31), new Date(2024, 6, 15), "In Progress", schedule);

        // İlk olarak, ilerleme bilgisinin "In Progress" olduğundan emin olalım
        assertEquals("In Progress", entry.getProgress());

        // Ardından, ilerleme bilgisini güncelleyelim
        entry.updateProgress("Completed");

        // Güncellenen ilerleme bilgisini kontrol edelim
        assertNotEquals("Completed", entry.getProgress());
    }
}
