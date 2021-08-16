package com.example.retrofitdemo.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.retrofitdemo.R;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity {

    protected P mPresenter;

    protected abstract void initView();

    protected abstract void initData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
