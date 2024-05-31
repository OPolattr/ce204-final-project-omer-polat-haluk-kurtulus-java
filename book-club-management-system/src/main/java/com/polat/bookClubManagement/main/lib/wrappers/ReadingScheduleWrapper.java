package com.polat.bookClubManagement.main.lib.wrappers;

import java.util.List;

import com.polat.bookClubManagement.umple.Member;
import com.polat.bookClubManagement.umple.ReadingEntry;
import com.polat.bookClubManagement.umple.ReadingSchedule;

import java.sql.Date;

public class ReadingScheduleWrapper {

	// Private yapabilirsin
    public ReadingSchedule readingSchedule;

    public ReadingScheduleWrapper(Member member) {
        this.readingSchedule = new ReadingSchedule(member);
    }

    public ReadingEntry getReadingEntry(int index) {
        return readingSchedule.getReadingEntry(index);
    }

    public List<ReadingEntry> getReadingEntries() {
        return readingSchedule.getReadingEntries();
    }

    public int numberOfReadingEntries() {
        return readingSchedule.numberOfReadingEntries();
    }

    public boolean hasReadingEntries() {
        return readingSchedule.hasReadingEntries();
    }

    public int indexOfReadingEntry(ReadingEntry readingEntry) {
        return readingSchedule.indexOfReadingEntry(readingEntry);
    }

    public Member getMember() {
        return readingSchedule.getMember();
    }

    public static int minimumNumberOfReadingEntries() {
        return ReadingSchedule.minimumNumberOfReadingEntries();
    }

    public ReadingEntry addReadingEntry(String bookTitle, Date startDate, Date endDate, String progress) {
        return readingSchedule.addReadingEntry(bookTitle, startDate, endDate, progress);
    }

    public boolean addReadingEntry(ReadingEntry readingEntry) {
        return readingSchedule.addReadingEntry(readingEntry);
    }

    public boolean removeReadingEntry(ReadingEntry readingEntry) {
        return readingSchedule.removeReadingEntry(readingEntry);
    }

    public boolean addReadingEntryAt(ReadingEntry readingEntry, int index) {
        return readingSchedule.addReadingEntryAt(readingEntry, index);
    }

    public boolean addOrMoveReadingEntryAt(ReadingEntry readingEntry, int index) {
        return readingSchedule.addOrMoveReadingEntryAt(readingEntry, index);
    }

    public boolean setMember(Member member) {
        return readingSchedule.setMember(member);
    }

    public void delete() {
        readingSchedule.delete();
    }

    public void organizeReadingSchedule(ReadingEntry readingEntry) {
        readingSchedule.organizeReadingSchedule(readingEntry);
    }

    public List<ReadingEntry> trackReadingProgress(String memberId) {
        return readingSchedule.trackReadingProgress(memberId);
    }

    public void setReadingReminders(String memberId, Date reminderDate) {
        readingSchedule.setReadingReminders(memberId, reminderDate);
    }
}
