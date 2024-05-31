package com.polat.bookClubManagement.main.lib.database;

import static org.junit.Assert.*;

import com.polat.bookClubManagement.main.lib.database.TopicDAL;
import com.polat.bookClubManagement.main.lib.models.TopicWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class TopicDALTest {

    private TopicDAL topicDAL;
    private TopicWrapper testTopic;

    @Before
    public void setUp() {
        topicDAL = new TopicDAL();
        testTopic = new TopicWrapper("test_topic_id", "Test Title", "Test Content", "test_author_id", new Date(System.currentTimeMillis()), "test_forum_id");
        topicDAL.addTopic(testTopic);
    }

    @After
    public void tearDown() {
        // Cleanup after test cases if necessary
    	topicDAL.deleteTopic("test_topic_id");
    }



    @Test
    public void testGetTopic() {
        topicDAL.addTopic(testTopic);
        TopicWrapper retrievedTopic = topicDAL.getTopic(testTopic.getTopicId());
        assertEquals(testTopic.getTitle(), retrievedTopic.getTitle());
        assertEquals(testTopic.getContent(), retrievedTopic.getContent());
        assertEquals(testTopic.getAuthorId(), retrievedTopic.getAuthorId());
        assertEquals(testTopic.getCreationDate(), retrievedTopic.getCreationDate());
        assertEquals(testTopic.getDiscussionForumId(), retrievedTopic.getDiscussionForumId());
    }

    @Test
    public void testUpdateTopic() {
        topicDAL.addTopic(testTopic);
        testTopic.setTitle("Updated Title");
        assertTrue(topicDAL.updateTopic(testTopic));
        TopicWrapper retrievedTopic = topicDAL.getTopic(testTopic.getTopicId());
        assertEquals(testTopic.getTitle(), retrievedTopic.getTitle());
    }

    @Test
    public void testDeleteTopic() {
        topicDAL.addTopic(testTopic);
        assertTrue(topicDAL.deleteTopic("test_topic_id"));
    }
    
    @Test
    public void testGetAllTopics() {
        // Add a test topic to the database
        topicDAL.addTopic(testTopic);

        // Retrieve all topics
        List<TopicWrapper> topics = topicDAL.getAllTopics();

        // Ensure that the list is not null and contains at least one topic
        assertNotNull(topics);
        assertTrue(topics.size() > 0);

        // Verify that the test topic is present in the list
        boolean testTopicFound = false;
        for (TopicWrapper topic : topics) {
            if (topic.getTopicId().equals(testTopic.getTopicId())) {
                testTopicFound = true;
                assertEquals(testTopic.getTitle(), topic.getTitle());
                assertEquals(testTopic.getContent(), topic.getContent());
                assertEquals(testTopic.getAuthorId(), topic.getAuthorId());
                assertEquals(testTopic.getCreationDate(), topic.getCreationDate());
                assertEquals(testTopic.getDiscussionForumId(), topic.getDiscussionForumId());
                break;
            }
        }

        assertTrue("Test topic not found in the list", testTopicFound);
    }
    // Additional tests for other methods can be added similarly
}
    // Additional tests for other methods can be added similarly
