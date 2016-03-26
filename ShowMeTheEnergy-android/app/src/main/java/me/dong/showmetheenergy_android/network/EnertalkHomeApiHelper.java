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
public class EnertalkHomeApiHelper {

    private static EnertalkHomeApiHelper instance;
    private EnertalkHomeApiService service;

    //logging
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC);

    OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();

    public static EnertalkHomeApiHelper getInstance(){
        synchronized (EnertalkHomeApiHelper.class) {
            if (instance == null) {
                instance = new EnertalkHomeApiHelper();
            }
            return instance;
        }
    }

    private EnertalkHomeApiHelper() {



        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Define.HOME_DOMAIN)
                .client(mOkHttpClient)
                .build();

        service = retrofit.create(EnertalkHomeApiService.class);
    }

    public Call<JsonArray> getDevicePeriodic(String accessToken, String uuid){
        return service.getDevicePeriodic("Bearer " + accessToken,
                uuid,
                "hourly",
                1458864169000L,
                1458950569000L
                );
//        return service.getDevicePeriodic(Define.HOME_DOMAIN
//        );
    }

//    public Call<JsonArray> getMonthUsage() {
//        return service.getMonthUsage();
//    }
//
//    public Call<JsonObject> postMonthUsage(String uuid, List<MonthUsage> datas) {
//        return service.postMonthUsage(uuid, datas);
//    }
}
