package com.polat.bookClubManagement.umple;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.sql.Date;

// line 61 "model.ump"
// line 162 "model.ump"
public class Meeting
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Meeting Attributes
  private String meetingId;
  private Date date;
  private String time;
  private String location;
  private String agenda;

  //Meeting Associations
  private MeetingPlanner meetingPlanner;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Meeting(String aMeetingId, Date aDate, String aTime, String aLocation, String aAgenda, MeetingPlanner aMeetingPlanner)
  {
    meetingId = aMeetingId;
    date = aDate;
    time = aTime;
    location = aLocation;
    agenda = aAgenda;
    boolean didAddMeetingPlanner = setMeetingPlanner(aMeetingPlanner);
    if (!didAddMeetingPlanner)
    {
      throw new RuntimeException("Unable to create meeting due to meetingPlanner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMeetingId(String aMeetingId)
  {
    boolean wasSet = false;
    meetingId = aMeetingId;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setTime(String aTime)
  {
    boolean wasSet = false;
    time = aTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setLocation(String aLocation)
  {
    boolean wasSet = false;
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public boolean setAgenda(String aAgenda)
  {
    boolean wasSet = false;
    agenda = aAgenda;
    wasSet = true;
    return wasSet;
  }

  public String getMeetingId()
  {
    return meetingId;
  }

  public Date getDate()
  {
    return date;
  }

  public String getTime()
  {
    return time;
  }

  public String getLocation()
  {
    return location;
  }

  public String getAgenda()
  {
    return agenda;
  }
  /* Code from template association_GetOne */
  public MeetingPlanner getMeetingPlanner()
  {
    return meetingPlanner;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMeetingPlanner(MeetingPlanner aMeetingPlanner)
  {
    boolean wasSet = false;
    if (aMeetingPlanner == null)
    {
      return wasSet;
    }

    MeetingPlanner existingMeetingPlanner = meetingPlanner;
    meetingPlanner = aMeetingPlanner;
    if (existingMeetingPlanner != null && !existingMeetingPlanner.equals(aMeetingPlanner))
    {
      existingMeetingPlanner.removeMeeting(this);
    }
    meetingPlanner.addMeeting(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    MeetingPlanner placeholderMeetingPlanner = meetingPlanner;
    this.meetingPlanner = null;
    if(placeholderMeetingPlanner != null)
    {
      placeholderMeetingPlanner.removeMeeting(this);
    }
  }

  // line 68 "model.ump"
  public void updateMeetingDetails(Date date, String time, String location, String agenda){
    
  }

  // line 69 "model.ump"
  public void addAttendee(String memberId){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "meetingId" + ":" + getMeetingId()+ "," +
            "time" + ":" + getTime()+ "," +
            "location" + ":" + getLocation()+ "," +
            "agenda" + ":" + getAgenda()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "meetingPlanner = "+(getMeetingPlanner()!=null?Integer.toHexString(System.identityHashCode(getMeetingPlanner())):"null");
  }
}