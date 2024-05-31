package com.polat.bookClubManagement.main.lib.models;

import static org.junit.Assert.*;
import java.sql.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import com.polat.bookClubManagement.main.lib.models.LikeWrapper;
import com.polat.bookClubManagement.umple.DiscussionForum;
import com.polat.bookClubManagement.umple.Like;
import com.polat.bookClubManagement.umple.Member;
import com.polat.bookClubManagement.umple.MemberManagement;
import com.polat.bookClubManagement.umple.Topic;

public class LikeWrapperTest3 {

    private LikeWrapper likeWrapper;
    private Topic topic1;
    private Topic topic2;
    private Topic topic3;
    private DiscussionForum discussionForum;
    private Member member;
    private MemberManagement memberManagement;
    private Date creationDate;

    @Before
    public void setUp() {
        creationDate = new Date(System.currentTimeMillis());
        memberManagement = new MemberManagement();
        member = new Member("memberId1", "Member Name", "member@example.com", "Fiction", memberManagement);
        discussionForum = new DiscussionForum(member);

        topic1 = new Topic("topicId1", "Title 1", "Content 1", "authorId1", creationDate, discussionForum);
        topic2 = new Topic("topicId2", "Title 2", "Content 2", "authorId2", creationDate, discussionForum);
        topic3 = new Topic("topicId3", "Title 3", "Content 3", "authorId3", creationDate, discussionForum);
        
        likeWrapper = new LikeWrapper("likeWrapperId", "memberId1", creationDate);
        likeWrapper.addTopic(topic1);
        likeWrapper.addTopic(topic2);
    }

    @Test
    public void testGetTopic() {
        // Verify that getTopic returns the correct topic at index 0
        Topic retrievedTopic1 = likeWrapper.getTopic(0);
        assertEquals(topic1, retrievedTopic1);

        // Verify that getTopic returns the correct topic at index 1
        Topic retrievedTopic2 = likeWrapper.getTopic(1);
        assertEquals(topic2, retrievedTopic2);

        // Verify that getTopic throws an IndexOutOfBoundsException for an invalid index
        try {
            likeWrapper.getTopic(2); // This should be out of bounds
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            // Test passed
        }
    }

    @Test
    public void testAddTopicAt() {
        // Add topic3 at index 1
        boolean wasAdded = likeWrapper.addTopicAt(topic3, 1);
        assertTrue(wasAdded);

        // Verify that topic3 is now at index 1
        Topic retrievedTopic = likeWrapper.getTopic(1);
        assertEquals(topic3, retrievedTopic);

        // Verify that topic2 is now at index 2
        retrievedTopic = likeWrapper.getTopic(2);
        assertEquals(topic2, retrievedTopic);

        // Verify the size of the list
        assertEquals(3, likeWrapper.numberOfTopics());

        // Add topic1 at index 0 (move existing topic)
        wasAdded = likeWrapper.addTopicAt(topic1, 0);
        assertFalse(wasAdded);

        // Verify the new order of topics
        assertEquals(topic1, likeWrapper.getTopic(0));
        assertEquals(topic3, likeWrapper.getTopic(1));
        assertEquals(topic2, likeWrapper.getTopic(2));
    }
}
