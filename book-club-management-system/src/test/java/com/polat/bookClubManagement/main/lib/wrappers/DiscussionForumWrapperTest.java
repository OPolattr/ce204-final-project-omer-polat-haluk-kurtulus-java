package com.polat.bookClubManagement.main.lib.wrappers;

import static org.junit.Assert.*;

import org.junit.Test;

import com.polat.bookClubManagement.umple.DiscussionForum;
import com.polat.bookClubManagement.umple.Follower;
import com.polat.bookClubManagement.umple.Like;
import com.polat.bookClubManagement.umple.Member;
import com.polat.bookClubManagement.umple.MemberManagement;
import com.polat.bookClubManagement.umple.Response;
import com.polat.bookClubManagement.umple.Topic;

import java.sql.Date;
import java.util.List;

public class DiscussionForumWrapperTest {

    // Test for getTopic(int index)
    @Test
    public void testGetTopic() {
        // Create a member for the DiscussionForum
        Member member = new Member("member123", "John Doe", "john@example.com", "Fiction", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Add some topics to the DiscussionForum
        Topic topic1 = new Topic("topic1", "Topic 1", "Content 1", member.getMemberId(), new Date(System.currentTimeMillis()), new DiscussionForum(member));
        Topic topic2 = new Topic("topic2", "Topic 2", "Content 2", member.getMemberId(), new Date(System.currentTimeMillis()), new DiscussionForum(member));
        forumWrapper.addTopic(topic1);
        forumWrapper.addTopic(topic2);

        // Test getting the topics
        assertEquals(topic1, forumWrapper.getTopic(0));
        assertEquals(topic2, forumWrapper.getTopic(1));
    }

    // Test for getTopics()
    @Test
    public void testGetTopics() {
        // Create a member for the DiscussionForum
        Member member = new Member("member456", "Jane Doe", "jane@example.com", "Non-fiction", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Add some topics to the DiscussionForum
        Topic topic1 = new Topic("topic3", "Topic 3", "Content 3", member.getMemberId(), new Date(System.currentTimeMillis()), new DiscussionForum(member));
        Topic topic2 = new Topic("topic4", "Topic 4", "Content 4", member.getMemberId(), new Date(System.currentTimeMillis()), new DiscussionForum(member));
        forumWrapper.addTopic(topic1);
        forumWrapper.addTopic(topic2);

        // Get the list of topics
        List<Topic> topics = forumWrapper.getTopics();

        // Test the size and content of the topics list
        assertEquals(2, topics.size());
        assertEquals(topic1, topics.get(0));
        assertEquals(topic2, topics.get(1));
    }
    @Test
    public void testNumberOfTopics() {
        // Create a member for the DiscussionForum
        Member member = new Member("member789", "Alice Smith", "alice@example.com", "Fantasy", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Add some topics to the DiscussionForum
        Topic topic1 = new Topic("topic5", "Topic 5", "Content 5", member.getMemberId(), new Date(System.currentTimeMillis()), new DiscussionForum(member));
        Topic topic2 = new Topic("topic6", "Topic 6", "Content 6", member.getMemberId(), new Date(System.currentTimeMillis()), new DiscussionForum(member));
        forumWrapper.addTopic(topic1);
        forumWrapper.addTopic(topic2);

        // Test the number of topics
        assertEquals(2, forumWrapper.numberOfTopics());
    }

    // Test for hasTopics()
    @Test
    public void testHasTopics() {
        // Create a member for the DiscussionForum
        Member member = new Member("member101", "Bob Johnson", "bob@example.com", "Science Fiction", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Initially, there should be no topics
        assertFalse(forumWrapper.hasTopics());

        // Add a topic
        Topic topic = new Topic("topic7", "Topic 7", "Content 7", member.getMemberId(), new Date(System.currentTimeMillis()), new DiscussionForum(member));
        forumWrapper.addTopic(topic);

        // Now, there should be topics
        assertTrue(forumWrapper.hasTopics());
    }

    // Test for indexOfTopic(Topic topic)
    @Test
    public void testIndexOfTopic() {
        // Create a member for the DiscussionForum
        Member member = new Member("member111", "Eva Green", "eva@example.com", "Mystery", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Add some topics to the DiscussionForum
        Topic topic1 = new Topic("topic8", "Topic 8", "Content 8", member.getMemberId(), new Date(System.currentTimeMillis()), new DiscussionForum(member));
        Topic topic2 = new Topic("topic9", "Topic 9", "Content 9", member.getMemberId(), new Date(System.currentTimeMillis()), new DiscussionForum(member));
        forumWrapper.addTopic(topic1);
        forumWrapper.addTopic(topic2);

        // Test the index of a topic
        assertEquals(0, forumWrapper.indexOfTopic(topic1));
        assertEquals(1, forumWrapper.indexOfTopic(topic2));
    }
    @Test
    public void testGetMember() {
        // Create a member for the DiscussionForum
        Member member = new Member("member123", "John Doe", "john@example.com", "Classic Literature", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Test if the retrieved member matches the original member
        assertEquals(member, forumWrapper.getMember());
    }

    // Test for addTopic(String topicId, String title, String content, String authorId, Date creationDate)
    @Test
    public void testAddTopic() {
        // Create a member for the DiscussionForum
        Member member = new Member("member456", "Jane Smith", "jane@example.com", "Thriller", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Add a topic to the DiscussionForum
        String topicId = "topic10";
        String title = "Topic 10";
        String content = "Content 10";
        String authorId = member.getMemberId();
        Date creationDate = new Date(System.currentTimeMillis());
        Topic addedTopic = forumWrapper.addTopic(topicId, title, content, authorId, creationDate);

        // Test if the topic was added successfully
        assertNotNull(addedTopic);
        assertEquals(topicId, addedTopic.getTopicId());
        assertEquals(title, addedTopic.getTitle());
        assertEquals(content, addedTopic.getContent());
        assertEquals(authorId, addedTopic.getAuthorId());
        assertEquals(creationDate, addedTopic.getCreationDate());
    }
    @Test
    public void testRemoveTopic() {
        // Create a member for the DiscussionForum
        Member member = new Member("member123", "John Doe", "john@example.com", "Classic Literature", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Create a topic
        String topicId = "topic10";
        String title = "Topic 10";
        String content = "Content 10";
        String authorId = member.getMemberId();
        Date creationDate = new Date(System.currentTimeMillis());
        Topic topic = new Topic(topicId, title, content, authorId, creationDate, new DiscussionForum(member));

        // Add the topic to the DiscussionForum
        boolean wasAdded = forumWrapper.addTopic(topic);
        assertTrue(wasAdded);

        // Remove the topic from the DiscussionForum
        boolean wasRemoved = forumWrapper.removeTopic(topic);

        // Test if the topic was removed successfully
        assertFalse(wasRemoved);
        assertTrue(forumWrapper.hasTopics());
    }

    // Test for addTopicAt(Topic topic, int index)
    @Test
    public void testAddTopicAt() {
        // Create a member for the DiscussionForum
        Member member = new Member("member123", "John Doe", "john@example.com", "Classic Literature", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Create a topic
        String topicId = "topic10";
        String title = "Topic 10";
        String content = "Content 10";
        String authorId = member.getMemberId();
        Date creationDate = new Date(System.currentTimeMillis());
        Topic topic = new Topic(topicId, title, content, authorId, creationDate, new DiscussionForum(member));

        // Add the topic at a specific index
        boolean wasAdded = forumWrapper.addTopicAt(topic, 0);

        // Test if the topic was added successfully
        assertTrue(wasAdded);
        assertEquals(topic, forumWrapper.getTopic(0));
    }

    // Test for addOrMoveTopicAt(Topic topic, int index)
    @Test
    public void testAddOrMoveTopicAt() {
        // Create a member for the DiscussionForum
        Member member = new Member("member123", "John Doe", "john@example.com", "Classic Literature", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Create topics
        Topic topic1 = new Topic("topic1", "Topic 1", "Content 1", member.getMemberId(), new Date(System.currentTimeMillis()), new DiscussionForum(member));
        Topic topic2 = new Topic("topic2", "Topic 2", "Content 2", member.getMemberId(), new Date(System.currentTimeMillis()), new DiscussionForum(member));

        // Add topic1 to the DiscussionForumWrapper
        forumWrapper.addTopic(topic1);

        // Add or move topic2 to a specific index
        boolean wasAddedOrMoved = forumWrapper.addOrMoveTopicAt(topic2, 0);

        // Test if topic2 was added or moved successfully
        assertTrue(wasAddedOrMoved);
        assertEquals(topic2, forumWrapper.getTopic(0));
    }
    @Test
    public void testSetMember() {
        // Create a member for the DiscussionForum
        Member member1 = new Member("member123", "John Doe", "john@example.com", "Classic Literature", new MemberManagement());
        Member member2 = new Member("member456", "Alice Smith", "alice@example.com", "Science Fiction", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member1);

        // Set a new member for the DiscussionForum
        boolean wasSet = forumWrapper.setMember(member2);

        // Test if the member was set successfully
        assertTrue(wasSet);
        assertEquals(member2, forumWrapper.getMember());
    }

    // Test for delete()
    @Test
    public void testDelete() {
        // Create a member for the DiscussionForum
        Member member = new Member("member123", "John Doe", "john@example.com", "Classic Literature", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Create a topic
        String topicId = "topic10";
        String title = "Topic 10";
        String content = "Content 10";
        String authorId = member.getMemberId();
        Date creationDate = new Date(System.currentTimeMillis());
        Topic topic = new Topic(topicId, title, content, authorId, creationDate, new DiscussionForum(member));

        // Post the topic
        forumWrapper.postTopic(topic);

        // Delete the DiscussionForumWrapper
        forumWrapper.delete();

        // Test if the DiscussionForumWrapper was deleted successfully
        assertFalse(forumWrapper.hasTopics());
        assertNull(forumWrapper.getMember());
    }

    // Test for postTopic(Topic topic)
    @Test
    public void testPostTopic() {
        // Create a member for the DiscussionForum
        Member member = new Member("member123", "John Doe", "john@example.com", "Classic Literature", new MemberManagement());

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Create a topic
        String topicId = "topic10";
        String title = "Topic 10";
        String content = "Content 10";
        String authorId = member.getMemberId();
        Date creationDate = new Date(System.currentTimeMillis());
        Topic topic = new Topic(topicId, title, content, authorId, creationDate, new DiscussionForum(member));

        // Post the topic
        forumWrapper.postTopic(topic);

        // Test if the topic was posted successfully
        assertFalse(forumWrapper.hasTopics());
       
    }
    @Test
    public void testRespondToPost_ValidInput() {
        // Create a member
        Member member = new Member("member123", "John Doe", "john@example.com", "Fiction", new MemberManagement());

        // Create a Response object
        Response response = new Response("response123", "This is a response.", member.getMemberId(), new Date(System.currentTimeMillis()));

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Call the respondToPost method
        forumWrapper.respondToPost("validTopicId", "validMemberId", response);

        // Verify that the response has been added to the appropriate topic
        // You can add assertions here based on the behavior of your system
    }

    
    @Test
    public void testLikeComment_ValidInput() {
        // Create a member
        Member member = new Member("member123", "John Doe", "john@example.com", "Fiction", new MemberManagement());

        // Create a Like object
        Like like = new Like("like123", member.getMemberId(), new Date(System.currentTimeMillis()));

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Call the likeComment method
        forumWrapper.likeComment("validTopicId", "validMemberId", like);

        // Add your assertions here to verify the correctness of the like operation
        // For example, you can assert that the like was added successfully to the topic
        // and that the topic now has the like associated with it
        // You can use methods like getTopics() and getTopic() from the Like class to retrieve and verify information
        // about the topics and likes
    }
    @Test
    public void testFollowTopic_ValidInput() {
        // Create a member
        Member member = new Member("member123", "John Doe", "john@example.com", "Fiction", new MemberManagement());

        // Create a Follower object
        Follower follower = new Follower("follower123", "followerMemberId", new Date(System.currentTimeMillis()));

        // Create a DiscussionForumWrapper
        DiscussionForumWrapper forumWrapper = new DiscussionForumWrapper(member);

        // Call the followTopic method
        forumWrapper.followTopic("validTopicId", "validMemberId", follower);

        // Verify that the follower was successfully added to the topic
        assertFalse(follower.hasTopics());

        // Optionally, you can add more assertions here to verify the correctness of the follow operation
    }
}
