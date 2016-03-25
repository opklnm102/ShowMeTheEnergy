package me.dong.showmetheenergy_android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.dong.showmetheenergy_android.model.Lank;

/**
 * Created by Dong on 2016-03-26.
 */
public class LankAdapter extends RecyclerView.Adapter<LankViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Lank> mLankArrayList;

    public LankAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mLankArrayList = new ArrayList<>();
        setDummy();
    }

    public void setDummy(){
        for(int i=0; i<20; i++){
            Lank lank = new Lank();
            lank.setLank(i + 1);
            lank.setId("hack.seoul01");
            lank.setPoint(13232);
            if(i%2==0){
                lank.setChange(1);
            }else if(i%3 == 0){
                lank.setChange(-1);
            }else{
                lank.setChange(0);
            }

            lank.setChangeRange(3);
            mLankArrayList.add(lank);
            notifyItemChanged(i);
        }
    }

    @Override
    public LankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list_lank, parent, false);

        return new LankViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LankViewHolder holder, int position) {

        Lank lank = mLankArrayList.get(position);

        holder.tvLank.setText(lank.getLank().toString());
        holder.tvId.setText(lank.getId());
        holder.tvPoint.setText(lank.getPoint().toString());
        holder.tvChangeRange.setText(lank.getChangeRange().toString());

        if(lank.getChange() == 1){
            holder.ivChange.setImageResource(R.drawable.ic_green_arrow);
        }else if(lank.getChange() == 0){
            holder.ivChange.setVisibility(View.INVISIBLE);
        }else{
            holder.ivChange.setImageResource(R.drawable.ic_red_arrow);
        }
    }

    @Override
    public int getItemCount() {
        return mLankArrayList.size();
    }
}
