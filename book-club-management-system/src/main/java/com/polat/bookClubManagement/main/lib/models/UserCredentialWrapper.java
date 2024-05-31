package com.polat.bookClubManagement.main.lib.models;

import com.polat.bookClubManagement.umple.AuthenticationManager;
import com.polat.bookClubManagement.umple.UserCredential;

public class UserCredentialWrapper {

  private UserCredential userCredential;

  public UserCredentialWrapper(String username, String password, AuthenticationManager authenticationManager) {
    this.userCredential = new UserCredential(username, password, authenticationManager);
  }

  public boolean setUsername(String username) {
    return userCredential.setUsername(username);
  }

  public boolean setPassword(String password) {
    return userCredential.setPassword(password);
  }

  public String getUsername() {
    return userCredential.getUsername();
  }

  public String getPassword() {
    return userCredential.getPassword();
  }

  public AuthenticationManager getAuthenticationManager() {
    return userCredential.getAuthenticationManager();
  }

  public boolean setAuthenticationManager(AuthenticationManager authenticationManager) {
    return userCredential.setAuthenticationManager(authenticationManager);
  }

  public void delete() {
    userCredential.delete();
  }

  @Override
  public String toString() {
    return userCredential.toString();
  }
}
