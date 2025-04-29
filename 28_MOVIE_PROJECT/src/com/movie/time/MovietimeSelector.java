package com.movie.time;

import java.sql.*;
import java.util.*;

public class MovietimeSelector {

    private static final String URL = "jdbc:oracle:thin:@192.168.18.12:1521:xe";
    private static final String USER = "MOVIE";
    private static final String PASSWORD = "movie";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            printMovies();

            System.out.print("영화 ID를 입력하세요: ");
            int movieId = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            System.out.print("예매자 ID를 입력하세요: ");
            String userId = scanner.nextLine();

            List<String> times = getScreeningTimes(movieId);

            if (times.isEmpty()) {
                System.out.println("상영 시간이 없습니다.");
                return;
            }

            for (int i = 0; i < times.size(); i++) {
                System.out.println((i + 1) + ". " + times.get(i));
            }

            System.out.print("상영 시간 선택 (1~" + times.size() + "): ");
            int timeChoice = scanner.nextInt();
            scanner.nextLine();

            if (timeChoice < 1 || timeChoice > times.size()) {
                System.out.println("잘못된 선택입니다.");
                return;
            }

            String selectedTime = times.get(timeChoice - 1);
            selectSeat(scanner, userId, selectedTime, movieId);
        }
    }

    // 영화 목록 출력
    private static void printMovies() {
        String sql = "SELECT movie_id, title FROM management ORDER BY movie_id";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("===== 영화 목록 =====");
            while (rs.next()) {
                System.out.printf("[%d] %s\n", rs.getInt("movie_id"), rs.getString("title"));
            }
        } catch (SQLException e) {
            System.err.println("영화 목록 오류: " + e.getMessage());
        }
    }

    // 상영 시간 조회 (management_screening 테이블)
    private static List<String> getScreeningTimes(int movieId) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT TO_CHAR(screening_time, 'YYYY-MM-DD HH24:MI') AS time FROM management_screening WHERE movie_id = ? ORDER BY screening_time";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("time"));
            }
        } catch (SQLException e) {
            System.err.println("상영시간 조회 오류: " + e.getMessage());
        }
        return list;
    }

    // 좌석 선택
    private static void selectSeat(Scanner scanner, String userId, String screeningTime, int movieId) {
        String[] seats = new String[10];
        boolean[] reserved = new boolean[10];
        reserved[2] = true;
        reserved[5] = true;

        for (int i = 0; i < seats.length; i++) {
            seats[i] = "Seat " + (i + 1);
            System.out.println(seats[i] + (reserved[i] ? " (예약됨)" : " (사용 가능)"));
        }

        System.out.print("좌석 선택 (1~10): ");
        int seat = scanner.nextInt();

        if (seat < 1 || seat > 10 || reserved[seat - 1]) {
            System.out.println("선택 불가한 좌석입니다.");
            return;
        }

        reserved[seat - 1] = true;
        saveReservation(userId, screeningTime, seats[seat - 1], movieId);
    }

    // 예매 정보 저장
    private static void saveReservation(String userId, String time, String seat, int movieId) {
        String nextIdSQL = "SELECT NVL(MAX(reservation_id), 0) + 1 AS next_id FROM reservation";
        String insertSQL = "INSERT INTO reservation (reservation_id, movie_id, screening_time, seat_number, user_id, reserved) "
                         + "VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD HH24:MI'), ?, ?, 1)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(nextIdSQL)) {

            int nextId = rs.next() ? rs.getInt("next_id") : 1;

            try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
                ps.setInt(1, nextId);
                ps.setInt(2, movieId);
                ps.setString(3, time);
                ps.setString(4, seat);
                ps.setString(5, userId);
                int result = ps.executeUpdate();
                System.out.println(result > 0 ? "예매 완료!" : "예매 실패");
            }

        } catch (SQLException e) {
            System.err.println("예매 저장 오류: " + e.getMessage());
        }
    }
}




