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

import com.polat.bookClubManagement.main.lib.database.LikeDAL;
import com.polat.bookClubManagement.main.lib.models.LikeWrapper;

public class LikeDALTest {

    private LikeDAL likeDAL;

    @Before
    public void setUp() throws Exception {
        // Test öncesinde gerekli hazırlıkları yap
        likeDAL = new LikeDAL();
    }

    @After
    public void tearDown() throws Exception {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM `like`")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testInsertLike() throws SQLException {
        LikeWrapper likeWrapper = new LikeWrapper("1", "member1", new Date(System.currentTimeMillis()));
        likeDAL.insertLike(likeWrapper);

        // Ekleme işlemi başarılıysa, ilgili like'ın veritabanında olup olmadığını kontrol et
        LikeWrapper retrievedLike = likeDAL.selectLike("1");
        assertNotNull(retrievedLike);
        assertEquals("member1", retrievedLike.getMemberId());
    }

    @Test
    public void testSelectLike() throws SQLException {
        // Öncelikle bir like ekle
        LikeWrapper likeWrapper = new LikeWrapper("2", "member2", new Date(System.currentTimeMillis()));
        likeDAL.insertLike(likeWrapper);

        // Ardından eklediğimiz like'ı seçip doğru şekilde seçilip seçilmediğini kontrol et
        LikeWrapper retrievedLike = likeDAL.selectLike("2");
        assertNotNull(retrievedLike);
        assertEquals("member2", retrievedLike.getMemberId());
    }

    @Test
    public void testSelectAllLikes() throws SQLException {
        // Öncelikle birkaç like ekle
        LikeWrapper likeWrapper1 = new LikeWrapper("3", "member3", new Date(System.currentTimeMillis()));
        likeDAL.insertLike(likeWrapper1);

        LikeWrapper likeWrapper2 = new LikeWrapper("4", "member4", new Date(System.currentTimeMillis()));
        likeDAL.insertLike(likeWrapper2);

        // Ardından tüm like'ları seç ve doğru sayıda seçildiğini kontrol et
        List<LikeWrapper> likes = likeDAL.selectAllLikes();
        assertNotNull(likes);
        assertEquals(2, likes.size());
    }

    @Test
    public void testDeleteLike() throws SQLException {
        // Öncelikle bir like ekle
        LikeWrapper likeWrapper = new LikeWrapper("5", "member5", new Date(System.currentTimeMillis()));
        likeDAL.insertLike(likeWrapper);

        // Ardından eklediğimiz like'ı sil ve silindiğini kontrol et
        assertTrue(likeDAL.deleteLike("5"));

        // Silinen like'ın artık veritabanında olmamasını kontrol et
        LikeWrapper deletedLike = likeDAL.selectLike("5");
        assertNull(deletedLike);
    }

    @Test
    public void testUpdateLike() throws SQLException {
        // Öncelikle bir like ekle
        LikeWrapper likeWrapper = new LikeWrapper("6", "member6", new Date(System.currentTimeMillis()));
        likeDAL.insertLike(likeWrapper);

        // Ardından eklediğimiz like'ın üye ID'sini güncelle
        likeWrapper.setMemberId("updatedMember");

        // Güncellenen like'ı veritabanına kaydet ve güncellenip güncellenmediğini kontrol et
        assertTrue(likeDAL.updateLike(likeWrapper));

        // Güncellenen like'ın veritabanında doğru şekilde güncellendiğini kontrol et
        LikeWrapper updatedLike = likeDAL.selectLike("6");
        assertNotNull(updatedLike);
        assertEquals("updatedMember", updatedLike.getMemberId());
    }
}
