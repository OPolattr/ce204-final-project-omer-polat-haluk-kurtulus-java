package com.polat.bookClubManagement.umple;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.util.*;

// line 25 "model.ump"
// line 137 "model.ump"
public class Member
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private String memberId;
  private String name;
  private String email;
  private String readingPreferences;

  //Member Associations
  private List<ReadingSchedule> readingSchedule;
  private List<MeetingPlanner> meetingPlanner;
  private List<DiscussionForum> discussionForum;
  private MemberManagement memberManagement;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(String aMemberId, String aName, String aEmail, String aReadingPreferences, MemberManagement aMemberManagement)
  {
    memberId = aMemberId;
    name = aName;
    email = aEmail;
    readingPreferences = aReadingPreferences;
    readingSchedule = new ArrayList<ReadingSchedule>();
    meetingPlanner = new ArrayList<MeetingPlanner>();
    discussionForum = new ArrayList<DiscussionForum>();
    boolean didAddMemberManagement = setMemberManagement(aMemberManagement);
    if (!didAddMemberManagement)
    {
      throw new RuntimeException("Unable to create member due to memberManagement. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMemberId(String aMemberId)
  {
    boolean wasSet = false;
    memberId = aMemberId;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setReadingPreferences(String aReadingPreferences)
  {
    boolean wasSet = false;
    readingPreferences = aReadingPreferences;
    wasSet = true;
    return wasSet;
  }

  public String getMemberId()
  {
    return memberId;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public String getReadingPreferences()
  {
    return readingPreferences;
  }
  /* Code from template association_GetMany */
  public ReadingSchedule getReadingSchedule(int index)
  {
    ReadingSchedule aReadingSchedule = readingSchedule.get(index);
    return aReadingSchedule;
  }

  public List<ReadingSchedule> getReadingSchedule()
  {
    List<ReadingSchedule> newReadingSchedule = Collections.unmodifiableList(readingSchedule);
    return newReadingSchedule;
  }

  public int numberOfReadingSchedule()
  {
    int number = readingSchedule.size();
    return number;
  }

  public boolean hasReadingSchedule()
  {
    boolean has = readingSchedule.size() > 0;
    return has;
  }

  public int indexOfReadingSchedule(ReadingSchedule aReadingSchedule)
  {
    int index = readingSchedule.indexOf(aReadingSchedule);
    return index;
  }
  /* Code from template association_GetMany */
  public MeetingPlanner getMeetingPlanner(int index)
  {
    MeetingPlanner aMeetingPlanner = meetingPlanner.get(index);
    return aMeetingPlanner;
  }

  public List<MeetingPlanner> getMeetingPlanner()
  {
    List<MeetingPlanner> newMeetingPlanner = Collections.unmodifiableList(meetingPlanner);
    return newMeetingPlanner;
  }

  public int numberOfMeetingPlanner()
  {
    int number = meetingPlanner.size();
    return number;
  }

  public boolean hasMeetingPlanner()
  {
    boolean has = meetingPlanner.size() > 0;
    return has;
  }

  public int indexOfMeetingPlanner(MeetingPlanner aMeetingPlanner)
  {
    int index = meetingPlanner.indexOf(aMeetingPlanner);
    return index;
  }
  /* Code from template association_GetMany */
  public DiscussionForum getDiscussionForum(int index)
  {
    DiscussionForum aDiscussionForum = discussionForum.get(index);
    return aDiscussionForum;
  }

  public List<DiscussionForum> getDiscussionForum()
  {
    List<DiscussionForum> newDiscussionForum = Collections.unmodifiableList(discussionForum);
    return newDiscussionForum;
  }

  public int numberOfDiscussionForum()
  {
    int number = discussionForum.size();
    return number;
  }

  public boolean hasDiscussionForum()
  {
    boolean has = discussionForum.size() > 0;
    return has;
  }

  public int indexOfDiscussionForum(DiscussionForum aDiscussionForum)
  {
    int index = discussionForum.indexOf(aDiscussionForum);
    return index;
  }
  /* Code from template association_GetOne */
  public MemberManagement getMemberManagement()
  {
    return memberManagement;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReadingSchedule()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ReadingSchedule addReadingSchedule()
  {
    return new ReadingSchedule(this);
  }

  public boolean addReadingSchedule(ReadingSchedule aReadingSchedule)
  {
    boolean wasAdded = false;
    if (readingSchedule.contains(aReadingSchedule)) { return false; }
    Member existingMember = aReadingSchedule.getMember();
    boolean isNewMember = existingMember != null && !this.equals(existingMember);
    if (isNewMember)
    {
      aReadingSchedule.setMember(this);
    }
    else
    {
      readingSchedule.add(aReadingSchedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReadingSchedule(ReadingSchedule aReadingSchedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aReadingSchedule, as it must always have a member
    if (!this.equals(aReadingSchedule.getMember()))
    {
      readingSchedule.remove(aReadingSchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReadingScheduleAt(ReadingSchedule aReadingSchedule, int index)
  {  
    boolean wasAdded = false;
    if(addReadingSchedule(aReadingSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReadingSchedule()) { index = numberOfReadingSchedule() - 1; }
      readingSchedule.remove(aReadingSchedule);
      readingSchedule.add(index, aReadingSchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReadingScheduleAt(ReadingSchedule aReadingSchedule, int index)
  {
    boolean wasAdded = false;
    if(readingSchedule.contains(aReadingSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReadingSchedule()) { index = numberOfReadingSchedule() - 1; }
      readingSchedule.remove(aReadingSchedule);
      readingSchedule.add(index, aReadingSchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReadingScheduleAt(aReadingSchedule, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMeetingPlanner()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MeetingPlanner addMeetingPlanner()
  {
    return new MeetingPlanner(this);
  }

  public boolean addMeetingPlanner(MeetingPlanner aMeetingPlanner)
  {
    boolean wasAdded = false;
    if (meetingPlanner.contains(aMeetingPlanner)) { return false; }
    Member existingMember = aMeetingPlanner.getMember();
    boolean isNewMember = existingMember != null && !this.equals(existingMember);
    if (isNewMember)
    {
      aMeetingPlanner.setMember(this);
    }
    else
    {
      meetingPlanner.add(aMeetingPlanner);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMeetingPlanner(MeetingPlanner aMeetingPlanner)
  {
    boolean wasRemoved = false;
    //Unable to remove aMeetingPlanner, as it must always have a member
    if (!this.equals(aMeetingPlanner.getMember()))
    {
      meetingPlanner.remove(aMeetingPlanner);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMeetingPlannerAt(MeetingPlanner aMeetingPlanner, int index)
  {  
    boolean wasAdded = false;
    if(addMeetingPlanner(aMeetingPlanner))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMeetingPlanner()) { index = numberOfMeetingPlanner() - 1; }
      meetingPlanner.remove(aMeetingPlanner);
      meetingPlanner.add(index, aMeetingPlanner);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMeetingPlannerAt(MeetingPlanner aMeetingPlanner, int index)
  {
    boolean wasAdded = false;
    if(meetingPlanner.contains(aMeetingPlanner))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMeetingPlanner()) { index = numberOfMeetingPlanner() - 1; }
      meetingPlanner.remove(aMeetingPlanner);
      meetingPlanner.add(index, aMeetingPlanner);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMeetingPlannerAt(aMeetingPlanner, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDiscussionForum()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public DiscussionForum addDiscussionForum()
  {
    return new DiscussionForum(this);
  }

  public boolean addDiscussionForum(DiscussionForum aDiscussionForum)
  {
    boolean wasAdded = false;
    if (discussionForum.contains(aDiscussionForum)) { return false; }
    Member existingMember = aDiscussionForum.getMember();
    boolean isNewMember = existingMember != null && !this.equals(existingMember);
    if (isNewMember)
    {
      aDiscussionForum.setMember(this);
    }
    else
    {
      discussionForum.add(aDiscussionForum);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDiscussionForum(DiscussionForum aDiscussionForum)
  {
    boolean wasRemoved = false;
    //Unable to remove aDiscussionForum, as it must always have a member
    if (!this.equals(aDiscussionForum.getMember()))
    {
      discussionForum.remove(aDiscussionForum);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDiscussionForumAt(DiscussionForum aDiscussionForum, int index)
  {  
    boolean wasAdded = false;
    if(addDiscussionForum(aDiscussionForum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDiscussionForum()) { index = numberOfDiscussionForum() - 1; }
      discussionForum.remove(aDiscussionForum);
      discussionForum.add(index, aDiscussionForum);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDiscussionForumAt(DiscussionForum aDiscussionForum, int index)
  {
    boolean wasAdded = false;
    if(discussionForum.contains(aDiscussionForum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDiscussionForum()) { index = numberOfDiscussionForum() - 1; }
      discussionForum.remove(aDiscussionForum);
      discussionForum.add(index, aDiscussionForum);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDiscussionForumAt(aDiscussionForum, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMemberManagement(MemberManagement aMemberManagement)
  {
    boolean wasSet = false;
    if (aMemberManagement == null)
    {
      return wasSet;
    }

    MemberManagement existingMemberManagement = memberManagement;
    memberManagement = aMemberManagement;
    if (existingMemberManagement != null && !existingMemberManagement.equals(aMemberManagement))
    {
      existingMemberManagement.removeMember(this);
    }
    memberManagement.addMember(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=readingSchedule.size(); i > 0; i--)
    {
      ReadingSchedule aReadingSchedule = readingSchedule.get(i - 1);
      aReadingSchedule.delete();
    }
    for(int i=meetingPlanner.size(); i > 0; i--)
    {
      MeetingPlanner aMeetingPlanner = meetingPlanner.get(i - 1);
      aMeetingPlanner.delete();
    }
    for(int i=discussionForum.size(); i > 0; i--)
    {
      DiscussionForum aDiscussionForum = discussionForum.get(i - 1);
      aDiscussionForum.delete();
    }
    MemberManagement placeholderMemberManagement = memberManagement;
    this.memberManagement = null;
    if(placeholderMemberManagement != null)
    {
      placeholderMemberManagement.removeMember(this);
    }
  }

  // line 34 "model.ump"
  public void updateProfile(String name, String email, String readingPreferences){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "memberId" + ":" + getMemberId()+ "," +
            "name" + ":" + getName()+ "," +
            "email" + ":" + getEmail()+ "," +
            "readingPreferences" + ":" + getReadingPreferences()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "memberManagement = "+(getMemberManagement()!=null?Integer.toHexString(System.identityHashCode(getMemberManagement())):"null");
  }
}