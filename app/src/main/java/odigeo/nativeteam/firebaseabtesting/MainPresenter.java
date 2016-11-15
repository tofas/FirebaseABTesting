package odigeo.nativeteam.firebaseabtesting;

/**
 * Created by daniel.morales on 14/11/16.
 */

public class MainPresenter {

    private MainViewInterface view;
    private RepoInteractor repoInteractor;

    public MainPresenter(MainViewInterface view) {
        this.view = view;
        repoInteractor = new RepoInteractor();
    }

    public void loadListOfRepos() {
        view.showLoading();
        repoInteractor
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
