package com.polat.bookClubManagement.umple;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 81 "model.ump"
// line 173 "model.ump"
public class Topic
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Topic Attributes
  private String topicId;
  private String title;
  private String content;
  private String authorId;
  private Date creationDate;

  //Topic Associations
  private List<Response> responses;
  private List<Like> likes;
  private List<Follower> followers;
  private DiscussionForum discussionForum;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Topic(String aTopicId, String aTitle, String aContent, String aAuthorId, Date aCreationDate, DiscussionForum aDiscussionForum)
  {
    topicId = aTopicId;
    title = aTitle;
    content = aContent;
    authorId = aAuthorId;
    creationDate = aCreationDate;
    responses = new ArrayList<Response>();
    likes = new ArrayList<Like>();
    followers = new ArrayList<Follower>();
    boolean didAddDiscussionForum = setDiscussionForum(aDiscussionForum);
    if (!didAddDiscussionForum)
    {
      throw new RuntimeException("Unable to create topic due to discussionForum. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTopicId(String aTopicId)
  {
    boolean wasSet = false;
    topicId = aTopicId;
    wasSet = true;
    return wasSet;
  }

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setContent(String aContent)
  {
    boolean wasSet = false;
    content = aContent;
    wasSet = true;
    return wasSet;
  }

  public boolean setAuthorId(String aAuthorId)
  {
    boolean wasSet = false;
    authorId = aAuthorId;
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

  public String getTopicId()
  {
    return topicId;
  }

  public String getTitle()
  {
    return title;
  }

  public String getContent()
  {
    return content;
  }

  public String getAuthorId()
  {
    return authorId;
  }

  public Date getCreationDate()
  {
    return creationDate;
  }
  /* Code from template association_GetMany */
  public Response getResponse(int index)
  {
    Response aResponse = responses.get(index);
    return aResponse;
  }

  public List<Response> getResponses()
  {
    List<Response> newResponses = Collections.unmodifiableList(responses);
    return newResponses;
  }

  public int numberOfResponses()
  {
    int number = responses.size();
    return number;
  }

  public boolean hasResponses()
  {
    boolean has = responses.size() > 0;
    return has;
  }

  public int indexOfResponse(Response aResponse)
  {
    int index = responses.indexOf(aResponse);
    return index;
  }
  /* Code from template association_GetMany */
  public Like getLike(int index)
  {
    Like aLike = likes.get(index);
    return aLike;
  }

  public List<Like> getLikes()
  {
    List<Like> newLikes = Collections.unmodifiableList(likes);
    return newLikes;
  }

  public int numberOfLikes()
  {
    int number = likes.size();
    return number;
  }

  public boolean hasLikes()
  {
    boolean has = likes.size() > 0;
    return has;
  }

  public int indexOfLike(Like aLike)
  {
    int index = likes.indexOf(aLike);
    return index;
  }
  /* Code from template association_GetMany */
  public Follower getFollower(int index)
  {
    Follower aFollower = followers.get(index);
    return aFollower;
  }

  public List<Follower> getFollowers()
  {
    List<Follower> newFollowers = Collections.unmodifiableList(followers);
    return newFollowers;
  }

  public int numberOfFollowers()
  {
    int number = followers.size();
    return number;
  }

  public boolean hasFollowers()
  {
    boolean has = followers.size() > 0;
    return has;
  }

  public int indexOfFollower(Follower aFollower)
  {
    int index = followers.indexOf(aFollower);
    return index;
  }
  /* Code from template association_GetOne */
  public DiscussionForum getDiscussionForum()
  {
    return discussionForum;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfResponses()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addResponse(Response aResponse)
  {
    boolean wasAdded = false;
    if (responses.contains(aResponse)) { return false; }
    responses.add(aResponse);
    if (aResponse.indexOfTopic(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aResponse.addTopic(this);
      if (!wasAdded)
      {
        responses.remove(aResponse);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeResponse(Response aResponse)
  {
    boolean wasRemoved = false;
    if (!responses.contains(aResponse))
    {
      return wasRemoved;
    }

    int oldIndex = responses.indexOf(aResponse);
    responses.remove(oldIndex);
    if (aResponse.indexOfTopic(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aResponse.removeTopic(this);
      if (!wasRemoved)
      {
        responses.add(oldIndex,aResponse);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addResponseAt(Response aResponse, int index)
  {  
    boolean wasAdded = false;
    if(addResponse(aResponse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResponses()) { index = numberOfResponses() - 1; }
      responses.remove(aResponse);
      responses.add(index, aResponse);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveResponseAt(Response aResponse, int index)
  {
    boolean wasAdded = false;
    if(responses.contains(aResponse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResponses()) { index = numberOfResponses() - 1; }
      responses.remove(aResponse);
      responses.add(index, aResponse);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addResponseAt(aResponse, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLikes()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addLike(Like aLike)
  {
    boolean wasAdded = false;
    if (likes.contains(aLike)) { return false; }
    likes.add(aLike);
    if (aLike.indexOfTopic(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLike.addTopic(this);
      if (!wasAdded)
      {
        likes.remove(aLike);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeLike(Like aLike)
  {
    boolean wasRemoved = false;
    if (!likes.contains(aLike))
    {
      return wasRemoved;
    }

    int oldIndex = likes.indexOf(aLike);
    likes.remove(oldIndex);
    if (aLike.indexOfTopic(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLike.removeTopic(this);
      if (!wasRemoved)
      {
        likes.add(oldIndex,aLike);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLikeAt(Like aLike, int index)
  {  
    boolean wasAdded = false;
    if(addLike(aLike))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLikes()) { index = numberOfLikes() - 1; }
      likes.remove(aLike);
      likes.add(index, aLike);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLikeAt(Like aLike, int index)
  {
    boolean wasAdded = false;
    if(likes.contains(aLike))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLikes()) { index = numberOfLikes() - 1; }
      likes.remove(aLike);
      likes.add(index, aLike);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLikeAt(aLike, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFollowers()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addFollower(Follower aFollower)
  {
    boolean wasAdded = false;
    if (followers.contains(aFollower)) { return false; }
    followers.add(aFollower);
    if (aFollower.indexOfTopic(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aFollower.addTopic(this);
      if (!wasAdded)
      {
        followers.remove(aFollower);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeFollower(Follower aFollower)
  {
    boolean wasRemoved = false;
    if (!followers.contains(aFollower))
    {
      return wasRemoved;
    }

    int oldIndex = followers.indexOf(aFollower);
    followers.remove(oldIndex);
    if (aFollower.indexOfTopic(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aFollower.removeTopic(this);
      if (!wasRemoved)
      {
        followers.add(oldIndex,aFollower);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addFollowerAt(Follower aFollower, int index)
  {  
    boolean wasAdded = false;
    if(addFollower(aFollower))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFollowers()) { index = numberOfFollowers() - 1; }
      followers.remove(aFollower);
      followers.add(index, aFollower);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFollowerAt(Follower aFollower, int index)
  {
    boolean wasAdded = false;
    if(followers.contains(aFollower))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFollowers()) { index = numberOfFollowers() - 1; }
      followers.remove(aFollower);
      followers.add(index, aFollower);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFollowerAt(aFollower, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setDiscussionForum(DiscussionForum aDiscussionForum)
  {
    boolean wasSet = false;
    if (aDiscussionForum == null)
    {
      return wasSet;
    }

    DiscussionForum existingDiscussionForum = discussionForum;
    discussionForum = aDiscussionForum;
    if (existingDiscussionForum != null && !existingDiscussionForum.equals(aDiscussionForum))
    {
      existingDiscussionForum.removeTopic(this);
    }
    discussionForum.addTopic(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Response> copyOfResponses = new ArrayList<Response>(responses);
    responses.clear();
    for(Response aResponse : copyOfResponses)
    {
      aResponse.removeTopic(this);
    }
    ArrayList<Like> copyOfLikes = new ArrayList<Like>(likes);
    likes.clear();
    for(Like aLike : copyOfLikes)
    {
      aLike.removeTopic(this);
    }
    ArrayList<Follower> copyOfFollowers = new ArrayList<Follower>(followers);
    followers.clear();
    for(Follower aFollower : copyOfFollowers)
    {
      aFollower.removeTopic(this);
    }
    DiscussionForum placeholderDiscussionForum = discussionForum;
    this.discussionForum = null;
    if(placeholderDiscussionForum != null)
    {
      placeholderDiscussionForum.removeTopic(this);
    }
  }

  // line 91 "model.ump"
  public void updateContent(String content){
    
  }



  public String toString()
  {
    return super.toString() + "["+
            "topicId" + ":" + getTopicId()+ "," +
            "title" + ":" + getTitle()+ "," +
            "content" + ":" + getContent()+ "," +
            "authorId" + ":" + getAuthorId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "creationDate" + "=" + (getCreationDate() != null ? !getCreationDate().equals(this)  ? getCreationDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "discussionForum = "+(getDiscussionForum()!=null?Integer.toHexString(System.identityHashCode(getDiscussionForum())):"null");
  }
}