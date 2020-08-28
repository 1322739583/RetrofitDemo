package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.retrofitdemo.adapter.LoginAdapter;
import com.example.retrofitdemo.rest.RepoService;
import com.example.retrofitdemo.rest.module.Repo;
import com.example.retrofitdemo.rest.module.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReposActivity extends AppCompatActivity {


    @BindView(R.id.recycler)
    RecyclerView recycler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        ServiceGenerator.createService(RepoService.class)
                .getRepos("1322739583")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SimpleObserver<List<Repo>>() {
                    @Override
                    public void onNext(List<Repo> repos) {
                        recycler.setAdapter(new LoginAdapter(R.layout.activity_login_item, repos));
                    }
                });





    }


}
