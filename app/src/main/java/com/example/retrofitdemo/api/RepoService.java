package com.example.retrofitdemo.api;

import com.example.retrofitdemo.mvp.model.AddRelease;
import com.example.retrofitdemo.mvp.model.response.AddReleaseResponse;
import com.example.retrofitdemo.mvp.model.response.AddUserResponse;
import com.example.retrofitdemo.mvp.model.Issue;
import com.example.retrofitdemo.mvp.model.Release;
import com.example.retrofitdemo.mvp.model.Repo;
import com.example.retrofitdemo.mvp.model.response.AssertResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepoService {

    @GET("/users/{user}/repos")
    Observable<List<Repo>> getRepos(@Path("user") String user);


    @GET("/users/{user}/repos")
    Observable<List<Repo>> getReposByPage(@Path("user") String user, @Query("page") int page, @Query("per_page") int perPage);

    @POST("/repos/{user}/{repo}/issues")
    Observable<AddUserResponse> addIssue(@Path("user") String user, @Path("repo") String repo, @Body Issue issue);

    @POST("/repos/{user}/{repo}/releases")
    Observable<AddReleaseResponse> addRelease(@Path("user") String user, @Path("repo") String repo, @Body AddRelease release);

    @GET("/repos/{user}/{repo}/releases/{id}")
    Observable<Release> getReleaseById(@Path("user") String user, @Path("repo") String repo, @Path("id") int id);

    @POST("/repos/{user}/{repo}/releases/{id}/assets")
    Observable<AssertResponse> uploadAssert(@Path("user") String user, @Path("repo") String repo, @Path("id") int id, @Query("name") String name);

}
