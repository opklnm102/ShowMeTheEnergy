package me.dong.showmetheenergy_android.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import me.dong.showmetheenergy_android.model.MonthUsage;
import me.dong.showmetheenergy_android.util.Define;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Dong on 2016-03-25.
 */
public class BackendHelper {

    private static BackendHelper instance;
    private BackendService service;

    //logging
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC);

    OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();

    public static BackendHelper getInstance() {
        synchronized (BackendHelper.class) {
            if (instance == null) {
                instance = new BackendHelper();
            }
            return instance;
        }
    }

    private BackendHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Define.LANKING_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();

        service = retrofit.create(BackendService.class);
    }

    public Call<JsonArray> getMonthUsage() {
        return service.getMonthUsage();
    }

    public Call<JsonObject> postMonthUsage(String uuid, List<MonthUsage> datas) {
        return service.postMonthUsage(uuid, datas);
    }
}
