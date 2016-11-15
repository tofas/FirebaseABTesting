package odigeo.nativeteam.firebaseabtesting.retrofit;

import odigeo.nativeteam.firebaseabtesting.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daniel.morales on 14/11/16.
 */

public class RetrofitService {

    public static <T> T getRetrofitService(Class<T> clazz) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        final Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        T service = restAdapter.create(clazz);

        return service;
    }
}
