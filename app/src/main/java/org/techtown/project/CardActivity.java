package org.techtown.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.techtown.project.databinding.ActivityCardBinding;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import io.realm.Realm;
import io.realm.RealmResults;


public class CardActivity extends AppCompatActivity{
    ActivityCardBinding cardBinding;
    CardAdapter adapter = new CardAdapter(this);
    static CountDownTimer timer;
    String message;                         //adapter 에서 끝이라는 메시지
    static long milSecond;                  //타이머의 초 변수
    String TAG="CardActivity";
    static Realm realm;
    TimerDB timerDB;
    DateDB dateDB;
    ArrayList<String> dateDbList = new ArrayList<>();
    ArrayList<String> datelist = new ArrayList<>();
    FirebaseDatabase database= FirebaseDatabase.getInstance();                  //파이어베이스db
    DatabaseReference dataRef = database.getReference().child("Ranking");       //파이어베이스db
    static ArrayList<Long> dblist = new ArrayList<>();
    ArrayList<Long> dblist3 = new ArrayList<>();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    static String gname;                                                        //구글사용자이름


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        cardBinding = DataBindingUtil.setContentView(this,R.layout.activity_card);

//        StartGameCustomDialog start = new StartGameCustomDialog(this);
//        start.show();


        Realm.init(this);
        realm = Realm.getDefaultInstance();
        cardBinding.recyclerView2.setHasFixedSize(true);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        cardBinding.recyclerView2.setLayoutManager(manager);
        cardBinding.recyclerView2.setAdapter(adapter);
        itemadd();
        timer(180000);




    }
    public void create(Context context){
    }
    public void timer(long a){                     //타이머 메소드
        timer= new CountDownTimer(a,1000){  //3분 (180초)180000
            @Override
            public void onTick(long millis) {
                milSecond =millis/1000;
                long minute = milSecond/60;
                long second = milSecond%60;
                cardBinding.textTimer.setText(minute+":"+second);
                Log.d(TAG,"milSecond : "+milSecond);
            }
            @Override
            public void onFinish() {
                cardBinding.textTimer.setText("0:0");
                Toast.makeText(CardActivity.this,"타이머가 지나서 끝났습니다",Toast.LENGTH_SHORT).show();
                Log.d("CardActivity","timer end");
            }
        };
        timer.start();
    }


    public void itemadd() {              //아이템을 추가하는 메소드

        Intent intent = getIntent();
        String catagori = intent.getExtras().getString("카테고리");
        String level = intent.getExtras().getString("난이도");
        gname = intent.getExtras().getString("이름");
        if (catagori.equals("타로")) {
            cardBinding.LinearLayoutBoss.setBackground(ContextCompat.getDrawable(this,R.drawable.tarotbackground));
            if (level.equals("상")) {
                adapter.addItem(new Card(0));
            } else if (level.equals("중")) {
                adapter.addItem(new Card(1));
            } else if (level.equals("하")) {
                adapter.addItem(new Card(2));
            }
        } else if (catagori.equals("동물")) {
            cardBinding.LinearLayoutBoss.setBackground(ContextCompat.getDrawable(this,R.drawable.hirsch));
            if (level.equals("상")) {
                adapter.addItem(new Card(3));
            } else if (level.equals("중")) {
                adapter.addItem(new Card(4));
            } else if (level.equals("하")) {
                adapter.addItem(new Card(5));
            }
        } else if (catagori.equals("국기")) {
            cardBinding.LinearLayoutBoss.setBackground(ContextCompat.getDrawable(this,R.drawable.planet));
            if (level.equals("상")) {
                adapter.addItem(new Card(6));
            } else if (level.equals("중")) {
                adapter.addItem(new Card(7));
            } else if (level.equals("하")) {
                adapter.addItem(new Card(8));
            }
        }

    }
    public void message(String mge, Context context){              //게임이 끝나면 기록을 체크해서 이기록이 최고기록이면 파이어베이스에 등록하는 메소드
        message=mge;
        Toast.makeText(context,"끝났습니다",Toast.LENGTH_SHORT).show();
//        System.currentTimeMillis();
//        String format5 = df.format(System.currentTimeMillis());
//        realm.beginTransaction();
//        dateDB =realm.createObject(DateDB.class);
//        dateDB.setDate(format5);
//        realm.commitTransaction();

        RealmResults<DateDB> DateList = realm.where(DateDB.class).findAll();
        for (DateDB dateDB : DateList) {

            datelist.add(dateDB.getDate());
            Log.d(TAG, "dblist3 : " + dateDB.getDate());
        }
        Log.d(TAG,"datelist : "+datelist);
//        if(datelist.size()==1){
//            realm.beginTransaction();
//            RealmResults<DateDB> list = realm.where(DateDB.class).findAll();
//            list.deleteAllFromRealm();
//            Log.d(TAG,"list : "+list);
//            dateDbSave();
//            realm.commitTransaction();
//        }

        if(message.equals("끝")){
            timer.cancel();
            Log.d(TAG,"end saveTimer : "+milSecond);
            dblist.add(milSecond);
            for(int i=0; i<dblist.size(); i++){
                realm.beginTransaction();
                timerDB = realm.createObject(TimerDB.class);
                timerDB.setTimer(dblist.get(i));
                realm.commitTransaction();
                Log.d(TAG,"message dblist : "+dblist.get(i));
            }
            RealmResults<TimerDB> List = realm.where(TimerDB.class).findAll();
            for (TimerDB timerDB2 : List) {
                dblist3.add(timerDB2.getTimer());
                Log.d(TAG, "dblist3 : " + timerDB2.getTimer());
            }
        }
        Collections.sort(dblist3);
        ArrayList<String> stringdblist = new ArrayList<>();
        stringdblist.add(String.valueOf(dblist3.get(dblist3.size()-1)));
        dataRef.child(gname).setValue(stringdblist.get(0));
    }

    public void dateDbSave(){

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
//            cal.add(Calendar.DATE, 1);
        String onedate = df.format(cal.getTime());
        Log.d(TAG,"cal : "+onedate);
        dateDbList.add(onedate);


        for(int i=0; i<dateDbList.size(); i++){
            realm.beginTransaction();
            dateDB =realm.createObject(DateDB.class);
            dateDB.setDate(dateDbList.get(i));
            realm.commitTransaction();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        long time=milSecond*1000;
        timer(time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
        timer.cancel();
    }


}
