package com.example.retrofitdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitdemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeiboItemImgsAdapter extends RecyclerView.Adapter<WeiboItemImgsAdapter.VH> {


    private String[] imgUrls;
    private Context context;


    public WeiboItemImgsAdapter(Context context,  String[] imgUrls) {
        this.imgUrls = imgUrls;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_weibo_img_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        //holder.tvUsername.setText(imgUrls.get(position).getContent());
        Glide.with(context).load(imgUrls[position]).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return imgUrls.length;
    }

    @OnClick(R.id.ivAvatar)
    public void onClick() {
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;

        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



}

