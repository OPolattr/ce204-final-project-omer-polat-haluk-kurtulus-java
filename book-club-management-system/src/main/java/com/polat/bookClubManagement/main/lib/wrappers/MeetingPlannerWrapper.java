package com.polat.bookClubManagement.main.lib.wrappers;



import java.util.List;

import com.polat.bookClubManagement.umple.Meeting;
import com.polat.bookClubManagement.umple.MeetingPlanner;
import com.polat.bookClubManagement.umple.Member;

import java.sql.Date;

public class MeetingPlannerWrapper {

    private MeetingPlanner meetingPlanner;

    public MeetingPlannerWrapper(Member member) {
        this.meetingPlanner = new MeetingPlanner(member);
    }

    public Meeting getMeeting(int index) {
        return meetingPlanner.getMeeting(index);
    }

    public List<Meeting> getMeetings() {
        return meetingPlanner.getMeetings();
    }

    public int numberOfMeetings() {
        return meetingPlanner.numberOfMeetings();
    }

    public boolean hasMeetings() {
        return meetingPlanner.hasMeetings();
    }

    public int indexOfMeeting(Meeting meeting) {
        return meetingPlanner.indexOfMeeting(meeting);
    }

    public Member getMember() {
        return meetingPlanner.getMember();
    }

    public static int minimumNumberOfMeetings() {
        return MeetingPlanner.minimumNumberOfMeetings();
    }

    public Meeting addMeeting(String meetingId, Date date, String time, String location, String agenda) {
        return meetingPlanner.addMeeting(meetingId, date, time, location, agenda);
    }

    public boolean addMeeting(Meeting meeting) {
        return meetingPlanner.addMeeting(meeting);
    }

    public boolean removeMeeting(Meeting meeting) {
        return meetingPlanner.removeMeeting(meeting);
    }

    public boolean addMeetingAt(Meeting meeting, int index) {
        return meetingPlanner.addMeetingAt(meeting, index);
    }

    public boolean addOrMoveMeetingAt(Meeting meeting, int index) {
        return meetingPlanner.addOrMoveMeetingAt(meeting, index);
    }

    public boolean setMember(Member member) {
        return meetingPlanner.setMember(member);
    }

    public void delete() {
        meetingPlanner.delete();
    }

    public void scheduleMeeting(Meeting meeting) {
        meetingPlanner.scheduleMeeting(meeting);
    }

    public void manageMeetings(String memberId) {
        meetingPlanner.manageMeetings(memberId);
    }

    public void rsvpForMeeting(String memberId, String meetingId) {
        meetingPlanner.rsvpForMeeting(memberId, meetingId);
    }
}
