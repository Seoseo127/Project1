package com.movie.reservation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatDAO {
    private static final String URL = "jdbc:oracle:thin:@192.168.18.12:1521:xe";
    private static final String USER = "MOVIE";
    private static final String PASSWORD = "movie";

    // 데이터베이스 연결
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 좌석 예매
    public boolean reserveSeat(int seatId, int userId) {
        String sql = "UPDATE seat SET reserved = 'Y' WHERE seat_id = ? AND reserved = 'N'";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, seatId);  // 예매할 좌석 ID

            int result = ps.executeUpdate();
            if (result > 0) {
                // 예매된 좌석은 `seat_reservation` 테이블에 예약 정보 저장
                String reservationSql = "INSERT INTO seat_reservation (reservation_id, seat_id, user_id, reservation_time, reserved) " +
                                        "VALUES (seat_reservation_seq.nextval, ?, ?, SYSDATE, 'Y')";
                try (PreparedStatement psInsert = conn.prepareStatement(reservationSql)) {
                    psInsert.setInt(1, seatId);
                    psInsert.setInt(2, userId);
                    psInsert.executeUpdate();
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 좌석 상태 조회 (예: 좌석 예약 가능 여부 확인)
    public boolean checkSeatAvailability(int seatId) {
        String sql = "SELECT reserved FROM seat WHERE seat_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, seatId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("reserved").equals("N");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
