package com.polat.bookClubManagement.umple;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 37 "model.ump"
// line 145 "model.ump"
public class ReadingSchedule
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ReadingSchedule Associations
  private List<ReadingEntry> readingEntries;
  private Member member;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ReadingSchedule(Member aMember)
  {
    readingEntries = new ArrayList<ReadingEntry>();
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create readingSchedule due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public ReadingEntry getReadingEntry(int index)
  {
    ReadingEntry aReadingEntry = readingEntries.get(index);
    return aReadingEntry;
  }

  public List<ReadingEntry> getReadingEntries()
  {
    List<ReadingEntry> newReadingEntries = Collections.unmodifiableList(readingEntries);
    return newReadingEntries;
  }

  public int numberOfReadingEntries()
  {
    int number = readingEntries.size();
    return number;
  }

  public boolean hasReadingEntries()
  {
    boolean has = readingEntries.size() > 0;
    return has;
  }

  public int indexOfReadingEntry(ReadingEntry aReadingEntry)
  {
    int index = readingEntries.indexOf(aReadingEntry);
    return index;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReadingEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ReadingEntry addReadingEntry(String aBookTitle, Date aStartDate, Date aEndDate, String aProgress)
  {
    return new ReadingEntry(aBookTitle, aStartDate, aEndDate, aProgress, this);
  }

  public boolean addReadingEntry(ReadingEntry aReadingEntry)
  {
    boolean wasAdded = false;
    if (readingEntries.contains(aReadingEntry)) { return false; }
    ReadingSchedule existingReadingSchedule = aReadingEntry.getReadingSchedule();
    boolean isNewReadingSchedule = existingReadingSchedule != null && !this.equals(existingReadingSchedule);
    if (isNewReadingSchedule)
    {
      aReadingEntry.setReadingSchedule(this);
    }
    else
    {
      readingEntries.add(aReadingEntry);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReadingEntry(ReadingEntry aReadingEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aReadingEntry, as it must always have a readingSchedule
    if (!this.equals(aReadingEntry.getReadingSchedule()))
    {
      readingEntries.remove(aReadingEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReadingEntryAt(ReadingEntry aReadingEntry, int index)
  {  
    boolean wasAdded = false;
    if(addReadingEntry(aReadingEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReadingEntries()) { index = numberOfReadingEntries() - 1; }
      readingEntries.remove(aReadingEntry);
      readingEntries.add(index, aReadingEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReadingEntryAt(ReadingEntry aReadingEntry, int index)
  {
    boolean wasAdded = false;
    if(readingEntries.contains(aReadingEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReadingEntries()) { index = numberOfReadingEntries() - 1; }
      readingEntries.remove(aReadingEntry);
      readingEntries.add(index, aReadingEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReadingEntryAt(aReadingEntry, index);
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
      existingMember.removeReadingSchedule(this);
    }
    member.addReadingSchedule(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=readingEntries.size(); i > 0; i--)
    {
      ReadingEntry aReadingEntry = readingEntries.get(i - 1);
      aReadingEntry.delete();
    }
    Member placeholderMember = member;
    this.member = null;
    if(placeholderMember != null)
    {
      placeholderMember.removeReadingSchedule(this);
    }
  }

  // line 40 "model.ump"
  public void organizeReadingSchedule(ReadingEntry readingEntry){
    
  }

  // line 41 "model.ump"
  public List<ReadingEntry> trackReadingProgress(String memberId){
	return readingEntries;
    
  }

  // line 42 "model.ump"
  public void setReadingReminders(String memberId, Date reminderDate){
    
  }

}