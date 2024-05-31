package com.polat.bookClubManagement.umple;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 53 "model.ump"
// line 156 "model.ump"
public class MeetingPlanner
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MeetingPlanner Associations
  private List<Meeting> meetings;
  private Member member;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MeetingPlanner(Member aMember)
  {
    meetings = new ArrayList<Meeting>();
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create meetingPlanner due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Meeting getMeeting(int index)
  {
    Meeting aMeeting = meetings.get(index);
    return aMeeting;
  }

  public List<Meeting> getMeetings()
  {
    List<Meeting> newMeetings = Collections.unmodifiableList(meetings);
    return newMeetings;
  }

  public int numberOfMeetings()
  {
    int number = meetings.size();
    return number;
  }

  public boolean hasMeetings()
  {
    boolean has = meetings.size() > 0;
    return has;
  }

  public int indexOfMeeting(Meeting aMeeting)
  {
    int index = meetings.indexOf(aMeeting);
    return index;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMeetings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Meeting addMeeting(String aMeetingId, Date aDate, String aTime, String aLocation, String aAgenda)
  {
    return new Meeting(aMeetingId, aDate, aTime, aLocation, aAgenda, this);
  }

  public boolean addMeeting(Meeting aMeeting)
  {
    boolean wasAdded = false;
    if (meetings.contains(aMeeting)) { return false; }
    MeetingPlanner existingMeetingPlanner = aMeeting.getMeetingPlanner();
    boolean isNewMeetingPlanner = existingMeetingPlanner != null && !this.equals(existingMeetingPlanner);
    if (isNewMeetingPlanner)
    {
      aMeeting.setMeetingPlanner(this);
    }
    else
    {
      meetings.add(aMeeting);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMeeting(Meeting aMeeting)
  {
    boolean wasRemoved = false;
    //Unable to remove aMeeting, as it must always have a meetingPlanner
    if (!this.equals(aMeeting.getMeetingPlanner()))
    {
      meetings.remove(aMeeting);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMeetingAt(Meeting aMeeting, int index)
  {  
    boolean wasAdded = false;
    if(addMeeting(aMeeting))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMeetings()) { index = numberOfMeetings() - 1; }
      meetings.remove(aMeeting);
      meetings.add(index, aMeeting);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMeetingAt(Meeting aMeeting, int index)
  {
    boolean wasAdded = false;
    if(meetings.contains(aMeeting))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMeetings()) { index = numberOfMeetings() - 1; }
      meetings.remove(aMeeting);
      meetings.add(index, aMeeting);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMeetingAt(aMeeting, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMember(Member aMember)
  {
    boolean wasSet = false;
    if (aMember == null)
    {
      return wasSet;
    }

    Member existingMember = member;
    member = aMember;
    if (existingMember != null && !existingMember.equals(aMember))
    {
      existingMember.removeMeetingPlanner(this);
    }
    member.addMeetingPlanner(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=meetings.size(); i > 0; i--)
    {
      Meeting aMeeting = meetings.get(i - 1);
      aMeeting.delete();
    }
    Member placeholderMember = member;
    this.member = null;
    if(placeholderMember != null)
    {
      placeholderMember.removeMeetingPlanner(this);
    }
  }

  // line 56 "model.ump"
  public void scheduleMeeting(Meeting meeting){
    
  }

  // line 57 "model.ump"
  public void manageMeetings(String memberId){
    
  }

  // line 58 "model.ump"
  public void rsvpForMeeting(String memberId, String meetingId){
    
  }

}