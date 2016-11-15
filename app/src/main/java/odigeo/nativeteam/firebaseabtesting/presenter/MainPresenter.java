package odigeo.nativeteam.firebaseabtesting.presenter;

import odigeo.nativeteam.firebaseabtesting.interactor.RepoInteractor;
import odigeo.nativeteam.firebaseabtesting.interactor.RepoInteractorInterface;
import odigeo.nativeteam.firebaseabtesting.view.MainViewInterface;

/**
 * Created by daniel.morales on 14/11/16.
 */

public class MainPresenter {

    private MainViewInterface view;
    private RepoInteractorInterface repoInteractorInterface;

    public MainPresenter(MainViewInterface view) {
        this.view = view;
        repoInteractorInterface = new RepoInteractor();
    }

    public void loadListOfRepos() {
        view.showLoading();
        repoInteractorInterface
                .getListOfRepos()
                .limit(10)
                .subscribe(repos -> {
                            view.refreshRepoList(repos);
                            view.hideLoading();
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            view.hideLoading();
                        },
                        () -> view.hideLoading());
    }
}
