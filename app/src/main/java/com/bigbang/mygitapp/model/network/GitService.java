package com.bigbang.mygitapp.model.network;

import com.bigbang.mygitapp.model.data.Repository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.bigbang.mygitapp.util.Constants.GET_REPOSITORIES;
import static com.bigbang.mygitapp.util.Constants.USER_PATH;

public interface GitService {
    @GET(GET_REPOSITORIES)
    Observable<List<Repository>> getUserRepositories(@Path(USER_PATH) String gitUserName);
}
