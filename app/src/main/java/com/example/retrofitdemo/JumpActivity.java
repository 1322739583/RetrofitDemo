package com.example.retrofitdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JumpActivity extends AppCompatActivity {


    @BindView(R.id.btnGetRepos)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;

    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnGetRepos)
    public void onBtn1Clicked() {
        //  intent.setClass(MainActivity.this,LoginActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.btn2)
    public void onBtn2Clicked() {
    }

    @OnClick(R.id.btn3)
    public void onBtn3Clicked() {
    }

    @OnClick(R.id.btn4)
    public void onBtn4Clicked() {
    }

    @OnClick(R.id.btn5)
    public void onBtn5Clicked() {
    }
}
