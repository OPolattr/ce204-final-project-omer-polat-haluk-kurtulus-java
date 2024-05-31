package com.polat.bookClubManagement.main.lib.database;

import com.polat.bookClubManagement.main.lib.models.MemberWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MemberDALTest {

    private MemberDAL memberDAL;

    @Before
    public void setUp() {
        // Her test başlamadan önce yeni bir MemberDAL örneği oluşturuyoruz
        memberDAL = new MemberDAL();
    }

    @After
    public void tearDown() {
        // Her test tamamlandıktan sonra memberDAL'ı temizle
        memberDAL = null;
    }

    @Test
    public void testAddMember() {
        MemberWrapper member = new MemberWrapper("123", "John Doe", "john@example.com", "Fiction");
        assertTrue(memberDAL.addMember(member));
    }

    @Test
    public void testGetMember() {
        MemberWrapper member = new MemberWrapper("123", "John Doe", "john@example.com", "Fiction");
        memberDAL.addMember(member);
        MemberWrapper retrievedMember = memberDAL.getMember("123");
        assertNotNull(retrievedMember);
        assertEquals(member.getMemberId(), retrievedMember.getMemberId());
        assertEquals(member.getName(), retrievedMember.getName());
        assertEquals(member.getEmail(), retrievedMember.getEmail());
        assertEquals(member.getReadingPreferences(), retrievedMember.getReadingPreferences());
    }

    @Test
    public void testGetAllMembers() {
        List<MemberWrapper> membersBefore = memberDAL.getAllMembers();
        MemberWrapper member = new MemberWrapper("123", "John Doe", "john@example.com", "Fiction");
        memberDAL.addMember(member);
        List<MemberWrapper> membersAfter = memberDAL.getAllMembers();
        assertEquals(membersBefore.size(), membersAfter.size());
    }

    @Test
    public void testUpdateMember() {
        MemberWrapper member = new MemberWrapper("123", "John Doe", "john@example.com", "Fiction");
        memberDAL.addMember(member);
        MemberWrapper updatedMember = new MemberWrapper("123", "Jane Doe", "jane@example.com", "Non-Fiction");
        assertTrue(memberDAL.updateMember(updatedMember));
        MemberWrapper retrievedMember = memberDAL.getMember("123");
        assertEquals(updatedMember.getName(), retrievedMember.getName());
        assertEquals(updatedMember.getEmail(), retrievedMember.getEmail());
        assertEquals(updatedMember.getReadingPreferences(), retrievedMember.getReadingPreferences());
    }

    @Test
    public void testDeleteMember() {
        MemberWrapper member = new MemberWrapper("123", "John Doe", "john@example.com", "Fiction");
        memberDAL.addMember(member);
        assertTrue(memberDAL.deleteMember("123"));
        assertNull(memberDAL.getMember("123"));
    }
}
