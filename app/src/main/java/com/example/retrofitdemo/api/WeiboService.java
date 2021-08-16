package com.example.retrofitdemo.api;

import com.example.retrofitdemo.mvp.model.Repo;
import com.example.retrofitdemo.mvp.model.response.WeiboItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeiboService {

    @GET("/{jsonname}")
    Observable<List<WeiboItem>> getWeiboItems(@Path("jsonname") String jsonname);

}
