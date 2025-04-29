CREATE TABLE users (
	user_id VARCHAR(20)	NOT NULL,
	user_password	VARCHAR(30)	NOT NULL,
	user_nickname	VARCHAR(10)	NOT NULL,
	user_phonenumber	VARCHAR(20)	NOT NULL,
	user_email	VARCHAR(50)	NOT NULL,
	user_birth	DATE	NULL
);

CREATE TABLE movies (
    movie_id VARCHAR2(20) NOT NULL,
    booking_id VARCHAR2(20) NOT NULL,
    showtime_id NUMBER NOT NULL,
    user_id VARCHAR2(20) NOT NULL,
    payment_id NUMBER NOT NULL,
    reservation_id NUMBER NOT NULL,
    user_id2 VARCHAR2(20) NOT NULL,
    add_movie NUMBER NOT NULL,
    movie_info VARCHAR2(100),
    show_time NUMBER
);

CREATE TABLE bookings (
    booking_id VARCHAR2(20) NOT NULL,
    showtime_id NUMBER NOT NULL,
    user_id VARCHAR2(20) NOT NULL,
    payment_id NUMBER NOT NULL,
    reservation_id NUMBER NOT NULL,
    user_id2 VARCHAR2(20) NOT NULL,
    seat_id NUMBER NOT NULL,
    field VARCHAR2(20) NOT NULL
);

CREATE TABLE payment (
    payment_id NUMBER NOT NULL,
    reservation_id NUMBER NOT NULL,
    user_id VARCHAR2(20) NOT NULL,
    amount NUMBER(10,2) NOT NULL,
    payment_method VARCHAR2(20) NOT NULL,
    payment_status VARCHAR2(20) NOT NULL,
    paid_at DATE NOT NULL,
    payment_result VARCHAR2(20) NOT NULL, -- ENUM 대체
    refund_requested VARCHAR2(5) DEFAULT 'FALSE', -- BOOLEAN 대체
    refund_status VARCHAR2(10) DEFAULT '대기', -- ENUM 대체
    refund_amount NUMBER(10,2),
    refund_processed_at DATE
);

CREATE TABLE management (
    movieId INT PRIMARY KEY,
    title VARCHAR2(100),
    genre VARCHAR2(50),
    release_date DATE,
    runtime INT
);

CREATE TABLE customer_services (
    inquiry_id NUMBER NOT NULL,
    user_id2 VARCHAR2(20) NOT NULL,
    user_id VARCHAR2(20) NOT NULL,
    subject VARCHAR2(255) NOT NULL,
    message CLOB NOT NULL,
    response CLOB,
    status VARCHAR2(20),
    create_at DATE,
    response_at DATE
);

CREATE TABLE MANAGEMENT_screening (
    screening_id INT,
    movie_id INT NOT NULL PRIMARY KEY,
    screening_time TIMESTAMP NOT NULL
);

drop table seat;
CREATE TABLE seat (
    seat_id INT PRIMARY KEY,     -- 좌석 고유 번호
    seat_row CHAR(1) NOT NULL,    -- 좌석 행 (A, B, C 이런 식)
    seat_col INT NOT NULL
    );

GRANT ALL PRIVILEGES ON SEAT TO MOVIE;
GRANT INSERT ON SEAT TO MOVIE;


CREATE TABLE RESERVATION (
    reservation_id NUMBER PRIMARY KEY,
    movie_id NUMBER,
    screening_time VARCHAR2(255),
    seat_number VARCHAR2(20),
    user_id VARCHAR2(50),
    reserved NUMBER(1)
);

DROP TABLE BOOKINGS;









                 



UPDATE seat
SET reserved = 'Y'
WHERE seat_id = ?;

SELECT b.USER_ID, b.MOVIE_ID, m.MOVIE_NAME, b.SCREENING_TIME
FROM BOOKING b
JOIN MOVIE m ON b.MOVIE_ID = m.MOVIE_ID
WHERE b.USER_ID = 'YUI'
ORDER BY b.BOOKING_DATE DESC;


ALTER TABLE users ADD CONSTRAINT pk_users PRIMARY KEY (user_id);

ALTER TABLE movies ADD CONSTRAINT pk_movies PRIMARY KEY (
    movie_id, booking_id, showtime_id, user_id, payment_id, reservation_id, user_id2, add_movie
);

ALTER TABLE bookings ADD CONSTRAINT pk_bookings PRIMARY KEY (
    booking_id, showtime_id, user_id, payment_id, reservation_id, user_id2
);

ALTER TABLE payment ADD CONSTRAINT pk_payment PRIMARY KEY (
    payment_id, reservation_id, user_id
);

ALTER TABLE management ADD CONSTRAINT pk_management PRIMARY KEY (add_movie);

ALTER TABLE customer_services ADD CONSTRAINT pk_customer_services PRIMARY KEY (
    inquiry_id, user_id2
);

