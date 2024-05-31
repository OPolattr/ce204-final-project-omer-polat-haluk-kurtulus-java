package com.polat.bookClubManagement.main.lib.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResponseWrapper {

    private String responseId;
    private String content;
    private String memberId;
    private Date creationDate;
    private List<String> topics;

    public ResponseWrapper(String responseId, String content, String memberId, Date creationDate) {
        this.responseId = responseId;
        this.content = content;
        this.memberId = memberId;
        this.creationDate = creationDate;
        this.topics = new ArrayList<>();
    }

    // Getters and Setters
    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<String> getTopics() {
        return Collections.unmodifiableList(topics);
    }

    public boolean addTopic(String topicId) {
        if (!topics.contains(topicId)) {
            return topics.add(topicId);
        }
        return false;
    }

    public boolean removeTopic(String topicId) {
        return topics.remove(topicId);
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "responseId='" + responseId + '\'' +
                ", content='" + content + '\'' +
                ", memberId='" + memberId + '\'' +
                ", creationDate=" + creationDate +
                ", topics=" + topics +
                '}';
    }
}
