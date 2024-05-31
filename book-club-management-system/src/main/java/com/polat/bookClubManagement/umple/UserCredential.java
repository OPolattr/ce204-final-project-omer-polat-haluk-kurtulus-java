package com.polat.bookClubManagement.umple;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/



// line 20 "model.ump"
// line 132 "model.ump"
public class UserCredential
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserCredential Attributes
  private String username;
  private String password;

  //UserCredential Associations
  private AuthenticationManager authenticationManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserCredential(String aUsername, String aPassword, AuthenticationManager aAuthenticationManager)
  {
    username = aUsername;
    password = aPassword;
    boolean didAddAuthenticationManager = setAuthenticationManager(aAuthenticationManager);
    if (!didAddAuthenticationManager)
    {
      throw new RuntimeException("Unable to create userCredential due to authenticationManager. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  public AuthenticationManager getAuthenticationManager()
  {
    return authenticationManager;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAuthenticationManager(AuthenticationManager aAuthenticationManager)
  {
    boolean wasSet = false;
    if (aAuthenticationManager == null)
    {
      return wasSet;
    }

    AuthenticationManager existingAuthenticationManager = authenticationManager;
    authenticationManager = aAuthenticationManager;
    if (existingAuthenticationManager != null && !existingAuthenticationManager.equals(aAuthenticationManager))
    {
      existingAuthenticationManager.removeUserCredential(this);
    }
    authenticationManager.addUserCredential(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    AuthenticationManager placeholderAuthenticationManager = authenticationManager;
    this.authenticationManager = null;
    if(placeholderAuthenticationManager != null)
    {
      placeholderAuthenticationManager.removeUserCredential(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "authenticationManager = "+(getAuthenticationManager()!=null?Integer.toHexString(System.identityHashCode(getAuthenticationManager())):"null");
  }
}