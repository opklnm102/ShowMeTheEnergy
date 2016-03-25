package me.dong.showmetheenergy_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by koansang on 2016. 2. 15..
 */

public class WebViewListener extends WebViewClient {

    public static final String TAG = WebViewListener.class.getSimpleName();

    AuthActivity activity;
    int eventType;

    public WebViewListener(AuthActivity activity, int eventType) {
        this.activity = activity;
        this.eventType = eventType;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        view.clearCache(true);
        view.clearHistory();
        Log.i(TAG,"URL : " + url);

        if(url.indexOf("?code=") >= 0 && eventType != 0) {
            String codeStr = url.split("\\?")[1];
            String code = codeStr.split("=")[1];

            final Intent intent = new Intent();
            intent.putExtra("code", code);
            activity.setResult(activity.RESULT_OK, intent);
            activity.finish();
        }
        else {
            eventType = 1;
        }

        super.onPageStarted(view, url, favicon);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
