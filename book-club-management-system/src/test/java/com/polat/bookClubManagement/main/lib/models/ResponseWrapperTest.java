package com.polat.bookClubManagement.main.lib.models;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.polat.bookClubManagement.main.lib.models.ResponseWrapper;


public class ResponseWrapperTest {

	private ResponseWrapper responseWrapper;

    @Before
    public void setUp() {
        String responseId = "123";
        String content = "Sample content";
        String memberId = "456";
        Date creationDate = new Date(System.currentTimeMillis());
        responseWrapper = new ResponseWrapper(responseId, content, memberId, creationDate);
    }

    @Test
    public void testGetResponseId() {
        assertEquals("123", responseWrapper.getResponseId());
    }

    @Test
    public void testSetResponseId() {
        responseWrapper.setResponseId("789");
        assertEquals("789", responseWrapper.getResponseId());
    }

    @Test
    public void testGetContent() {
        assertEquals("Sample content", responseWrapper.getContent());
    }

    @Test
    public void testSetContent() {
        responseWrapper.setContent("New content");
        assertEquals("New content", responseWrapper.getContent());
    }

    @Test
    public void testGetMemberId() {
        assertEquals("456", responseWrapper.getMemberId());
    }

    @Test
    public void testSetMemberId() {
        responseWrapper.setMemberId("789");
        assertEquals("789", responseWrapper.getMemberId());
    }

    @Test
    public void testGetCreationDate() {
        assertNotNull(responseWrapper.getCreationDate());
    }

    @Test
    public void testSetCreationDate() {
        Date newCreationDate = new Date(System.currentTimeMillis());
        responseWrapper.setCreationDate(newCreationDate);
        assertEquals(newCreationDate, responseWrapper.getCreationDate());
    }

    @Test
    public void testGetTopics() {
        assertNotNull(responseWrapper.getTopics());
        assertTrue(responseWrapper.getTopics().isEmpty());
    }

    @Test
    public void testAddTopic() {
        assertTrue(responseWrapper.addTopic("Topic1"));
        assertTrue(responseWrapper.getTopics().contains("Topic1"));
    }

    @Test
    public void testRemoveTopic() {
        responseWrapper.addTopic("Topic1");
        assertTrue(responseWrapper.removeTopic("Topic1"));
        assertFalse(responseWrapper.getTopics().contains("Topic1"));
    }

    @Test
    public void testToString() {
        String expected = responseWrapper.toString();
        assertNotNull(expected);
    }

}
