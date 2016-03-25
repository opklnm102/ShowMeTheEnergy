package me.dong.showmetheenergy_android.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Dong on 2016-03-25.
 */
public interface EnertalkAuthService {

    @Headers("Accept: application/json")
    @GET("login")
    Call<JsonObject> getAuthorizationCode(@Query("client_id") String clientId,
                                          @Query("response_type") String responseType,
                                          @Query("redirect_uri") String redirectUri,
                                          @Query("app_version") String appVersion,
                                          @Query("back_url") String backUrl
    );


//    client_id=abcdedf123456
//    &response_type=code
//    &redirect_uri=http://localhost:8080/callback
//    // &app_version=web
//    // &back_url=authorization

//    @Headers({
//            "Accept: application/json",
//            "appKey: " + Constants.myKEY
//    })
    //상품 검색
//    @GET("products")
//    Call<JsonObject> productSearch(@Query("version") String version,
//                                   @Query("searchKeyword") String searchKeyword);


    //상품정보 조회
//    @Headers("Accept: application/json")
//    @GET("products/{productCode}")
//    Call<JsonObject> productInfoSearch(@Query("version") String version,
//                                       @Path("productCode") Integer productCode);

}
