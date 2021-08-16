package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofitdemo.adapter.WeiboAdapter;
import com.example.retrofitdemo.api.WeiboService;
import com.example.retrofitdemo.mvp.model.response.WeiboItem;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeiboActivity extends AppCompatActivity {


    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo);
        Log.d("WeiboActivity", "Activity启动了");


        getData();


//        List<DataModel> dataModels = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            DataModel dataModel = new DataModel(null, "item" + i);
//            dataModels.add(dataModel);
//        }

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }

    private void getData() {
        LocalServiceGenerator.createService(WeiboService.class)
                .getWeiboItems("items.json")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SimpleObserver<List<WeiboItem>>() {
                    @Override
                    public void onNext(List<WeiboItem> repos) {
                        Log.d("WeiboActivity", "success");
                        Log.d("WeiboActivity", "repos:" + repos);
                        recyclerView.setAdapter(new WeiboAdapter(getApplicationContext(), repos));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("WeiboActivity", "faild");
                        Log.d("WeiboActivity", e.getMessage());
                    }
                });

    }
}
