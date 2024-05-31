package com.polat.bookClubManagement.umple;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.util.*;

// line 2 "model.ump"
// line 120 "model.ump"
public class MemberManagement
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MemberManagement Associations
  private List<AuthenticationManager> authManager;
  private List<Member> members;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MemberManagement()
  {
    authManager = new ArrayList<AuthenticationManager>();
    members = new ArrayList<Member>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public AuthenticationManager getAuthManager(int index)
  {
    AuthenticationManager aAuthManager = authManager.get(index);
    return aAuthManager;
  }

  public List<AuthenticationManager> getAuthManager()
  {
    List<AuthenticationManager> newAuthManager = Collections.unmodifiableList(authManager);
    return newAuthManager;
  }

  public int numberOfAuthManager()
  {
    int number = authManager.size();
    return number;
  }

  public boolean hasAuthManager()
  {
    boolean has = authManager.size() > 0;
    return has;
  }

  public int indexOfAuthManager(AuthenticationManager aAuthManager)
  {
    int index = authManager.indexOf(aAuthManager);
    return index;
  }
  /* Code from template association_GetMany */
  public Member getMember(int index)
  {
    Member aMember = members.get(index);
    return aMember;
  }

  public List<Member> getMembers()
  {
    List<Member> newMembers = Collections.unmodifiableList(members);
    return newMembers;
  }

  public int numberOfMembers()
  {
    int number = members.size();
    return number;
  }

  public boolean hasMembers()
  {
    boolean has = members.size() > 0;
    return has;
  }

  public int indexOfMember(Member aMember)
  {
    int index = members.indexOf(aMember);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAuthManager()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public AuthenticationManager addAuthManager()
  {
    return new AuthenticationManager(this);
  }

  public boolean addAuthManager(AuthenticationManager aAuthManager)
  {
    boolean wasAdded = false;
    if (authManager.contains(aAuthManager)) { return false; }
    MemberManagement existingMemberManagement = aAuthManager.getMemberManagement();
    boolean isNewMemberManagement = existingMemberManagement != null && !this.equals(existingMemberManagement);
    if (isNewMemberManagement)
    {
      aAuthManager.setMemberManagement(this);
    }
    else
    {
      authManager.add(aAuthManager);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAuthManager(AuthenticationManager aAuthManager)
  {
    boolean wasRemoved = false;
    //Unable to remove aAuthManager, as it must always have a memberManagement
    if (!this.equals(aAuthManager.getMemberManagement()))
    {
      authManager.remove(aAuthManager);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAuthManagerAt(AuthenticationManager aAuthManager, int index)
  {  
    boolean wasAdded = false;
    if(addAuthManager(aAuthManager))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAuthManager()) { index = numberOfAuthManager() - 1; }
      authManager.remove(aAuthManager);
      authManager.add(index, aAuthManager);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAuthManagerAt(AuthenticationManager aAuthManager, int index)
  {
    boolean wasAdded = false;
    if(authManager.contains(aAuthManager))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAuthManager()) { index = numberOfAuthManager() - 1; }
      authManager.remove(aAuthManager);
      authManager.add(index, aAuthManager);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAuthManagerAt(aAuthManager, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMembers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Member addMember(String aMemberId, String aName, String aEmail, String aReadingPreferences)
  {
    return new Member(aMemberId, aName, aEmail, aReadingPreferences, this);
  }

  public boolean addMember(Member aMember)
  {
    boolean wasAdded = false;
    if (members.contains(aMember)) { return false; }
    MemberManagement existingMemberManagement = aMember.getMemberManagement();
    boolean isNewMemberManagement = existingMemberManagement != null && !this.equals(existingMemberManagement);
    if (isNewMemberManagement)
    {
      aMember.setMemberManagement(this);
    }
    else
    {
      members.add(aMember);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMember(Member aMember)
  {
    boolean wasRemoved = false;
    //Unable to remove aMember, as it must always have a memberManagement
    if (!this.equals(aMember.getMemberManagement()))
    {
      members.remove(aMember);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMemberAt(Member aMember, int index)
  {  
    boolean wasAdded = false;
    if(addMember(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMembers()) { index = numberOfMembers() - 1; }
      members.remove(aMember);
      members.add(index, aMember);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMemberAt(Member aMember, int index)
  {
    boolean wasAdded = false;
    if(members.contains(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMembers()) { index = numberOfMembers() - 1; }
      members.remove(aMember);
      members.add(index, aMember);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMemberAt(aMember, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=authManager.size(); i > 0; i--)
    {
      AuthenticationManager aAuthManager = authManager.get(i - 1);
      aAuthManager.delete();
    }
    for(int i=members.size(); i > 0; i--)
    {
      Member aMember = members.get(i - 1);
      aMember.delete();
    }
  }



  // line 7 "model.ump"
  public void updateMemberDetails(Member member){
    
  }

  // line 8 "model.ump"
  public void deleteMember(String memberId){
    
  }

  // line 9 "model.ump"
  public Member viewMemberProfile(String memberId){
	return null;
    
  }

}