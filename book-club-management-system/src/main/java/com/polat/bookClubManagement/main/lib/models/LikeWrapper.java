package com.polat.bookClubManagement.main.lib.models;


import java.sql.Date;
import java.util.List;

import com.polat.bookClubManagement.umple.Like;
import com.polat.bookClubManagement.umple.Topic;

public class LikeWrapper {

    private Like like;

    public LikeWrapper(String likeId, String memberId, Date creationDate) {
        this.like = new Like(likeId, memberId, creationDate);
    }

    public boolean setLikeId(String likeId) {
        return like.setLikeId(likeId);
    }

    public boolean setMemberId(String memberId) {
        return like.setMemberId(memberId);
    }

    public boolean setCreationDate(Date creationDate) {
        return like.setCreationDate(creationDate);
    }

    public String getLikeId() {
        return like.getLikeId();
    }

    public String getMemberId() {
        return like.getMemberId();
    }

    public Date getCreationDate() {
        return like.getCreationDate();
    }

    public Topic getTopic(int index) {
        return like.getTopic(index);
    }

    public List<Topic> getTopics() {
        return like.getTopics();
    }

    public int numberOfTopics() {
        return like.numberOfTopics();
    }

    public boolean hasTopics() {
        return like.hasTopics();
    }

    public int indexOfTopic(Topic topic) {
        return like.indexOfTopic(topic);
    }

    public static int minimumNumberOfTopics() {
        return Like.minimumNumberOfTopics();
    }

    public boolean addTopic(Topic topic) {
        return like.addTopic(topic);
    }

    public boolean removeTopic(Topic topic) {
        return like.removeTopic(topic);
    }

    public boolean addTopicAt(Topic topic, int index) {
        return like.addTopicAt(topic, index);
    }

    public boolean addOrMoveTopicAt(Topic topic, int index) {
        return like.addOrMoveTopicAt(topic, index);
    }

    public void delete() {
        like.delete();
    }

    @Override
    public String toString() {
        return like.toString();
    }
}
