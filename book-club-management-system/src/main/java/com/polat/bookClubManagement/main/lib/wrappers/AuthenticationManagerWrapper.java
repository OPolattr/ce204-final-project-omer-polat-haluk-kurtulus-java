package com.polat.bookClubManagement.main.lib.wrappers;

import java.util.List;

import com.polat.bookClubManagement.umple.AuthenticationManager;
import com.polat.bookClubManagement.umple.MemberManagement;
import com.polat.bookClubManagement.umple.UserCredential;

public class AuthenticationManagerWrapper {

    private AuthenticationManager authManager;

    public AuthenticationManagerWrapper(MemberManagement memberManagement) {
        this.authManager = new AuthenticationManager(memberManagement);
    }
    
    

    public UserCredential getUserCredential(int index) {
        return authManager.getUserCredential(index);
    }

    public List<UserCredential> getUserCredentials() {
        return authManager.getUserCredentials();
    }

    public int numberOfUserCredentials() {
        return authManager.numberOfUserCredentials();
    }

    public boolean hasUserCredentials() {
        return authManager.hasUserCredentials();
    }

    public int indexOfUserCredential(UserCredential aUserCredential) {
        return authManager.indexOfUserCredential(aUserCredential);
    }

    public MemberManagement getMemberManagement() {
        return authManager.getMemberManagement();
    }

    public UserCredential addUserCredential(String aUsername, String aPassword) {
        return authManager.addUserCredential(aUsername, aPassword);
    }

    public boolean addUserCredential(UserCredential aUserCredential) {
        return authManager.addUserCredential(aUserCredential);
    }

    public boolean removeUserCredential(UserCredential aUserCredential) {
        return authManager.removeUserCredential(aUserCredential);
    }

    public boolean addUserCredentialAt(UserCredential aUserCredential, int index) {
        return authManager.addUserCredentialAt(aUserCredential, index);
    }

    public boolean addOrMoveUserCredentialAt(UserCredential aUserCredential, int index) {
        return authManager.addOrMoveUserCredentialAt(aUserCredential, index);
    }

    public boolean setMemberManagement(MemberManagement aMemberManagement) {
        return authManager.setMemberManagement(aMemberManagement);
    }

    public void delete() {
        authManager.delete();
    }

    public boolean login(String username, String password) {
        return authManager.login(username, password);
    }

    public void logout(String username) {
        authManager.logout(username);
    }

    public boolean register(String username, String password) {
        UserCredential newUser = new UserCredential(username, password, authManager);
        boolean added = authManager.addUserCredential(newUser);
        return added;
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        return authManager.changePassword(username, oldPassword, newPassword);
    }
}
