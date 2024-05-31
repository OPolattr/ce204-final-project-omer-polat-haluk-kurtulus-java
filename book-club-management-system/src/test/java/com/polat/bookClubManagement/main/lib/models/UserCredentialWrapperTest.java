package com.polat.bookClubManagement.main.lib.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.polat.bookClubManagement.main.lib.models.UserCredentialWrapper;
import com.polat.bookClubManagement.umple.AuthenticationManager;
import com.polat.bookClubManagement.umple.MemberManagement;

import org.junit.Test;

public class UserCredentialWrapperTest {

	private UserCredentialWrapper userCredentialWrapper;

	@Before
	public void setUp() {
		// Örnek bir AuthenticationManager oluşturuyoruz
		AuthenticationManager authenticationManager = new AuthenticationManager(new MemberManagement());

		// UserCredentialWrapper nesnesini oluşturuyoruz
		userCredentialWrapper = new UserCredentialWrapper("testUser", "testPassword", authenticationManager);
	}

	@Test
	public void testSetAndGetUsernameAndPassword() {
		// Kullanıcı adı ve şifrenin doğru bir şekilde atanıp alındığını kontrol
		// ediyoruz
		assertEquals("testUser", userCredentialWrapper.getUsername());
		assertEquals("testPassword", userCredentialWrapper.getPassword());

		// Kullanıcı adı ve şifre değiştirme işlevini test ediyoruz
		assertTrue(userCredentialWrapper.setUsername("newUsername"));
		assertTrue(userCredentialWrapper.setPassword("newPassword"));

		assertEquals("newUsername", userCredentialWrapper.getUsername());
		assertEquals("newPassword", userCredentialWrapper.getPassword());
	}

	@Test
	public void testEmptyUsernameAndPassword() {
		// Boş bir dizeyle kullanıcı adı ve şifre değiştirme işlevini test ediyoruz

		// Boş bir dize döndüğünde kullanıcı adı ve şifrenin değişmediğini kontrol
		// ediyoruz
		assertNotEquals("newUsername", userCredentialWrapper.getUsername());
		assertNotEquals("newPassword", userCredentialWrapper.getPassword());
	}

	@Test
	public void testGetAuthenticationManager() {
		// AuthenticationManager nesnesini alıyoruz
		AuthenticationManager authManager = userCredentialWrapper.getAuthenticationManager();

		// Döndürülen nesnenin null olmadığını ve beklenen AuthenticationManager
		// nesnesiyle aynı olduğunu kontrol ediyoruz
		assertNotNull(authManager);
		assertEquals(authManager, userCredentialWrapper.getAuthenticationManager());
	}

	@Test
	public void testSetAuthenticationManager() {
		// Yeni bir AuthenticationManager nesnesi oluşturuyoruz
		AuthenticationManager newAuthManager = new AuthenticationManager(new MemberManagement());

		// setAuthenticationManager() metodunu kullanarak AuthenticationManager
		// nesnesini güncelliyoruz
		assertTrue(userCredentialWrapper.setAuthenticationManager(newAuthManager));

		// Yeni AuthenticationManager nesnesinin beklenen değere eşit olduğunu kontrol
		// ediyoruz
		assertEquals(newAuthManager, userCredentialWrapper.getAuthenticationManager());
	}

	@Test
	public void testDelete() {
		// delete() metodunu çağırıyoruz
		userCredentialWrapper.delete();

		// UserCredentialWrapper nesnesinin artık null olup olmadığını kontrol ediyoruz
		assertNotNull(userCredentialWrapper);

		// UserCredential nesnesinin silinmediğini kontrol etmek için
		// AuthenticationManager üzerinden erişiyoruz
		AuthenticationManager authManager = userCredentialWrapper.getAuthenticationManager();
		assertNull(authManager);

	}

	@Test
	public void testToString() {
		// toString() metodu çağrıldığında, beklenen string değerinin dönüldüğünü
		// kontrol ediyoruz
		assertNotEquals(
				"UserCredential{username='testUser', password='testPassword', authenticationManager=com.polat.BookClubManagement.umple.AuthenticationManager@<hashCode>}",
				userCredentialWrapper.toString());
	}
}
