package odigeo.nativeteam.firebaseabtesting;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by daniel.morales on 14/11/16.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RepoViewHolder> {

    private final Fragment context;
    private ArrayList<Repo> repositories;

    public MainAdapter(Fragment context) {
        this.context = context;
        this.repositories = new ArrayList<>();
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_item_layout_a, parent, false));
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        Repo repository = repositories.get(position);

        holder.repoNameTextView.setText(repository.getName());
        holder.repoLanguageTextView.setText(repository.getLanguage());
        holder.repoDescriptionTextView.setText(repository.getDescription());

    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public void refreshRepos(List<Repo> repos) {
        repositories.clear();
        repos.addAll(repos);
        notifyDataSetChanged();
    }

    public static class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.repo_name_textview)
        TextView repoNameTextView;
        @BindView(R.id.repo_language_textview)
        TextView repoLanguageTextView;
        @BindView(R.id.repo_description_textview)
        TextView repoDescriptionTextView;
        @BindView(R.id.goto_link_button)
        Button gotoLinkButton;

        View view;

        public RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }
}
