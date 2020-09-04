package com.example.retrofitdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitdemo.mvp.ui.repo.ReposActivity;
import com.example.retrofitdemo.mvp.api.RepoService;
import com.example.retrofitdemo.mvp.module.response.AddUserResponse;
import com.example.retrofitdemo.mvp.module.Issue;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btnGetRepos)
    Button btn1;
    @BindView(R.id.btnAddIssue)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;

   Intent intent=new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnGetRepos)
    public void btnGetRepos() {
         intent.setClass(MainActivity.this, ReposActivity.class);
          startActivity(intent);

    }

    @OnClick(R.id.btnAddIssue)
    public void btnAddIssue() {
        Issue issue=new Issue();
        issue.setTitle("this is title");
        issue.setBody("this is body");
        List<String> lables=new ArrayList<>();
        lables.add("bug");

        ServiceGenerator.createService(RepoService.class)
                .addIssue("1322739583","RetrofitDemo",issue)
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribeOn(Schedulers.io())
                 .subscribe(new SimpleObserver<AddUserResponse>() {
                     @Override
                     public void onNext(AddUserResponse addUserResponse) {
                         Log.d("MainActivity", "addUserResponse:" + addUserResponse);
                     }
                 });
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
