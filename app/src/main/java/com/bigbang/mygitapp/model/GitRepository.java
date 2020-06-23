package com.bigbang.mygitapp.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bigbang.mygitapp.model.data.Repository;
import com.bigbang.mygitapp.model.network.GitRetrofitInstance;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GitRepository {
    private GitRetrofitInstance retrofitInstance;

    public GitRepository() {
        retrofitInstance = GitRetrofitInstance.getGitRetrofitInstance();
    }

    private CompositeDisposable viewModelComposite = new CompositeDisposable();

    private MutableLiveData<List<Repository>> repositoryLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Repository>> searchLiveData = new MutableLiveData<>();

    public LiveData<List<Repository>> getRepositoryLiveData() {
        viewModelComposite.add(
                retrofitInstance.getRepositories("Facebook")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(repoList -> {
                            repositoryLiveData.setValue(repoList);

                        }, throwable -> {
                            Log.d("TAG_ERROR", "" + throwable.getLocalizedMessage());
                        })
        );
        return repositoryLiveData;
    }

    public LiveData<List<Repository>> getSearchResults(String userName) {

        viewModelComposite.clear();

        viewModelComposite.add(
                retrofitInstance.getRepositories(userName)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(repoList -> {
                            searchLiveData.setValue(repoList);

                        }, throwable -> {
                            Log.d("TAG_ERROR", "" + throwable.getLocalizedMessage());
                        })
        );

        return searchLiveData;
    }


}
