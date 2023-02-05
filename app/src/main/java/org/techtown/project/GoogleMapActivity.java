package org.techtown.project;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.techtown.project.databinding.ActivityGooglemapBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    ActivityGooglemapBinding googleMapActivity;
    private GoogleMap googlemap;
    Geocoder geocoder;
    List<Address> addressList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String TAG = "GoogleMapActivity";
    ArrayList<String []> googleList = new ArrayList<String []>();
    ArrayList<String> nameList = new ArrayList<String>();
    String item[] = {"선택하기","세종특별자치시"};
    String items1[] = {"서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주도"};
    String itemSeoul[] = {"강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구",
            "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};
    String itemBusan[] = {"강서구", "금정구", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구", "기장군"};
    String itemDeGu[] = {"남구", "달서구", "동구", "북구", "서구", "수성구", "중구", "달성군"};
    String itemInCheon[] = {"계양구", "남동구", "동구", "미추홀구", "부평구", "서구", "연수구", "중구", "강화군", "웅진군"};
    String itemGwangJu[] = {"광산구", "남구", "동구", "북구", "서구"};
    String itemDeJeon[] = {"대덕구", "동구", "서구", "유성구", "중구"};
    String itemUlSan[] = {"남구", "동구", "북구", "중구", "울주군"};
    String itemSeJong[] = {"조치원읍", "금남면", "부강면", "소정면", "연기면", "연동면", "연서면", "장군면", "전동면", "전의면", "고운동", "대평동", "도담동", "보람동", "소담동", "새롬동", "아름동", "종촌동", "한솔동"};
    String itemGyeongGiDo[] = {"고양시", "과천시", "광명시", "광주시", "구리시", "군포시", "김포시", "남양주시", "동두천시", "부천시", "성남시", "수원시", "시흥시", "안산시", "안성시", "안양시", "양주시", "여주시", "오산시", "용인시", "의왕시", "의정부시",
            "이천시", "파주시", "평택시", "포천시", "하남시", "화성시", "가평군", "양평군", "연천군"};
    String itemGangWonDo[] = {"강릉시", "동해시", "삼척시", "속초시", "원주시", "춘천시", "태백시", "고성군", "양구군", "양양군", "영월군", "인제군", "정선군", "철원군", "평창군", "홍천군", "화천군", "횡성군"};
    String itemChungCheongBukDo[] = {"제천시", "청주시", "충주시", "괴산군", "단양군", "보은군", "영동군", "옥천군", "음성군", "증평군", "진천군"};
    String itemChungCheongNamDo[] = {"계릉시", "공주시", "논산시", "당진시", "보령시", "서산시", "아산시", "천안시", "금산군", "부여군", "서천군", "예산군", "청양군", "태안군", "홍성군"};
    String itemJeolLaBukDo[] = {"군산시", "김제시", "남원시", "익산시", "전주시", "정읍시", "고창군", "무주군", "부안군", "순창군", "완주군", "임실군", "장수군", "진안군"};
    String itemJeolLaNamDo[] = {"광양시", "나주시", "목포시", "순천시", "여수시", "강진군", "고흥군", "곡성군", "구례군", "담양군", "무안군", "보성군", "신안군", "영광군", "영암군", "완도군", "장성군", "장흥군", "진도군", "함평군", "해남군", "화순군"};
    String itemGyeongSangBukDo[] = {"경산시", "경주시", "구미시", "김천시", "문경시", "상주시", "안동시", "영주시", "영천시", "포항시", "고령군", "군위군", "봉화군", "성주군", "영덕군", "영양군", "예천군", "울릉군", "울진군", "의성군", "청도군", "청송군", "칠곡군"};
    String itemGyeongSangNamDo[] = {"거제시", "김해시", "밀양시", "사천시", "양산시", "진주시", "창원시", "통영시", "거창군", "고성군", "남해군", "산청군", "의령군", "창녕군", "하동군", "함안군", "함양군", "합천군"};
    String itemJeJuIsland[] = {"서귀포시", "제주시"};
    Handler handler;
    Runnable run;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleMapActivity = DataBindingUtil.setContentView(this, R.layout.activity_googlemap);
        DatabaseReference dataRef = database.getReference().child("Location");
        DatabaseReference dataRef2 = database.getReference().child("Ranking");




        dataRef.addValueEventListener(new ValueEventListener() {              //파이어베이스의 저장된 사람들의 위치를 불러오기
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object>  map = null;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {   //딜레이  해결하기
                    String dbname = postSnapshot.getKey();
                    map = (Map<String, Object>) dataSnapshot.getValue();


                    nameList.add(dbname);
                    Log.d("==", "dbname : " + dbname);

                }
                for(Map.Entry<String ,Object> maps :map.entrySet()){
                    String db[] = new String[4];
                    String name = maps.getKey();
                    String value= String.valueOf(maps.getValue());
                    String sub=value.substring(1,value.length()-1);
                    String [] aaa= sub.split("=");
                    String what=aaa[0];
                    String what2=aaa[1];
                    db[0] = what;
                    db[1] = what2;
                    db[2]= name;
                    googleList.add(db);
                    Log.d("==", "w : " + what);
                    Log.d("==", "w2 : " + what2);
                    Log.d("==", "w2 : " +  name);
                    Log.d("==", "db0 : " + db[0]);
                    Log.d("==", "db1 : " + db[1]);
                    Log.d("==", "maps1 : " + maps.getValue());
                    Log.d("==", "maps2 : " + maps.getKey());
                    aaa=null;

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "ERROR --------------------------------------------------------------------------------------------------------------------------------------------" + databaseError);
            }
        });




        dataRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String name = postSnapshot.getKey();
                    String ranking = postSnapshot.getValue(String.class);
                    Log.d("==", "name2 : " + name);
                    for (int j = 0; j < googleList.size(); j++) {
                            if (googleList.get(j)[2].equals(name)) {
                                googleList.get(j)[3] = ranking;
                            }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        run = new Runnable() {
            @Override
            public void run() {

                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(GoogleMapActivity.this);
            }
        };
        handler = new Handler();
        handler.postDelayed(run, 1200);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(run);

    }

    public void onMapReady(final GoogleMap googleMap) {
        googlemap = googleMap;
        geocoder = new Geocoder(this);
        LatLng nam = new LatLng(36.49209233850249, 127.73128733038902);
        googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(nam, 7));

        googleMapActivity.btnSearch.setOnClickListener(new View.OnClickListener() {           //edittext에 지역을 써서 그지역을 볼수있게 해준다
            @Override
            public void onClick(View v) {
                String address = googleMapActivity.editTextSearch.getText().toString();

                try {
                    addressList = geocoder.getFromLocationName(address, 11);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(addressList.get(0).toString());        //콤마를 기준으로 split
                String []splitStr = addressList.get(0).toString().split(",");
                String address2 = splitStr[0].substring(splitStr[0].indexOf("\"") + 1,splitStr[0].length() - 2); // 주소
                System.out.println(address2);
                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도

                LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,10));

            }
        });
        for (int i = 0; i < googleList.size(); i++) {                               // for문을 통해 파이어베이스에 위치를 등록한 사람들의 지역순위  구글맵에 마커로 뿌린다
            for (int j = 0; j < items1.length; j++) {
                if (googleList.get(i)[0].equals(items1[j])) {
                    if (items1[j].equals("서울특별시")) {
                        if (googleList.get(i)[1].equals(itemSeoul[0])) {
                            LatLng Seoul0 = new LatLng(37.517409, 127.047433);
                            MarkerOptions markerSeoul0 = new MarkerOptions();
                            markerSeoul0.position(Seoul0);
                            markerSeoul0.title(itemSeoul[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond3 = minute + "분" + " " + second + "초";
                            markerSeoul0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond3);
                            googlemap.addMarker(markerSeoul0);
                        } else if (googleList.get(i)[1].equals(itemSeoul[1])) {
                            LatLng Seoul1 = new LatLng(37.530200, 127.123774);
                            MarkerOptions markerSeoul1 = new MarkerOptions();
                            markerSeoul1.position(Seoul1);
                            markerSeoul1.title(itemSeoul[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul1);
                        } else if (googleList.get(i)[1].equals(itemSeoul[2])) {
                            LatLng Seoul2 = new LatLng(37.639795, 127.025519);
                            MarkerOptions markerSeoul2 = new MarkerOptions();
                            markerSeoul2.position(Seoul2);
                            markerSeoul2.title(itemSeoul[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul2);
                        } else if (googleList.get(i)[1].equals(itemSeoul[3])) {
                            LatLng Seoul3 = new LatLng(37.550894, 126.849527);
                            MarkerOptions markerSeoul3 = new MarkerOptions();
                            markerSeoul3.position(Seoul3);
                            markerSeoul3.title(itemSeoul[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul3);
                        } else if (googleList.get(i)[1].equals(itemSeoul[4])) {
                            LatLng Seoul4 = new LatLng(37.478129, 126.951501);
                            MarkerOptions markerSeoul4 = new MarkerOptions();
                            markerSeoul4.position(Seoul4);
                            markerSeoul4.title(itemSeoul[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul4);
                        } else if (googleList.get(i)[1].equals(itemSeoul[5])) {
                            LatLng Seoul5 = new LatLng(37.538546, 127.082372);
                            MarkerOptions markerSeoul5 = new MarkerOptions();
                            markerSeoul5.position(Seoul5);
                            markerSeoul5.title(itemSeoul[5] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul5.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul5);
                        } else if (googleList.get(i)[1].equals(itemSeoul[6])) {
                            LatLng Seoul6 = new LatLng(37.495550, 126.887625);
                            MarkerOptions markerSeoul6 = new MarkerOptions();
                            markerSeoul6.position(Seoul6);
                            markerSeoul6.title(itemSeoul[6] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul6.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul6);
                        } else if (googleList.get(i)[1].equals(itemSeoul[7])) {
                            LatLng Seoul7 = new LatLng(37.456859, 126.895390);
                            MarkerOptions markerSeoul7 = new MarkerOptions();
                            markerSeoul7.position(Seoul7);
                            markerSeoul7.title(itemSeoul[7] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul7.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul7);
                        } else if (googleList.get(i)[1].equals(itemSeoul[8])) {
                            LatLng Seoul8 = new LatLng(37.654085, 127.056333);
                            MarkerOptions markerSeoul8 = new MarkerOptions();
                            markerSeoul8.position(Seoul8);
                            markerSeoul8.title(itemSeoul[8] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul8.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul8);
                        } else if (googleList.get(i)[1].equals(itemSeoul[9])) {
                            LatLng Seoul9 = new LatLng(37.668770, 127.047083);
                            MarkerOptions markerSeoul9 = new MarkerOptions();
                            markerSeoul9.position(Seoul9);
                            markerSeoul9.title(itemSeoul[9] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul9.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul9);
                        } else if (googleList.get(i)[1].equals(itemSeoul[10])) {
                            LatLng Seoul10 = new LatLng(37.574474, 127.039735);
                            MarkerOptions markerSeoul10 = new MarkerOptions();
                            markerSeoul10.position(Seoul10);
                            markerSeoul10.title(itemSeoul[10] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul10.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul10);
                        } else if (googleList.get(i)[1].equals(itemSeoul[11])) {
                            LatLng Seoul11 = new LatLng(37.512439, 126.939802);
                            MarkerOptions markerSeoul11 = new MarkerOptions();
                            markerSeoul11.position(Seoul11);
                            markerSeoul11.title(itemSeoul[11] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul11.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul11);
                        } else if (googleList.get(i)[1].equals(itemSeoul[12])) {
                            LatLng Seoul12 = new LatLng(37.566197, 126.901643);
                            MarkerOptions markerSeoul12 = new MarkerOptions();
                            markerSeoul12.position(Seoul12);
                            markerSeoul12.title(itemSeoul[12] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul12.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul12);
                        } else if (googleList.get(i)[1].equals(itemSeoul[13])) {
                            LatLng Seoul13 = new LatLng(37.579180, 126.936771);
                            MarkerOptions markerSeoul13 = new MarkerOptions();
                            markerSeoul13.position(Seoul13);
                            markerSeoul13.title(itemSeoul[13] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul13.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul13);
                        } else if (googleList.get(i)[1].equals(itemSeoul[14])) {
                            LatLng Seoul14 = new LatLng(37.483589, 127.032649);
                            MarkerOptions markerSeoul14 = new MarkerOptions();
                            markerSeoul14.position(Seoul14);
                            markerSeoul14.title(itemSeoul[14] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul14.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul14);
                        } else if (googleList.get(i)[1].equals(itemSeoul[15])) {
                            LatLng Seoul15 = new LatLng(37.563062, 127.036583);
                            MarkerOptions markerSeoul15 = new MarkerOptions();
                            markerSeoul15.position(Seoul15);
                            markerSeoul15.title(itemSeoul[15] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul15.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul15);
                        } else if (googleList.get(i)[1].equals(itemSeoul[16])) {
                            LatLng Seoul16 = new LatLng(37.589362, 127.016742);
                            MarkerOptions markerSeoul16 = new MarkerOptions();
                            markerSeoul16.position(Seoul16);
                            markerSeoul16.title(itemSeoul[16] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul16.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul16);
                        } else if (googleList.get(i)[1].equals(itemSeoul[17])) {
                            LatLng Seoul17 = new LatLng(37.514459, 127.106069);
                            MarkerOptions markerSeoul17 = new MarkerOptions();
                            markerSeoul17.position(Seoul17);
                            markerSeoul17.title(itemSeoul[17] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul17.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul17);
                        } else if (googleList.get(i)[1].equals(itemSeoul[18])) {
                            LatLng Seoul18 = new LatLng(37.516976, 126.866562);
                            MarkerOptions markerSeoul18 = new MarkerOptions();
                            markerSeoul18.position(Seoul18);
                            markerSeoul18.title(itemSeoul[18] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul18.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul18);
                        } else if (googleList.get(i)[1].equals(itemSeoul[19])) {
                            LatLng Seoul19 = new LatLng(37.526284, 126.896283);
                            MarkerOptions markerSeoul19 = new MarkerOptions();
                            markerSeoul19.position(Seoul19);
                            markerSeoul19.title(itemSeoul[19] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul19.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul19);
                        } else if (googleList.get(i)[1].equals(itemSeoul[20])) {
                            LatLng Seoul20 = new LatLng(37.532613, 126.990047);
                            MarkerOptions markerSeoul20 = new MarkerOptions();
                            markerSeoul20.position(Seoul20);
                            markerSeoul20.title(itemSeoul[20] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul20.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul20);
                        } else if (googleList.get(i)[1].equals(itemSeoul[21])) {
                            LatLng Seoul21 = new LatLng(37.602783, 126.929236);
                            MarkerOptions markerSeoul21 = new MarkerOptions();
                            markerSeoul21.position(Seoul21);
                            markerSeoul21.title(itemSeoul[21] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul21.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul21);
                        } else if (googleList.get(i)[1].equals(itemSeoul[22])) {
                            LatLng Seoul22 = new LatLng(37.572777, 126.979243);
                            MarkerOptions markerSeoul22 = new MarkerOptions();
                            markerSeoul22.position(Seoul22);
                            markerSeoul22.title(itemSeoul[22] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul22.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul22);
                        } else if (googleList.get(i)[1].equals(itemSeoul[23])) {
                            LatLng Seoul23 = new LatLng(37.563768, 126.997546);
                            MarkerOptions markerSeoul23 = new MarkerOptions();
                            markerSeoul23.position(Seoul23);
                            markerSeoul23.title(itemSeoul[23] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul23.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul23);
                        } else if (googleList.get(i)[1].equals(itemSeoul[24])) {
                            LatLng Seoul24 = new LatLng(37.606525, 127.092575);
                            MarkerOptions markerSeoul24 = new MarkerOptions();
                            markerSeoul24.position(Seoul24);
                            markerSeoul24.title(itemSeoul[24] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeoul24.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeoul24);
                        }
                    } else if (items1[j].equals("부산광역시")) {
                        if (googleList.get(i)[1].equals(itemBusan[0])) {
                            LatLng BuSan0 = new LatLng(35.212226, 128.980694);
                            MarkerOptions markerBuSan0 = new MarkerOptions();
                            markerBuSan0.position(BuSan0);
                            markerBuSan0.title(itemBusan[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan0);
                        } else if (googleList.get(i)[1].equals(itemBusan[1])) {
                            LatLng BuSan1 = new LatLng(35.243280, 129.092149);
                            MarkerOptions markerBuSan1 = new MarkerOptions();
                            markerBuSan1.position(BuSan1);
                            markerBuSan1.title(itemBusan[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan1);
                        } else if (googleList.get(i)[1].equals(itemBusan[2])) {
                            LatLng BuSan2 = new LatLng(35.136592, 129.084257);
                            MarkerOptions markerBuSan2 = new MarkerOptions();
                            markerBuSan2.position(BuSan2);
                            markerBuSan2.title(itemBusan[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan2);
                        } else if (googleList.get(i)[1].equals(itemBusan[3])) {
                            LatLng BuSan3 = new LatLng(35.129296, 129.045503);
                            MarkerOptions markerBuSan3 = new MarkerOptions();
                            markerBuSan3.position(BuSan3);
                            markerBuSan3.title(itemBusan[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan3);

                        } else if (googleList.get(i)[1].equals(itemBusan[4])) {
                            LatLng BuSan4 = new LatLng(35.204917, 129.083576);
                            MarkerOptions markerBuSan4 = new MarkerOptions();
                            markerBuSan4.position(BuSan4);
                            markerBuSan4.title(itemBusan[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan4);

                        } else if (googleList.get(i)[1].equals(itemBusan[5])) {
                            LatLng BuSan5 = new LatLng(35.163130, 129.053024);
                            MarkerOptions markerBuSan5 = new MarkerOptions();
                            markerBuSan5.position(BuSan5);
                            markerBuSan5.title(itemBusan[5] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan5.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan5);

                        } else if (googleList.get(i)[1].equals(itemBusan[6])) {
                            LatLng BuSan6 = new LatLng(35.197382, 128.990253);
                            MarkerOptions markerBuSan6 = new MarkerOptions();
                            markerBuSan6.position(BuSan6);
                            markerBuSan6.title(itemBusan[6] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan6.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan6);

                        } else if (googleList.get(i)[1].equals(itemBusan[7])) {
                            LatLng BuSan7 = new LatLng(35.152575, 128.991183);
                            MarkerOptions markerBuSan7 = new MarkerOptions();
                            markerBuSan7.position(BuSan7);
                            markerBuSan7.title(itemBusan[7] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan7.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan7);

                        } else if (googleList.get(i)[1].equals(itemBusan[8])) {
                            LatLng BuSan8 = new LatLng(35.104506, 128.974798);
                            MarkerOptions markerBuSan8 = new MarkerOptions();
                            markerBuSan8.position(BuSan8);
                            markerBuSan8.title(itemBusan[8] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan8.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan8);

                        } else if (googleList.get(i)[1].equals(itemBusan[9])) {
                            LatLng BuSan9 = new LatLng(35.097922, 129.024203);
                            MarkerOptions markerBuSan9 = new MarkerOptions();
                            markerBuSan9.position(BuSan9);
                            markerBuSan9.title(itemBusan[9] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan9.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan9);
                        } else if (googleList.get(i)[1].equals(itemBusan[10])) {
                            LatLng BuSan10 = new LatLng(35.145555, 129.113188);
                            MarkerOptions markerBuSan10 = new MarkerOptions();
                            markerBuSan10.position(BuSan10);
                            markerBuSan10.title(itemBusan[10] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan10.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan10);
                        } else if (googleList.get(i)[1].equals(itemBusan[11])) {
                            LatLng BuSan11 = new LatLng(35.176214, 129.079635);
                            MarkerOptions markerBuSan11 = new MarkerOptions();
                            markerBuSan11.position(BuSan11);
                            markerBuSan11.title(itemBusan[11] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan11.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan11);
                        } else if (googleList.get(i)[1].equals(itemBusan[12])) {
                            LatLng BuSan12 = new LatLng(35.091198, 129.067908);
                            MarkerOptions markerBuSan12 = new MarkerOptions();
                            markerBuSan12.position(BuSan12);
                            markerBuSan12.title(itemBusan[12] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan12.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan12);
                        } else if (googleList.get(i)[1].equals(itemBusan[13])) {
                            LatLng BuSan13 = new LatLng(35.106144, 129.032331);
                            MarkerOptions markerBuSan13 = new MarkerOptions();
                            markerBuSan13.position(BuSan13);
                            markerBuSan13.title(itemBusan[13] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan13.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan13);
                        } else if (googleList.get(i)[1].equals(itemBusan[14])) {
                            LatLng BuSan14 = new LatLng(35.163129, 129.163609);
                            MarkerOptions markerBuSan14 = new MarkerOptions();
                            markerBuSan14.position(BuSan14);
                            markerBuSan14.title(itemBusan[14] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan14.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan14);
                        } else if (googleList.get(i)[1].equals(itemBusan[15])) {
                            LatLng BuSan15 = new LatLng(35.244654, 129.222250);
                            MarkerOptions markerBuSan15 = new MarkerOptions();
                            markerBuSan15.position(BuSan15);
                            markerBuSan15.title(itemBusan[15] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerBuSan15.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerBuSan15);
                        }
                    } else if (items1[j].equals("대구광역시")) {
                        if (googleList.get(i)[1].equals(itemDeGu[0])) {
                            LatLng DeGu0 = new LatLng(35.846019, 128.597737);
                            MarkerOptions markerDeGu0 = new MarkerOptions();
                            markerDeGu0.position(DeGu0);
                            markerDeGu0.title(itemDeGu[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeGu0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeGu0);
                        } else if (googleList.get(i)[1].equals(itemDeGu[1])) {
                            LatLng DeGu1 = new LatLng(35.774506, 128.431261);
                            MarkerOptions markerDeGu1 = new MarkerOptions();
                            markerDeGu1.position(DeGu1);
                            markerDeGu1.title(itemDeGu[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeGu1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeGu1);
                        } else if (googleList.get(i)[1].equals(itemDeGu[2])) {
                            LatLng DeGu2 = new LatLng(35.886599, 128.635563);
                            MarkerOptions markerDeGu2 = new MarkerOptions();
                            markerDeGu2.position(DeGu2);
                            markerDeGu2.title(itemDeGu[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeGu2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeGu2);
                        } else if (googleList.get(i)[1].equals(itemDeGu[3])) {
                            LatLng DeGu3 = new LatLng(35.885655, 128.582943);
                            MarkerOptions markerDeGu3 = new MarkerOptions();
                            markerDeGu3.position(DeGu3);
                            markerDeGu3.title(itemDeGu[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeGu3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeGu3);
                        } else if (googleList.get(i)[1].equals(itemDeGu[4])) {
                            LatLng DeGu4 = new LatLng(35.871732, 128.559180);
                            MarkerOptions markerDeGu4 = new MarkerOptions();
                            markerDeGu4.position(DeGu4);
                            markerDeGu4.title(itemDeGu[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeGu4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeGu4);
                        } else if (googleList.get(i)[1].equals(itemDeGu[5])) {
                            LatLng DeGu5 = new LatLng(35.858163, 128.630645);
                            MarkerOptions markerDeGu5 = new MarkerOptions();
                            markerDeGu5.position(DeGu5);
                            markerDeGu5.title(itemDeGu[5] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeGu5.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeGu5);
                        } else if (googleList.get(i)[1].equals(itemDeGu[6])) {
                            LatLng DeGu6 = new LatLng(35.869374, 128.606091);
                            MarkerOptions markerDeGu6 = new MarkerOptions();
                            markerDeGu6.position(DeGu6);
                            markerDeGu6.title(itemDeGu[6] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeGu6.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeGu6);
                        } else if (googleList.get(i)[1].equals(itemDeGu[7])) {
                            LatLng DeGu7 = new LatLng(35.774492, 128.431257);
                            MarkerOptions markerDeGu7 = new MarkerOptions();
                            markerDeGu7.position(DeGu7);
                            markerDeGu7.title(itemDeGu[7] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeGu7.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeGu7);
                        }
                    } else if (items1[j].equals("인천광역시")) {
                        if (googleList.get(i)[1].equals(itemInCheon[0])) {
                            LatLng InCheon0 = new LatLng(37.537286, 126.737751);
                            MarkerOptions markerInCheon0 = new MarkerOptions();
                            markerInCheon0.position(InCheon0);
                            markerInCheon0.title(itemInCheon[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerInCheon0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerInCheon0);
                        } else if (googleList.get(i)[1].equals(itemInCheon[1])) {
                            LatLng InCheon1 = new LatLng(37.446908, 126.730981);
                            MarkerOptions markerInCheon1 = new MarkerOptions();
                            markerInCheon1.position(InCheon1);
                            markerInCheon1.title(itemInCheon[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerInCheon1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerInCheon1);
                        } else if (googleList.get(i)[1].equals(itemInCheon[2])) {
                            LatLng InCheon2 = new LatLng(37.473850, 126.643246);
                            MarkerOptions markerInCheon2 = new MarkerOptions();
                            markerInCheon2.position(InCheon2);
                            markerInCheon2.title(itemInCheon[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerInCheon2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerInCheon2);
                        } else if (googleList.get(i)[1].equals(itemInCheon[3])) {
                            LatLng InCheon3 = new LatLng(37.463795, 126.649867);
                            MarkerOptions markerInCheon3 = new MarkerOptions();
                            markerInCheon3.position(InCheon3);
                            markerInCheon3.title(itemInCheon[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerInCheon3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerInCheon3);
                        } else if (googleList.get(i)[1].equals(itemInCheon[4])) {
                            LatLng InCheon4 = new LatLng(37.506744, 126.722333);
                            MarkerOptions markerInCheon4 = new MarkerOptions();
                            markerInCheon4.position(InCheon4);
                            markerInCheon4.title(itemInCheon[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerInCheon4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerInCheon4);
                        } else if (googleList.get(i)[1].equals(itemInCheon[5])) {
                            LatLng InCheon5 = new LatLng(37.545520, 126.676035);
                            MarkerOptions markerInCheon5 = new MarkerOptions();
                            markerInCheon5.position(InCheon5);
                            markerInCheon5.title(itemInCheon[5] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerInCheon5.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerInCheon5);
                        } else if (googleList.get(i)[1].equals(itemInCheon[6])) {
                            LatLng InCheon6 = new LatLng(37.410166, 126.678318);
                            MarkerOptions markerInCheon6 = new MarkerOptions();
                            markerInCheon6.position(InCheon6);
                            markerInCheon6.title(itemInCheon[6] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerInCheon6.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerInCheon6);
                        } else if (googleList.get(i)[1].equals(itemInCheon[7])) {
                            LatLng InCheon7 = new LatLng(37.473620, 126.622206);
                            MarkerOptions markerInCheon7 = new MarkerOptions();
                            markerInCheon7.position(InCheon7);
                            markerInCheon7.title(itemInCheon[7] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerInCheon7.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerInCheon7);
                        } else if (googleList.get(i)[1].equals(itemInCheon[8])) {
                            LatLng InCheon8 = new LatLng(37.746670, 126.487870);
                            MarkerOptions markerInCheon8 = new MarkerOptions();
                            markerInCheon8.position(InCheon8);
                            markerInCheon8.title(itemInCheon[8] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerInCheon8.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerInCheon8);
                        } else if (googleList.get(i)[1].equals(itemInCheon[9])) {
                            LatLng InCheon9 = new LatLng(37.446489, 126.636808);
                            MarkerOptions markerInCheon9 = new MarkerOptions();
                            markerInCheon9.position(InCheon9);
                            markerInCheon9.title(itemInCheon[9] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerInCheon9.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerInCheon9);
                        }
                    } else if (items1[j].equals("광주광역시")) {
                        if (googleList.get(i)[1].equals(itemGwangJu[0])) {
                            LatLng GwangJu0 = new LatLng(35.139657, 126.793659);
                            MarkerOptions markerGwangJu0 = new MarkerOptions();
                            markerGwangJu0.position(GwangJu0);
                            markerGwangJu0.title(itemGwangJu[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGwangJu0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGwangJu0);
                        } else if (googleList.get(i)[1].equals(itemGwangJu[1])) {
                            LatLng GwangJu1 = new LatLng(35.132763, 126.902435);
                            MarkerOptions markerGwangJu1 = new MarkerOptions();
                            markerGwangJu1.position(GwangJu1);
                            markerGwangJu1.title(itemGwangJu[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGwangJu1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGwangJu1);
                        } else if (googleList.get(i)[1].equals(itemGwangJu[2])) {
                            LatLng GwangJu2 = new LatLng(35.146051, 126.923150);
                            MarkerOptions markerGwangJu2 = new MarkerOptions();
                            markerGwangJu2.position(GwangJu2);
                            markerGwangJu2.title(itemGwangJu[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGwangJu2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGwangJu2);
                        } else if (googleList.get(i)[1].equals(itemGwangJu[3])) {
                            LatLng GwangJu3 = new LatLng(35.174207, 126.912030);
                            MarkerOptions markerGwangJu3 = new MarkerOptions();
                            markerGwangJu3.position(GwangJu3);
                            markerGwangJu3.title(itemGwangJu[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGwangJu3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGwangJu3);
                        } else if (googleList.get(i)[1].equals(itemGwangJu[4])) {
                            LatLng GwangJu4 = new LatLng(35.151503, 126.890266);
                            MarkerOptions markerGwangJu4 = new MarkerOptions();
                            markerGwangJu4.position(GwangJu4);
                            markerGwangJu4.title(itemGwangJu[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGwangJu4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGwangJu4);
                        }
                    } else if (items1[j].equals("대전광역시")) {
                        if (googleList.get(i)[1].equals(itemDeJeon[0])) {
                            LatLng DeJeon0 = new LatLng(36.346753, 127.415613);
                            MarkerOptions markerDeJeon0 = new MarkerOptions();
                            markerDeJeon0.position(DeJeon0);
                            markerDeJeon0.title(itemDeJeon[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeJeon0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeJeon0);
                        } else if (googleList.get(i)[1].equals(itemDeJeon[1])) {
                            LatLng DeJeon1 = new LatLng(36.311855, 127.454900);
                            MarkerOptions markerDeJeon1 = new MarkerOptions();
                            markerDeJeon1.position(DeJeon1);
                            markerDeJeon1.title(itemDeJeon[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeJeon1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeJeon1);
                        } else if (googleList.get(i)[1].equals(itemDeJeon[2])) {
                            LatLng DeJeon2 = new LatLng(36.355721, 127.384133);
                            MarkerOptions markerDeJeon2 = new MarkerOptions();
                            markerDeJeon2.position(DeJeon2);
                            markerDeJeon2.title(itemDeJeon[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeJeon2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeJeon2);
                        } else if (googleList.get(i)[1].equals(itemDeJeon[3])) {
                            LatLng DeJeon3 = new LatLng(36.362321, 127.356082);
                            MarkerOptions markerDeJeon3 = new MarkerOptions();
                            markerDeJeon3.position(DeJeon3);
                            markerDeJeon3.title(itemDeJeon[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeJeon3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeJeon3);
                        } else if (googleList.get(i)[1].equals(itemDeJeon[4])) {
                            LatLng DeJeon4 = new LatLng(36.325569, 127.421345);
                            MarkerOptions markerDeJeon4 = new MarkerOptions();
                            markerDeJeon4.position(DeJeon4);
                            markerDeJeon4.title(itemDeJeon[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerDeJeon4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerDeJeon4);
                        }
                    } else if (items1[j].equals("울산광역시")) {
                        if (googleList.get(i)[1].equals(itemUlSan[0])) {
                            LatLng UlSan0 = new LatLng(35.543819, 129.330074);
                            MarkerOptions markerUlSan0 = new MarkerOptions();
                            markerUlSan0.position(UlSan0);
                            markerUlSan0.title(itemUlSan[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerUlSan0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerUlSan0);
                        } else if (googleList.get(i)[1].equals(itemUlSan[1])) {
                            LatLng UlSan1 = new LatLng(35.504973, 129.416736);
                            MarkerOptions markerUlSan = new MarkerOptions();
                            markerUlSan.position(UlSan1);
                            markerUlSan.title(itemUlSan[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerUlSan.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerUlSan);
                        } else if (googleList.get(i)[1].equals(itemUlSan[2])) {
                            LatLng UlSan2 = new LatLng(35.582712, 129.361238);
                            MarkerOptions markerUlSan2 = new MarkerOptions();
                            markerUlSan2.position(UlSan2);
                            markerUlSan2.title(itemUlSan[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerUlSan2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerUlSan2);
                        } else if (googleList.get(i)[1].equals(itemUlSan[3])) {
                            LatLng UlSan3 = new LatLng(35.569475, 129.332749);
                            MarkerOptions markerUlSan3 = new MarkerOptions();
                            markerUlSan3.position(UlSan3);
                            markerUlSan3.title(itemUlSan[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerUlSan3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerUlSan3);
                        } else if (googleList.get(i)[1].equals(itemUlSan[4])) {
                            LatLng UlSan4 = new LatLng(35.522136, 129.242479);
                            MarkerOptions markerUlSan4 = new MarkerOptions();
                            markerUlSan4.position(UlSan4);
                            markerUlSan4.title(itemUlSan[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerUlSan4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerUlSan4);
                        }
                    } else if (items1[j].equals("세종특별자치시")) {
                        if(googleList.get(i)[1].equals(item[1])){
                            LatLng SeJong = new LatLng(36.480173, 127.289033);
                            MarkerOptions markerSeJong = new MarkerOptions();
                            markerSeJong.position(SeJong);
                            markerSeJong.title(item[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerSeJong.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerSeJong);
                        }
                    } else if (items1[j].equals("경기도")) {
                        if (googleList.get(i)[1].equals(itemGyeongGiDo[0])) {
                            LatLng GyeongGiDo0 = new LatLng(37.658369, 126.831955);
                            MarkerOptions markerGyeongGiDo0 = new MarkerOptions();
                            markerGyeongGiDo0.position(GyeongGiDo0);
                            markerGyeongGiDo0.title(itemGyeongGiDo[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo0);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[1])) {
                            LatLng GyeongGiDo1 = new LatLng(37.429154, 126.987620);
                            MarkerOptions markerGyeongGiDo1 = new MarkerOptions();
                            markerGyeongGiDo1.position(GyeongGiDo1);
                            markerGyeongGiDo1.title(itemGyeongGiDo[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo1);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[2])) {
                            LatLng GyeongGiDo2 = new LatLng(37.478633, 126.864682);
                            MarkerOptions markerGyeongGiDo2 = new MarkerOptions();
                            markerGyeongGiDo2.position(GyeongGiDo2);
                            markerGyeongGiDo2.title(itemGyeongGiDo[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo2);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[3])) {
                            LatLng GyeongGiDo3 = new LatLng(37.429415, 127.255157);
                            MarkerOptions markerGyeongGiDo3 = new MarkerOptions();
                            markerGyeongGiDo3.position(GyeongGiDo3);
                            markerGyeongGiDo3.title(itemGyeongGiDo[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo3);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[4])) {
                            LatLng GyeongGiDo4 = new LatLng(37.594310, 127.129735);
                            MarkerOptions markerGyeongGiDo4 = new MarkerOptions();
                            markerGyeongGiDo4.position(GyeongGiDo4);
                            markerGyeongGiDo4.title(itemGyeongGiDo[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo4);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[5])) {
                            LatLng GyeongGiDo5 = new LatLng(37.361638, 126.935198);
                            MarkerOptions markerGyeongGiDo5 = new MarkerOptions();
                            markerGyeongGiDo5.position(GyeongGiDo5);
                            markerGyeongGiDo5.title(itemGyeongGiDo[5] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo5.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo5);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[6])) {
                            LatLng GyeongGiDo6 = new LatLng(37.615320, 126.715489);
                            MarkerOptions markerGyeongGiDo6 = new MarkerOptions();
                            markerGyeongGiDo6.position(GyeongGiDo6);
                            markerGyeongGiDo6.title(itemGyeongGiDo[6] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo6.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo6);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[7])) {
                            LatLng GyeongGiDo7 = new LatLng(37.635757, 127.217088);
                            MarkerOptions markerGyeongGiDo7 = new MarkerOptions();
                            markerGyeongGiDo7.position(GyeongGiDo7);
                            markerGyeongGiDo7.title(itemGyeongGiDo[7] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo7.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo7);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[8])) {
                            LatLng GyeongGiDo8 = new LatLng(37.903583, 127.060398);
                            MarkerOptions markerGyeongGiDo8 = new MarkerOptions();
                            markerGyeongGiDo8.position(GyeongGiDo8);
                            markerGyeongGiDo8.title(itemGyeongGiDo[8] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo8.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo8);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[9])) {
                            LatLng GyeongGiDo9 = new LatLng(37.503440, 126.765986);
                            MarkerOptions markerGyeongGiDo9 = new MarkerOptions();
                            markerGyeongGiDo9.position(GyeongGiDo9);
                            markerGyeongGiDo9.title(itemGyeongGiDo[9] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo9.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo9);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[10])) {
                            LatLng GyeongGiDo10 = new LatLng(37.420077, 127.126595);
                            MarkerOptions markerGyeongGiDo10 = new MarkerOptions();
                            markerGyeongGiDo10.position(GyeongGiDo10);
                            markerGyeongGiDo10.title(itemGyeongGiDo[10] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo10.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo10);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[11])) {
                            LatLng GyeongGiDo11 = new LatLng(37.263408, 127.028630);
                            MarkerOptions markerGyeongGiDo11 = new MarkerOptions();
                            markerGyeongGiDo11.position(GyeongGiDo11);
                            markerGyeongGiDo11.title(itemGyeongGiDo[11] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo11.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo11);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[12])) {
                            LatLng GyeongGiDo12 = new LatLng(37.380125, 126.802842);
                            MarkerOptions markerGyeongGiDo12 = new MarkerOptions();
                            markerGyeongGiDo12.position(GyeongGiDo12);
                            markerGyeongGiDo12.title(itemGyeongGiDo[12] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo12.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo12);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[13])) {
                            LatLng GyeongGiDo13 = new LatLng(37.321866, 126.830840);
                            MarkerOptions markerGyeongGiDo13 = new MarkerOptions();
                            markerGyeongGiDo13.position(GyeongGiDo13);
                            markerGyeongGiDo13.title(itemGyeongGiDo[13] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo13.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo13);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[14])) {
                            LatLng GyeongGiDo14 = new LatLng(37.008018, 127.279846);
                            MarkerOptions markerGyeongGiDo14 = new MarkerOptions();
                            markerGyeongGiDo14.position(GyeongGiDo14);
                            markerGyeongGiDo14.title(itemGyeongGiDo[14] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo14.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo14);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[15])) {
                            LatLng GyeongGiDo15 = new LatLng(37.394154, 126.956880);
                            MarkerOptions markerGyeongGiDo15 = new MarkerOptions();
                            markerGyeongGiDo15.position(GyeongGiDo15);
                            markerGyeongGiDo15.title(itemGyeongGiDo[15] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo15.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo15);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[16])) {
                            LatLng GyeongGiDo16 = new LatLng(37.785268, 127.045804);
                            MarkerOptions markerGyeongGiDo16 = new MarkerOptions();
                            markerGyeongGiDo16.position(GyeongGiDo16);
                            markerGyeongGiDo16.title(itemGyeongGiDo[16] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo16.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo16);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[17])) {
                            LatLng GyeongGiDo17 = new LatLng(37.298214, 127.637338);
                            MarkerOptions markerGyeongGiDo17 = new MarkerOptions();
                            markerGyeongGiDo17.position(GyeongGiDo17);
                            markerGyeongGiDo17.title(itemGyeongGiDo[17] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo17.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo17);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[18])) {
                            LatLng GyeongGiDo18 = new LatLng(37.149831, 127.077480);
                            MarkerOptions markerGyeongGiDo18 = new MarkerOptions();
                            markerGyeongGiDo18.position(GyeongGiDo18);
                            markerGyeongGiDo18.title(itemGyeongGiDo[18] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo18.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo18);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[19])) {
                            LatLng GyeongGiDo19 = new LatLng(37.241007, 127.177852);
                            MarkerOptions markerGyeongGiDo19 = new MarkerOptions();
                            markerGyeongGiDo19.position(GyeongGiDo19);
                            markerGyeongGiDo19.title(itemGyeongGiDo[19] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo19.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo19);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[20])) {
                            LatLng GyeongGiDo20 = new LatLng(37.344679, 126.968325);
                            MarkerOptions markerGyeongGiDo20 = new MarkerOptions();
                            markerGyeongGiDo20.position(GyeongGiDo20);
                            markerGyeongGiDo20.title(itemGyeongGiDo[20] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo20.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo20);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[21])) {
                            LatLng GyeongGiDo21 = new LatLng(37.738026, 127.033630);
                            MarkerOptions markerGyeongGiDo21 = new MarkerOptions();
                            markerGyeongGiDo21.position(GyeongGiDo21);
                            markerGyeongGiDo21.title(itemGyeongGiDo[21] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo21.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo21);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[22])) {
                            LatLng GyeongGiDo22 = new LatLng(37.272234, 127.435050);
                            MarkerOptions markerGyeongGiDo22 = new MarkerOptions();
                            markerGyeongGiDo22.position(GyeongGiDo22);
                            markerGyeongGiDo22.title(itemGyeongGiDo[22] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo22.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo22);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[23])) {
                            LatLng GyeongGiDo23 = new LatLng(37.759860, 126.779871);
                            MarkerOptions markerGyeongGiDo23 = new MarkerOptions();
                            markerGyeongGiDo23.position(GyeongGiDo23);
                            markerGyeongGiDo23.title(itemGyeongGiDo[23] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo23.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo23);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[24])) {
                            LatLng GyeongGiDo24 = new LatLng(36.992328, 127.112688);
                            MarkerOptions markerGyeongGiDo24 = new MarkerOptions();
                            markerGyeongGiDo24.position(GyeongGiDo24);
                            markerGyeongGiDo24.title(itemGyeongGiDo[24] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo24.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo24);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[25])) {
                            LatLng GyeongGiDo25 = new LatLng(37.894927, 127.200312);
                            MarkerOptions markerGyeongGiDo25 = new MarkerOptions();
                            markerGyeongGiDo25.position(GyeongGiDo25);
                            markerGyeongGiDo25.title(itemGyeongGiDo[25] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo25.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo25);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[26])) {
                            LatLng GyeongGiDo26 = new LatLng(37.539326, 127.214950);
                            MarkerOptions markerGyeongGiDo26 = new MarkerOptions();
                            markerGyeongGiDo26.position(GyeongGiDo26);
                            markerGyeongGiDo26.title(itemGyeongGiDo[26] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo26.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo26);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[27])) {
                            LatLng GyeongGiDo27 = new LatLng(37.199414, 126.831694);
                            MarkerOptions markerGyeongGiDo27 = new MarkerOptions();
                            markerGyeongGiDo27.position(GyeongGiDo27);
                            markerGyeongGiDo27.title(itemGyeongGiDo[27] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo27.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo27);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[28])) {
                            LatLng GyeongGiDo28 = new LatLng(37.831268, 127.509551);
                            MarkerOptions markerGyeongGiDo28 = new MarkerOptions();
                            markerGyeongGiDo28.position(GyeongGiDo28);
                            markerGyeongGiDo28.title(itemGyeongGiDo[28] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo28.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo28);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[29])) {
                            LatLng GyeongGiDo29 = new LatLng(37.491703, 127.487498);
                            MarkerOptions markerGyeongGiDo29 = new MarkerOptions();
                            markerGyeongGiDo29.position(GyeongGiDo29);
                            markerGyeongGiDo29.title(itemGyeongGiDo[29] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo29.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo29);
                        } else if (googleList.get(i)[1].equals(itemGyeongGiDo[30])) {
                            LatLng GyeongGiDo30 = new LatLng(38.096413, 127.075063);
                            MarkerOptions markerGyeongGiDo30 = new MarkerOptions();
                            markerGyeongGiDo30.position(GyeongGiDo30);
                            markerGyeongGiDo30.title(itemGyeongGiDo[30] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongGiDo30.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongGiDo30);
                        }
                    } else if (items1[j].equals("강원도")) {
                        if (googleList.get(i)[1].equals(itemGangWonDo[0])) {
                            LatLng GangWonDo0 = new LatLng(37.752008, 128.875957);
                            MarkerOptions markerGangWonDo0 = new MarkerOptions();
                            markerGangWonDo0.position(GangWonDo0);
                            markerGangWonDo0.title(itemGangWonDo[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo0);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[1])) {
                            LatLng GangWonDo1 = new LatLng(37.524656, 129.114326);
                            MarkerOptions markerGangWonDo1 = new MarkerOptions();
                            markerGangWonDo1.position(GangWonDo1);
                            markerGangWonDo1.title(itemGangWonDo[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo1);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[2])) {
                            LatLng GangWonDo2 = new LatLng(37.449922, 129.165174);
                            MarkerOptions markerGangWonDo2 = new MarkerOptions();
                            markerGangWonDo2.position(GangWonDo2);
                            markerGangWonDo2.title(itemGangWonDo[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo2);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[3])) {
                            LatLng GangWonDo3 = new LatLng(38.206985, 128.591869);
                            MarkerOptions markerGangWonDo3 = new MarkerOptions();
                            markerGangWonDo3.position(GangWonDo3);
                            markerGangWonDo3.title(itemGangWonDo[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo3);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[4])) {
                            LatLng GangWonDo4 = new LatLng(37.342050, 127.919730);
                            MarkerOptions markerGangWonDo4 = new MarkerOptions();
                            markerGangWonDo4.position(GangWonDo4);
                            markerGangWonDo4.title(itemGangWonDo[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo4);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[5])) {
                            LatLng GangWonDo5 = new LatLng(37.881284, 127.730086);
                            MarkerOptions markerGangWonDo5 = new MarkerOptions();
                            markerGangWonDo5.position(GangWonDo5);
                            markerGangWonDo5.title(itemGangWonDo[5] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo5.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo5);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[6])) {
                            LatLng GangWonDo6 = new LatLng(37.164069, 128.985570);
                            MarkerOptions markerGangWonDo6 = new MarkerOptions();
                            markerGangWonDo6.position(GangWonDo6);
                            markerGangWonDo6.title(itemGangWonDo[6] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo6.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo6);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[7])) {
                            LatLng GangWonDo7 = new LatLng(38.380615, 128.467836);
                            MarkerOptions markerGangWonDo7 = new MarkerOptions();
                            markerGangWonDo7.position(GangWonDo7);
                            markerGangWonDo7.title(itemGangWonDo[7] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo7.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo7);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[8])) {
                            LatLng GangWonDo8 = new LatLng(38.109975, 127.989975);
                            MarkerOptions markerGangWonDo8 = new MarkerOptions();
                            markerGangWonDo8.position(GangWonDo8);
                            markerGangWonDo8.title(itemGangWonDo[8] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo8.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo8);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[9])) {
                            LatLng GangWonDo9 = new LatLng(38.075401, 128.619119);
                            MarkerOptions markerGangWonDo9 = new MarkerOptions();
                            markerGangWonDo9.position(GangWonDo9);
                            markerGangWonDo9.title(itemGangWonDo[9] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo9.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo9);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[10])) {
                            LatLng GangWonDo10 = new LatLng(37.183694, 128.461827);
                            MarkerOptions markerGangWonDo10 = new MarkerOptions();
                            markerGangWonDo10.position(GangWonDo10);
                            markerGangWonDo10.title(itemGangWonDo[10] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo10.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo10);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[11])) {
                            LatLng GangWonDo11 = new LatLng(38.069642, 128.170313);
                            MarkerOptions markerGangWonDo11 = new MarkerOptions();
                            markerGangWonDo11.position(GangWonDo11);
                            markerGangWonDo11.title(itemGangWonDo[11] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo11.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo11);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[12])) {
                            LatLng GangWonDo12 = new LatLng(37.380620, 128.660865);
                            MarkerOptions markerGangWonDo12 = new MarkerOptions();
                            markerGangWonDo12.position(GangWonDo12);
                            markerGangWonDo12.title(itemGangWonDo[12] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo12.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo12);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[13])) {
                            LatLng GangWonDo13 = new LatLng(38.146693, 127.313400);
                            MarkerOptions markerGangWonDo13 = new MarkerOptions();
                            markerGangWonDo13.position(GangWonDo13);
                            markerGangWonDo13.title(itemGangWonDo[13] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo13.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo13);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[14])) {
                            LatLng GangWonDo14 = new LatLng(37.370753, 128.390332);
                            MarkerOptions markerGangWonDo14 = new MarkerOptions();
                            markerGangWonDo14.position(GangWonDo14);
                            markerGangWonDo14.title(itemGangWonDo[14] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo14.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo14);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[15])) {
                            LatLng GangWonDo15 = new LatLng(37.697039, 127.888829);
                            MarkerOptions markerGangWonDo15 = new MarkerOptions();
                            markerGangWonDo15.position(GangWonDo15);
                            markerGangWonDo15.title(itemGangWonDo[15] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo15.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo15);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[16])) {
                            LatLng GangWonDo16 = new LatLng(38.106281, 127.708125);
                            MarkerOptions markerGangWonDo16 = new MarkerOptions();
                            markerGangWonDo16.position(GangWonDo16);
                            markerGangWonDo16.title(itemGangWonDo[16] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo16.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo16);
                        } else if (googleList.get(i)[1].equals(itemGangWonDo[17])) {
                            LatLng GangWonDo17 = new LatLng(37.491699, 127.985044);
                            MarkerOptions markerGangWonDo17 = new MarkerOptions();
                            markerGangWonDo17.position(GangWonDo17);
                            markerGangWonDo17.title(itemGangWonDo[17] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGangWonDo17.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGangWonDo17);
                        }
                    } else if (items1[j].equals("충청북도")) {
                        if (googleList.get(i)[1].equals(itemChungCheongBukDo[0])) {
                            LatLng ChungCheongBukDo0 = new LatLng(37.132576, 128.190937);
                            MarkerOptions markerChungCheongBukDo0 = new MarkerOptions();
                            markerChungCheongBukDo0.position(ChungCheongBukDo0);
                            markerChungCheongBukDo0.title(itemChungCheongBukDo[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongBukDo0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongBukDo0);
                        } else if (googleList.get(i)[1].equals(itemChungCheongBukDo[1])) {
                            LatLng ChungCheongBukDo1 = new LatLng(36.642050, 127.488831);
                            MarkerOptions markerChungCheongBukDo1 = new MarkerOptions();
                            markerChungCheongBukDo1.position(ChungCheongBukDo1);
                            markerChungCheongBukDo1.title(itemChungCheongBukDo[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongBukDo1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongBukDo1);
                        } else if (googleList.get(i)[1].equals(itemChungCheongBukDo[2])) {
                            LatLng ChungCheongBukDo2 = new LatLng(36.990858, 127.925935);
                            MarkerOptions markerChungCheongBukDo2 = new MarkerOptions();
                            markerChungCheongBukDo2.position(ChungCheongBukDo2);
                            markerChungCheongBukDo2.title(itemChungCheongBukDo[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongBukDo2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongBukDo2);
                        } else if (googleList.get(i)[1].equals(itemChungCheongBukDo[3])) {
                            LatLng ChungCheongBukDo3 = new LatLng(36.815326, 127.786640);
                            MarkerOptions markerChungCheongBukDo3 = new MarkerOptions();
                            markerChungCheongBukDo3.position(ChungCheongBukDo3);
                            markerChungCheongBukDo3.title(itemChungCheongBukDo[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongBukDo3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongBukDo3);
                        } else if (googleList.get(i)[1].equals(itemChungCheongBukDo[4])) {
                            LatLng ChungCheongBukDo4 = new LatLng(36.984653, 128.365465);
                            MarkerOptions markerChungCheongBukDo4 = new MarkerOptions();
                            markerChungCheongBukDo4.position(ChungCheongBukDo4);
                            markerChungCheongBukDo4.title(itemChungCheongBukDo[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongBukDo4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongBukDo4);
                        } else if (googleList.get(i)[1].equals(itemChungCheongBukDo[5])) {
                            LatLng ChungCheongBukDo5 = new LatLng(36.489404, 127.729482);
                            MarkerOptions markerChungCheongBukDo5 = new MarkerOptions();
                            markerChungCheongBukDo5.position(ChungCheongBukDo5);
                            markerChungCheongBukDo5.title(itemChungCheongBukDo[5] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongBukDo5.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongBukDo5);
                        } else if (googleList.get(i)[1].equals(itemChungCheongBukDo[6])) {
                            LatLng ChungCheongBukDo6 = new LatLng(36.175010, 127.783424);
                            MarkerOptions markerChungCheongBukDo6 = new MarkerOptions();
                            markerChungCheongBukDo6.position(ChungCheongBukDo6);
                            markerChungCheongBukDo6.title(itemChungCheongBukDo[6] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongBukDo6.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongBukDo6);
                        } else if (googleList.get(i)[1].equals(itemChungCheongBukDo[7])) {
                            LatLng ChungCheongBukDo7 = new LatLng(36.306367, 127.571288);
                            MarkerOptions markerChungCheongBukDo7 = new MarkerOptions();
                            markerChungCheongBukDo7.position(ChungCheongBukDo7);
                            markerChungCheongBukDo7.title(itemChungCheongBukDo[7] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongBukDo7.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongBukDo7);
                        } else if (googleList.get(i)[1].equals(itemChungCheongBukDo[8])) {
                            LatLng ChungCheongBukDo8 = new LatLng(36.940236, 127.690498);
                            MarkerOptions markerChungCheongBukDo8 = new MarkerOptions();
                            markerChungCheongBukDo8.position(ChungCheongBukDo8);
                            markerChungCheongBukDo8.title(itemChungCheongBukDo[8] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongBukDo8.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongBukDo8);
                        } else if (googleList.get(i)[1].equals(itemChungCheongBukDo[9])) {
                            LatLng ChungCheongBukDo9 = new LatLng(36.785308, 127.581483);
                            MarkerOptions markerChungCheongBukDo9 = new MarkerOptions();
                            markerChungCheongBukDo9.position(ChungCheongBukDo9);
                            markerChungCheongBukDo9.title(itemChungCheongBukDo[9] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongBukDo9.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongBukDo9);
                        } else if (googleList.get(i)[1].equals(itemChungCheongBukDo[10])) {
                            LatLng ChungCheongBukDo10 = new LatLng(36.855376, 127.435630);
                            MarkerOptions markerChungCheongBukDo10 = new MarkerOptions();
                            markerChungCheongBukDo10.position(ChungCheongBukDo10);
                            markerChungCheongBukDo10.title(itemChungCheongBukDo[10] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongBukDo10.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongBukDo10);
                        }
                    } else if (items1[j].equals("충청남도")) {
                        if (googleList.get(i)[1].equals(itemChungCheongNamDo[0])) {
                            LatLng ChungCheongNamDo0 = new LatLng(36.274586, 127.248706);
                            MarkerOptions markerChungCheongNamDo0 = new MarkerOptions();
                            markerChungCheongNamDo0.position(ChungCheongNamDo0);
                            markerChungCheongNamDo0.title(itemChungCheongNamDo[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo0);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[1])) {
                            LatLng ChungCheongNamDo1 = new LatLng(36.446597, 127.119033);
                            MarkerOptions markerChungCheongNamDo1 = new MarkerOptions();
                            markerChungCheongNamDo1.position(ChungCheongNamDo1);
                            markerChungCheongNamDo1.title(itemChungCheongNamDo[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo1);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[2])) {
                            LatLng ChungCheongNamDo2 = new LatLng(36.187166, 127.098862);
                            MarkerOptions markerChungCheongNamDo2 = new MarkerOptions();
                            markerChungCheongNamDo2.position(ChungCheongNamDo2);
                            markerChungCheongNamDo2.title(itemChungCheongNamDo[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo2);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[3])) {
                            LatLng ChungCheongNamDo3 = new LatLng(36.889918, 126.645810);
                            MarkerOptions markerChungCheongNamDo3 = new MarkerOptions();
                            markerChungCheongNamDo3.position(ChungCheongNamDo3);
                            markerChungCheongNamDo3.title(itemChungCheongNamDo[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo3);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[4])) {
                            LatLng ChungCheongNamDo4 = new LatLng(36.333416, 126.612750);
                            MarkerOptions markerChungCheongNamDo4 = new MarkerOptions();
                            markerChungCheongNamDo4.position(ChungCheongNamDo4);
                            markerChungCheongNamDo4.title(itemChungCheongNamDo[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo4);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[5])) {
                            LatLng ChungCheongNamDo5 = new LatLng(36.784803, 126.450321);
                            MarkerOptions markerChungCheongNamDo5 = new MarkerOptions();
                            markerChungCheongNamDo5.position(ChungCheongNamDo5);
                            markerChungCheongNamDo5.title(itemChungCheongNamDo[5] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo5.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo5);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[6])) {
                            LatLng ChungCheongNamDo6 = new LatLng(36.789939, 127.002569);
                            MarkerOptions markerChungCheongNamDo6 = new MarkerOptions();
                            markerChungCheongNamDo6.position(ChungCheongNamDo6);
                            markerChungCheongNamDo6.title(itemChungCheongNamDo[6] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo6.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo6);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[7])) {
                            LatLng ChungCheongNamDo7 = new LatLng(36.815084, 127.113794);
                            MarkerOptions markerChungCheongNamDo7 = new MarkerOptions();
                            markerChungCheongNamDo7.position(ChungCheongNamDo7);
                            markerChungCheongNamDo7.title(itemChungCheongNamDo[7] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo7.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo7);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[8])) {
                            LatLng ChungCheongNamDo8 = new LatLng(36.108770, 127.488098);
                            MarkerOptions markerChungCheongNamDo8 = new MarkerOptions();
                            markerChungCheongNamDo8.position(ChungCheongNamDo8);
                            markerChungCheongNamDo8.title(itemChungCheongNamDo[8] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo8.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo8);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[9])) {
                            LatLng ChungCheongNamDo9 = new LatLng(36.275710, 126.909796);
                            MarkerOptions markerChungCheongNamDo9 = new MarkerOptions();
                            markerChungCheongNamDo9.position(ChungCheongNamDo9);
                            markerChungCheongNamDo9.title(itemChungCheongNamDo[9] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo9.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo9);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[10])) {
                            LatLng ChungCheongNamDo10 = new LatLng(36.080367, 126.691784);
                            MarkerOptions markerChungCheongNamDo10 = new MarkerOptions();
                            markerChungCheongNamDo10.position(ChungCheongNamDo10);
                            markerChungCheongNamDo10.title(itemChungCheongNamDo[10] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo10.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo10);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[11])) {
                            LatLng ChungCheongNamDo11 = new LatLng(36.680228, 126.844667);
                            MarkerOptions markerChungCheongNamDo11 = new MarkerOptions();
                            markerChungCheongNamDo11.position(ChungCheongNamDo11);
                            markerChungCheongNamDo11.title(itemChungCheongNamDo[11] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo11.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo11);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[12])) {
                            LatLng ChungCheongNamDo12 = new LatLng(36.459232, 126.802218);
                            MarkerOptions markerChungCheongNamDo12 = new MarkerOptions();
                            markerChungCheongNamDo12.position(ChungCheongNamDo12);
                            markerChungCheongNamDo12.title(itemChungCheongNamDo[12] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo12.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo12);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[13])) {
                            LatLng ChungCheongNamDo13 = new LatLng(36.745609, 126.298015);
                            MarkerOptions markerChungCheongNamDo13 = new MarkerOptions();
                            markerChungCheongNamDo13.position(ChungCheongNamDo13);
                            markerChungCheongNamDo13.title(itemChungCheongNamDo[13] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo13.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo13);
                        } else if (googleList.get(i)[1].equals(itemChungCheongNamDo[14])) {
                            LatLng ChungCheongNamDo14 = new LatLng(36.601278, 126.660808);
                            MarkerOptions markerChungCheongNamDo14 = new MarkerOptions();
                            markerChungCheongNamDo14.position(ChungCheongNamDo14);
                            markerChungCheongNamDo14.title(itemChungCheongNamDo[14] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerChungCheongNamDo14.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerChungCheongNamDo14);
                        }
                    } else if (items1[j].equals("전라북도")) {
                        if (googleList.get(i)[1].equals(itemJeolLaBukDo[0])) {
                            LatLng JeolLaBukDo0 = new LatLng(35.967608, 126.736813);
                            MarkerOptions markerJeolLaBukDo0 = new MarkerOptions();
                            markerJeolLaBukDo0.position(JeolLaBukDo0);
                            markerJeolLaBukDo0.title(itemJeolLaBukDo[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo0);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[1])) {
                            LatLng JeolLaBukDo1 = new LatLng(35.803526, 126.880520);
                            MarkerOptions markerJeolLaBukDo1 = new MarkerOptions();
                            markerJeolLaBukDo1.position(JeolLaBukDo1);
                            markerJeolLaBukDo1.title(itemJeolLaBukDo[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo1);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[2])) {
                            LatLng JeolLaBukDo2 = new LatLng(35.416339, 127.390403);
                            MarkerOptions markerJeolLaBukDo2 = new MarkerOptions();
                            markerJeolLaBukDo2.position(JeolLaBukDo2);
                            markerJeolLaBukDo2.title(itemJeolLaBukDo[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo2);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[3])) {
                            LatLng JeolLaBukDo3 = new LatLng(35.948253, 126.957707);
                            MarkerOptions markerJeolLaBukDo3 = new MarkerOptions();
                            markerJeolLaBukDo3.position(JeolLaBukDo3);
                            markerJeolLaBukDo3.title(itemJeolLaBukDo[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo3);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[4])) {
                            LatLng JeolLaBukDo4 = new LatLng(35.824126, 127.147994);
                            MarkerOptions markerJeolLaBukDo4 = new MarkerOptions();
                            markerJeolLaBukDo4.position(JeolLaBukDo4);
                            markerJeolLaBukDo4.title(itemJeolLaBukDo[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo4);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[5])) {
                            LatLng JeolLaBukDo5 = new LatLng(35.569857, 126.856022);
                            MarkerOptions markerJeolLaBukDo5 = new MarkerOptions();
                            markerJeolLaBukDo5.position(JeolLaBukDo5);
                            markerJeolLaBukDo5.title(itemJeolLaBukDo[5] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo5.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo5);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[6])) {
                            LatLng JeolLaBukDo6 = new LatLng(35.435790, 126.702099);
                            MarkerOptions markerJeolLaBukDo6 = new MarkerOptions();
                            markerJeolLaBukDo6.position(JeolLaBukDo6);
                            markerJeolLaBukDo6.title(itemJeolLaBukDo[6] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo6.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo6);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[7])) {
                            LatLng JeolLaBukDo7 = new LatLng(36.006818, 127.660824);
                            MarkerOptions markerJeolLaBukDo7 = new MarkerOptions();
                            markerJeolLaBukDo7.position(JeolLaBukDo7);
                            markerJeolLaBukDo7.title(itemJeolLaBukDo[7] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo7.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo7);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[8])) {
                            LatLng JeolLaBukDo8 = new LatLng(35.731814, 126.733470);
                            MarkerOptions markerJeolLaBukDo8 = new MarkerOptions();
                            markerJeolLaBukDo8.position(JeolLaBukDo8);
                            markerJeolLaBukDo8.title(itemJeolLaBukDo[8] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo8.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo8);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[9])) {
                            LatLng JeolLaBukDo9 = new LatLng(35.374439, 127.137504);
                            MarkerOptions markerJeolLaBukDo9 = new MarkerOptions();
                            markerJeolLaBukDo9.position(JeolLaBukDo9);
                            markerJeolLaBukDo9.title(itemJeolLaBukDo[9] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo9.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo9);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[10])) {
                            LatLng JeolLaBukDo10 = new LatLng(35.904800, 127.162082);
                            MarkerOptions markerJeolLaBukDo10 = new MarkerOptions();
                            markerJeolLaBukDo10.position(JeolLaBukDo10);
                            markerJeolLaBukDo10.title(itemJeolLaBukDo[10] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo10.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo10);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[11])) {
                            LatLng JeolLaBukDo11 = new LatLng(35.617784, 127.289089);
                            MarkerOptions markerJeolLaBukDo11 = new MarkerOptions();
                            markerJeolLaBukDo11.position(JeolLaBukDo11);
                            markerJeolLaBukDo11.title(itemJeolLaBukDo[11] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo11.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo11);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[12])) {
                            LatLng JeolLaBukDo12 = new LatLng(35.647008, 127.521598);
                            MarkerOptions markerJeolLaBukDo12 = new MarkerOptions();
                            markerJeolLaBukDo12.position(JeolLaBukDo12);
                            markerJeolLaBukDo12.title(itemJeolLaBukDo[12] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo12.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo12);
                        } else if (googleList.get(i)[1].equals(itemJeolLaBukDo[13])) {
                            LatLng JeolLaBukDo13 = new LatLng(35.791729, 127.424895);
                            MarkerOptions markerJeolLaBukDo13 = new MarkerOptions();
                            markerJeolLaBukDo13.position(JeolLaBukDo13);
                            markerJeolLaBukDo13.title(itemJeolLaBukDo[13] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaBukDo13.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaBukDo13);
                        }
                    } else if (items1[j].equals("전라남도")) {
                        if (googleList.get(i)[1].equals(itemJeolLaNamDo[0])) {
                            LatLng JeolLaNamDo0 = new LatLng(34.940643, 127.695993);
                            MarkerOptions markerJeolLaNamDo0 = new MarkerOptions();
                            markerJeolLaNamDo0.position(JeolLaNamDo0);
                            markerJeolLaNamDo0.title(itemJeolLaNamDo[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo0);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[1])) {
                            LatLng JeolLaNamDo1 = new LatLng(35.015888, 126.710881);
                            MarkerOptions markerJeolLaNamDo1 = new MarkerOptions();
                            markerJeolLaNamDo1.position(JeolLaNamDo1);
                            markerJeolLaNamDo1.title(itemJeolLaNamDo[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo1);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[2])) {
                            LatLng JeolLaNamDo2 = new LatLng(34.811807, 126.392311);
                            MarkerOptions markerJeolLaNamDo2 = new MarkerOptions();
                            markerJeolLaNamDo2.position(JeolLaNamDo2);
                            markerJeolLaNamDo2.title(itemJeolLaNamDo[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo2);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[3])) {
                            LatLng JeolLaNamDo3 = new LatLng(34.950579, 127.487145);
                            MarkerOptions markerJeolLaNamDo3 = new MarkerOptions();
                            markerJeolLaNamDo3.position(JeolLaNamDo3);
                            markerJeolLaNamDo3.title(itemJeolLaNamDo[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo3);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[4])) {
                            LatLng JeolLaNamDo4 = new LatLng(34.760405, 127.662271);
                            MarkerOptions markerJeolLaNamDo4 = new MarkerOptions();
                            markerJeolLaNamDo4.position(JeolLaNamDo4);
                            markerJeolLaNamDo4.title(itemJeolLaNamDo[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo4);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[5])) {
                            LatLng JeolLaNamDo5 = new LatLng(34.642041, 126.767304);
                            MarkerOptions markerJeolLaNamDo5 = new MarkerOptions();
                            markerJeolLaNamDo5.position(JeolLaNamDo5);
                            markerJeolLaNamDo5.title(itemJeolLaNamDo[5] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo5.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo5);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[6])) {
                            LatLng JeolLaNamDo6 = new LatLng(34.611204, 127.285059);
                            MarkerOptions markerJeolLaNamDo6 = new MarkerOptions();
                            markerJeolLaNamDo6.position(JeolLaNamDo6);
                            markerJeolLaNamDo6.title(itemJeolLaNamDo[6] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo6.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo6);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[7])) {
                            LatLng JeolLaNamDo7 = new LatLng(35.281991, 127.292067);
                            MarkerOptions markerJeolLaNamDo7 = new MarkerOptions();
                            markerJeolLaNamDo7.position(JeolLaNamDo7);
                            markerJeolLaNamDo7.title(itemJeolLaNamDo[7] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo7.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo7);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[8])) {
                            LatLng JeolLaNamDo8 = new LatLng(35.202488, 127.462793);
                            MarkerOptions markerJeolLaNamDo8 = new MarkerOptions();
                            markerJeolLaNamDo8.position(JeolLaNamDo8);
                            markerJeolLaNamDo8.title(itemJeolLaNamDo[8] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo8.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo8);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[9])) {
                            LatLng JeolLaNamDo9 = new LatLng(35.321212, 126.988292);
                            MarkerOptions markerJeolLaNamDo9 = new MarkerOptions();
                            markerJeolLaNamDo9.position(JeolLaNamDo9);
                            markerJeolLaNamDo9.title(itemJeolLaNamDo[9] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo9.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo9);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[10])) {
                            LatLng JeolLaNamDo10 = new LatLng(34.990445, 126.481710);
                            MarkerOptions markerJeolLaNamDo10 = new MarkerOptions();
                            markerJeolLaNamDo10.position(JeolLaNamDo10);
                            markerJeolLaNamDo10.title(itemJeolLaNamDo[10] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo10.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo10);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[11])) {
                            LatLng JeolLaNamDo11 = new LatLng(34.771430, 127.080097);
                            MarkerOptions markerJeolLaNamDo11 = new MarkerOptions();
                            markerJeolLaNamDo11.position(JeolLaNamDo11);
                            markerJeolLaNamDo11.title(itemJeolLaNamDo[11] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo11.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo11);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[12])) {
                            LatLng JeolLaNamDo12 = new LatLng(34.833309, 126.351381);
                            MarkerOptions markerJeolLaNamDo12 = new MarkerOptions();
                            markerJeolLaNamDo12.position(JeolLaNamDo12);
                            markerJeolLaNamDo12.title(itemJeolLaNamDo[12] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo12.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo12);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[13])) {
                            LatLng JeolLaNamDo13 = new LatLng(35.277130, 126.512062);
                            MarkerOptions markerJeolLaNamDo13 = new MarkerOptions();
                            markerJeolLaNamDo13.position(JeolLaNamDo13);
                            markerJeolLaNamDo13.title(itemJeolLaNamDo[13] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo13.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo13);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[14])) {
                            LatLng JeolLaNamDo14 = new LatLng(34.800160, 126.696823);
                            MarkerOptions markerJeolLaNamDo14 = new MarkerOptions();
                            markerJeolLaNamDo14.position(JeolLaNamDo14);
                            markerJeolLaNamDo14.title(itemJeolLaNamDo[14] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo14.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo14);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[15])) {
                            LatLng JeolLaNamDo15 = new LatLng(34.310975, 126.755079);
                            MarkerOptions markerJeolLaNamDo15 = new MarkerOptions();
                            markerJeolLaNamDo15.position(JeolLaNamDo15);
                            markerJeolLaNamDo15.title(itemJeolLaNamDo[15] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo15.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo15);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[16])) {
                            LatLng JeolLaNamDo16 = new LatLng(35.301993, 126.784676);
                            MarkerOptions markerJeolLaNamDo16 = new MarkerOptions();
                            markerJeolLaNamDo16.position(JeolLaNamDo16);
                            markerJeolLaNamDo16.title(itemJeolLaNamDo[16] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo16.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo16);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[17])) {
                            LatLng JeolLaNamDo17 = new LatLng(34.681595, 126.907088);
                            MarkerOptions markerJeolLaNamDo17 = new MarkerOptions();
                            markerJeolLaNamDo17.position(JeolLaNamDo17);
                            markerJeolLaNamDo17.title(itemJeolLaNamDo[17] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo17.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo17);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[18])) {
                            LatLng JeolLaNamDo18 = new LatLng(34.486831, 126.263555);
                            MarkerOptions markerJeolLaNamDo18 = new MarkerOptions();
                            markerJeolLaNamDo18.position(JeolLaNamDo18);
                            markerJeolLaNamDo18.title(itemJeolLaNamDo[18] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo18.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo18);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[19])) {
                            LatLng JeolLaNamDo19 = new LatLng(35.065920, 126.516686);
                            MarkerOptions markerJeolLaNamDo19 = new MarkerOptions();
                            markerJeolLaNamDo19.position(JeolLaNamDo19);
                            markerJeolLaNamDo19.title(itemJeolLaNamDo[19] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo19.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo19);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[20])) {
                            LatLng JeolLaNamDo20 = new LatLng(34.573349, 126.599237);
                            MarkerOptions markerJeolLaNamDo20 = new MarkerOptions();
                            markerJeolLaNamDo20.position(JeolLaNamDo20);
                            markerJeolLaNamDo20.title(itemJeolLaNamDo[20] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo20.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo20);
                        } else if (googleList.get(i)[1].equals(itemJeolLaNamDo[21])) {
                            LatLng JeolLaNamDo21 = new LatLng(35.064433, 126.986659);
                            MarkerOptions markerJeolLaNamDo21 = new MarkerOptions();
                            markerJeolLaNamDo21.position(JeolLaNamDo21);
                            markerJeolLaNamDo21.title(itemJeolLaNamDo[21] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeolLaNamDo21.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeolLaNamDo21);
                        }
                    } else if (items1[j].equals("경상북도")) {
                        if (googleList.get(i)[1].equals(itemGyeongSangBukDo[0])) {
                            LatLng GyeongSangBukDo0 = new LatLng(35.825098, 128.740571);
                            MarkerOptions markerGyeongSangBukDo0 = new MarkerOptions();
                            markerGyeongSangBukDo0.position(GyeongSangBukDo0);
                            markerGyeongSangBukDo0.title(itemGyeongSangBukDo[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo0);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[1])) {
                            LatLng GyeongSangBukDo1 = new LatLng(35.856190, 129.224822);
                            MarkerOptions markerGyeongSangBukDo1 = new MarkerOptions();
                            markerGyeongSangBukDo1.position(GyeongSangBukDo1);
                            markerGyeongSangBukDo1.title(itemGyeongSangBukDo[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo1);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[2])) {
                            LatLng GyeongSangBukDo2 = new LatLng(36.119635, 128.344292);
                            MarkerOptions markerGyeongSangBukDo2 = new MarkerOptions();
                            markerGyeongSangBukDo2.position(GyeongSangBukDo2);
                            markerGyeongSangBukDo2.title(itemGyeongSangBukDo[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo2);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[3])) {
                            LatLng GyeongSangBukDo3 = new LatLng(36.139867, 128.113643);
                            MarkerOptions markerGyeongSangBukDo3 = new MarkerOptions();
                            markerGyeongSangBukDo3.position(GyeongSangBukDo3);
                            markerGyeongSangBukDo3.title(itemGyeongSangBukDo[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo3);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[4])) {
                            LatLng GyeongSangBukDo4 = new LatLng(36.586511, 128.186810);
                            MarkerOptions markerGyeongSangBukDo4 = new MarkerOptions();
                            markerGyeongSangBukDo4.position(GyeongSangBukDo4);
                            markerGyeongSangBukDo4.title(itemGyeongSangBukDo[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo4);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[5])) {
                            LatLng GyeongSangBukDo5 = new LatLng(36.411002, 128.159031);
                            MarkerOptions markerGyeongSangBukDo5 = new MarkerOptions();
                            markerGyeongSangBukDo5.position(GyeongSangBukDo5);
                            markerGyeongSangBukDo5.title(itemGyeongSangBukDo[5] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo5.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo5);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[6])) {
                            LatLng GyeongSangBukDo6 = new LatLng(36.568428, 128.729555);
                            MarkerOptions markerGyeongSangBukDo6 = new MarkerOptions();
                            markerGyeongSangBukDo6.position(GyeongSangBukDo6);
                            markerGyeongSangBukDo6.title(itemGyeongSangBukDo[6] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo6.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo6);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[7])) {
                            LatLng GyeongSangBukDo7 = new LatLng(36.805697, 128.624011);
                            MarkerOptions markerGyeongSangBukDo7 = new MarkerOptions();
                            markerGyeongSangBukDo7.position(GyeongSangBukDo7);
                            markerGyeongSangBukDo7.title(itemGyeongSangBukDo[7] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo7.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo7);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[8])) {
                            LatLng GyeongSangBukDo8 = new LatLng(35.973270, 128.938616);
                            MarkerOptions markerGyeongSangBukDo8 = new MarkerOptions();
                            markerGyeongSangBukDo8.position(GyeongSangBukDo8);
                            markerGyeongSangBukDo8.title(itemGyeongSangBukDo[8] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo8.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo8);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[9])) {
                            LatLng GyeongSangBukDo9 = new LatLng(36.019053, 129.343653);
                            MarkerOptions markerGyeongSangBukDo9 = new MarkerOptions();
                            markerGyeongSangBukDo9.position(GyeongSangBukDo9);
                            markerGyeongSangBukDo9.title(itemGyeongSangBukDo[9] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo9.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo9);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[10])) {
                            LatLng GyeongSangBukDo10 = new LatLng(35.726121, 128.262951);
                            MarkerOptions markerGyeongSangBukDo10 = new MarkerOptions();
                            markerGyeongSangBukDo10.position(GyeongSangBukDo10);
                            markerGyeongSangBukDo10.title(itemGyeongSangBukDo[10] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo10.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo10);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[11])) {
                            LatLng GyeongSangBukDo11 = new LatLng(36.242916, 128.572924);
                            MarkerOptions markerGyeongSangBukDo11 = new MarkerOptions();
                            markerGyeongSangBukDo11.position(GyeongSangBukDo11);
                            markerGyeongSangBukDo11.title(itemGyeongSangBukDo[11] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo11.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo11);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[12])) {
                            LatLng GyeongSangBukDo12 = new LatLng(36.893124, 128.732587);
                            MarkerOptions markerGyeongSangBukDo12 = new MarkerOptions();
                            markerGyeongSangBukDo12.position(GyeongSangBukDo12);
                            markerGyeongSangBukDo12.title(itemGyeongSangBukDo[12] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo12.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo12);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[13])) {
                            LatLng GyeongSangBukDo13 = new LatLng(35.919327, 128.283240);
                            MarkerOptions markerGyeongSangBukDo13 = new MarkerOptions();
                            markerGyeongSangBukDo13.position(GyeongSangBukDo13);
                            markerGyeongSangBukDo13.title(itemGyeongSangBukDo[13] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo13.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo13);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[14])) {
                            LatLng GyeongSangBukDo14 = new LatLng(36.415038, 129.366068);
                            MarkerOptions markerGyeongSangBukDo14 = new MarkerOptions();
                            markerGyeongSangBukDo14.position(GyeongSangBukDo14);
                            markerGyeongSangBukDo14.title(itemGyeongSangBukDo[14] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo14.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo14);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[15])) {
                            LatLng GyeongSangBukDo15 = new LatLng(36.666674, 129.112403);
                            MarkerOptions markerGyeongSangBukDo15 = new MarkerOptions();
                            markerGyeongSangBukDo15.position(GyeongSangBukDo15);
                            markerGyeongSangBukDo15.title(itemGyeongSangBukDo[15] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo15.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo15);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[16])) {
                            LatLng GyeongSangBukDo16 = new LatLng(36.646662, 128.437314);
                            MarkerOptions markerGyeongSangBukDo16 = new MarkerOptions();
                            markerGyeongSangBukDo16.position(GyeongSangBukDo16);
                            markerGyeongSangBukDo16.title(itemGyeongSangBukDo[16] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo16.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo16);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[17])) {
                            LatLng GyeongSangBukDo17 = new LatLng(37.484422, 130.905869);
                            MarkerOptions markerGyeongSangBukDo17 = new MarkerOptions();
                            markerGyeongSangBukDo17.position(GyeongSangBukDo17);
                            markerGyeongSangBukDo17.title(itemGyeongSangBukDo[17] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo17.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo17);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[18])) {
                            LatLng GyeongSangBukDo18 = new LatLng(36.993060, 129.400560);
                            MarkerOptions markerGyeongSangBukDo18 = new MarkerOptions();
                            markerGyeongSangBukDo18.position(GyeongSangBukDo18);
                            markerGyeongSangBukDo18.title(itemGyeongSangBukDo[18] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo18.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo18);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[19])) {
                            LatLng GyeongSangBukDo19 = new LatLng(36.352702, 128.697101);
                            MarkerOptions markerGyeongSangBukDo19 = new MarkerOptions();
                            markerGyeongSangBukDo19.position(GyeongSangBukDo19);
                            markerGyeongSangBukDo19.title(itemGyeongSangBukDo[19] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo19.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo19);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[20])) {
                            LatLng GyeongSangBukDo20 = new LatLng(35.647348, 128.734379);
                            MarkerOptions markerGyeongSangBukDo20 = new MarkerOptions();
                            markerGyeongSangBukDo20.position(GyeongSangBukDo20);
                            markerGyeongSangBukDo20.title(itemGyeongSangBukDo[20] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo20.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo20);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[21])) {
                            LatLng GyeongSangBukDo21 = new LatLng(36.436281, 129.057080);
                            MarkerOptions markerGyeongSangBukDo21 = new MarkerOptions();
                            markerGyeongSangBukDo21.position(GyeongSangBukDo21);
                            markerGyeongSangBukDo21.title(itemGyeongSangBukDo[21] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo21.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo21);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangBukDo[22])) {
                            LatLng GyeongSangBukDo22 = new LatLng(35.995555, 128.401678);
                            MarkerOptions markerGyeongSangBukDo22 = new MarkerOptions();
                            markerGyeongSangBukDo22.position(GyeongSangBukDo22);
                            markerGyeongSangBukDo22.title(itemGyeongSangBukDo[22] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangBukDo22.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangBukDo22);
                        }
                    } else if (items1[j].equals("경상남도")) {
                        if (googleList.get(i)[1].equals(itemGyeongSangNamDo[0])) {
                            LatLng GyeongSangNamDo0 = new LatLng(34.880541, 128.621180);
                            MarkerOptions markerGyeongSangNamDo0 = new MarkerOptions();
                            markerGyeongSangNamDo0.position(GyeongSangNamDo0);
                            markerGyeongSangNamDo0.title(itemGyeongSangNamDo[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo0);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[1])) {
                            LatLng GyeongSangNamDo1 = new LatLng(35.228665, 128.889342);
                            MarkerOptions markerGyeongSangNamDo1 = new MarkerOptions();
                            markerGyeongSangNamDo1.position(GyeongSangNamDo1);
                            markerGyeongSangNamDo1.title(itemGyeongSangNamDo[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo1);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[2])) {
                            LatLng GyeongSangNamDo2 = new LatLng(35.503786, 128.746625);
                            MarkerOptions markerGyeongSangNamDo2 = new MarkerOptions();
                            markerGyeongSangNamDo2.position(GyeongSangNamDo2);
                            markerGyeongSangNamDo2.title(itemGyeongSangNamDo[2] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo2.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo2);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[3])) {
                            LatLng GyeongSangNamDo3 = new LatLng(35.003681, 128.064270);
                            MarkerOptions markerGyeongSangNamDo3 = new MarkerOptions();
                            markerGyeongSangNamDo3.position(GyeongSangNamDo3);
                            markerGyeongSangNamDo3.title(itemGyeongSangNamDo[3] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo3.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo3);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[4])) {
                            LatLng GyeongSangNamDo4 = new LatLng(35.334996, 129.037088);
                            MarkerOptions markerGyeongSangNamDo4 = new MarkerOptions();
                            markerGyeongSangNamDo4.position(GyeongSangNamDo4);
                            markerGyeongSangNamDo4.title(itemGyeongSangNamDo[4] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo4.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo4);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[5])) {
                            LatLng GyeongSangNamDo5 = new LatLng(35.180308, 128.108720);
                            MarkerOptions markerGyeongSangNamDo5 = new MarkerOptions();
                            markerGyeongSangNamDo5.position(GyeongSangNamDo5);
                            markerGyeongSangNamDo5.title(itemGyeongSangNamDo[5] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo5.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo5);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[6])) {
                            LatLng GyeongSangNamDo6 = new LatLng(35.227925, 128.681837);
                            MarkerOptions markerGyeongSangNamDo6 = new MarkerOptions();
                            markerGyeongSangNamDo6.position(GyeongSangNamDo6);
                            markerGyeongSangNamDo6.title(itemGyeongSangNamDo[6] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo6.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo6);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[7])) {
                            LatLng GyeongSangNamDo7 = new LatLng(34.854413, 128.433236);
                            MarkerOptions markerGyeongSangNamDo7 = new MarkerOptions();
                            markerGyeongSangNamDo7.position(GyeongSangNamDo7);
                            markerGyeongSangNamDo7.title(itemGyeongSangNamDo[7] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo7.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo7);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[8])) {
                            LatLng GyeongSangNamDo8 = new LatLng(35.686391, 127.909383);
                            MarkerOptions markerGyeongSangNamDo8 = new MarkerOptions();
                            markerGyeongSangNamDo8.position(GyeongSangNamDo8);
                            markerGyeongSangNamDo8.title(itemGyeongSangNamDo[8] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo8.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo8);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[9])) {
                            LatLng GyeongSangNamDo9 = new LatLng(34.972980, 128.322247);
                            MarkerOptions markerGyeongSangNamDo9 = new MarkerOptions();
                            markerGyeongSangNamDo9.position(GyeongSangNamDo9);
                            markerGyeongSangNamDo9.title(itemGyeongSangNamDo[9] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo9.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo9);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[10])) {
                            LatLng GyeongSangNamDo10 = new LatLng(34.837675, 127.892601);
                            MarkerOptions markerGyeongSangNamDo10 = new MarkerOptions();
                            markerGyeongSangNamDo10.position(GyeongSangNamDo10);
                            markerGyeongSangNamDo10.title(itemGyeongSangNamDo[10] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo10.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo10);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[11])) {
                            LatLng GyeongSangNamDo11 = new LatLng(35.415549, 127.873466);
                            MarkerOptions markerGyeongSangNamDo11 = new MarkerOptions();
                            markerGyeongSangNamDo11.position(GyeongSangNamDo11);
                            markerGyeongSangNamDo11.title(itemGyeongSangNamDo[11] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo11.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo11);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[12])) {
                            LatLng GyeongSangNamDo12 = new LatLng(35.322182, 128.261748);
                            MarkerOptions markerGyeongSangNamDo12 = new MarkerOptions();
                            markerGyeongSangNamDo12.position(GyeongSangNamDo12);
                            markerGyeongSangNamDo12.title(itemGyeongSangNamDo[12] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo12.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo12);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[13])) {
                            LatLng GyeongSangNamDo13 = new LatLng(35.544565, 128.492406);
                            MarkerOptions markerGyeongSangNamDo13 = new MarkerOptions();
                            markerGyeongSangNamDo13.position(GyeongSangNamDo13);
                            markerGyeongSangNamDo13.title(itemGyeongSangNamDo[13] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo13.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo13);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[14])) {
                            LatLng GyeongSangNamDo14 = new LatLng(35.067432, 127.751823);
                            MarkerOptions markerGyeongSangNamDo14 = new MarkerOptions();
                            markerGyeongSangNamDo14.position(GyeongSangNamDo14);
                            markerGyeongSangNamDo14.title(itemGyeongSangNamDo[14] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo14.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo14);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[15])) {
                            LatLng GyeongSangNamDo15 = new LatLng(35.272472, 128.406543);
                            MarkerOptions markerGyeongSangNamDo15 = new MarkerOptions();
                            markerGyeongSangNamDo15.position(GyeongSangNamDo15);
                            markerGyeongSangNamDo15.title(itemGyeongSangNamDo[15] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo15.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo15);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[16])) {
                            LatLng GyeongSangNamDo16 = new LatLng(35.520528, 127.725173);
                            MarkerOptions markerGyeongSangNamDo16 = new MarkerOptions();
                            markerGyeongSangNamDo16.position(GyeongSangNamDo16);
                            markerGyeongSangNamDo16.title(itemGyeongSangNamDo[16] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo16.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo16);
                        } else if (googleList.get(i)[1].equals(itemGyeongSangNamDo[17])) {
                            LatLng GyeongSangNamDo17 = new LatLng(35.566607, 128.165872);
                            MarkerOptions markerGyeongSangNamDo17 = new MarkerOptions();
                            markerGyeongSangNamDo17.position(GyeongSangNamDo17);
                            markerGyeongSangNamDo17.title(itemGyeongSangNamDo[17] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerGyeongSangNamDo17.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerGyeongSangNamDo17);
                        }
                    } else if (items1[j].equals("제주도")) {
                        if (googleList.get(i)[1].equals(itemJeJuIsland[0])) {
                            LatLng JeJu0 = new LatLng(33.253948, 126.559615);
                            MarkerOptions markerJeJu0 = new MarkerOptions();
                            markerJeJu0.position(JeJu0);
                            markerJeJu0.title(itemJeJuIsland[0] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeJu0.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeJu0);
                        } else if (googleList.get(i)[1].equals(itemJeJuIsland[1])) {
                            LatLng JeJu1 = new LatLng(33.499588, 126.531268);
                            MarkerOptions markerJeJu1 = new MarkerOptions();
                            markerJeJu1.position(JeJu1);
                            markerJeJu1.title(itemJeJuIsland[1] + "대표");
                            long milSeond2 = Long.valueOf(googleList.get(i)[3]);
                            long minute = milSeond2 / 60;
                            long second = milSeond2 % 60;
                            String strmilSeond2 = minute + "분" + " " + second + "초";
                            markerJeJu1.snippet(googleList.get(i)[2] + "님" + "의 기록 : " + strmilSeond2);
                            googlemap.addMarker(markerJeJu1);
                        }
                    }
                }
            }


        }

    }
}
