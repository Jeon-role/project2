package org.techtown.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import org.techtown.project.databinding.ActivityMainBinding;
import org.techtown.project.databinding.DrawerheaderBinding;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.LocaleData;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import io.realm.Realm;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    CardRuleCustomDialog cardRuleCustomDialog;
    static MyAreaCustomDialog myArea;
    DisplayMetrics dm;
    int width;
    int height;
    String TAG = "MainActivity";
    ProgressDialog progressDialog;
    static String  googleName;
    String googleEmail;
    Uri googleUri;
    URL url;
    String str;
    Bitmap bm;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    static int a=1;
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference dataRef3 = database.getReference().child("Location");
    DatabaseReference data = database.getReference().child("hi");
    ArrayList<String []> array = new ArrayList<>();
//    public static Context maincontext;
    String title;






    public void demiss(String Do ,String Si){   //지역등록하는 다이얼로그를 종료하는 메소드
        Log.d(TAG,"googleName : "+googleName);
        Log.d(TAG,"Do : "+Do);
        Log.d(TAG,"Si : "+Si);
        dataRef3.child(googleName).removeValue();
        dataRef3.child(googleName).child(Do).setValue(Si);
        myArea.dismiss();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        maincontext = this;
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Intent gintent = getIntent();
        String  name= gintent.getExtras().getString("name");
        googleName = gintent.getExtras().getString("googleName");
        Log.d(TAG, "googleName : "+googleName);
        googleEmail = gintent.getExtras().getString("googleEmail");
        Log.d(TAG, "googleEmail : "+googleEmail);
        googleUri = gintent.getParcelableExtra("googleUri");
        Log.d(TAG, "googleUri : "+googleUri);
        str = googleUri.toString();

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(MainActivity.this,"안녕 디비야",Toast.LENGTH_SHORT).show();
                Log.d(TAG,"hihihihihihi=================================");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//        data.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Toast.makeText(MainActivity.this,"안녕 디비야",Toast.LENGTH_SHORT);
//                Log.d(TAG,"hihihihihihi=================================");
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Toast.makeText(MainActivity.this,"안녕 디비야2",Toast.LENGTH_SHORT);
//                Log.d(TAG,"hihihihihihi=================================2");
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });






//        RealmResults<TimerDB> List2 = realm.where(TimerDB.class).findAll();
//        for(TimerDB gameDB3 : List2){
//            Log.d(TAG,"message");
//            Log.d(TAG, "date2 : " + gameDB3.getDate());
//            if(a==1){
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(new Date());
////                    cal.add(Calendar.DATE, 1);
//                String onedate = df.format(cal.getTime());
//                Log.d(TAG,"cal : "+onedate);
//
//                realm.beginTransaction();
//                timerDB = realm.createObject(TimerDB.class);
//                timerDB.setDate(onedate);
//                realm.commitTransaction();
////                realm.beginTransaction();
////                gameDB3.setDate(null);
////                realm.commitTransaction();
//                Log.d(TAG, "date2 : " + gameDB3.getDate());
//                a=2;
//            }
//        }


        Thread imgThread = new Thread(){         //구글의 사용자정보중 이미지를 uri로 가져와 비트맵으로 만드는 쓰레드
            @Override
            public void run() {
                try{
                    url = new URL(str);
                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                    bm = BitmapFactory.decodeStream(bis);
                    bis.close();

                }catch (IOException e){ e.printStackTrace();}
            }
        };

        imgThread.start();

        try{
            imgThread.join();   //메인스레드 기달리게하기

            DrawerheaderBinding headerBindg = DrawerheaderBinding.bind(mainBinding.naviView.getHeaderView(0));
            headerBindg.textGoogleName.setText(googleName);
            headerBindg.textGoogleEmail.setText(googleEmail);
            headerBindg.imageGoogleUrl.setImageBitmap(bm);
        }catch (InterruptedException e){}

        if(name.equals("jeon")){

        }
        else {             //로그인이 되어있으면 발동되는 조건
            loading();
            loadingEnd();
        }


        dm = getApplicationContext().getResources().getDisplayMetrics();    //디스플레이의 크기를 읽어와 크기를 지정할수있게 해준다
        width = dm.widthPixels;
        height = dm.heightPixels;
        int fakewidth = width/3;
        final int truewidth = fakewidth*2;


        mainBinding.naviView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                int id = menuItem.getItemId();
                title = menuItem.getTitle().toString();

                if (id == R.id.menuLogout) {         //로그아웃을 누르면 발동하는 리스너
                    mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
                    Log.d(TAG, "btn logout");
                    Intent intent = new Intent();
                    setResult(6000, intent);
                    finish();
                } else if (id == R.id.menuNoti) {    //알림을 누르면 발동하는 리스너
                    DatabaseReference dataRef4 = database.getReference().child("Ranking");
                    dataRef4.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for( DataSnapshot postSnapshot : dataSnapshot.getChildren()){   //딜레이  해결하기
                                String value = postSnapshot.getValue(String.class);
                                String  dbname = postSnapshot.getKey();
                                String db [] = new String[2];
                                db[0]= dbname;
                                db[1]= value;
                                if(dbname.equals(googleName)){
                                    array.add(db);
                                }
                                Log.d("==", "11111");
                            }

                            share();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
                mainBinding.drawerLayout.closeDrawers();
                return true;
            }
        });


        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == mainBinding.menuImageView) {           //메뉴아이템을 누르면 발동하는 리스너
                    mainBinding.drawerLayout.openDrawer(GravityCompat.START);
                } else if (v == mainBinding.btnTest) {
                    Intent intent = new Intent(MainActivity.this, TestActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "btn test");
                } else if (v == mainBinding.btnCard) {      //카드버튼을 누르면 발동하는 리스너
                    cardRuleCustomDialog = new CardRuleCustomDialog(MainActivity.this);
                    WindowManager.LayoutParams vm = cardRuleCustomDialog.getWindow().getAttributes();
                    vm.copyFrom(cardRuleCustomDialog.getWindow().getAttributes());
                    vm.width = truewidth;
                    vm.height = height / 2;
                    cardRuleCustomDialog.googleName(googleName);
                    cardRuleCustomDialog.show();
                    Log.d(TAG, "btn card");
                }
                else if(v == mainBinding.btnMyArea){          //지역추가버튼을 누르면 발동하는 리스너
                    myArea = new MyAreaCustomDialog(MainActivity.this);
                    WindowManager.LayoutParams vm2 =myArea.getWindow().getAttributes();
                    vm2.copyFrom(myArea.getWindow().getAttributes());
                    vm2.width = truewidth;
                    vm2.height = height/2;
                    myArea.show();
                    Log.d(TAG, "btn myArea");

                } else if (v == mainBinding.btnGoogleMap) {         //지역랭킹버튼을 누르면 발동하는 리스너
                    Intent intent1 = new Intent(MainActivity.this, GoogleMapActivity.class);
                    startActivity(intent1);
                    Log.d(TAG, "btn googleMap");
                } else if (v == mainBinding.barChartImage) {          //나의성적버튼을 누르면 발동하는 리스너
                    Intent intent2 = new Intent(MainActivity.this, BarChartActivity.class);
                    startActivity(intent2);
                    Log.d(TAG, "btn barChart");
                } else if (v == mainBinding.rankingImage) {     //랭킹버튼을 누르면 발동하는 리스너
                    Intent intent3 = new Intent(MainActivity.this, RankingActivity.class);
                    startActivity(intent3);
                    Log.d(TAG, "btn ranking");
                }
            }
        };
        mainBinding.menuImageView.setOnClickListener(click);
        mainBinding.btnTest.setOnClickListener(click);
        mainBinding.btnGoogleMap.setOnClickListener(click);
        mainBinding.barChartImage.setOnClickListener(click);
        mainBinding.rankingImage.setOnClickListener(click);
        mainBinding.btnCard.setOnClickListener(click);
        mainBinding.btnMyArea.setOnClickListener(click);





    }
    public void share(){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                long milSeond2 = Long.valueOf(array.get(0)[1]);
                long minute = milSeond2/60;
                long second = milSeond2%60;
                String aa= array.get(0)[0]+"님의 최고성적은 "+minute+"분"+second+"초"+" 입니다";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,title);
                intent.putExtra(Intent.EXTRA_TEXT,aa);
                intent.setPackage("com.kakao.talk");
                Intent chooser = Intent.createChooser(intent,"공유");
                startActivity(chooser);
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(run,1000);
    }


    public void onBackPressed() {                    //두번을 눌러야 나가게 만들었다.
        if (mainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            long tempTime = System.currentTimeMillis();
            long intervalTime = tempTime - backPressedTime;

            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
            {
                Intent intent = new Intent();
                setResult(8000, intent);
                super.onBackPressed();
            }
            else {
                backPressedTime = tempTime;
                Toast.makeText(getApplicationContext(), "나가실려면 한번더누르세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loading() {                   //로딩 메소드
        //로딩
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog = new ProgressDialog(MainActivity.this);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setCancelable(false);
                        progressDialog.setMessage("로그인 중입니다");
                        progressDialog.show();
                    }
                }, 0);
    }

    public void loadingEnd() {          //로딩 종료 메소드
        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Log.d(TAG, "loadingEnd");
                    }
                }, 3000);
    }



}
