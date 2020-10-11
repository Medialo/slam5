package com.example.td4;

import android.app.Application;

public class NewsListApplication extends Application {


    private String login;


    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


}
