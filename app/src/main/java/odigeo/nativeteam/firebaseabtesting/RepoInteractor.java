package odigeo.nativeteam.firebaseabtesting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by daniel.morales on 14/11/16.
 */

public class RepoInteractor {

    private final String ITEMS = "items";
    private final String ID = "id";
    private final String NAME = "name";
    private final String URL = "url";
    private final String DESCRIPTION = "description";
    private final String CREATED_AT = "created_at";
    private final String LANGUAGE = "language";

    private RepoController controller;

    public RepoInteractor() {
        controller = new RepoController();
    }

    public Observable<List<Repo>> getListOfRepos() {
        return controller.getRepos("library+android")
                .map(json -> parseListOfRepos(json))
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());
    }

    private List<Repo> parseListOfRepos(JsonObject json) {
        List<Repo> repos = new ArrayList<>();

        JsonArray array = json.getAsJsonArray(ITEMS);

        for(int i = 0; i < array.size(); ++i) {
            repos.add(parseRepoObject(array.get(i).getAsJsonObject()));
        }

        return repos;
    }

    private Repo parseRepoObject(JsonObject asJsonObject) {
        Repo repo = new Repo();

        repo.id = asJsonObject.get(ID).getAsLong();
        repo.name = asJsonObject.get(NAME).getAsString();
        repo.url = asJsonObject.get(URL).getAsString();
        repo.description = asJsonObject.get(DESCRIPTION).getAsString();
        repo.createdAt = asJsonObject.get(CREATED_AT).getAsString();
        repo.language = asJsonObject.get(LANGUAGE).getAsString();

        return repo;
    }
}
