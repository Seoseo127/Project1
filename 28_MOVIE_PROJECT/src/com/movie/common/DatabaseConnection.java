package com.movie.common;

import java.sql.*;

public class DatabaseConnection {

    public static void main(String[] args) {
        // Oracle JDBC 드라이버 로드
        final String DRIVER = "oracle.jdbc.OracleDriver";
        final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Oracle DB 연결 URL
        final String USER = "MOVIE"; // 데이터베이스 사용자 이름
        final String PASSWORD = "movie"; // 데이터베이스 비밀번호

        try {
            // Oracle JDBC 드라이버 로드
            Class.forName(DRIVER);
            System.out.println("Driver loaded successfully.");

            // 데이터베이스 연결
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT movie_id FROM movies")) {

                System.out.println("Connection established.");

                // 결과 출력
                while (rs.next()) {
                    String movieId = rs.getString("movie_id");
                    System.out.println("Movie ID: " + movieId);
                }

            } catch (SQLException e) {
                System.err.println("SQL Error: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

