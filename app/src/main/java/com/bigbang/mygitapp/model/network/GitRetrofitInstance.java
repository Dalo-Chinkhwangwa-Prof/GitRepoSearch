package com.bigbang.mygitapp.model.network;

import com.bigbang.mygitapp.model.data.Repository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitRetrofitInstance {

    private static GitRetrofitInstance gitRetrofitInstance = null;

    private GitService gitService;

    private GitRetrofitInstance(){
        gitService = createGitService(createRetrofitInstance());

    }
    public static GitRetrofitInstance getGitRetrofitInstance() {
        if(gitRetrofitInstance == null)
            gitRetrofitInstance = new GitRetrofitInstance();
        return gitRetrofitInstance;
    }

    private Retrofit createRetrofitInstance(){
        return new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private GitService createGitService(Retrofit retrofitInstance) {
        return retrofitInstance.create(GitService.class);
    }

    public Observable<List<Repository>> getRepositories(String userName){
        return gitService.getUserRepositories(userName);
    }

}
