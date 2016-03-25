package me.dong.showmetheenergy_android.network;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

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


//    https://api.encoredtech.com/1.2/
// devices/6A92ED86-E66B-11E5-9730-9A79F3FFF7B8/usages
// ?period=hourly
// &start=1458864169000
// &end=1458950569000


}
