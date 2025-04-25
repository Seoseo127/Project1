package com.movie.management;

import java.sql.Date;

public class MovieVO {

    private int movieId;
    private String title;
    private String genre;
    private Date releaseDate;
    private int runtime;

    // 생성자
    public MovieVO(int movieId, String title, String genre, Date releaseDate, int runtime) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
    }

    // Getter & Setter
    public int getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public Date getReleaseDate() { return releaseDate; }
    public int getRuntime() { return runtime; }

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	};

    
    

	}
