package com.polat.bookClubManagement.main.lib.models;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.polat.bookClubManagement.main.lib.models.LikeWrapper;
import com.polat.bookClubManagement.umple.DiscussionForum;
import com.polat.bookClubManagement.umple.Like;
import com.polat.bookClubManagement.umple.Member;
import com.polat.bookClubManagement.umple.MemberManagement;
import com.polat.bookClubManagement.umple.Topic;

public class LikeWrapperTest {

    private LikeWrapper likeWrapper;
    private Like like;
    private Topic topic1;
    private Topic topic2;
    private DiscussionForum discussionForum;
    private Member member;
    private MemberManagement memberManagement;
    private Date creationDate;

    @Before
    public void setUp() {
        creationDate = new Date(System.currentTimeMillis());
        like = new Like("likeId1", "memberId1", creationDate);
        likeWrapper = new LikeWrapper("likeWrapperId", "memberId1", creationDate);

        memberManagement = new MemberManagement();
        member = new Member("memberId1", "Member Name", "member@example.com", "Fiction", memberManagement);
        discussionForum = new DiscussionForum(member);

        topic1 = new Topic("topicId1", "Title 1", "Content 1", "authorId1", creationDate, discussionForum);
        topic2 = new Topic("topicId2", "Title 2", "Content 2", "authorId2", creationDate, discussionForum);
    }

    @Test
    public void testSetAndGetLikeId() {
        assertTrue(likeWrapper.setLikeId("likeId2"));
        assertEquals("likeId2", likeWrapper.getLikeId());
    }

    @Test
    public void testSetAndGetMemberId1() {
        assertTrue(likeWrapper.setMemberId("memberId2"));
        assertEquals("memberId2", likeWrapper.getMemberId());
    }

    @Test
    public void testSetAndGetCreationDate() {
        Date newDate = new Date(System.currentTimeMillis() - 100000);
        assertTrue(likeWrapper.setCreationDate(newDate));
        assertEquals(newDate, likeWrapper.getCreationDate());
    }

    @Test
    public void testGetTopic() {
        discussionForum.addTopic(topic1);
        discussionForum.addTopic(topic2);
        
        assertEquals(topic1, discussionForum.getTopic(0));
    }

    @Test
    public void testGetTopics() {
        discussionForum.addTopic(topic1);
        discussionForum.addTopic(topic2);
        
        List<Topic> topics = new ArrayList<>();
        topics.add(topic1);
        topics.add(topic2);

        assertEquals(topics, discussionForum.getTopics());
    }

    @Test
    public void testNumberOfTopics() {
        discussionForum.addTopic(topic1);
        discussionForum.addTopic(topic2);

        assertEquals(2, discussionForum.numberOfTopics());
    }

    @Test
    public void testHasTopics() {
        discussionForum.addTopic(topic1);

        assertTrue(discussionForum.hasTopics());
    }

    @Test
    public void testIndexOfTopic() {
        discussionForum.addTopic(topic1);
        discussionForum.addTopic(topic2);

        assertEquals(1, discussionForum.indexOfTopic(topic2));
    }

    @Test
    public void testMinimumNumberOfTopics() {
        assertEquals(0, DiscussionForum.minimumNumberOfTopics());
    }

    @Test
    public void testAddTopic() {
        assertFalse(discussionForum.addTopic(topic1));
    }

    @Test
    public void testRemoveTopic() {
        discussionForum.addTopic(topic1);
        discussionForum.addTopic(topic2);

        assertFalse(discussionForum.removeTopic(topic2));
    }

    @Test
    public void testAddTopicAt() {
        discussionForum.addTopic(topic1);
        discussionForum.addTopic(topic2);

        assertFalse(discussionForum.addTopicAt(topic2, 0));
        assertNotEquals(topic2, discussionForum.getTopic(0));
    }

    @Test
    public void testAddOrMoveTopicAt() {
        discussionForum.addTopic(topic1);
        discussionForum.addTopic(topic2);

        assertTrue(discussionForum.addOrMoveTopicAt(topic2, 0));
        assertEquals(topic2, discussionForum.getTopic(0));
    }

    @Test
    public void testDeleteDiscussionForum() {
        discussionForum.addTopic(topic1);
        discussionForum.addTopic(topic2);

        discussionForum.delete();
        assertFalse(discussionForum.hasTopics());
    }

    @Test
    public void testToString() {
        String expected = "Like: likeWrapperId";
        assertNotEquals(expected, likeWrapper.toString());
    }

    // Additional tests for Member class
    @Test
    public void testSetAndGetMemberId() {
        assertTrue(member.setMemberId("newMemberId"));
        assertEquals("newMemberId", member.getMemberId());
    }

    @Test
    public void testSetAndGetName() {
        assertTrue(member.setName("New Name"));
        assertEquals("New Name", member.getName());
    }

    @Test
    public void testSetAndGetEmail() {
        assertTrue(member.setEmail("newemail@example.com"));
        assertEquals("newemail@example.com", member.getEmail());
    }

    @Test
    public void testSetAndGetReadingPreferences() {
        assertTrue(member.setReadingPreferences("Non-Fiction"));
        assertEquals("Non-Fiction", member.getReadingPreferences());
    }

    @Test
    public void testUpdateProfile() {
        member.updateProfile("Updated Name", "updatedemail@example.com", "Mystery");
        assertNotEquals("Updated Name", member.getName());
        assertNotEquals("updatedemail@example.com", member.getEmail());
        assertNotEquals("Mystery", member.getReadingPreferences());
    }

    @Test
    public void testToStringMember() {
        String expected = "Member: memberId1, Name: Member Name, Email: member@example.com, Preferences: Fiction";
        String actual = "Member: " + member.getMemberId() + ", Name: " + member.getName() + ", Email: " + member.getEmail() + ", Preferences: " + member.getReadingPreferences();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteMember() {
        member.delete();
        assertFalse(member.hasDiscussionForum());
    }
}
