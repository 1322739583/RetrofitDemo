package com.example.retrofitdemo.mvp.ui.repo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.ServiceGenerator;
import com.example.retrofitdemo.SimpleObserver;
import com.example.retrofitdemo.adapter.LoginAdapter;
import com.example.retrofitdemo.mvp.api.RepoService;
import com.example.retrofitdemo.mvp.module.Repo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ReposActivity extends AppCompatActivity {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    LoginAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);



    //    getRepos();
        getReposByPage();






    }

    private void getReposByPage() {
        ServiceGenerator.createService(RepoService.class)
                .getReposByPage("1322739583",2,10)

                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SimpleObserver<List<Repo>>() {
                    @Override
                    public void onNext(List<Repo> repos) {
                        adapter=new LoginAdapter(R.layout.activity_login_item, repos);
                          Log.d("ReposActivity", "repos:" + repos);
                        Log.d("ReposActivity", "doAfterNext");
                        adapter.setEnableLoadMore(true);
                        adapter.setOnLoadMoreListener(() -> {
                            Log.d("ReposActivity", "load more");
                        },recycler);
                        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recycler.setAdapter(adapter);
                    }
                });
    }

    private void getRepos() {
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
