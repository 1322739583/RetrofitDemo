package com.example.retrofitdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitdemo.rest.RepoService;
import com.example.retrofitdemo.rest.module.AddRelease;
import com.example.retrofitdemo.rest.module.AddReleaseResponse;
import com.example.retrofitdemo.rest.module.Release;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class ReleaseActivity extends AppCompatActivity {
    RepoService service;
    Intent intent = new Intent();
    @BindView(R.id.btnCreateRelease)
    Button btnCreateRelease;
    @BindView(R.id.btnCreateReleaseThenGetRelease)
    Button btnCreateReleaseThenGetRelease;
    private String user;
    private String repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        ButterKnife.bind(this);
        service = ServiceGenerator.createService(RepoService.class);
        user = "1322739583";
        repo = "RetrofitDemo";
    }


    @OnClick(R.id.btnCreateRelease)
    public void onBtnCreateReleaseClicked() {
       service.addRelease(user,repo,getRequestParame())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeOn(Schedulers.io())
               .subscribe(new SimpleObserver<AddReleaseResponse>() {
                   @Override
                   public void onNext(AddReleaseResponse addReleaseResponse) {
                       Log.d("ReleaseActivity", "addReleaseResponse.getId():" + addReleaseResponse.getId());
                   }
               });


    }


    @OnClick(R.id.btnCreateReleaseThenGetRelease)
    public void btnCreateReleaseThenGetRelease() {

        service
                .addRelease(user, repo, getRequestParame())
                .flatMap(new Function<AddReleaseResponse, ObservableSource<Release>>() {
                    @Override
                    public ObservableSource<Release> apply(AddReleaseResponse addReleaseResponse) throws Exception {
                        return service.getReleaseById(user, repo, addReleaseResponse.getId());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SimpleObserver<Release>() {
                    @Override
                    public void onNext(Release data) {
                        Log.d("ReleaseActivity", "data:" + data);
                    }
                });
    }

    public AddRelease getRequestParame(){
        AddRelease release = new AddRelease();
        release.setName("release name5");
        release.setBody("release body5");
        release.setDraft(false);
        release.setPrerelease(false);
        release.setTag_name("tag5");
        release.setTarget_commitish("master");
        return release;
    }
}
