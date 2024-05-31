package com.polat.bookClubManagement.main.lib.models;

import java.sql.Date;

public class MeetingWrapper {

  private String meetingId;
  private Date date;
  private String time;
  private String location;
  private String agenda;
  private String meetingPlannerId;

  public MeetingWrapper(String meetingId, Date date, String time, String location, String agenda, String meetingPlannerId) {
    this.meetingId = meetingId;
    this.date = date;
    this.time = time;
    this.location = location;
    this.agenda = agenda;
    this.meetingPlannerId = meetingPlannerId;
  }

  // Getters and Setters
  public String getMeetingId() {
    return meetingId;
  }

  public void setMeetingId(String meetingId) {
    this.meetingId = meetingId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getAgenda() {
    return agenda;
  }

  public void setAgenda(String agenda) {
    this.agenda = agenda;
  }

  public String getMeetingPlannerId() {
    return meetingPlannerId;
  }

  public void setMeetingPlannerId(String meetingPlannerId) {
    this.meetingPlannerId = meetingPlannerId;
  }

  @Override
  public String toString() {
    return "MeetingWrapper{" +
            "meetingId='" + meetingId + '\'' +
            ", date=" + date +
            ", time='" + time + '\'' +
            ", location='" + location + '\'' +
            ", agenda='" + agenda + '\'' +
            ", meetingPlannerId='" + meetingPlannerId + '\'' +
            '}';
  }
}
