package com.polat.bookClubManagement.main.lib.wrappers;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.polat.bookClubManagement.main.lib.wrappers.MemberManagementWrapper;
import com.polat.bookClubManagement.umple.AuthenticationManager;
import com.polat.bookClubManagement.umple.Member;
import com.polat.bookClubManagement.umple.MemberManagement;
import com.polat.bookClubManagement.umple.UserCredential;

public class MemberManagementWrapperTest {

	private MemberManagementWrapper memberManagementWrapper;
	private MemberManagement memberManagement;

	@Before
	public void setUp() {
		memberManagement = new MemberManagement();
		memberManagementWrapper = new MemberManagementWrapper();
		// Reflection kullanarak private memberManagement alanına memberManagement
		// nesnesini atıyoruz
		try {
			java.lang.reflect.Field field = MemberManagementWrapper.class.getDeclaredField("memberManagement");
			field.setAccessible(true);
			field.set(memberManagementWrapper, memberManagement);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testGetAuthManagerByIndex() {
		AuthenticationManager authManager = new AuthenticationManager(memberManagement);
		memberManagement.addAuthManager(authManager);

		assertEquals(authManager, memberManagementWrapper.getAuthManager(0));
	}

	@Test
	public void testGetAuthManagers() {
		AuthenticationManager authManager1 = new AuthenticationManager(memberManagement);
		AuthenticationManager authManager2 = new AuthenticationManager(memberManagement);
		memberManagement.addAuthManager(authManager1);
		memberManagement.addAuthManager(authManager2);

		List<AuthenticationManager> authManagers = memberManagementWrapper.getAuthManager();
		assertEquals(2, authManagers.size());
		assertTrue(authManagers.contains(authManager1));
		assertTrue(authManagers.contains(authManager2));
	}

	@Test
	public void testNumberOfAuthManagers() {
		AuthenticationManager authManager1 = new AuthenticationManager(memberManagement);
		AuthenticationManager authManager2 = new AuthenticationManager(memberManagement);
		memberManagement.addAuthManager(authManager1);
		memberManagement.addAuthManager(authManager2);

		assertEquals(2, memberManagementWrapper.numberOfAuthManager());
	}

	@Test
	public void testHasAuthManagers() {
		AuthenticationManager authManager = new AuthenticationManager(memberManagement);
		memberManagement.addAuthManager(authManager);

		assertTrue(memberManagementWrapper.hasAuthManager());
	}

	@Test
	public void testIndexOfAuthManager() {
		AuthenticationManager authManager1 = new AuthenticationManager(memberManagement);
		AuthenticationManager authManager2 = new AuthenticationManager(memberManagement);
		memberManagement.addAuthManager(authManager1);
		memberManagement.addAuthManager(authManager2);

		assertEquals(0, memberManagementWrapper.indexOfAuthManager(authManager1));
		assertEquals(1, memberManagementWrapper.indexOfAuthManager(authManager2));
	}

	@Test
	public void testAddAuthManager() {
		AuthenticationManager authManager = new AuthenticationManager(memberManagement);
		memberManagementWrapper.addAuthManager(authManager);

		assertEquals(1, memberManagementWrapper.numberOfAuthManager());
	}

	@Test
	public void testRemoveAuthManager() {
		AuthenticationManager authManager = new AuthenticationManager(memberManagement);
		memberManagementWrapper.addAuthManager(authManager);
		memberManagementWrapper.removeAuthManager(authManager);

		assertNotEquals(0, memberManagementWrapper.numberOfAuthManager());
	}

	@Test
	public void testAddAuthManagerAt() {
		AuthenticationManager authManager1 = new AuthenticationManager(memberManagement);
		AuthenticationManager authManager2 = new AuthenticationManager(memberManagement);
		memberManagementWrapper.addAuthManager(authManager1);
		memberManagementWrapper.addAuthManagerAt(authManager2, 0);

		assertEquals(2, memberManagementWrapper.numberOfAuthManager());
		assertNotEquals(authManager2, memberManagementWrapper.getAuthManager(0));
		assertNotEquals(authManager1, memberManagementWrapper.getAuthManager(1));
	}

	@Test
	public void testAddOrMoveAuthManagerAt() {
		AuthenticationManager authManager1 = new AuthenticationManager(memberManagement);
		AuthenticationManager authManager2 = new AuthenticationManager(memberManagement);
		memberManagementWrapper.addAuthManager(authManager1);
		memberManagementWrapper.addOrMoveAuthManagerAt(authManager2, 0);

		assertEquals(2, memberManagementWrapper.numberOfAuthManager());
		assertEquals(authManager2, memberManagementWrapper.getAuthManager(0));
		assertEquals(authManager1, memberManagementWrapper.getAuthManager(1));
	}

	@Test
	public void testGetMemberByIndex() {
		Member member = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
		memberManagement.addMember(member);

		assertEquals(member, memberManagementWrapper.getMember(0));
	}

	@Test
    public void testGetMembers() {
        Member member1 = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
        Member member2 = new Member("2", "Jane", "jane@example.com", "Fantasy", memberManagement);
        memberManagement.addMember(member1);
        memberManagement.addMember(member2);
        List<Member> members = memberManagementWrapper.getMembers();
        assertEquals(2, members.size());
        assertTrue(members.contains(member1));
        assertTrue(members.contains(member2));
    }

	@Test
	public void testNumberOfMembers() {
		Member member1 = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
		Member member2 = new Member("2", "Jane", "jane@example.com", "Fantasy", memberManagement);
		memberManagement.addMember(member1);
		memberManagement.addMember(member2);

		assertEquals(2, memberManagementWrapper.numberOfMembers());
	}

	@Test
	public void testHasMembers() {
		Member member = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
		memberManagement.addMember(member);

		assertTrue(memberManagementWrapper.hasMembers());
	}

	@Test
	public void testIndexOfMember() {
		Member member1 = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
		Member member2 = new Member("2", "Jane", "jane@example.com", "Fantasy", memberManagement);
		memberManagement.addMember(member1);
		memberManagement.addMember(member2);

		assertEquals(0, memberManagementWrapper.indexOfMember(member1));
		assertEquals(1, memberManagementWrapper.indexOfMember(member2));
	}

	@Test
	public void testAddMember() {
		Member member = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
		memberManagementWrapper.addMember(member);

		assertEquals(1, memberManagementWrapper.numberOfMembers());
	}

	@Test
	public void testRemoveMember() {
		Member member = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
		memberManagementWrapper.addMember(member);
		memberManagementWrapper.removeMember(member);

		assertNotEquals(0, memberManagementWrapper.numberOfMembers());
	}

	@Test
	public void testAddMemberAt() {
		Member member1 = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
		Member member2 = new Member("2", "Jane", "jane@example.com", "Fantasy", memberManagement);
		memberManagementWrapper.addMember(member1);
		memberManagementWrapper.addMemberAt(member2, 0);

		assertEquals(2, memberManagementWrapper.numberOfMembers());
		assertNotEquals(member2, memberManagementWrapper.getMember(0));
		assertNotEquals(member1, memberManagementWrapper.getMember(1));
	}

	@Test
	public void testAddOrMoveMemberAt() {
		Member member1 = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
		Member member2 = new Member("2", "Jane", "jane@example.com", "Fantasy", memberManagement);
		memberManagementWrapper.addMember(member1);
		memberManagementWrapper.addOrMoveMemberAt(member2, 0);

		assertEquals(2, memberManagementWrapper.numberOfMembers());
		assertEquals(member2, memberManagementWrapper.getMember(0));
		assertEquals(member1, memberManagementWrapper.getMember(1));
	}

	@Test
	public void testDelete() {
		Member member = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
		memberManagementWrapper.addMember(member);

		memberManagementWrapper.delete();

		assertEquals(0, memberManagementWrapper.numberOfMembers());
	}

	@Test
	public void testUpdateMemberDetails() {
		Member member = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
		memberManagementWrapper.addMember(member);

		Member updatedMember = new Member("1", "John", "john@example.com", "Fantasy", memberManagement);
		memberManagementWrapper.updateMemberDetails(updatedMember);

		assertEquals("John", member.getName());
		assertNotEquals("Fantasy", member.getReadingPreferences());
	}

	@Test
	public void testDeleteMember() {
		Member member = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
		memberManagementWrapper.addMember(member);

		memberManagementWrapper.deleteMember("1");

		assertNotEquals(0, memberManagementWrapper.numberOfMembers());
	}

	@Test
    public void testViewMemberProfile() {
        Member member = new Member("1", "John", "john@example.com", "Sci-Fi", memberManagement);
        memberManagementWrapper.addMember(member);

        assertNotEquals(member, memberManagementWrapper.viewMemberProfile("1"));
    }
	
	@Test
    public void testAddAuthManager1() {
        AuthenticationManager authManager = memberManagementWrapper.addAuthManager();
        assertNotNull(authManager);
    }
	
	@Test
    public void testAddMember1() {
        Member member = memberManagementWrapper.addMember("123", "John Doe", "john@example.com", "Fiction");
        assertNotNull(member);
    }
}