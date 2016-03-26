package me.dong.showmetheenergy_android.util;

import me.dong.showmetheenergy_android.BuildConfig;
import me.dong.showmetheenergy_android.model.AuthInfo;

/**
 * Created by Dong on 2016-03-25.
 */
public class Define {

    /* Auth Info.*/
    public static final String CLIENT_ID = BuildConfig.ENERTALK_OPEN_API_CLIENT_ID;
    public static final String CLIENT_SECRET = BuildConfig.ENERTALK_OPEN_API_CLIENT_SECRET;

    public static final String AUTH_DOMAIN = "https://enertalk-auth.encoredtech.com";

//    public static final String HOME_DOMAIN = "https://api.encoredtech.com:8082/1.2";

    public static final String HOME_DOMAIN = "https://api.encoredtech.com/1.2/";
    public static final String LANKING_DOMAIN = "http://52.34.93.99:8080/";

    public static final String CARD_DOMAIN = "https://enertalk-card.encoredtech.com";

    public static AuthInfo AUTH_INFO = null;

    public static final String PREFERENCE_NAME = "showmetheenergy_settings";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String UUID = "uuid";


    //Activity Request Code
    public static final int SIGNIN_CODE = 1001;


    //    home.dev@encoredtech.com
//    !Encored0325

//    hack.seoul01@encoredtech.com

}
