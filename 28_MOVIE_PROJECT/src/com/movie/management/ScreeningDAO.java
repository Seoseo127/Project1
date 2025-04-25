package com.movie.management;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreeningDAO {
    private static final String URL = "jdbc:oracle:thin:@192.168.18.12:1521:xe";
    private static final String USER = "MOVIE";
    private static final String PASSWORD = "movie";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private Timestamp parseTimestamp(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return Timestamp.valueOf(LocalDateTime.parse(input, formatter));
    }

    public boolean insert(int movieId, String screeningtime) {
        String sql = "INSERT INTO management_screening (movie_id, screening_time) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, movieId);
            ps.setTimestamp(2, parseTimestamp(screeningtime));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("상영시간 추가 실패: " + e.getMessage());
        }
        return false;
    }

    public boolean update(int movieId, String oldTime, String newTime) {
        String sql = "UPDATE management_screening SET screening_time = ? WHERE movie_id = ? AND screening_time = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, parseTimestamp(newTime));
            ps.setInt(2, movieId);
            ps.setTimestamp(3, parseTimestamp(oldTime));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("상영시간 수정 실패: " + e.getMessage());
        }
        return false;
    }

    public boolean delete(int movieId, String screeningtime) {
        String sql = "DELETE FROM management_screening WHERE movie_id = ? AND screening_time = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, movieId);
            ps.setTimestamp(2, parseTimestamp(screeningtime));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("상영시간 삭제 실패: " + e.getMessage());
        }
        return false;
    }
}


