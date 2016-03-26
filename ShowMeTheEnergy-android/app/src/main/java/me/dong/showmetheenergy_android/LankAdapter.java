package me.dong.showmetheenergy_android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.dong.showmetheenergy_android.model.Rank;

/**
 * Created by Dong on 2016-03-26.
 */
public class LankAdapter extends RecyclerView.Adapter<LankViewHolder> {

    public static final String TAG = LankAdapter.class.getSimpleName();

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Rank> mRankArrayList;

    public LankAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mRankArrayList = new ArrayList<>();
        setDummy();
    }

    public void setDummy(){
        for(int i=0; i<20; i++){
            Rank rank = new Rank();
            rank.setLank(i + 1);
            rank.setUuid("hack.seoul01");
            rank.setPoint(13232);
            if(i%2==0){
                rank.setChange(1);
            }else if(i%3 == 0){
                rank.setChange(-1);
            }else{
                rank.setChange(0);
            }

            rank.setChangeRange(3);
            mRankArrayList.add(rank);
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

        Rank rank = mRankArrayList.get(position);

        holder.tvLank.setText(rank.getLank().toString());
        holder.tvId.setText(rank.getUuid());
        holder.tvPoint.setText(rank.getPoint().toString());
        holder.tvChangeRange.setText(rank.getChangeRange().toString());

        if(rank.getChange() == 1){
            holder.ivChange.setImageResource(R.drawable.ic_green_arrow);
        }else if(rank.getChange() == 0){
            holder.ivChange.setVisibility(View.INVISIBLE);
        }else{
            holder.ivChange.setImageResource(R.drawable.ic_red_arrow);
        }
    }

    @Override
    public int getItemCount() {
        return mRankArrayList.size();
    }

    public void setData(ArrayList<Rank> datas){

        mRankArrayList = new ArrayList<>();

        for(int i=0; i<datas.size(); i++){
            Rank rank = datas.get(i);
            rank.setLank(i + 1);
            int flag = i%3;
            if(flag == 0){
                rank.setChange(1);
            }else if(flag == 1){
                rank.setChange(-1);
            }else{
                rank.setChange(0);
            }

            rank.setChangeRange(i%3);

            Log.d(TAG, " " + rank);

            mRankArrayList.add(rank);
        }
        notifyDataSetChanged();
    }
}
