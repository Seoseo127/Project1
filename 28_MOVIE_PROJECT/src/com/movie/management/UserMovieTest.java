package com.movie.management;

import java.sql.Date;

public class UserMovieTest {


	    public static void main(String[] args) {
	        // 테스트용 영화 객체 생성
	        MovieVO movie1 = new MovieVO(1, "주토피아", "animation", Date.valueOf("2025-01-20"), 120);
	        MovieVO movie2 = new MovieVO(2, "짱구는못말려", "animation", Date.valueOf("2024-02-12"), 110);

	        // UserMovie 객체 생성
	        UserMovie userMovie = new UserMovie();

	        // 1. 영화 등록 테스트
	        if (userMovie.insert(movie1)) {
	            System.out.println("영화 등록 성공");
	        } else {
	            System.out.println("영화 등록 실패");
	        }
	        
	        if (userMovie.insert(movie2)) {
	            System.out.println("영화 등록 성공");
	        } else {
	            System.out.println("영화 등록 실패");
	        }
	        

//	        // 2. 영화 수정 테스트
//	        movie1.setTitle("");
//	        movie1.setRuntime(138);
//	        if (userMovie.update(movie1)) {
//	            System.out.println("영화 수정 성공");
//	        } else {
//	            System.out.println("영화 수정 실패");
//	        }
//	        
//	        movie2.setTitle("213");
//	        movie2.setRuntime(899);
//	        if (userMovie.update(movie2)) {
//	            System.out.println("영화 수정 성공");
//	        } else {
//	            System.out.println("영화 수정 실패");
//	        }

//	        // 3. 영화 삭제 테스트
//	        if (userMovie.delete(movie1.getMovieId())) {
//	            System.out.println("영화 삭제 성공");
//	        } else {
//	            System.out.println("영화 삭제 실패");
//	        }
	    }
	}

