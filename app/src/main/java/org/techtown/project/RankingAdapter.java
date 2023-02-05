package org.techtown.project;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{            //랭킹 액티비티의 어댑터
    public static final int VIEW_TYPE_A = 0;
    ArrayList<Ranking> rankingList = new ArrayList<>();



    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rankingitem_ranking, parent, false);
            return new ViewHolder1(v);
    }
    public int getItemViewType(int position) {
            return VIEW_TYPE_A;

    }
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Ranking ranking = rankingList.get(position);
        if(holder instanceof ViewHolder1){
            if(ranking.getRankingId()==1){
                ((ViewHolder1) holder).rankingImage.setImageResource(R.drawable.medal1);
                ((ViewHolder1) holder).rankingItemLayout.setBackgroundResource(R.drawable.layoutitem_gold);        //랭킹이 1위면 금메달과 layout의 색변경
            }
            else if(ranking.getRankingId()==2){
                ((ViewHolder1) holder).rankingImage.setImageResource(R.drawable.medal2);
                ((ViewHolder1) holder).rankingItemLayout.setBackgroundResource(R.drawable.layoutitem_silver);
            }
            else if(ranking.getRankingId()==3){
                ((ViewHolder1) holder).rankingImage.setImageResource(R.drawable.medal3);
                ((ViewHolder1) holder).rankingItemLayout.setBackgroundResource(R.drawable.layoutitem_bronze);
            }
            else{

            }
            ((ViewHolder1) holder).rankingCount.setText(""+ranking.getCount());
            ((ViewHolder1) holder).rankingName.setText(""+ranking.getRankingname());
            ((ViewHolder1) holder).rankingRanking.setText(""+ranking.getRanking());
        }
    }
    public void addItem(Ranking ranking){
        rankingList.add(ranking);
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return rankingList.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        TextView rankingCount;
        TextView rankingName;
        TextView rankingRanking;
        ImageView rankingImage;
        LinearLayout rankingItemLayout;
        public ViewHolder1( View view){
            super(view);
            rankingItemLayout = (LinearLayout) view.findViewById(R.id.rankingItemLayout);
            rankingCount = (TextView) view.findViewById(R.id.rankingCount);
            rankingName = (TextView) view.findViewById(R.id.rankingName);
            rankingRanking = (TextView) view.findViewById(R.id.rankingRanking);
            rankingImage = (ImageView) view.findViewById(R.id.rankingImage);
        }
    }

}
