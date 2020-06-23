package com.bigbang.mygitapp.model.network;

import com.bigbang.mygitapp.model.data.Repository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitService {
    @GET("users/{user_name}/repos")
    Observable<List<Repository>> getUserRepositories(@Path("user_name") String gitUserName);
}
