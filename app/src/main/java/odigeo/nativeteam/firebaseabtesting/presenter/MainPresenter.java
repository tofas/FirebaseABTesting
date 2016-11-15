package odigeo.nativeteam.firebaseabtesting.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.concurrent.Executor;

import odigeo.nativeteam.firebaseabtesting.BuildConfig;
import odigeo.nativeteam.firebaseabtesting.R;
import odigeo.nativeteam.firebaseabtesting.interactor.RepoInteractor;
import odigeo.nativeteam.firebaseabtesting.interactor.RepoInteractorInterface;
import odigeo.nativeteam.firebaseabtesting.view.MainViewInterface;

/**
 * Created by daniel.morales on 14/11/16.
 */

public class MainPresenter {

    private static final String WELCOME_MESSAGE_KEY = "welcome_message";

    private MainViewInterface view;
    private RepoInteractorInterface repoInteractorInterface;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    public MainPresenter(MainViewInterface view) {
        this.view = view;
        repoInteractorInterface = new RepoInteractor();
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);
    }

    public void initContent() {
        view.setTitle(mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY));
        fetchTitleConfig();
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

    public void fetchTitleConfig() {
        long cacheExpiration = 3600; // 1 hour in seconds.
        // If in developer mode cacheExpiration is set to 0 so each fetch will retrieve values from
        // the server.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(((Fragment) view).getActivity(), task -> {
                    if (task.isSuccessful()) {
                        Log.d("FIREBASE", "Fetch Succeeded");

                        // Once the config is successfully fetched it must be activated before newly fetched
                        // values are returned.
                        mFirebaseRemoteConfig.activateFetched();
                    } else {
                        Log.d("FIREBASE", "Fetch Failed");
                    }
                    updateTitleConfig();
                });
    }

    void updateTitleConfig() {
        view.setTitle(mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY));
    }
}
