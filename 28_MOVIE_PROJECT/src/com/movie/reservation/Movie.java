package com.movie.reservation;

import java.sql.Date;

import com.movie.management.MovieVO;

public class Movie {
    private int movieId;
    private String title;
    private String genre;
    private Date releaseDate;
    private int runtime;

    // 생성자
    public Movie(int movieId, String title, String genre, Date releaseDate, int runtime) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
    }

    // toString() 메소드로 영화 정보 출력
    @Override
    public String toString() {
        return String.format("영화 ID: %d, 제목: %s, 장르: %s, 개봉일: %s, 상영시간: %d", 
                              movieId, title, genre, releaseDate, runtime);
    }

    // main() 메소드에서 객체 생성 및 출력
    public static void main(String[] args) {
        Movie movie = new Movie(1, "주토피아", "애니매이션", Date.valueOf("2025-03-25"), 108);
        System.out.println(movie);
    }

	public Object getMovieId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getGenre() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getReleaseDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getRuntime() {
		// TODO Auto-generated method stub
		return null;
	}
}


