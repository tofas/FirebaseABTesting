package odigeo.nativeteam.firebaseabtesting.controller;

import com.google.gson.JsonObject;

import odigeo.nativeteam.firebaseabtesting.retrofit.GitHubService;
import odigeo.nativeteam.firebaseabtesting.retrofit.RetrofitService;
import rx.Observable;

/**
 * Created by daniel.morales on 14/11/16.
 */

public class RepoController implements RepoControllerInterface{

    GitHubService service;

    public RepoController() {
        service = RetrofitService.getRetrofitService(GitHubService.class);
    }

    @Override
    public Observable<JsonObject> getRepos(String params) {
        return service.listRepos(params, 1, 30);
    }
}
