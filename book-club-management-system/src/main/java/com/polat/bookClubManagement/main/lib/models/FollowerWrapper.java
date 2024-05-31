package com.polat.bookClubManagement.main.lib.models;



import java.sql.Date;
import java.util.List;

import com.polat.bookClubManagement.umple.Follower;
import com.polat.bookClubManagement.umple.Topic;

public class FollowerWrapper {

    private Follower follower;

    public FollowerWrapper(String followerId, String memberId, Date creationDate) {
        this.follower = new Follower(followerId, memberId, creationDate);
    }

    public boolean setFollowerId(String followerId) {
        return follower.setFollowerId(followerId);
    }

    public boolean setMemberId(String memberId) {
        return follower.setMemberId(memberId);
    }

    public boolean setCreationDate(Date creationDate) {
        return follower.setCreationDate(creationDate);
    }

    public String getFollowerId() {
        return follower.getFollowerId();
    }

    public String getMemberId() {
        return follower.getMemberId();
    }

    public Date getCreationDate() {
        return follower.getCreationDate();
    }

    public Topic getTopic(int index) {
        return follower.getTopic(index);
    }

    public List<Topic> getTopics() {
        return follower.getTopics();
    }

    public int numberOfTopics() {
        return follower.numberOfTopics();
    }

    public boolean hasTopics() {
        return follower.hasTopics();
    }

    public int indexOfTopic(Topic topic) {
        return follower.indexOfTopic(topic);
    }

    public static int minimumNumberOfTopics() {
        return Follower.minimumNumberOfTopics();
    }

    public boolean addTopic(Topic topic) {
        return follower.addTopic(topic);
    }

    public boolean removeTopic(Topic topic) {
        return follower.removeTopic(topic);
    }

    public boolean addTopicAt(Topic topic, int index) {
        return follower.addTopicAt(topic, index);
    }

    public boolean addOrMoveTopicAt(Topic topic, int index) {
        return follower.addOrMoveTopicAt(topic, index);
    }

    public void delete() {
        follower.delete();
    }

    @Override
    public String toString() {
        return follower.toString();
    }
}
