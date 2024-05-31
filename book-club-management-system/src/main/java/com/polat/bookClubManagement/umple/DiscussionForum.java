package com.polat.bookClubManagement.umple;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 72 "model.ump"
// line 167 "model.ump"
public class DiscussionForum
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DiscussionForum Associations
  private List<Topic> topics;
  private Member member;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DiscussionForum(Member aMember)
  {
    topics = new ArrayList<Topic>();
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create discussionForum due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTopics()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Topic addTopic(String aTopicId, String aTitle, String aContent, String aAuthorId, Date aCreationDate)
  {
    return new Topic(aTopicId, aTitle, aContent, aAuthorId, aCreationDate, this);
  }

  public boolean addTopic(Topic aTopic)
  {
    boolean wasAdded = false;
    if (topics.contains(aTopic)) { return false; }
    DiscussionForum existingDiscussionForum = aTopic.getDiscussionForum();
    boolean isNewDiscussionForum = existingDiscussionForum != null && !this.equals(existingDiscussionForum);
    if (isNewDiscussionForum)
    {
      aTopic.setDiscussionForum(this);
    }
    else
    {
      topics.add(aTopic);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTopic(Topic aTopic)
  {
    boolean wasRemoved = false;
    //Unable to remove aTopic, as it must always have a discussionForum
    if (!this.equals(aTopic.getDiscussionForum()))
    {
      topics.remove(aTopic);
      wasRemoved = true;
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
      existingMember.removeDiscussionForum(this);
    }
    member.addDiscussionForum(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=topics.size(); i > 0; i--)
    {
      Topic aTopic = topics.get(i - 1);
      aTopic.delete();
    }
    Member placeholderMember = member;
    this.member = null;
    if(placeholderMember != null)
    {
      placeholderMember.removeDiscussionForum(this);
    }
  }

  // line 75 "model.ump"
  public void postTopic(Topic topic){
    
  }

  // line 76 "model.ump"
  public void respondToPost(String topicId, String memberId, Response response){
    
  }

  // line 77 "model.ump"
  public void likeComment(String topicId, String memberId, Like like){
    
  }

  // line 78 "model.ump"
  public void followTopic(String topicId, String memberId, Follower follower){
    
  }

}