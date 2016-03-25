package me.dong.showmetheenergy_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import me.dong.showmetheenergy_android.model.AuthInfo;
import me.dong.showmetheenergy_android.util.AppUtils;
import me.dong.showmetheenergy_android.util.Define;

public class AuthActivity extends AppCompatActivity {

    public static final String TAG = AuthActivity.class.getSimpleName();

    private String loginCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        WebView wv = (WebView) findViewById(R.id.signInView);
        wv.clearCache(true);
        wv.clearHistory();
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAllowContentAccess(true);
        wv.getSettings().setBlockNetworkLoads(false);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.getSettings().setAppCacheEnabled(false);
        wv.setWebViewClient(new WebViewListener(this, 0));

        Log.i(TAG, "Init Signin");

        wv.loadUrl(makeAuthURL());
    }

    private String makeAuthURL() {
        String url = "";

        AuthInfo authInfo = new AuthInfo(Define.CLIENT_ID, Define.CLIENT_SECRET);

        url += AppUtils.getUrlEncodeString("client_id", authInfo.getClient_id()) + "&";
        url += AppUtils.getUrlEncodeString("response_type", authInfo.getResponse_type()) + "&";
        url += AppUtils.getUrlEncodeString("redirect_uri", authInfo.getRedirect_uri()) + "&";
        url += AppUtils.getUrlEncodeString("app_version", authInfo.getApp_version()) + "&";
        url += AppUtils.getUrlEncodeString("back_url", authInfo.getBack_url());

        Log.i(TAG, " " + url);

        return Define.AUTH_DOMAIN + "/login?" + url;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }
}
