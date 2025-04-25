package com.movie.management;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserMovie {
    final String URL = "jdbc:oracle:thin:@192.168.18.12:1521:xe";
    final String USER = "MOVIE";
    final String PASSWORD = "movie";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 영화 등록 (movieId 수동 입력 방식)
    public boolean insert(MovieVO vo) {
        String sql = "INSERT INTO management (movie_id, title, genre, release_date, runtime) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

        	ps.setInt(1, vo.getMovieId());  // 소문자로 시작하는 메서드
        	ps.setString(2, vo.getTitle());  // 소문자로 시작하는 메서드
        	ps.setString(3, vo.getGenre());  // 소문자로 시작하는 메서드
        	ps.setDate(4, vo.getReleaseDate());  // 소문자로 시작하는 메서드
        	ps.setInt(5, vo.getRuntime());  // 소문자로 시작하는 메서드
        	
            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("영화 등록 중 오류: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // 영화 수정
    public boolean update(MovieVO vo) {
        String sql = "UPDATE management SET title = ?, genre = ?, release_date = ?, runtime = ? " +
                     "WHERE movie_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

        	ps.setString(1, vo.getTitle());
        	ps.setString(2, vo.getGenre());
        	ps.setDate(3, vo.getReleaseDate());
        	ps.setInt(4, vo.getRuntime());
        	ps.setInt(5, vo.getMovieId());  // WHERE 절

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("영화 수정 중 오류: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // 영화 삭제
    public boolean delete(int movieId) {
        String sql = "DELETE FROM management WHERE movie_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, movieId);
            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("영화 삭제 중 오류: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

	public boolean insertScreening(ScreeningVO newScreening) {
		// TODO Auto-generated method stub
		return false;
	}
}