package me.dong.showmetheenergy_android.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import me.dong.showmetheenergy_android.model.MonthUsage;
import me.dong.showmetheenergy_android.util.Define;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Dong on 2016-03-25.
 */
public interface EnertalkHomeApiService {

    @GET("devices/{uuid}/usages")
    Call<JsonArray> getDevicePeriodic(@Header("Authorization") String authorization,
                                      @Path("uuid") String uuid,
                                      @Query("period") String period,
                                      @Query("start") long start,
                                      @Query("end") long end);


//    @Headers("Accept: application/json")
//    @GET("api/monthusage")
//    Call<JsonArray> getMonthUsage();
//
//    @Headers("Accept: application/json")
//    @POST("api/monthusage")
//    Call<JsonObject> postMonthUsage(@Header("uuid") String uuid,
//                                    @Body List<MonthUsage> monthUsages);


//    https://api.encoredtech.com/1.2/
// devices/6A92ED86-E66B-11E5-9730-9A79F3FFF7B8/usages
// ?period=hourly
// &start=1458864169000
// &end=1458950569000


}
