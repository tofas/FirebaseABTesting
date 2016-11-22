package odigeo.nativeteam.firebaseabtesting.view;

import java.util.List;

import odigeo.nativeteam.firebaseabtesting.controller.Repo;

/**
 * Created by daniel.morales on 14/11/16.
 */
public interface MainViewInterface {

    void showInfoMessage(String title);
    void hideInfoMessage();

    void showLoading();

    void refreshRepoList(List<Repo> repos);

    void hideLoading();
}