package com.movie.reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieList {

    // 데이터베이스 연결 정보
    private static final String URL = "jdbc:oracle:thin:@192.168.18.12:1521:xe";
    private static final String USER = "MOVIE";
    private static final String PASSWORD = "movie";

    // 영화 목록 조회
    public List<Movie> selectMovies() {
        String sql = "SELECT movie_id, title, genre, release_date, runtime FROM management";
        List<Movie> movieList = new ArrayList<>();
        System.out.println("=== 영화 목록 ===");
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                movieList.add(new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getDate("release_date"),
                    rs.getInt("runtime")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieList;
    }

    // 테스트용 메인 메소드
    public static void main(String[] args) {
        MovieList movieList = new MovieList();
        List<Movie> movies = movieList.selectMovies();
        movies.forEach(System.out::println);
    }
}