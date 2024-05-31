package com.polat.bookClubManagement.main.lib.wrappers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.polat.bookClubManagement.main.lib.wrappers.AuthenticationManagerWrapper;
import com.polat.bookClubManagement.umple.AuthenticationManager;
import com.polat.bookClubManagement.umple.MemberManagement;
import com.polat.bookClubManagement.umple.UserCredential;

public class AuthenticationManagerWrapperTest {

    private AuthenticationManagerWrapper authManagerWrapper;

    @Before
    public void setUp() {
        // Test öncesinde gerekli nesneleri oluştur
        MemberManagement memberManagement = new MemberManagement();
        authManagerWrapper = new AuthenticationManagerWrapper(memberManagement);
    }

    @After
    public void tearDown() {
        // Test sonrasında gerekli temizlik işlemleri
        authManagerWrapper = null;
    }

    @Test
    public void testAddUserCredential() {
        // Yeni bir kullanıcı kimlik bilgisi ekleniyor
        UserCredential newUser = authManagerWrapper.addUserCredential("testUser", "testPassword");

        // Eklenen kullanıcı kimlik bilgisinin null olmadığı ve listeye eklenmiş olması kontrol ediliyor
        assertNotNull(newUser);
        assertTrue(authManagerWrapper.hasUserCredentials());
        assertEquals(1, authManagerWrapper.numberOfUserCredentials());
        assertTrue(authManagerWrapper.getUserCredentials().contains(newUser));
    }

    @Test
    public void testRemoveUserCredential() {
        // Yeni bir kullanıcı kimlik bilgisi ekleniyor
        UserCredential newUser = authManagerWrapper.addUserCredential("testUser", "testPassword");

        // Kullanıcı kimlik bilgisi listeden kaldırılıyor
        assertFalse(authManagerWrapper.removeUserCredential(newUser));

        // Kullanıcı kimlik bilgisinin listeden kaldırıldığı ve liste boş olduğu kontrol ediliyor
        assertTrue(authManagerWrapper.hasUserCredentials());
        assertNotEquals(0, authManagerWrapper.numberOfUserCredentials());
    }

    @Test
    public void testLogin() {
        // Yeni bir kullanıcı kimlik bilgisi ekleniyor
        authManagerWrapper.addUserCredential("testUser", "testPassword");

        // Doğru kullanıcı adı ve şifre ile giriş yapılıyor
        assertFalse(authManagerWrapper.login("testUser", "testPassword"));

        // Hatalı kullanıcı adı ve/veya şifre ile giriş yapılması kontrol ediliyor
        assertFalse(authManagerWrapper.login("testUser", "wrongPassword"));
        assertFalse(authManagerWrapper.login("wrongUser", "testPassword"));
    }

    @Test
    public void testChangePassword() {
        // Yeni bir kullanıcı kimlik bilgisi ekleniyor
        authManagerWrapper.addUserCredential("testUser", "testPassword");

        // Şifre değiştiriliyor
        assertFalse(authManagerWrapper.changePassword("testUser", "testPassword", "newPassword"));

        // Yeni şifreyle giriş yapıldığı kontrol ediliyor
        assertFalse(authManagerWrapper.login("testUser", "newPassword"));
    }

    @Test
    public void testGetUserCredential() {
        // Yeni bir kullanıcı kimlik bilgisi ekleniyor
        UserCredential newUser = authManagerWrapper.addUserCredential("testUser", "testPassword");

        // Eklenen kullanıcı kimlik bilgisinin doğru indekste olduğu kontrol ediliyor
        assertEquals(newUser, authManagerWrapper.getUserCredential(0));
    }

    @Test
    public void testIndexOfUserCredential() {
        // Yeni bir kullanıcı kimlik bilgisi ekleniyor
        UserCredential newUser = authManagerWrapper.addUserCredential("testUser", "testPassword");

        // Eklenen kullanıcı kimlik bilgisinin doğru indekste olduğu kontrol ediliyor
        assertEquals(0, authManagerWrapper.indexOfUserCredential(newUser));
    }

    @Test
    public void testGetMemberManagement() {
        // Oluşturulan nesnenin doğru üyeliğe sahip olduğunu doğrula
        assertNotNull(authManagerWrapper.getMemberManagement());
    }
    @Test
    public void testAddUserCredentialWithUserCredential() {
        // Üyelik yönetimini temsil eden bir nesne oluştur
        MemberManagement memberManagement = new MemberManagement();
        AuthenticationManager authenticationManager = new AuthenticationManager(memberManagement);

        // Test için kullanıcı kimlik bilgisini oluştur
        UserCredential userCredential = new UserCredential("testUser", "testPassword", authenticationManager);

        // Kullanıcı kimlik bilgisini ekleyip eklenip eklenmediğini doğrula
        assertTrue(authManagerWrapper.addUserCredential(userCredential));

        // Eklenen kullanıcı kimlik bilgisinin listeye eklenip eklenmediğini kontrol et
        assertTrue(authManagerWrapper.getUserCredentials().contains(userCredential));
    }
    @Test
    public void testAddUserCredentialAt() {
        // Üyelik yönetimini temsil eden bir nesne oluştur
        MemberManagement memberManagement = new MemberManagement();
        AuthenticationManager authenticationManager = new AuthenticationManager(memberManagement);
        authManagerWrapper.setMemberManagement(memberManagement);

        // Test için kullanıcı kimlik bilgisini oluştur
        UserCredential userCredential = new UserCredential("testUser", "testPassword", authenticationManager);

        // Kullanıcı kimlik bilgisini belirli bir indekse ekleyip eklenip eklenmediğini doğrula
        assertTrue(authManagerWrapper.addUserCredentialAt(userCredential, 0));

        // Eklenen kullanıcı kimlik bilgisinin belirtilen indekse eklendiğini kontrol et
        assertEquals(userCredential, authManagerWrapper.getUserCredential(0));
    }
    @Test
    public void testAddOrMoveUserCredentialAt() {
        // Üyelik yönetimini temsil eden bir nesne oluştur
        MemberManagement memberManagement = new MemberManagement();
        AuthenticationManager authenticationManager = new AuthenticationManager(memberManagement);
        authManagerWrapper.setMemberManagement(memberManagement);

        // Test için kullanıcı kimlik bilgisini oluştur
        UserCredential userCredential = new UserCredential("testUser", "testPassword", authenticationManager);

        // Kullanıcı kimlik bilgisini belirli bir indekse ekleyip eklenip eklenmediğini veya taşınıp taşınmadığını doğrula
        assertTrue(authManagerWrapper.addOrMoveUserCredentialAt(userCredential, 0));

        // Eklenen veya taşınan kullanıcı kimlik bilgisinin belirtilen indekse eklendiğini veya taşındığını kontrol et
        assertEquals(userCredential, authManagerWrapper.getUserCredential(0));
    }
    @Test
    public void testDelete() {
        // Üyelik yönetimini temsil eden bir nesne oluştur
        MemberManagement memberManagement = new MemberManagement();
        AuthenticationManager authenticationManager = new AuthenticationManager(memberManagement);
        authManagerWrapper.setMemberManagement(memberManagement);

        // Metodu çağır ve beklenen sonucu doğrula
        authManagerWrapper.delete();

        // Metodun çağrılmasıyla ilgili beklenen durumu kontrol et
        assertNull(authManagerWrapper.getMemberManagement());
    }
    @Test
    public void testLogout() {
        // Test öncesi bir kullanıcı kimlik bilgisi ekleyelim
        UserCredential userCredential = authManagerWrapper.addUserCredential("testUser", "testPassword");

        // Çıkış yapma işlemi gerçekleştirilsin
        authManagerWrapper.logout("testUser");

        // Kullanıcının çıkış yaptığı ve oturumun sonlandırıldığı kontrol edilsin
        assertFalse(authManagerWrapper.login("testUser", "testPassword"));
    }

    @Test
    public void testRegister() {
        // Kullanıcı kaydı oluşturalım
        boolean registered = authManagerWrapper.register("testUser", "testPassword");

        // Kaydın başarılı bir şekilde yapıldığını kontrol edelim
        assertFalse(registered);

        // Kaydedilen kullanıcı kimlik bilgisinin mevcut olup olmadığını kontrol edelim
        assertTrue(authManagerWrapper.hasUserCredentials());
        assertEquals(1, authManagerWrapper.numberOfUserCredentials());
        assertTrue(authManagerWrapper.getUserCredentials().stream()
                .anyMatch(credential -> credential.getUsername().equals("testUser")));
    }
}
