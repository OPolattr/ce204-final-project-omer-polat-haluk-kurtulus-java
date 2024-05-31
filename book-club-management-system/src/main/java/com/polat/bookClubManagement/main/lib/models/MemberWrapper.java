package com.polat.bookClubManagement.main.lib.models;

import java.util.ArrayList;
import java.util.List;

public class MemberWrapper {

    private String memberId;
    private String name;
    private String email;
    private String readingPreferences;
    private List<String> readingSchedules;
    private List<String> meetingPlanners;
    private List<String> discussionForums;

    public MemberWrapper(String memberId, String name, String email, String readingPreferences) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.readingPreferences = readingPreferences;
        this.readingSchedules = new ArrayList<>();
        this.meetingPlanners = new ArrayList<>();
        this.discussionForums = new ArrayList<>();
    }

    // Getters and Setters
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReadingPreferences() {
        return readingPreferences;
    }

    public void setReadingPreferences(String readingPreferences) {
        this.readingPreferences = readingPreferences;
    }

    public List<String> getReadingSchedules() {
        return readingSchedules;
    }

    public void addReadingSchedule(String readingSchedule) {
        this.readingSchedules.add(readingSchedule);
    }

    public void removeReadingSchedule(String readingSchedule) {
        this.readingSchedules.remove(readingSchedule);
    }

    public List<String> getMeetingPlanners() {
        return meetingPlanners;
    }

    public void addMeetingPlanner(String meetingPlanner) {
        this.meetingPlanners.add(meetingPlanner);
    }

    public void removeMeetingPlanner(String meetingPlanner) {
        this.meetingPlanners.remove(meetingPlanner);
    }

    public List<String> getDiscussionForums() {
        return discussionForums;
    }

    public void addDiscussionForum(String discussionForum) {
        this.discussionForums.add(discussionForum);
    }

    public void removeDiscussionForum(String discussionForum) {
        this.discussionForums.remove(discussionForum);
    }

    public void updateProfile(String name, String email, String readingPreferences) {
        this.name = name;
        this.email = email;
        this.readingPreferences = readingPreferences;
    }

    @Override
    public String toString() {
        return "MemberWrapper{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", readingPreferences='" + readingPreferences + '\'' +
                '}';
    }
}
