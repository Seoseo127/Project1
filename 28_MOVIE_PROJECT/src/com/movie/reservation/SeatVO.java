package com.movie.reservation;

import java.sql.Timestamp;

public class SeatVO {
    private int reservationId;         // 예매 ID
    private int seatId;                // 좌석 ID
    private int userId;                // 사용자 ID
    private Timestamp reservationTime; // 예매 시간
    private String reserved;           // 예매 상태 ('Y' or 'N')

    // 기본 생성자
    public SeatVO() {}

    // 생성자 오버로딩
    public SeatVO(int reservationId, int seatId, int userId, Timestamp reservationTime, String reserved) {
        this.reservationId = reservationId;
        this.seatId = seatId;
        this.userId = userId;
        this.reservationTime = reservationTime;
        this.reserved = reserved;
    }

    // Getter & Setter
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "SeatReservationVO{" +
                "reservationId=" + reservationId +
                ", seatId=" + seatId +
                ", userId=" + userId +
                ", reservationTime=" + reservationTime +
                ", reserved='" + reserved + '\'' +
                '}';
    }
}
