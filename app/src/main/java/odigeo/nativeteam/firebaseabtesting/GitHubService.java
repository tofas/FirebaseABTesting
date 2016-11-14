package odigeo.nativeteam.firebaseabtesting;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by daniel.morales on 14/11/16.
 */

public interface GitHubService {
    @GET("users/{user}/repos")
    Observable<JsonObject> listRepos(@Path("q") String searchParam);
}
