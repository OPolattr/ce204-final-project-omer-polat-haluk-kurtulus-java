package com.polat.bookClubManagement.main.lib.database;

import com.polat.bookClubManagement.main.lib.database.ReadingEntryDAL;
import com.polat.bookClubManagement.main.lib.models.ReadingEntryWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ReadingEntryDALTest {

    private ReadingEntryDAL readingEntryDAL;

    @Before
    public void setUp() {
        readingEntryDAL = new ReadingEntryDAL();
    }

    @After
    public void tearDown() {
        // Veritabanındaki test verilerini temizleme işlemi
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            // Tabloyu temizle
            stmt.executeUpdate("DELETE FROM reading_entry");
        } catch (SQLException e) {
            System.err.println("Veritabanı temizleme hatası: " + e.getMessage());
        }
    }


    @Test
    public void testAddReadingEntry() {
        // Test verilerini oluştur
        ReadingEntryWrapper readingEntry = new ReadingEntryWrapper("Test Book", Date.valueOf("2024-06-01"),
                Date.valueOf("2024-07-01"), "In progress", "member_id");

        // Ekleme işlemi
        assertTrue(readingEntryDAL.addReadingEntry(readingEntry));
    }

    @Test
    public void testGetReadingEntry() {
        // Test verilerini ekleyelim
        ReadingEntryWrapper readingEntry = new ReadingEntryWrapper("Test Book", Date.valueOf("2024-06-01"),
                Date.valueOf("2024-07-01"), "In progress", "member_id");
        readingEntryDAL.addReadingEntry(readingEntry);

        // Getirme işlemi
        ReadingEntryWrapper retrievedEntry = readingEntryDAL.getReadingEntry("Test Book");

        // Doğrulama
        assertNotNull(retrievedEntry);
        assertEquals("Test Book", retrievedEntry.getBookTitle());
    }

    @Test
    public void testGetAllReadingEntries() {
        // Test verilerini ekleyelim
        ReadingEntryWrapper readingEntry1 = new ReadingEntryWrapper("Test Book 1", Date.valueOf("2024-06-01"),
                Date.valueOf("2024-07-01"), "In progress", "member_id");
        ReadingEntryWrapper readingEntry2 = new ReadingEntryWrapper("Test Book 2", Date.valueOf("2024-06-01"),
                Date.valueOf("2024-07-01"), "Completed", "member_id");
        readingEntryDAL.addReadingEntry(readingEntry1);
        readingEntryDAL.addReadingEntry(readingEntry2);

        // Tüm kayıtları getirme işlemi
        List<ReadingEntryWrapper> entries = readingEntryDAL.getAllReadingEntries();

        // Doğrulama
        assertEquals(2, entries.size());
    }

    @Test
    public void testUpdateReadingEntry() {
        // Test verilerini ekleyelim
        ReadingEntryWrapper readingEntry = new ReadingEntryWrapper("Test Book", Date.valueOf("2024-06-01"),
                Date.valueOf("2024-07-01"), "In progress", "member_id");
        readingEntryDAL.addReadingEntry(readingEntry);

        // Güncelleme işlemi
        readingEntry.setProgress("Completed");
        assertTrue(readingEntryDAL.updateReadingEntry(readingEntry));

        // Güncellenen veriyi kontrol etme işlemi
        ReadingEntryWrapper updatedEntry = readingEntryDAL.getReadingEntry("Test Book");
        assertEquals("Completed", updatedEntry.getProgress());
    }

    @Test
    public void testDeleteReadingEntry() {
        // Test verilerini ekleyelim
        ReadingEntryWrapper readingEntry = new ReadingEntryWrapper("Test Book", Date.valueOf("2024-06-01"),
                Date.valueOf("2024-07-01"), "In progress", "member_id");
        readingEntryDAL.addReadingEntry(readingEntry);

        // Silme işlemi
        assertTrue(readingEntryDAL.deleteReadingEntry("Test Book"));

        // Silinen veriyi kontrol etme işlemi
        ReadingEntryWrapper deletedEntry = readingEntryDAL.getReadingEntry("Test Book");
        assertNull(deletedEntry);
    }
}
