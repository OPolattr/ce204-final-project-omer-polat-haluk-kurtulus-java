package com.polat.bookClubManagement.main.lib.database;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.polat.bookClubManagement.main.lib.database.FollowerDAL;
import com.polat.bookClubManagement.main.lib.models.FollowerWrapper;

public class FollowerDALTest {

    private FollowerDAL followerDAL;

    @Before
    public void setUp() throws Exception {
        // Test öncesinde gerekli hazırlıkları yap
        followerDAL = new FollowerDAL();
    }

    @After
    public void tearDown() throws Exception {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM follower")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void testInsertFollower() throws SQLException {
        FollowerWrapper followerWrapper = new FollowerWrapper("1", "member1", new Date(System.currentTimeMillis()));
        followerDAL.insertFollower(followerWrapper);

        // Ekleme işlemi başarılıysa, ilgili follower'ın veritabanında olup olmadığını kontrol et
        FollowerWrapper retrievedFollower = followerDAL.selectFollower("1");
        assertNotNull(retrievedFollower);
        assertEquals("member1", retrievedFollower.getMemberId());
    }

    @Test
    public void testSelectFollower() throws SQLException {
        // Öncelikle bir follower ekle
        FollowerWrapper followerWrapper = new FollowerWrapper("2", "member2", new Date(System.currentTimeMillis()));
        followerDAL.insertFollower(followerWrapper);

        // Ardından eklediğimiz follower'ı seçip doğru şekilde seçilip seçilmediğini kontrol et
        FollowerWrapper retrievedFollower = followerDAL.selectFollower("2");
        assertNotNull(retrievedFollower);
        assertEquals("member2", retrievedFollower.getMemberId());
    }

    @Test
    public void testSelectAllFollowers() throws SQLException {
        // Öncelikle birkaç follower ekle
        FollowerWrapper followerWrapper1 = new FollowerWrapper("3", "member3", new Date(System.currentTimeMillis()));
        followerDAL.insertFollower(followerWrapper1);

        FollowerWrapper followerWrapper2 = new FollowerWrapper("4", "member4", new Date(System.currentTimeMillis()));
        followerDAL.insertFollower(followerWrapper2);

        // Ardından tüm follower'ları seç ve doğru sayıda seçildiğini kontrol et
        List<FollowerWrapper> followers = followerDAL.selectAllFollowers();
        assertNotNull(followers);
        assertEquals(2, followers.size());
    }

    @Test
    public void testDeleteFollower() throws SQLException {
        // Öncelikle bir follower ekle
        FollowerWrapper followerWrapper = new FollowerWrapper("5", "member5", new Date(System.currentTimeMillis()));
        followerDAL.insertFollower(followerWrapper);

        // Ardından eklediğimiz follower'ı sil ve silindiğini kontrol et
        assertTrue(followerDAL.deleteFollower("5"));

        // Silinen follower'ın artık veritabanında olmamasını kontrol et
        FollowerWrapper deletedFollower = followerDAL.selectFollower("5");
        assertNull(deletedFollower);
    }

    @Test
    public void testUpdateFollower() throws SQLException {
        // Öncelikle bir follower ekle
        FollowerWrapper followerWrapper = new FollowerWrapper("6", "member6", new Date(System.currentTimeMillis()));
        followerDAL.insertFollower(followerWrapper);

        // Ardından eklediğimiz follower'ın üye ID'sini güncelle
        followerWrapper.setMemberId("updatedMember");

        // Güncellenen follower'ı veritabanına kaydet ve güncellenip güncellenmediğini kontrol et
        assertTrue(followerDAL.updateFollower(followerWrapper));

        // Güncellenen follower'ın veritabanında doğru şekilde güncellendiğini kontrol et
        FollowerWrapper updatedFollower = followerDAL.selectFollower("6");
        assertNotNull(updatedFollower);
        assertEquals("updatedMember", updatedFollower.getMemberId());
    }
}
