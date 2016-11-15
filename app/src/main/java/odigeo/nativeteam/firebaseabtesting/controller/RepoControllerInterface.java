package odigeo.nativeteam.firebaseabtesting.controller;

import com.google.gson.JsonObject;

import rx.Observable;

/**
 * Created by daniel.morales on 15/11/16.
 */

public interface RepoControllerInterface {
    Observable<JsonObject> getRepos(String params);
}
