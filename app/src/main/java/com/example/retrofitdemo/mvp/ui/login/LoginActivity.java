package com.example.retrofitdemo.mvp.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.retrofitdemo.R;
import com.example.retrofitdemo.mvp.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.recycler)
    RecyclerView recycler;

    List<User> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        recycler.setLayoutManager(new LinearLayoutManager(this));
    //    recycler.setAdapter(new LoginAdapter(R.layout.activity_login_item, data));


    }


}
