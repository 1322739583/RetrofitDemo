package com.example.retrofitdemo;

import android.app.Application;

import hugo.weaving.internal.Hugo;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG) {

          //  Hugo.setEnabled(true);
        }
    }
}
