package com.polat.bookClubManagement.umple;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 104 "model.ump"
// line 186 "model.ump"
public class Like
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Like Attributes
  private String likeId;
  private String memberId;
  private Date creationDate;

  //Like Associations
  private List<Topic> topics;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Like(String aLikeId, String aMemberId, Date aCreationDate)
  {
    likeId = aLikeId;
    memberId = aMemberId;
    creationDate = aCreationDate;
    topics = new ArrayList<Topic>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLikeId(String aLikeId)
  {
    boolean wasSet = false;
    likeId = aLikeId;
    wasSet = true;
    return wasSet;
  }

  public boolean setMemberId(String aMemberId)
  {
    boolean wasSet = false;
    memberId = aMemberId;
    wasSet = true;
    return wasSet;
  }

  public boolean setCreationDate(Date aCreationDate)
  {
    boolean wasSet = false;
    creationDate = aCreationDate;
    wasSet = true;
    return wasSet;
  }

  public String getLikeId()
  {
    return likeId;
  }

  public String getMemberId()
  {
    return memberId;
  }

  public Date getCreationDate()
  {
    return creationDate;
  }
  /* Code from template association_GetMany */
  public Topic getTopic(int index)
  {
    Topic aTopic = topics.get(index);
    return aTopic;
  }

  public List<Topic> getTopics()
  {
    List<Topic> newTopics = Collections.unmodifiableList(topics);
    return newTopics;
  }

  public int numberOfTopics()
  {
    int number = topics.size();
    return number;
  }

  public boolean hasTopics()
  {
    boolean has = topics.size() > 0;
    return has;
  }

  public int indexOfTopic(Topic aTopic)
  {
    int index = topics.indexOf(aTopic);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTopics()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addTopic(Topic aTopic)
  {
    boolean wasAdded = false;
    if (topics.contains(aTopic)) { return false; }
    topics.add(aTopic);
    if (aTopic.indexOfLike(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTopic.addLike(this);
      if (!wasAdded)
      {
        topics.remove(aTopic);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeTopic(Topic aTopic)
  {
    boolean wasRemoved = false;
    if (!topics.contains(aTopic))
    {
      return wasRemoved;
    }

    int oldIndex = topics.indexOf(aTopic);
    topics.remove(oldIndex);
    if (aTopic.indexOfLike(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTopic.removeLike(this);
      if (!wasRemoved)
      {
        topics.add(oldIndex,aTopic);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTopicAt(Topic aTopic, int index)
  {  
    boolean wasAdded = false;
    if(addTopic(aTopic))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTopics()) { index = numberOfTopics() - 1; }
      topics.remove(aTopic);
      topics.add(index, aTopic);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTopicAt(Topic aTopic, int index)
  {
    boolean wasAdded = false;
    if(topics.contains(aTopic))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTopics()) { index = numberOfTopics() - 1; }
      topics.remove(aTopic);
      topics.add(index, aTopic);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTopicAt(aTopic, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Topic> copyOfTopics = new ArrayList<Topic>(topics);
    topics.clear();
    for(Topic aTopic : copyOfTopics)
    {
      aTopic.removeLike(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "likeId" + ":" + getLikeId()+ "," +
            "memberId" + ":" + getMemberId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "creationDate" + "=" + (getCreationDate() != null ? !getCreationDate().equals(this)  ? getCreationDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}