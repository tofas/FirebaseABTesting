package odigeo.nativeteam.firebaseabtesting.presenter;

import android.support.v4.app.Fragment;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import odigeo.nativeteam.firebaseabtesting.BuildConfig;
import odigeo.nativeteam.firebaseabtesting.R;
import odigeo.nativeteam.firebaseabtesting.interactor.RepoInteractor;
import odigeo.nativeteam.firebaseabtesting.interactor.RepoInteractorInterface;
import odigeo.nativeteam.firebaseabtesting.view.MainViewInterface;

/**
 * Created by daniel.morales on 14/11/16.
 */

public class MainPresenter {

    private static final String INFO_MESSAGE_KEY = "info_message";
    private static final String SHOW_INFO_MESSAGE = "show_info_message";

    private MainViewInterface view;
    private RepoInteractorInterface repoInteractorInterface;

    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private FirebaseAnalytics mFirebaseAnalytics;

    public MainPresenter(MainViewInterface view) {
        this.view = view;
        repoInteractorInterface = new RepoInteractor();
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(((Fragment) view).getContext());

        configExperiment1();
    }

    private void configExperiment1() {
        String experiment1_variant = mFirebaseRemoteConfig.getString("experiment_1");
        AppMeasurement.getInstance(((Fragment) view).getContext()).setUserProperty("ExperimentCardLayout", experiment1_variant);
    }

    public void initContent() {
        view.showLoading();
        fetchRemoteConfig();
    }

    private void fetchRemoteConfig() {
        long cacheExpiration = 3600;

        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(((Fragment) view).getActivity(), task -> {
                    if (task.isSuccessful()) {
                        mFirebaseRemoteConfig.activateFetched();
                    }
                    checkInfoMessage();
                    loadListOfRepos();
                });
    }

    private void checkInfoMessage() {
        if(mFirebaseRemoteConfig.getBoolean(SHOW_INFO_MESSAGE)) {
            view.showInfoMessage(mFirebaseRemoteConfig.getString(INFO_MESSAGE_KEY));
        } else {
            view.hideInfoMessage();
        }

    }

    private void loadListOfRepos() {
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
