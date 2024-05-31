package com.polat.bookClubManagement.main.lib.database;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.polat.bookClubManagement.main.lib.models.FollowerWrapper;


public class FollowerDAL {

    private static final String INSERT_FOLLOWER_SQL = "INSERT INTO follower (follower_id, member_id, creation_date) VALUES (?, ?, ?)";
    private static final String SELECT_FOLLOWER_BY_ID = "SELECT * FROM follower WHERE follower_id = ?";
    private static final String SELECT_ALL_FOLLOWERS = "SELECT * FROM follower";
    private static final String DELETE_FOLLOWER_SQL = "DELETE FROM follower WHERE follower_id = ?";
    private static final String UPDATE_FOLLOWER_SQL = "UPDATE follower SET member_id = ?, creation_date = ? WHERE follower_id = ?";

    public void insertFollower(FollowerWrapper followerWrapper) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FOLLOWER_SQL)) {
            preparedStatement.setString(1, followerWrapper.getFollowerId());
            preparedStatement.setString(2, followerWrapper.getMemberId());
            preparedStatement.setDate(3, followerWrapper.getCreationDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public FollowerWrapper selectFollower(String followerId) {
        FollowerWrapper followerWrapper = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FOLLOWER_BY_ID)) {
            preparedStatement.setString(1, followerId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String memberId = rs.getString("member_id");
                Date creationDate = rs.getDate("creation_date");
                followerWrapper = new FollowerWrapper(followerId, memberId, creationDate);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return followerWrapper;
    }

    public List<FollowerWrapper> selectAllFollowers() {
        List<FollowerWrapper> followers = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FOLLOWERS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String followerId = rs.getString("follower_id");
                String memberId = rs.getString("member_id");
                Date creationDate = rs.getDate("creation_date");
                followers.add(new FollowerWrapper(followerId, memberId, creationDate));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return followers;
    }

    public boolean deleteFollower(String followerId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FOLLOWER_SQL)) {
            statement.setString(1, followerId);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateFollower(FollowerWrapper followerWrapper) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_FOLLOWER_SQL)) {
            statement.setString(1, followerWrapper.getMemberId());
            statement.setDate(2, followerWrapper.getCreationDate());
            statement.setString(3, followerWrapper.getFollowerId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
