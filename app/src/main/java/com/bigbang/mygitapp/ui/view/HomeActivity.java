package com.bigbang.mygitapp.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.bigbang.mygitapp.R;
import com.bigbang.mygitapp.model.data.Repository;
import com.bigbang.mygitapp.ui.adapter.GitAdapter;
import com.bigbang.mygitapp.viewmodel.GitViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    private GitViewModel gitViewModel;
    private Observer<List<Repository>> repositoryObserver;
    private GitAdapter gitAdapter = new GitAdapter(new ArrayList<>());

    @BindView(R.id.repository_recyclerview)
    RecyclerView repositoryRecyclerView;

    @BindView(R.id.git_repo_edittext)
    EditText gitRepoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        repositoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        repositoryRecyclerView.setAdapter(gitAdapter);

        gitViewModel = ViewModelProviders.of(this).get(GitViewModel.class);

        repositoryObserver = this::updateRepositories;

        gitViewModel.getRepos().observe(this, repositoryObserver);


        gitRepoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                DO nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //gitViewModel.getResults(s.toString().trim()).observe(HomeActivity.this, repositoryObserver);
            }

            @Override
            public void afterTextChanged(Editable s) {
//              Do nothing
            }
        });

    }

    private void updateRepositories(List<Repository> repositories) {
        gitAdapter.setRepositoryList(repositories);
    }

}