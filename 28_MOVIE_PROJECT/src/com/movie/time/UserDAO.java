package com.movie.time;

import java.sql.*;

public class UserDAO {

    // DB 연결 메소드 (가정)
    private static Connection getConnection() throws SQLException {
        String URL = "jdbc:oracle:thin:@192.168.18.12:1521:xe";  // DB URL (예시)
        String USER = "MOVIE";  // DB 사용자명 (예시)
        String PASSWORD = "movie";  // DB 비밀번호 (예시)

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ID로 사용자 조회 메소드
    public String selectById(String userId) {
        String sql = "SELECT USER_ID FROM USERS WHERE USER_ID = ?"; // 수정된 쿼리

        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userId);  // 입력된 사용자 ID로 검색

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // USER_ID를 String 타입으로 반환
                return rs.getString("USER_ID");
            }
        } catch (SQLException e) {
            System.err.println("DB 오류: " + e.getMessage());
        }

        return null;  // 사용자 ID가 없으면 null 반환
    }


}