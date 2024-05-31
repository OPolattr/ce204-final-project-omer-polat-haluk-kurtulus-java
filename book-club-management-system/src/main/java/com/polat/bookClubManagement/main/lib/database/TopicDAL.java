package com.polat.bookClubManagement.main.lib.database;

import com.polat.bookClubManagement.main.lib.models.TopicWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDAL {

	public boolean addTopic(TopicWrapper topic) {
        String sql = "INSERT INTO topic(topic_id, title, content, author_id, creation_date, discussion_forum_member_member_id) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, topic.getTopicId());
            pstmt.setString(2, topic.getTitle());
            pstmt.setString(3, topic.getContent());
            pstmt.setString(4, topic.getAuthorId());
            pstmt.setDate(5, topic.getCreationDate());
            pstmt.setString(6, topic.getDiscussionForumId());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public TopicWrapper getTopic(String topicId) {
        String sql = "SELECT topic_id, title, content, author_id, creation_date, discussion_forum_member_member_id FROM topic WHERE topic_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, topicId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                String authorId = rs.getString("author_id");
                Date creationDate = rs.getDate("creation_date");
                String discussionForumId = rs.getString("discussion_forum_member_member_id");

                return new TopicWrapper(topicId, title, content, authorId, creationDate, discussionForumId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<TopicWrapper> getAllTopics() {
        String sql = "SELECT topic_id, title, content, author_id, creation_date, discussion_forum_member_member_id FROM topic";
        List<TopicWrapper> topics = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String topicId = rs.getString("topic_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String authorId = rs.getString("author_id");
                Date creationDate = rs.getDate("creation_date");
                String discussionForumId = rs.getString("discussion_forum_member_member_id");

                topics.add(new TopicWrapper(topicId, title, content, authorId, creationDate, discussionForumId));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return topics;
    }

    public boolean updateTopic(TopicWrapper topic) {
        String sql = "UPDATE topic SET title = ?, content = ?, author_id = ?, creation_date = ?, discussion_forum_member_member_id = ? WHERE topic_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, topic.getTitle());
            pstmt.setString(2, topic.getContent());
            pstmt.setString(3, topic.getAuthorId());
            pstmt.setDate(4, topic.getCreationDate());
            pstmt.setString(5, topic.getDiscussionForumId());
            pstmt.setString(6, topic.getTopicId());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteTopic(String topicId) {
        String sql = "DELETE FROM topic WHERE topic_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, topicId);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Methods for managing Responses, Likes, and Followers

    public boolean addResponseToTopic(String topicId, String responseId, String content, String memberId, Date creationDate) {
        String sql = "INSERT INTO response(response_id, content, member_id, creation_date, topic_id) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, responseId);
            pstmt.setString(2, content);
            pstmt.setString(3, memberId);
            pstmt.setDate(4, creationDate);
            pstmt.setString(5, topicId);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<String> getResponsesForTopic(String topicId) {
        String sql = "SELECT response_id, content, member_id, creation_date FROM response WHERE topic_id = ?";
        List<String> responses = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, topicId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String responseId = rs.getString("response_id");
                String content = rs.getString("content");
                String memberId = rs.getString("member_id");
                Date creationDate = rs.getDate("creation_date");

                responses.add("Response ID: " + responseId + ", Content: " + content + ", Member ID: " + memberId + ", Creation Date: " + creationDate);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return responses;
    }

    public boolean addLikeToTopic(String topicId, String likeId) {
        String sql = "INSERT INTO `like`(like_id, topic_id) VALUES(?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, likeId);
            pstmt.setString(2, topicId);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<String> getLikesForTopic(String topicId) {
        String sql = "SELECT like_id FROM `like` WHERE topic_id = ?";
        List<String> likes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, topicId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                likes.add(rs.getString("like_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return likes;
    }

    public boolean addFollowerToTopic(String topicId, String followerId) {
        String sql = "INSERT INTO follower(follower_id, topic_id) VALUES(?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, followerId);
            pstmt.setString(2, topicId);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<String> getFollowersForTopic(String topicId) {
        String sql = "SELECT follower_id FROM follower WHERE topic_id = ?";
        List<String> followers = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, topicId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                followers.add(rs.getString("follower_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return followers;
    }
}
