package me.dong.showmetheenergy_android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.dong.showmetheenergy_android.model.Lank;
import me.dong.showmetheenergy_android.network.BackendHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LankActivity extends AppCompatActivity {

    public static final String TAG = LankActivity.class.getSimpleName();

    @Bind(R.id.recyclerView_lank)
    RecyclerView rvLank;

    LankAdapter mLankAdapter;

    protected static BackendHelper sBackendHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lank);

        ButterKnife.bind(this);

        if(sBackendHelper == null){
            sBackendHelper = BackendHelper.getInstance();
        }

        mLankAdapter = new LankAdapter(this);
        rvLank.setLayoutManager(new LinearLayoutManager(this));
        rvLank.setHasFixedSize(true);
        rvLank.setAdapter(mLankAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getMonthUsage();
    }

    public void getMonthUsage(){

        Call<JsonArray> call = sBackendHelper.getMonthUsage();
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Response<JsonArray> response) {

                JsonArray jaRoot = response.body();

                if(jaRoot != null){

                    final ArrayList<Lank> lanks = new ArrayList<Lank>();

                    for(int i=0; i<jaRoot.size(); i++){
                        Lank lank = new Gson().fromJson(jaRoot.get(i), Lank.class);
                        lanks.add(lank);
                    }

                    Log.d(TAG, " " + lanks);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mLankAdapter.setData(lanks);
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
}
