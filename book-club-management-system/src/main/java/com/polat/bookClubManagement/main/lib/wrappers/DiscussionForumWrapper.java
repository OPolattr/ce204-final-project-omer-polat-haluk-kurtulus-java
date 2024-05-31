package com.polat.bookClubManagement.main.lib.wrappers;


import java.sql.Date;
import java.util.List;

import com.polat.bookClubManagement.umple.DiscussionForum;
import com.polat.bookClubManagement.umple.Follower;
import com.polat.bookClubManagement.umple.Like;
import com.polat.bookClubManagement.umple.Member;
import com.polat.bookClubManagement.umple.Response;
import com.polat.bookClubManagement.umple.Topic;

public class DiscussionForumWrapper {

    private DiscussionForum discussionForum;

    public DiscussionForumWrapper(Member member) {
        this.discussionForum = new DiscussionForum(member);
    }

    public Topic getTopic(int index) {
        return discussionForum.getTopic(index);
    }

    public List<Topic> getTopics() {
        return discussionForum.getTopics();
    }

    public int numberOfTopics() {
        return discussionForum.numberOfTopics();
    }

    public boolean hasTopics() {
        return discussionForum.hasTopics();
    }

    public int indexOfTopic(Topic topic) {
        return discussionForum.indexOfTopic(topic);
    }

    public Member getMember() {
        return discussionForum.getMember();
    }

    public Topic addTopic(String topicId, String title, String content, String authorId, Date creationDate) {
        return discussionForum.addTopic(topicId, title, content, authorId, creationDate);
    }

    public boolean addTopic(Topic topic) {
        return discussionForum.addTopic(topic);
    }

    public boolean removeTopic(Topic topic) {
        return discussionForum.removeTopic(topic);
    }

    public boolean addTopicAt(Topic topic, int index) {
        return discussionForum.addTopicAt(topic, index);
    }

    public boolean addOrMoveTopicAt(Topic topic, int index) {
        return discussionForum.addOrMoveTopicAt(topic, index);
    }

    public boolean setMember(Member member) {
        return discussionForum.setMember(member);
    }

    public void delete() {
        discussionForum.delete();
    }

    public void postTopic(Topic topic) {
        discussionForum.postTopic(topic);
    }

    public void respondToPost(String topicId, String memberId, Response response) {
        discussionForum.respondToPost(topicId, memberId, response);
    }

    public void likeComment(String topicId, String memberId, Like like) {
        discussionForum.likeComment(topicId, memberId, like);
    }

    public void followTopic(String topicId, String memberId, Follower follower) {
        discussionForum.followTopic(topicId, memberId, follower);
    }
}
