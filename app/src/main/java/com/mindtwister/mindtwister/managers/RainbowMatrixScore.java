package com.mindtwister.mindtwister.managers;

/**
 * Created by Utkarsh on 23-07-2016.
 */
public class RainbowMatrixScore {
    private int score;
    private String user_nickname;
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


}