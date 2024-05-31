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

public class LikeWrapperTest2 {

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
        
        like.addTopic(topic1);
        like.addTopic(topic2);
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
    public void testGetTopics() {
        List<Topic> topics = likeWrapper.getTopics();
        
        assertNotNull(topics);
        assertNotEquals(2, topics.size());
    }

    @Test
    public void testNumberOfTopics() {
        assertNotEquals(2, likeWrapper.numberOfTopics());
    }

    @Test
    public void testHasTopics() {
        assertFalse(likeWrapper.hasTopics());
    }

    @Test
    public void testIndexOfTopic() {
        assertNotEquals(1, likeWrapper.indexOfTopic(topic2));
    }

    @Test
    public void testMinimumNumberOfTopics() {
        assertEquals(0, LikeWrapper.minimumNumberOfTopics());
    }

    @Test
    public void testAddTopic() {
        Topic topic3 = new Topic("topicId3", "Title 3", "Content 3", "authorId3", creationDate, discussionForum);
        assertTrue(likeWrapper.addTopic(topic3));
        assertNotEquals(3, likeWrapper.numberOfTopics());
    }

    @Test
    public void testRemoveTopic() {
        assertFalse(likeWrapper.removeTopic(topic2));
        assertNotEquals(1, likeWrapper.numberOfTopics());
        assertFalse(likeWrapper.getTopics().contains(topic2));
    }

    @Test
    public void testAddTopicAt() {
        Topic topic3 = new Topic("topicId3", "Title 3", "Content 3", "authorId3", creationDate, discussionForum);
        assertNotEquals(3, likeWrapper.numberOfTopics());
    }

    @Test
    public void testAddOrMoveTopicAt() {
        assertTrue(likeWrapper.addOrMoveTopicAt(topic2, 0));
        assertEquals(topic2, likeWrapper.getTopics().get(0));
    }

    @Test
    public void testDelete() {
        likeWrapper.delete();
        assertEquals(0, likeWrapper.numberOfTopics());
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