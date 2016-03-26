package me.dong.showmetheenergy_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.dong.showmetheenergy_android.model.MonthUsage;
import me.dong.showmetheenergy_android.network.BackendHelper;
import me.dong.showmetheenergy_android.network.EnertalkHomeApiHelper;
import me.dong.showmetheenergy_android.util.Define;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    protected static EnertalkHomeApiHelper sEnertalkHomeApiHelper;
    protected static BackendHelper sBackendHelper;

    protected static SharedPreferences preferences;

    private ReboundProvider mReboundProvider;
    private Spring mScaleSpring;
    private final RankShowSpringListener mSpringListener = new RankShowSpringListener();

    @Bind(R.id.imageView_rank_show)
    ImageView ivRankShow;

    @Bind(R.id.textView_month_Usage_before)
    TextView tvMonthUsageBefore;

    @Bind(R.id.textView_month_Usage_current)
    TextView tvMonthUsageCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        if (mReboundProvider == null) {
            mReboundProvider = ReboundProvider.getInstance();
        }

        mScaleSpring = mReboundProvider.getNewSpring();

        if (sEnertalkHomeApiHelper == null) {
            sEnertalkHomeApiHelper = EnertalkHomeApiHelper.getInstance();
        }

        if (sBackendHelper == null) {
            sBackendHelper = BackendHelper.getInstance();
        }

        if (preferences == null) {
            preferences = getSharedPreferences(Define.PREFERENCE_NAME, Context.MODE_PRIVATE);
        }

//        ivRankShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, LankActivity.class);
//                startActivity(intent);
//            }
//        });

        ivRankShow.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // When pressed start solving the spring to 1.
                        mScaleSpring.setEndValue(1);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // When released start solving the spring to 0.
                        mScaleSpring.setEndValue(0);

                        new Handler(getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, LankActivity.class);
                                startActivity(intent);
                            }
                        }, 2000);

                        break;
                }
                return true;
            }
        });
    }

    public void setMonthUsage(MonthUsage beforeData, MonthUsage CurrentData) {

        Double beforeUsage = beforeData.getUnitPeriodUsage().doubleValue() / 1000000;
        Double currentUsage = CurrentData.getUnitPeriodUsage().doubleValue() / 1000000;

        tvMonthUsageBefore.setText(beforeUsage + "kwh");
        tvMonthUsageCurrent.setText(currentUsage + "kwh");
    }

    @Override
    protected void onResume() {
        super.onResume();

        mScaleSpring.addListener(mSpringListener);

        getDevicePeriodic();
    }

    public void getDevicePeriodic() {

        String accessToken = preferences.getString(Define.ACCESS_TOKEN, "empty");
        String uuid = preferences.getString(Define.UUID, "empty");

        Log.d(TAG, " " + accessToken + " " + uuid);

        Call<JsonArray> call = sEnertalkHomeApiHelper.getDevicePeriodic(accessToken, uuid);
        call.enqueue(new Callback<JsonArray>() {

            @Override
            public void onResponse(Response<JsonArray> response) {

                JsonArray jaRoot = response.body();
                Log.d(TAG, " " + jaRoot);

                if (jaRoot != null) {
                    int size = jaRoot.size();
                    ArrayList<MonthUsage> dataArrayList = new ArrayList<MonthUsage>();

                    for (int i = 0; i < size; i++) {
                        MonthUsage data = new Gson().fromJson(jaRoot.get(i), MonthUsage.class);
                        dataArrayList.add(data);
                    }

                    postMonthUsage(dataArrayList);

                    Log.d(TAG, " " + dataArrayList);

                    final MonthUsage beforeData = dataArrayList.get(size - 2);
                    final MonthUsage CurrentData = dataArrayList.get(size - 1);

                    Log.d(TAG, " " + beforeData + " " + CurrentData);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setMonthUsage(beforeData, CurrentData);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, " Throwable is " + t);
            }
        });
    }

    public void postMonthUsage(List<MonthUsage> datas) {

        String uuid = preferences.getString(Define.UUID, "empty");

        Call<JsonObject> call = sBackendHelper.postMonthUsage(uuid, datas);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Response<JsonObject> response) {
                Log.d(TAG, " postMonthUsage: " + response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, " Throwable is " + t);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScaleSpring.removeListener(mSpringListener);
    }

    private class RankShowSpringListener extends SimpleSpringListener {

        @Override
        public void onSpringUpdate(Spring spring) {
            super.onSpringUpdate(spring);

            float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 0.5);
            ivRankShow.setScaleX(mappedValue);
            ivRankShow.setScaleY(mappedValue);
        }
    }
}
