package odigeo.nativeteam.firebaseabtesting;

import com.google.gson.JsonObject;

import rx.Observable;

/**
 * Created by daniel.morales on 14/11/16.
 */

public class RepoController {

    GitHubService service;

    public RepoController() {
        service = RetrofitService.getRetrofitService(GitHubService.class);
    }

    public Observable<JsonObject> getRepos(String params) {
        return service.listRepos(params);
    }
}
