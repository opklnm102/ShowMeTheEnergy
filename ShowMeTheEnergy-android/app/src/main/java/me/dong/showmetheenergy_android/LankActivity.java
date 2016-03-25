package me.dong.showmetheenergy_android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LankActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView_lank)
    RecyclerView rvLank;

    LankAdapter mLankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lank);

        ButterKnife.bind(this);

        mLankAdapter = new LankAdapter(this);
        rvLank.setLayoutManager(new LinearLayoutManager(this));
        rvLank.setHasFixedSize(true);
        rvLank.setAdapter(mLankAdapter);
    }
}
