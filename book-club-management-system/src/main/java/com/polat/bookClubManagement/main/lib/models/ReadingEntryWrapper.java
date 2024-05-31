package com.polat.bookClubManagement.main.lib.models;

import java.sql.Date;

public class ReadingEntryWrapper {

    private String bookTitle;
    private Date startDate;
    private Date endDate;
    private String progress;
    private String readingScheduleMemberId;

    public ReadingEntryWrapper(String bookTitle, Date startDate, Date endDate, String progress, String readingScheduleMemberId) {
        this.bookTitle = bookTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
        this.readingScheduleMemberId = readingScheduleMemberId;
    }

    // Getters and Setters
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getReadingScheduleMemberId() {
        return readingScheduleMemberId;
    }

    public void setReadingScheduleMemberId(String readingScheduleMemberId) {
        this.readingScheduleMemberId = readingScheduleMemberId;
    }

    @Override
    public String toString() {
        return "ReadingEntryWrapper{" +
                "bookTitle='" + bookTitle + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", progress='" + progress + '\'' +
                ", readingScheduleMemberId='" + readingScheduleMemberId + '\'' +
                '}';
    }
}
