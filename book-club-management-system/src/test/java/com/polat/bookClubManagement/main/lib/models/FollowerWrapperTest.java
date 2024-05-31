package com.polat.bookClubManagement.main.lib.models;

import com.polat.bookClubManagement.main.lib.models.FollowerWrapper;
import com.polat.bookClubManagement.umple.Follower;
import com.polat.bookClubManagement.umple.Topic;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FollowerWrapperTest {

	private FollowerWrapper followerWrapper;
    private Follower follower;
    private String followerId = "follower123";
    private String memberId = "member123";
    private Date creationDate = new Date(System.currentTimeMillis());

    @Before
    public void setUp() {
        followerWrapper = new FollowerWrapper(followerId, memberId, creationDate);
        follower = new Follower(followerId, memberId, creationDate);
    }

    @Test
    public void testSetFollowerId() {
        assertTrue(followerWrapper.setFollowerId(followerId));
        assertEquals(followerId, followerWrapper.getFollowerId());
    }

    @Test
    public void testSetMemberId() {
        assertTrue(followerWrapper.setMemberId(memberId));
        assertEquals(memberId, followerWrapper.getMemberId());
    }

    @Test
    public void testSetCreationDate() {
        assertTrue(followerWrapper.setCreationDate(creationDate));
        assertEquals(creationDate, followerWrapper.getCreationDate());
    }

    @Test
    public void testGetFollowerId() {
        assertEquals(followerId, followerWrapper.getFollowerId());
    }

    @Test
    public void testGetMemberId() {
        assertEquals(memberId, followerWrapper.getMemberId());
    }

    @Test
    public void testGetCreationDate() {
        assertEquals(creationDate, followerWrapper.getCreationDate());
    }

    @Test
    public void testMinimumNumberOfTopics() {
        assertEquals(0, FollowerWrapper.minimumNumberOfTopics());
    }

    @Test
    public void testDelete() {
        followerWrapper.delete();
        assertEquals(0, followerWrapper.numberOfTopics());
    }

    @Test
    public void testToString() {
        String expectedString = "FollowerWrapper[" +
                "followerId" + ":" + followerId + "," +
                "memberId" + ":" + memberId + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "creationDate" + "=" + creationDate.toString();
        assertNotEquals(expectedString, followerWrapper.toString());
    }
}
