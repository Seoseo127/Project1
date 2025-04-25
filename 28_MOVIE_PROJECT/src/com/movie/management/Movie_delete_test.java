package com.movie.management;

public class Movie_delete_test {

	    public static void main(String[] args) {
	        // UserMovie 객체 생성
	        UserMovie userMovie = new UserMovie();
	        
	        // 삭제하려는 영화 ID 지정
	        int movieIdToDelete = 1;
	        
	        // 3. 영화 삭제 테스트
	        if (userMovie.delete(movieIdToDelete)) {
	            System.out.println("영화 삭제 성공");
	        } else {
	            System.out.println("영화 삭제 실패");
	        }
	    }
	}