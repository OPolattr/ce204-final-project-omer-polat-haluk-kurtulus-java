package com.polat.bookClubManagement.main.lib.wrappers;

import static org.junit.Assert.*;

import com.polat.bookClubManagement.umple.Member;
import com.polat.bookClubManagement.umple.MemberManagement;
import com.polat.bookClubManagement.umple.ReadingEntry;
import com.polat.bookClubManagement.umple.ReadingSchedule;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReadingScheduleWrapperTest {

    private ReadingScheduleWrapper readingScheduleWrapper;
    private Member member;
    private ReadingSchedule readingSchedule;

    @Before
    public void setUp() {
        member = new Member("1", "John Doe", "john@example.com", "Fiction", new MemberManagement());
        readingSchedule = new ReadingSchedule(member);
        readingScheduleWrapper = new ReadingScheduleWrapper(member);
        readingScheduleWrapper.readingSchedule = readingSchedule; // Injecting real ReadingSchedule object
    }

    @Test
    public void testGetReadingEntry() {
        ReadingEntry readingEntry = new ReadingEntry("Book Title", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "In Progress", readingSchedule);
        readingSchedule.addReadingEntry(readingEntry);

        ReadingEntry result = readingScheduleWrapper.getReadingEntry(0);
        assertEquals(readingEntry, result);
    }

    @Test
    public void testGetReadingEntries() {
        ReadingEntry readingEntry1 = new ReadingEntry("Book Title 1", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "In Progress", readingSchedule);
        ReadingEntry readingEntry2 = new ReadingEntry("Book Title 2", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "In Progress", readingSchedule);
        readingSchedule.addReadingEntry(readingEntry1);
        readingSchedule.addReadingEntry(readingEntry2);

        List<ReadingEntry> result = readingScheduleWrapper.getReadingEntries();
        assertEquals(2, result.size());
        assertEquals(readingEntry1, result.get(0));
        assertEquals(readingEntry2, result.get(1));
    }

    @Test
    public void testNumberOfReadingEntries() {
        ReadingEntry readingEntry = new ReadingEntry("Book Title", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "In Progress", readingSchedule);
        readingSchedule.addReadingEntry(readingEntry);

        int result = readingScheduleWrapper.numberOfReadingEntries();
        assertEquals(1, result);
    }

    @Test
    public void testHasReadingEntries() {
        assertFalse(readingScheduleWrapper.hasReadingEntries());

        ReadingEntry readingEntry = new ReadingEntry("Book Title", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "In Progress", readingSchedule);
        readingSchedule.addReadingEntry(readingEntry);

        assertTrue(readingScheduleWrapper.hasReadingEntries());
    }

    @Test
    public void testIndexOfReadingEntry() {
        ReadingEntry readingEntry = new ReadingEntry("Book Title", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "In Progress", readingSchedule);
        readingSchedule.addReadingEntry(readingEntry);

        int result = readingScheduleWrapper.indexOfReadingEntry(readingEntry);
        assertEquals(0, result);
    }

    @Test
    public void testGetMember() {
        Member result = readingScheduleWrapper.getMember();
        assertEquals(member, result);
    }

    @Test
    public void testMinimumNumberOfReadingEntries() {
        int result = ReadingScheduleWrapper.minimumNumberOfReadingEntries();
        assertEquals(0, result); // Assuming the minimum number is 0
    }

    @Test
    public void testAddReadingEntry() {
        String bookTitle = "Book Title";
        Date startDate = new Date(System.currentTimeMillis());
        Date endDate = new Date(System.currentTimeMillis() + 86400000L);
        String progress = "In Progress";

        ReadingEntry result = readingScheduleWrapper.addReadingEntry(bookTitle, startDate, endDate, progress);
        assertNotNull(result);
        assertEquals(bookTitle, result.getBookTitle());
        assertEquals(startDate, result.getStartDate());
        assertEquals(endDate, result.getEndDate());
        assertEquals(progress, result.getProgress());
    }

    @Test
    public void testAddReadingEntryWithObject() {
        ReadingEntry readingEntry = new ReadingEntry("Book Title", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "In Progress", readingSchedule);
        boolean result = readingScheduleWrapper.addReadingEntry(readingEntry);
        assertFalse(result);
    }

    @Test
    public void testRemoveReadingEntry() {
        ReadingEntry readingEntry = new ReadingEntry("Book Title", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "In Progress", readingSchedule);
        readingSchedule.addReadingEntry(readingEntry);

        boolean result = readingScheduleWrapper.removeReadingEntry(readingEntry);
        assertFalse(result);
        assertTrue(readingScheduleWrapper.hasReadingEntries());
    }

    @Test
    public void testAddReadingEntryAt() {
        ReadingEntry readingEntry = new ReadingEntry("Book Title", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "In Progress", readingSchedule);
        boolean result = readingScheduleWrapper.addReadingEntryAt(readingEntry, 0);
        assertFalse(result);
    }

    @Test
    public void testAddOrMoveReadingEntryAt() {
        ReadingEntry readingEntry = new ReadingEntry("Book Title", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "In Progress", readingSchedule);
        boolean result = readingScheduleWrapper.addOrMoveReadingEntryAt(readingEntry, 0);
        assertTrue(result);
    }

    @Test
    public void testSetMember() {
        Member newMember = new Member("2", "Jane Doe", "jane@example.com", "Non-fiction", new MemberManagement());
        boolean result = readingScheduleWrapper.setMember(newMember);
        assertTrue(result);
        assertEquals(newMember, readingScheduleWrapper.getMember());
    }

    @Test
    public void testDelete() {
        readingScheduleWrapper.delete();
        assertEquals(0, readingScheduleWrapper.numberOfReadingEntries());
    }

    @Test
    public void testOrganizeReadingSchedule() {
        ReadingEntry readingEntry = new ReadingEntry("Book Title", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "In Progress", readingSchedule);
        readingScheduleWrapper.addReadingEntry(readingEntry);
        readingScheduleWrapper.organizeReadingSchedule(readingEntry);
        // Assuming organizeReadingSchedule modifies the reading entries in some way.
    }

    @Test
    public void testTrackReadingProgress() {
        ReadingEntry readingEntry1 = new ReadingEntry("Book Title 1", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "In Progress", readingSchedule);
        ReadingEntry readingEntry2 = new ReadingEntry("Book Title 2", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L), "Completed", readingSchedule);
        readingSchedule.addReadingEntry(readingEntry1);
        readingSchedule.addReadingEntry(readingEntry2);

        List<ReadingEntry> result = readingScheduleWrapper.trackReadingProgress(member.getMemberId());
        assertEquals(2, result.size());
    }

    @Test
    public void testSetReadingReminders() {
        Date reminderDate = new Date(System.currentTimeMillis() + 86400000L);
        readingScheduleWrapper.setReadingReminders(member.getMemberId(), reminderDate);
        // Assuming setReadingReminders sets some state or schedules a reminder
    }
}
