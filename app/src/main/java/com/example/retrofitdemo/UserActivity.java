package com.example.retrofitdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.retrofitdemo.adapter.UserAdapter;
import com.example.retrofitdemo.rest.module.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserActivity extends AppCompatActivity {


    @BindView(R.id.recycler)
    RecyclerView recycler;

    List<User> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new UserAdapter(R.layout.activity_user_item, data));


    }


}
