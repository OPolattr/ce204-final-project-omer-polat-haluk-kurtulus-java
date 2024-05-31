package com.polat.bookClubManagement.main.lib.wrappers;


import java.util.List;

import com.polat.bookClubManagement.umple.AuthenticationManager;
import com.polat.bookClubManagement.umple.Member;
import com.polat.bookClubManagement.umple.MemberManagement;

public class MemberManagementWrapper {

    private MemberManagement memberManagement;

    public MemberManagementWrapper() {
        this.memberManagement = new MemberManagement();
    }

    public AuthenticationManager getAuthManager(int index) {
        return memberManagement.getAuthManager(index);
    }

    public List<AuthenticationManager> getAuthManager() {
        return memberManagement.getAuthManager();
    }

    public int numberOfAuthManager() {
        return memberManagement.numberOfAuthManager();
    }

    public boolean hasAuthManager() {
        return memberManagement.hasAuthManager();
    }

    public int indexOfAuthManager(AuthenticationManager authManager) {
        return memberManagement.indexOfAuthManager(authManager);
    }

    public Member getMember(int index) {
        return memberManagement.getMember(index);
    }

    public List<Member> getMembers() {
        return memberManagement.getMembers();
    }

    public int numberOfMembers() {
        return memberManagement.numberOfMembers();
    }

    public boolean hasMembers() {
        return memberManagement.hasMembers();
    }

    public int indexOfMember(Member member) {
        return memberManagement.indexOfMember(member);
    }

    public AuthenticationManager addAuthManager() {
        return memberManagement.addAuthManager();
    }

    public boolean addAuthManager(AuthenticationManager authManager) {
        return memberManagement.addAuthManager(authManager);
    }

    public boolean removeAuthManager(AuthenticationManager authManager) {
        return memberManagement.removeAuthManager(authManager);
    }

    public boolean addAuthManagerAt(AuthenticationManager authManager, int index) {
        return memberManagement.addAuthManagerAt(authManager, index);
    }

    public boolean addOrMoveAuthManagerAt(AuthenticationManager authManager, int index) {
        return memberManagement.addOrMoveAuthManagerAt(authManager, index);
    }

    public Member addMember(String memberId, String name, String email, String readingPreferences) {
        return memberManagement.addMember(memberId, name, email, readingPreferences);
    }

    public boolean addMember(Member member) {
        return memberManagement.addMember(member);
    }

    public boolean removeMember(Member member) {
        return memberManagement.removeMember(member);
    }

    public boolean addMemberAt(Member member, int index) {
        return memberManagement.addMemberAt(member, index);
    }

    public boolean addOrMoveMemberAt(Member member, int index) {
        return memberManagement.addOrMoveMemberAt(member, index);
    }

    public void delete() {
        memberManagement.delete();
    }

    public void updateMemberDetails(Member member) {
        memberManagement.updateMemberDetails(member);
    }

    public void deleteMember(String memberId) {
        memberManagement.deleteMember(memberId);
    }

    public Member viewMemberProfile(String memberId) {
        return memberManagement.viewMemberProfile(memberId);
    }
}
