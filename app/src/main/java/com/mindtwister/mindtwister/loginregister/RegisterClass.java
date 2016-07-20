package com.mindtwister.mindtwister.loginregister;

/**
 * Created by jyothi on 7/16/2016.
 */
public class RegisterClass {
    private String user_name;
    private String user_nickname;
    private String user_password;
    private String user_email;
    private int user_age;

    public RegisterClass() {
    }

    public RegisterClass(String user_name, String user_nickname, String user_password, String user_email, int user_age) {
        this.user_name = user_name;
        this.user_nickname = user_nickname;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_age = user_age;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

}
