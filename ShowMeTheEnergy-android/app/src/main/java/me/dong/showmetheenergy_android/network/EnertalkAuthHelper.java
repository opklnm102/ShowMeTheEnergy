package me.dong.showmetheenergy_android.network;

import com.google.gson.JsonObject;

import me.dong.showmetheenergy_android.model.AuthInfo;
import me.dong.showmetheenergy_android.util.Define;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Dong on 2016-03-25.
 */
public class EnertalkAuthHelper {

    private static EnertalkAuthHelper instance;
    private EnertalkAuthService service;

    //logging
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC);

    OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();

    public static EnertalkAuthHelper getInstance(){
        synchronized (EnertalkAuthHelper.class) {
            if (instance == null) {
                instance = new EnertalkAuthHelper();
            }
            return instance;
        }
    }

    private EnertalkAuthHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Define.AUTH_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();

        service = retrofit.create(EnertalkAuthService.class);
    }


    public Call<JsonObject> getAuthorizationCode(AuthInfo authInfo) {

        return service.getAuthorizationCode(authInfo.getClient_id(),
                authInfo.getResponse_type(),
                authInfo.getRedirect_uri(),
                authInfo.getApp_version(),
                authInfo.getBack_url()
        );
    }
}
