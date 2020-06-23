package com.bigbang.mygitapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bigbang.mygitapp.model.GitRepository;
import com.bigbang.mygitapp.model.data.Repository;

import java.util.List;

public class GitViewModel extends ViewModel {
    private GitRepository gitRepository = new GitRepository();

    public LiveData<List<Repository>> getResults(String searchString) {
        return gitRepository.getSearchResults(searchString);
    }

    public LiveData<List<Repository>> getRepos() {
        return gitRepository.getRepositoryLiveData();
    }


}
