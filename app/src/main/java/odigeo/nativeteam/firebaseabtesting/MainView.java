package odigeo.nativeteam.firebaseabtesting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by daniel.morales on 14/11/16.
 */

public class MainView extends Fragment implements MainViewInterface {

    private static MainView instance;

    private MainPresenter presenter;

    @BindView(R.id.main_progressLayout)
    RelativeLayout relativeProgressLayout;
    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;

    private MainAdapter adapter;

    public static MainView getInstance() {
        if(instance == null)
            instance = new MainView();

        return instance;
    }

    public MainView() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_view, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadListOfRepos();
    }

    private void initRecyclerView() {
        adapter = new MainAdapter(this);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        relativeProgressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        relativeProgressLayout.setVisibility(View.GONE);
    }

    @Override
    public void refreshRepoList(List<Repo> repos) {
        adapter.refreshRepos(repos);
    }

}
