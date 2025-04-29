package com.movie.admin;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "MOVIE";
    private static final String PASSWORD = "movie";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- 예매정보 관리자 메뉴 ---");
            System.out.println("1. 예매 정보 조회");
            System.out.println("2. 예매 정보 수정");
            System.out.println("3. 예매 정보 삭제");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            
            // 메뉴 선택 받기
            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();  // 개행 문자 처리
            }

            // 선택에 따른 처리
            switch (choice) {
                case 1:
                    getReservationInformation();
                    break;
                case 2:
                    updateReservationInformation(scanner);
                    break;
                case 3:
                    deleteReservationInformation(scanner);
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close(); // Scanner 종료
                    return; // 프로그램 종료
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해 주세요.");
            }
        }
    }

    // 예매 내역 조회 (RESERVATION_INFORMATION 테이블)
    public static void getReservationInformation() {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement()) {

            String selectSql = "SELECT reservation_id, movie_id, screening_time, seat_number, user_id, reservation_status FROM RESERVATION_INFORMATION";
            ResultSet rs = stmt.executeQuery(selectSql);

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
        }
    }

    // 예매 정보 수정
    private static void updateReservationInformation(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            System.out.print("수정할 예약 ID를 입력하세요: ");
            int reservationId = scanner.nextInt();
            scanner.nextLine();  // 개행 문자 처리

            System.out.print("새 좌석 번호를 입력하세요: ");
            String newSeatNumber = scanner.nextLine();

            System.out.print("새 상영 시간을 입력하세요: ");
            String newScreeningTime = scanner.nextLine();

            String updateSql = "UPDATE RESERVATION_INFORMATION "
                             + "SET seat_number = ?, screening_time = ? "
                             + "WHERE reservation_id = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
                pstmt.setString(1, newSeatNumber);
                pstmt.setString(2, newScreeningTime);
                pstmt.setInt(3, reservationId);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("예매 정보가 성공적으로 수정되었습니다.");
                } else {
                    System.out.println("해당 예약 ID를 찾을 수 없습니다.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 예매 정보 삭제
    private static void deleteReservationInformation(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            System.out.print("삭제할 예약 ID를 입력하세요: ");
            int reservationId = scanner.nextInt();
            scanner.nextLine();  // 개행 문자 처리

            String deleteSql = "DELETE FROM RESERVATION_INFORMATION WHERE reservation_id = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {
                pstmt.setInt(1, reservationId);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("예매 정보가 성공적으로 삭제되었습니다.");
                } else {
                    System.out.println("해당 예약 ID를 찾을 수 없습니다.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

