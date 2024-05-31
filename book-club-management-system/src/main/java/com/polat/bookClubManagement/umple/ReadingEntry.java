package com.polat.bookClubManagement.umple;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.sql.Date;

// line 45 "model.ump"
// line 151 "model.ump"
public class ReadingEntry
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ReadingEntry Attributes
  private String bookTitle;
  private Date startDate;
  private Date endDate;
  private String progress;

  //ReadingEntry Associations
  private ReadingSchedule readingSchedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ReadingEntry(String aBookTitle, Date aStartDate, Date aEndDate, String aProgress, ReadingSchedule aReadingSchedule)
  {
    bookTitle = aBookTitle;
    startDate = aStartDate;
    endDate = aEndDate;
    progress = aProgress;
    boolean didAddReadingSchedule = setReadingSchedule(aReadingSchedule);
    if (!didAddReadingSchedule)
    {
      throw new RuntimeException("Unable to create readingEntry due to readingSchedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBookTitle(String aBookTitle)
  {
    boolean wasSet = false;
    bookTitle = aBookTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setProgress(String aProgress)
  {
    boolean wasSet = false;
    progress = aProgress;
    wasSet = true;
    return wasSet;
  }

  public String getBookTitle()
  {
    return bookTitle;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public String getProgress()
  {
    return progress;
  }
  /* Code from template association_GetOne */
  public ReadingSchedule getReadingSchedule()
  {
    return readingSchedule;
  }
  /* Code from template association_SetOneToMany */
  public boolean setReadingSchedule(ReadingSchedule aReadingSchedule)
  {
    boolean wasSet = false;
    if (aReadingSchedule == null)
    {
      return wasSet;
    }

    ReadingSchedule existingReadingSchedule = readingSchedule;
    readingSchedule = aReadingSchedule;
    if (existingReadingSchedule != null && !existingReadingSchedule.equals(aReadingSchedule))
    {
      existingReadingSchedule.removeReadingEntry(this);
    }
    readingSchedule.addReadingEntry(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ReadingSchedule placeholderReadingSchedule = readingSchedule;
    this.readingSchedule = null;
    if(placeholderReadingSchedule != null)
    {
      placeholderReadingSchedule.removeReadingEntry(this);
    }
  }

  // line 50 "model.ump"
  public void updateProgress(String progress){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "bookTitle" + ":" + getBookTitle()+ "," +
            "progress" + ":" + getProgress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "readingSchedule = "+(getReadingSchedule()!=null?Integer.toHexString(System.identityHashCode(getReadingSchedule())):"null");
  }
}