package me.dong.showmetheenergy_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.dong.showmetheenergy_android.util.Define;
import me.dong.showmetheenergy_android.util.HttpRequest;


public class SigninActivity extends AppCompatActivity {

    public static final String TAG = SigninActivity.class.getSimpleName();

    protected static SharedPreferences preferences;

    @Bind(R.id.button_signin)
    ImageView ivSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ButterKnife.bind(this);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        if(preferences == null){
            preferences = getSharedPreferences(Define.PREFERENCE_NAME, Context.MODE_PRIVATE);
        }

        String accessTokenCheck = preferences.getString(Define.ACCESS_TOKEN, "empty");

        if(!accessTokenCheck.equals("empty")){
            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        ivSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, AuthActivity.class);
                startActivityForResult(intent, Define.SIGNIN_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                String code = data.getExtras().getString("code");
                getToken(code);

                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                intent.putExtra("code", code);

                startActivity(intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getToken(String code) {

        try {
            JSONObject json = new JSONObject();
            json.put("client_id", Define.CLIENT_ID);
            json.put("client_secret", Define.CLIENT_SECRET);
            json.put("grant_type", "authorization_code");
            json.put("code", code);

            HttpRequest request = new HttpRequest();
            request.execute("POST", Define.AUTH_DOMAIN + "/token", "", "", json);

            JSONObject result = (JSONObject) request.get();

            Log.d(TAG, "token " + result);

            String accessToken = result.getString("access_token");

            request = new HttpRequest();
//            request.execute("GET", Define.AUTH_DOMAIN + "/uuid", "Bearer", Define.ACCESS_TOKEN, null);

            request.execute("GET", Define.AUTH_DOMAIN + "/uuid", "Bearer", accessToken, null);
            result = (JSONObject) request.get();

            Log.d(TAG, "uuid " + result);

            String uuid = result.getString("uuid");
            Log.i(TAG, "Device UUId " + uuid);

            putPreferences(accessToken, uuid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putPreferences(String accessToken, String uuid) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Define.ACCESS_TOKEN, accessToken);
        editor.putString(Define.UUID, uuid);
        editor.commit();
    }
}
