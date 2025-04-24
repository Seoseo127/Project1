package com.movie.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.movie.vo.UserVO;

public class UserDAO {
    final String DRIVER = "oracle.jdbc.OracleDriver";
    final String URL = "jdbc:oracle:thin:@192.168.18.12:1521:xe"; // Oracle DB 연결 URL
    final String USER = "MOVIE"; // 데이터베이스 사용자 이름
    final String PASSWORD = "movie"; // 데이터베이스 비밀번호
    
    
    public boolean insert(UserVO vo) {

      // 데이터베이스 연결
       String sql = ""+
             "INSERT INTO users "+
             "       (user_id, user_password, user_nickname, user_phonenumber, user_email, user_birth) " + 
             "VALUES (?, ?, ?, ?, ?, ?)";
      try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql);) {
         // PreparedStatement로 SQL 쿼리 파라미터 설정
         ps.setString(1, vo.getUserId());
         ps.setString(2, vo.getUserPassword());
         ps.setString(3, vo.getUserNickname());
         ps.setString(4, vo.getUserPhonenumber());
         ps.setString(5, vo.getUserEmail());
         ps.setDate(6, vo.getUserBirth());
         
         
         int rowsAffected = ps.executeUpdate(); // SQL 실행
         if (rowsAffected > 0) {
             System.out.println("New user added successfully.");
         }
         return true;
      } catch (SQLException e) {
          System.err.println("SQL Error: " + e.getMessage());
          e.printStackTrace();
      }

      return false;
    }

    // 회원 정보 조회 메서드
    public UserVO selectById(String userId) {
        String sql = "" +
              "SELECT user_id, user_password, user_nickname, user_phonenumber, user_email, user_birth " +
              "FROM users " +
              "WHERE user_id = ? ";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, userId); // 쿼리 파라미터 설정
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new UserVO(rs.getString("user_id"),
                                  rs.getString("user_password"),
                                  rs.getString("user_nickname"),
                                  rs.getString("user_phonenumber"),
                                  rs.getString("user_email"),
                                  rs.getDate("user_birth"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserVO login(String userId, String userPw) {
        String sql = "SELECT * FROM users WHERE user_id = ? AND user_password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userId);
            ps.setString(2, userPw);

            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new UserVO(
                        rs.getString("user_id"),
                        rs.getString("user_password"),
                        rs.getString("user_nickname"),
                        rs.getString("user_phonenumber"),
                        rs.getString("user_email"),
                        rs.getDate("user_birth")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("로그인 중 오류: " + e.getMessage());
            e.printStackTrace();
        }

        return null; // 로그인 실패 시 null 반환
    }

	


    
}


