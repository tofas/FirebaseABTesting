package odigeo.nativeteam.firebaseabtesting.retrofit;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by daniel.morales on 14/11/16.
 */

public interface GitHubService {
    @GET("search/repositories")
    Observable<JsonObject> listRepos(@Query("q") String searchParam, @Query("page") int page, @Query("per_page") int perPage);
}
