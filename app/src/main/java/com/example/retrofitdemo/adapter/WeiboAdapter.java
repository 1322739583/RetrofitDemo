package com.example.retrofitdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.mvp.model.response.WeiboItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeiboAdapter extends RecyclerView.Adapter<WeiboAdapter.VH> {


    private List<WeiboItem> dataModels;
    private Context context;


    public WeiboAdapter(Context context, List<WeiboItem> dataModels) {
        this.dataModels = dataModels;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_weibo_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.tvUsername.setText(dataModels.get(position).getUsername());
        holder.tvContent.setText(dataModels.get(position).getContent());
        holder.tvCreateTime.setText(dataModels.get(position).getCreateTime());

      //  RoundedBitmapDrawable roundedBitmapDrawable1 = RoundedBitmapDrawableFactory.create(context.getResources(), BitmapFactory.decodeResource(context.getResources(), R.id.rvImgs));

        Glide.with(context).load(dataModels.get(position).getAvatarUrl()).into(holder.ivAvatar);
        //
        holder.rvImgs.setLayoutManager(new GridLayoutManager(context,3));
        //holder.rvImgs.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        holder.rvImgs.setAdapter(new WeiboItemImgsAdapter(context,dataModels.get(position).getImgUrls()));



    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    @OnClick(R.id.ivAvatar)
    public void onClick() {
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.ivAvatar)
        ImageView ivAvatar;
        @BindView(R.id.tvUsername)
        TextView tvUsername;
        @BindView(R.id.tvCreateTime)
        TextView tvCreateTime;
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.rvImgs)
        RecyclerView rvImgs;

        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
