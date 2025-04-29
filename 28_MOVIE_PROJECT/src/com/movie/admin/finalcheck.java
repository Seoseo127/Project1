package com.movie.admin;

import java.sql.*;

public class finalcheck {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "MOVIE";
    private static final String PASSWORD = "movie";

    public static void main(String[] args) {
        // 예약정보를 RESERVATION_INFORMATION에 저장
        saveReservationToInformation();

        // 저장된 예매 정보 조회
        getReservationInformation();
    }

    // RESERVATION → RESERVATION_INFORMATION 복사 및 결제 완료 처리
    private static void saveReservationToInformation() {
        Connection conn = null;
        PreparedStatement selectStmt = null;
        PreparedStatement insertStmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 1. RESERVATION 테이블에서 모든 예약 내역 조회
            String selectSql = "SELECT reservation_id, movie_id, screening_time, seat_number, user_id, reserved FROM RESERVATION";
            selectStmt = conn.prepareStatement(selectSql);
            rs = selectStmt.executeQuery();

            // 2. 각 데이터 RESERVATION_INFORMATION 테이블로 복사
            String insertSql = "INSERT INTO RESERVATION_INFORMATION (reservation_id, movie_id, screening_time, seat_number, user_id, reservation_status) "
                             + "VALUES (?, ?, ?, ?, ?, ?)";

            insertStmt = conn.prepareStatement(insertSql);

            while (rs.next()) {
                int reservationId = rs.getInt("reservation_id");
                int movieId = rs.getInt("movie_id");
                String screeningTime = rs.getString("screening_time");
                String seatNumber = rs.getString("seat_number");
                String userId = rs.getString("user_id");
                int reserved = rs.getInt("reserved");  // 1이면 예약됨

                // 중복 방지: 동일 reservation_id가 이미 RESERVATION_INFORMATION에 있으면 skip
                if (isAlreadyInserted(conn, reservationId)) {
                    continue;
                }

                // 예약 상태를 텍스트로 변환
                String reservationStatus = (reserved == 1) ? "결제 완료" : "예약 실패";

                // 데이터 삽입
                insertStmt.setInt(1, reservationId);
                insertStmt.setInt(2, movieId);
                insertStmt.setString(3, screeningTime);
                insertStmt.setString(4, seatNumber);
                insertStmt.setString(5, userId);
                insertStmt.setString(6, reservationStatus);

                insertStmt.executeUpdate();

                // 결제 완료 메시지 출력
                if (reserved == 1) {
                    System.out.println("예약 ID " + reservationId + ": 결제 완료되었습니다.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (selectStmt != null) selectStmt.close();
                if (insertStmt != null) insertStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 중복 체크 함수: 같은 reservation_id가 이미 저장되었는지 확인
    private static boolean isAlreadyInserted(Connection conn, int reservationId) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM RESERVATION_INFORMATION WHERE reservation_id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, reservationId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }
        }
    }

    // RESERVATION_INFORMATION 테이블 조회
    public static void getReservationInformation() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String selectSql = "SELECT reservation_id, movie_id, screening_time, seat_number, user_id, reservation_status FROM RESERVATION_INFORMATION";
            pstmt = conn.prepareStatement(selectSql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n[최종 예매 내역]");
                do {
                    System.out.println("예약 ID: " + rs.getInt("reservation_id"));
                    System.out.println("영화 ID: " + rs.getInt("movie_id"));
                    System.out.println("상영 시간: " + rs.getString("screening_time"));
                    System.out.println("좌석 번호: " + rs.getString("seat_number"));
                    System.out.println("사용자 ID: " + rs.getString("user_id"));
                    System.out.println("예약 상태: " + rs.getString("reservation_status"));
                    System.out.println("-----");
                } while (rs.next());
            } else {
                System.out.println("예매 내역이 없습니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

	
