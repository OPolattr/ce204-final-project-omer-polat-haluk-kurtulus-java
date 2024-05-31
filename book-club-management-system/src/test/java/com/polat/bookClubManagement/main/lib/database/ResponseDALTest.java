package com.polat.bookClubManagement.main.lib.database;

import com.polat.bookClubManagement.main.lib.database.ResponseDAL;
import com.polat.bookClubManagement.main.lib.models.ResponseWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ResponseDALTest {

    private ResponseDAL responseDAL;
    private ResponseWrapper testResponse;

    @Before
    public void setUp() {
        responseDAL = new ResponseDAL();
        // Test için bir response oluştur
        testResponse = new ResponseWrapper("testResponseId", "Test Content", "testMemberId", new Date(System.currentTimeMillis()));
    }

    @After
    public void tearDown() {
        // Test sonrası temizlik işlemleri
        // Eğer testResponse veritabanında eklenmişse, bu veriyi silmek gerekir.
        responseDAL.deleteResponse(testResponse.getResponseId());
    }

    @Test
    public void testAddResponse() {
        assertTrue(responseDAL.addResponse(testResponse));
    }

    @Test
    public void testGetResponse() {
        responseDAL.addResponse(testResponse);
        ResponseWrapper retrievedResponse = responseDAL.getResponse(testResponse.getResponseId());
        assertNotNull(retrievedResponse);
        assertEquals(testResponse.getResponseId(), retrievedResponse.getResponseId());
    }

    @Test
    public void testGetAllResponses() {
        responseDAL.addResponse(testResponse);
        List<ResponseWrapper> responses = responseDAL.getAllResponses();
        assertTrue(responses.size() > 0);
        boolean found = false;
        for (ResponseWrapper response : responses) {
            if (response.getResponseId().equals(testResponse.getResponseId())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testUpdateResponse() {
        responseDAL.addResponse(testResponse);
        testResponse.setContent("Updated Content");
        assertTrue(responseDAL.updateResponse(testResponse));
        ResponseWrapper updatedResponse = responseDAL.getResponse(testResponse.getResponseId());
        assertNotNull(updatedResponse);
        assertEquals("Updated Content", updatedResponse.getContent());
    }

    @Test
    public void testDeleteResponse() {
        responseDAL.addResponse(testResponse);
        assertTrue(responseDAL.deleteResponse(testResponse.getResponseId()));
        assertNull(responseDAL.getResponse(testResponse.getResponseId()));
    }
}
