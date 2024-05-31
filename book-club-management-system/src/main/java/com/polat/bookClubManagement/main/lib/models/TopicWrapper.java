package com.polat.bookClubManagement.main.lib.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopicWrapper {

    private String topicId;
    private String title;
    private String content;
    private String authorId;
    private Date creationDate;
    private String discussionForumId;
    private List<ResponseWrapper> responses;
    private List<String> likes;
    private List<String> followers;

    public TopicWrapper(String topicId, String title, String content, String authorId, Date creationDate, String discussionForumId) {
        this.topicId = topicId;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.creationDate = creationDate;
        this.discussionForumId = discussionForumId;
        this.responses = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    // Getters and Setters
    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDiscussionForumId() {
        return discussionForumId;
    }

    public void setDiscussionForumId(String discussionForumId) {
        this.discussionForumId = discussionForumId;
    }

    public List<ResponseWrapper> getResponses() {
        return Collections.unmodifiableList(responses);
    }

    public boolean addResponse(ResponseWrapper response) {
        return responses.add(response);
    }

    public boolean removeResponse(ResponseWrapper response) {
        return responses.remove(response);
    }

    public List<String> getLikes() {
        return Collections.unmodifiableList(likes);
    }

    public boolean addLike(String likeId) {
        if (!likes.contains(likeId)) {
            return likes.add(likeId);
        }
        return false;
    }

    public boolean removeLike(String likeId) {
        return likes.remove(likeId);
    }

    public List<String> getFollowers() {
        return Collections.unmodifiableList(followers);
    }

    public boolean addFollower(String followerId) {
        if (!followers.contains(followerId)) {
            return followers.add(followerId);
        }
        return false;
    }

    public boolean removeFollower(String followerId) {
        return followers.remove(followerId);
    }

    @Override
    public String toString() {
        return "TopicWrapper{" +
                "topicId='" + topicId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorId='" + authorId + '\'' +
                ", creationDate=" + creationDate +
                ", discussionForumId='" + discussionForumId + '\'' +
                ", responses=" + responses +
                ", likes=" + likes +
                ", followers=" + followers +
                '}';
    }
}
