package com.polat.bookClubManagement.main.lib.database;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.polat.bookClubManagement.main.lib.models.LikeWrapper;

public class LikeDAL {

    private static final String INSERT_LIKE_SQL = "INSERT INTO `like` (like_id, member_id, creation_date) VALUES (?, ?, ?)";
    private static final String SELECT_LIKE_BY_ID = "SELECT * FROM `like` WHERE like_id = ?";
    private static final String SELECT_ALL_LIKES = "SELECT * FROM `like`";
    private static final String DELETE_LIKE_SQL = "DELETE FROM `like` WHERE like_id = ?";
    private static final String UPDATE_LIKE_SQL = "UPDATE `like` SET member_id = ?, creation_date = ? WHERE like_id = ?";

    public void insertLike(LikeWrapper likeWrapper) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIKE_SQL)) {
            preparedStatement.setString(1, likeWrapper.getLikeId());
            preparedStatement.setString(2, likeWrapper.getMemberId());
            preparedStatement.setDate(3, likeWrapper.getCreationDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public LikeWrapper selectLike(String likeId) {
        LikeWrapper likeWrapper = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIKE_BY_ID)) {
            preparedStatement.setString(1, likeId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String memberId = rs.getString("member_id");
                Date creationDate = rs.getDate("creation_date");
                likeWrapper = new LikeWrapper(likeId, memberId, creationDate);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return likeWrapper;
    }

    public List<LikeWrapper> selectAllLikes() {
        List<LikeWrapper> likes = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIKES)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String likeId = rs.getString("like_id");
                String memberId = rs.getString("member_id");
                Date creationDate = rs.getDate("creation_date");
                likes.add(new LikeWrapper(likeId, memberId, creationDate));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return likes;
    }

    public boolean deleteLike(String likeId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_LIKE_SQL)) {
            statement.setString(1, likeId);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateLike(LikeWrapper likeWrapper) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LIKE_SQL)) {
            statement.setString(1, likeWrapper.getMemberId());
            statement.setDate(2, likeWrapper.getCreationDate());
            statement.setString(3, likeWrapper.getLikeId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
