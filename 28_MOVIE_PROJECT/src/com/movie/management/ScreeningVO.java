package com.movie.management;

import java.sql.Timestamp;

public class ScreeningVO {
    private int movieId;
    private Timestamp screeningTime;

    public ScreeningVO(int movieId, Timestamp screeningTime) {
        this.movieId = movieId;
        this.screeningTime = screeningTime;
    }

    public int getMovieId() {
        return movieId;
    }

    public Timestamp getScreeningTime() {
        return screeningTime;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setScreeningTime(Timestamp screeningTime) {
        this.screeningTime = screeningTime;
    }
}

