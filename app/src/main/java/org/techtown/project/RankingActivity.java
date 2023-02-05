package org.techtown.project;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.techtown.project.databinding.ActivityRankingBinding;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//import static org.techtown.project.MainActivity.maincontext;


public class RankingActivity extends AppCompatActivity {
    ActivityRankingBinding rankingBinding;

    String TAG = "RankingActivity";
    ArrayList<String[]> rankingList = new ArrayList<String[]>();
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference dataRef = database.getReference().child("Ranking");
    RankingAdapter adapter = new RankingAdapter();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rankingBinding = DataBindingUtil.setContentView(this,R.layout.activity_ranking);
        rankingBinding.recyclerViewRanking.setHasFixedSize(true);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        rankingBinding.recyclerViewRanking.setLayoutManager(manager);
        rankingBinding.recyclerViewRanking.setAdapter(adapter);

            dataRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for( DataSnapshot postSnapshot : dataSnapshot.getChildren()){   //딜레이  해결하기
                        String value = postSnapshot.getValue(String.class);
                        String  dbname = postSnapshot.getKey();
                        String db [] = new String[2];
                        db[0]= dbname;
                        db[1]= value;
                        rankingList.add(db);
                        Log.d("==", "11111");
                    }
                    rankingList = sortAscending(rankingList);
                    for(int i=0; i<rankingList.size(); i++){
                        long milSeond2 = Long.valueOf(rankingList.get(i)[1]);
                        long minute = milSeond2/60;
                        long second = milSeond2%60;
                        String strmilSeond2=minute+"분"+" "+second+"초";
                        if(i==0 || i==1 || i==2){
                            if(i==0){
                                adapter.addItem(new Ranking((i+1)+"등",rankingList.get(i)[0],strmilSeond2,0,1)); //int count,String rankingName , String ranking,int itemViewType ,int rankingid
                            }
                            else if(i==1){
                                adapter.addItem(new Ranking((i+1)+"등",rankingList.get(i)[0],strmilSeond2,0,2));
                            }
                            else {
                                adapter.addItem(new Ranking((i+1)+"등",rankingList.get(i)[0],strmilSeond2,0,3));
                            }
                        }
                        else{
                            adapter.addItem(new Ranking((i+1)+"등",rankingList.get(i)[0],strmilSeond2,0));
                        }
                    }
                    adapter.notifyDataSetChanged();

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d(TAG,"ERROR --------------------------------------------------------------------------------------------------------------------------------------------"+databaseError);
                }
            });

        Log.d("==","22222222");

    }

    public static ArrayList<String[]> sortAscending(ArrayList<String[]> list){          //어레이 리스트안에있는 String형 배열을 기준으로 정렬해주는 매소드
        Collections.sort(list, new Comparator<String[]>() {
            @Override
            public int compare(String[] strings, String[] t1) {
                if(Integer.valueOf(strings[1])<Integer.valueOf(t1[1])){
                    return 1;
                }
                else if(Integer.valueOf(strings[1])==Integer.valueOf(t1[1])){
                    return strings[1].compareTo(t1[1]);
                }
                else {
                    return -1;
                }
            }
        });
        return list;
    }






}
