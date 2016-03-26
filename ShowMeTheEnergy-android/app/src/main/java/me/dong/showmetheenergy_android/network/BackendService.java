package me.dong.showmetheenergy_android.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import me.dong.showmetheenergy_android.model.MonthUsage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Dong on 2016-03-25.
 */
public interface BackendService {

    @Headers("Accept: application/json")
    @GET("api/monthusage")
    Call<JsonArray> getMonthUsage();

    @Headers("Accept: application/json")
    @POST("api/monthusage")
    Call<JsonObject> postMonthUsage(@Header("uuid") String uuid,
                                    @Body List<MonthUsage> monthUsages);
}
