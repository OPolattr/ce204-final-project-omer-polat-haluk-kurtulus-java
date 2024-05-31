package com.polat.bookClubManagement.main.lib.models;

import com.polat.bookClubManagement.main.lib.models.ResponseWrapper;
import com.polat.bookClubManagement.main.lib.models.TopicWrapper;
import org.junit.Test;
import java.sql.Date;
import static org.junit.Assert.*;

import org.junit.Test;

public class TopicWrapperTest {

	@Test
	public void testAddResponse() {
		TopicWrapper topic = createDummyTopic();
		ResponseWrapper response = createDummyResponse();

		assertTrue(topic.getResponses().isEmpty());
		assertTrue(topic.addResponse(response));
		assertEquals(1, topic.getResponses().size());
		assertTrue(topic.getResponses().contains(response));
	}

	@Test
	public void testRemoveResponse() {
		TopicWrapper topic = createDummyTopic();
		ResponseWrapper response = createDummyResponse();
		topic.addResponse(response);

		assertTrue(topic.removeResponse(response));
		assertTrue(topic.getResponses().isEmpty());
	}

	@Test
	public void testAddLike() {
		TopicWrapper topic = createDummyTopic();

		assertTrue(topic.getLikes().isEmpty());
		assertTrue(topic.addLike("like1"));
		assertEquals(1, topic.getLikes().size());
		assertTrue(topic.getLikes().contains("like1"));
	}

	@Test
	public void testRemoveLike() {
		TopicWrapper topic = createDummyTopic();
		topic.addLike("like1");

		assertTrue(topic.removeLike("like1"));
		assertTrue(topic.getLikes().isEmpty());
	}

	@Test
	public void testAddFollower() {
		TopicWrapper topic = createDummyTopic();

		assertTrue(topic.getFollowers().isEmpty());
		assertTrue(topic.addFollower("follower1"));
		assertEquals(1, topic.getFollowers().size());
		assertTrue(topic.getFollowers().contains("follower1"));
	}

	@Test
	public void testRemoveFollower() {
		TopicWrapper topic = createDummyTopic();
		topic.addFollower("follower1");

		assertTrue(topic.removeFollower("follower1"));
		assertTrue(topic.getFollowers().isEmpty());
	}

	private TopicWrapper createDummyTopic() {
		return new TopicWrapper("1", "Test Topic", "Test Content", "author1", new Date(System.currentTimeMillis()),
				"forum1");
	}

	private ResponseWrapper createDummyResponse() {
		return new ResponseWrapper("1", "Test Response", "member1", new Date(System.currentTimeMillis()));
	}
	
	@Test
    public void testGettersAndSetters() {
        TopicWrapper topic = new TopicWrapper("1", "Test Topic", "Test Content", "author1", new Date(System.currentTimeMillis()), "forum1");

        assertEquals("1", topic.getTopicId());
        assertEquals("Test Topic", topic.getTitle());
        assertEquals("Test Content", topic.getContent());
        assertEquals("author1", topic.getAuthorId());
        assertNotNull(topic.getCreationDate());
        assertEquals("forum1", topic.getDiscussionForumId());

        topic.setTopicId("2");
        assertEquals("2", topic.getTopicId());

        topic.setTitle("New Title");
        assertEquals("New Title", topic.getTitle());

        topic.setContent("New Content");
        assertEquals("New Content", topic.getContent());

        topic.setAuthorId("author2");
        assertEquals("author2", topic.getAuthorId());

        Date newCreationDate = new Date(System.currentTimeMillis() - 100000);
        topic.setCreationDate(newCreationDate);
        assertEquals(newCreationDate, topic.getCreationDate());

        topic.setDiscussionForumId("forum2");
        assertEquals("forum2", topic.getDiscussionForumId());
    }
	
	@Test
    public void testToString() {
        TopicWrapper topic = createDummyTopic1();
        ResponseWrapper response1 = createDummyResponse("response1");
        ResponseWrapper response2 = createDummyResponse("response2");
        topic.addResponse(response1);
        topic.addResponse(response2);
        topic.addLike("like1");
        topic.addLike("like2");
        topic.addFollower("follower1");
        topic.addFollower("follower2");

        String expectedString = "TopicWrapper{" +
                "topicId='1', " +
                "title='Test Topic', " +
                "content='Test Content', " +
                "authorId='author1', " +
                "creationDate=" + topic.getCreationDate() + ", " +
                "discussionForumId='forum1', " +
                "responses=[" + response1.toString() + ", " + response2.toString() + "], " +
                "likes=[like1, like2], " +
                "followers=[follower1, follower2]" +
                "}";
        
        assertEquals(expectedString, topic.toString());
    }
	
	private TopicWrapper createDummyTopic1() {
        return new TopicWrapper("1", "Test Topic", "Test Content", "author1", new Date(System.currentTimeMillis()), "forum1");
    }

    private ResponseWrapper createDummyResponse(String id) {
        return new ResponseWrapper(id, "Test Response", "member1", new Date(System.currentTimeMillis()));
    }

}
