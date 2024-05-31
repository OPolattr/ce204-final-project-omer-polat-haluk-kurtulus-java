package com.polat.bookClubManagement.umple;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.util.*;

// line 12 "model.ump"
// line 126 "model.ump"
public class AuthenticationManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AuthenticationManager Associations
  private List<UserCredential> userCredentials;
  private MemberManagement memberManagement;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AuthenticationManager(MemberManagement aMemberManagement)
  {
    userCredentials = new ArrayList<UserCredential>();
    boolean didAddMemberManagement = setMemberManagement(aMemberManagement);
    if (!didAddMemberManagement)
    {
      throw new RuntimeException("Unable to create authManager due to memberManagement. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public UserCredential getUserCredential(int index)
  {
    UserCredential aUserCredential = userCredentials.get(index);
    return aUserCredential;
  }

  public List<UserCredential> getUserCredentials()
  {
    List<UserCredential> newUserCredentials = Collections.unmodifiableList(userCredentials);
    return newUserCredentials;
  }

  public int numberOfUserCredentials()
  {
    int number = userCredentials.size();
    return number;
  }

  public boolean hasUserCredentials()
  {
    boolean has = userCredentials.size() > 0;
    return has;
  }

  public int indexOfUserCredential(UserCredential aUserCredential)
  {
    int index = userCredentials.indexOf(aUserCredential);
    return index;
  }
  /* Code from template association_GetOne */
  public MemberManagement getMemberManagement()
  {
    return memberManagement;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserCredentials()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public UserCredential addUserCredential(String aUsername, String aPassword)
  {
    return new UserCredential(aUsername, aPassword, this);
  }

  public boolean addUserCredential(UserCredential aUserCredential)
  {
    boolean wasAdded = false;
    if (userCredentials.contains(aUserCredential)) { return false; }
    AuthenticationManager existingAuthenticationManager = aUserCredential.getAuthenticationManager();
    boolean isNewAuthenticationManager = existingAuthenticationManager != null && !this.equals(existingAuthenticationManager);
    if (isNewAuthenticationManager)
    {
      aUserCredential.setAuthenticationManager(this);
    }
    else
    {
      userCredentials.add(aUserCredential);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUserCredential(UserCredential aUserCredential)
  {
    boolean wasRemoved = false;
    //Unable to remove aUserCredential, as it must always have a authenticationManager
    if (!this.equals(aUserCredential.getAuthenticationManager()))
    {
      userCredentials.remove(aUserCredential);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserCredentialAt(UserCredential aUserCredential, int index)
  {  
    boolean wasAdded = false;
    if(addUserCredential(aUserCredential))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserCredentials()) { index = numberOfUserCredentials() - 1; }
      userCredentials.remove(aUserCredential);
      userCredentials.add(index, aUserCredential);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserCredentialAt(UserCredential aUserCredential, int index)
  {
    boolean wasAdded = false;
    if(userCredentials.contains(aUserCredential))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserCredentials()) { index = numberOfUserCredentials() - 1; }
      userCredentials.remove(aUserCredential);
      userCredentials.add(index, aUserCredential);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserCredentialAt(aUserCredential, index);
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
      existingMemberManagement.removeAuthManager(this);
    }
    memberManagement.addAuthManager(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=userCredentials.size(); i > 0; i--)
    {
      UserCredential aUserCredential = userCredentials.get(i - 1);
      aUserCredential.delete();
    }
    MemberManagement placeholderMemberManagement = memberManagement;
    this.memberManagement = null;
    if(placeholderMemberManagement != null)
    {
      placeholderMemberManagement.removeAuthManager(this);
    }
  }

  // line 14 "model.ump"
  public boolean login(String username, String password){
	return false;
    
  }

  // line 15 "model.ump"
  public void logout(String username){
    
  }

  // line 16 "model.ump"
  public boolean register(String username, String password){
	return false;
    
  }

  // line 17 "model.ump"
  public boolean changePassword(String username, String oldPassword, String newPassword){
	return false;
    
  }

}