package me.dong.showmetheenergy_android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Dong on 2016-03-26.
 */
public class LankViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.textView_lank)
    TextView tvLank;

    @Bind(R.id.textView_id)
    TextView tvId;

    @Bind(R.id.textView_point)
    TextView tvPoint;

    @Bind(R.id.imageView_change)
    ImageView ivChange;

    @Bind(R.id.textView_change_range)
    TextView tvChangeRange;

    View mView;

    public LankViewHolder(View itemView) {
        super(itemView);
        mView = itemView;

        ButterKnife.bind(this, mView);
    }
}
