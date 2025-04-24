package com.movie.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.movie.vo.UserVO;

public class userLogin {
    final String DRIVER = "oracle.jdbc.OracleDriver";
    final String URL = "jdbc:oracle:thin:@192.168.18.12:1521:xe"; // Oracle DB 연결 URL
    final String USER = "MOVIE"; // 데이터베이스 사용자 이름
    final String PASSWORD = "movie"; // 데이터베이스 비밀번호

    public boolean login(String userId, String userPassword) {
        String sql = "SELECT * FROM users WHERE user_id = ? AND user_password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userId);
            ps.setString(2, userPassword);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("✅ 로그인 성공! 환영합니다, " + rs.getString("user_nickname") + "님");
                return true;
            } else {
                System.out.println("❌ 로그인 실패: 아이디 또는 비밀번호가 틀렸습니다.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Error during login: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }
}