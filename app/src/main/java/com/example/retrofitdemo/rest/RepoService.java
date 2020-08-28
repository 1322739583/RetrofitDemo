package com.example.retrofitdemo.rest;

import com.example.retrofitdemo.rest.module.AddRelease;
import com.example.retrofitdemo.rest.module.AddReleaseResponse;
import com.example.retrofitdemo.rest.module.AddUserResponse;
import com.example.retrofitdemo.rest.module.Issue;
import com.example.retrofitdemo.rest.module.Release;
import com.example.retrofitdemo.rest.module.Repo;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RepoService {

    @GET("/users/{user}/repos")
    Observable<List<Repo>> getRepos(@Path("user") String user);

   @POST("/repos/{user}/{repo}/issues")
    Observable<AddUserResponse>  addIssue(@Path("user")String user, @Path("repo")String repo, @Body Issue issue);

   @POST("/repos/{user}/{repo}/releases")
   Observable<AddReleaseResponse> addRelease(@Path("user")String user, @Path("repo")String repo,@Body AddRelease release);

    @GET("/repos/{user}/{repo}/releases/{id}")
    Observable<Release> getReleaseById(@Path("user")String user, @Path("repo")String repo, @Path("id")int id);


}
