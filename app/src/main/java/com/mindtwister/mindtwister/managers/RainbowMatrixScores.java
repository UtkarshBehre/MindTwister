package com.mindtwister.mindtwister.managers;

/**
 * Created by Utkarsh on 23-07-2016.
 */

/*
    Conditions to store user score in the database repective table
    1. rows entry must not exceed 10
    2. score must be entered according to greater value
 */


public class RainbowMatrixScores {
    private int score;
    private String user_nickname;
    private String game_name;
    private String user_email;
    private String difficultylevel;

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getDifficultylevel() {
        return difficultylevel;
    }

    public void setDifficultylevel(String difficultylevel) {
        this.difficultylevel = difficultylevel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }
}
