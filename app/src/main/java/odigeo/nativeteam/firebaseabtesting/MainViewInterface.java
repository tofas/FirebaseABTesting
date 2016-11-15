package odigeo.nativeteam.firebaseabtesting;

import java.util.List;

/**
 * Created by daniel.morales on 14/11/16.
 */
public interface MainViewInterface {
    void showLoading();

    void refreshRepoList(List<Repo> repos);

    void hideLoading();
}
