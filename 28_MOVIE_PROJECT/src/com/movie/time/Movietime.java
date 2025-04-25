package com.movie.time;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Movietime {

    // DB 연결 메소드 (가정)
    private static Connection getConnection() throws SQLException {
        String URL = "jdbc:oracle:thin:@192.168.18.12:1521:xe";  // DB URL (예시)
        String USER = "MOVIE";  // DB 사용자명 (예시)
        String PASSWORD = "movie";  // DB 비밀번호 (예시)

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 상영시간 조회 메소드 (정확한 테이블 및 컬럼명에 맞게 수정)
    public static List<String> getScreeningTimes(int movieId) {
        List<String> screeningTimes = new ArrayList<>();
        String sql = "SELECT SCREENING_TIME FROM MANAGEMENT_SCREENING WHERE MOVIE_ID = ?";  // 테이블 이름 및 컬럼명 확인 필요

        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, movieId);  // 영화 ID 설정
            ResultSet rs = ps.executeQuery();

            // 결과가 있을 경우 리스트에 추가
            while (rs.next()) {
                // TIMESTAMP 값을 String으로 변환하여 리스트에 추가
                Timestamp timestamp = rs.getTimestamp("SCREENING_TIME");
                if (timestamp != null) {
                    screeningTimes.add(timestamp.toString());  // 또는 원하는 포맷으로 변환 가능
                }
            }

        } catch (SQLException e) {
            System.err.println("DB 오류: " + e.getMessage());
        }

        return screeningTimes;  // 결과를 반환
    }

    // main() 메소드 예시 (테스트용)
    public static void main(String[] args) {
        int movieId = 1;  // 예시 영화 ID
        List<String> screeningTimes = getScreeningTimes(movieId);

        if (screeningTimes.isEmpty()) {
            System.out.println("선택할 수 있는 상영시간이 없습니다.");
        } else {
            System.out.println("상영시간 목록:");
            for (String time : screeningTimes) {
                System.out.println(time);
            }
        }
    }
}