package com.example.retrofitdemo.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.rest.module.User;


import java.util.List;

public class UserAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public UserAdapter(int layoutResId, @Nullable List<User> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, User item) {
        TextView textView = helper.itemView.findViewById(R.id.text);
    }
}
