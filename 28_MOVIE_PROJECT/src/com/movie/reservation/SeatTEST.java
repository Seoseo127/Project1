package com.movie.reservation;

public class SeatTEST {
    public static void main(String[] args) {
        SeatDAO dao = new SeatDAO();
        
        // 좌석 번호와 사용자를 입력받아 예매 처리
        int seatId = 10;  // 예시: 좌석 ID
        int userId = 1;   // 예시: 사용자 ID

        if (dao.checkSeatAvailability(seatId)) {
            if (dao.reserveSeat(seatId, userId)) {
                System.out.println("좌석 예매 성공");
            } else {
                System.out.println("좌석 예매 실패");
            }
        } else {
            System.out.println("해당 좌석은 이미 예매되었습니다.");
        }
    }
}

