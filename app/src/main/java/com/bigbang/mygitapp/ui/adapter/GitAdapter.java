package com.bigbang.mygitapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bigbang.mygitapp.R;
import com.bigbang.mygitapp.model.data.Repository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.GitViewHolder> {

    private List<Repository> repositoryList;

    public GitAdapter(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    public void setRepositoryList(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_item_layout, parent, false);
        return new GitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GitViewHolder holder, int position) {
        String repoName = repositoryList.get(position).getName();
        holder.repositoryNameTextView.setText(repoName);
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    class GitViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.avatar_imageview)
        ImageView avatarImageView;

        @BindView(R.id.repository_name_textview)
        TextView repositoryNameTextView;

        public GitViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
