package com.example.retrofitdemo.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.rest.module.Repo;
import com.example.retrofitdemo.rest.module.User;


import java.util.List;

public class LoginAdapter extends BaseQuickAdapter<Repo, BaseViewHolder> {

    public LoginAdapter(int layoutResId, @Nullable List<Repo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Repo item) {
        TextView textView = helper.itemView.findViewById(R.id.text);
        textView.setText(item.getName());
    }
}
