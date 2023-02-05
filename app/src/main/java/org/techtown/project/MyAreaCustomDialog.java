package org.techtown.project;



import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.NumberPicker;



public class MyAreaCustomDialog extends Dialog {

    Context context;
    String item[] = {"선택하기","세종특별자치시"};
    String items1[] = {"선택하기", "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주도"};
    String itemSeoul[] = {"강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구",
            "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};
    String itemBusan[] = {"강서구", "금정구", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구", "기장군"};
    String itemDeGu[] = {"남구", "달서구", "동구", "북구", "서구", "수성구", "중구", "달성군"};
    String itemInCheon[] = {"계양구", "남동구", "동구", "미추홀구", "부평구", "서구", "연수구", "중구", "강화군", "웅진군"};
    String itemGwangJu[] = {"광산구", "남구", "동구", "북구", "서구"};
    String itemDeJeon[] = {"대덕구", "동구", "서구", "유성구", "중구"};
    String itemUlSan[] = {"남구", "동구", "북구", "중구", "울주군"};
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


    Button btnRegister;
    NumberPicker numberPicker1;
    NumberPicker numberPicker2;
    String TAG = "MyAreaCustomDialog";
    String levelValue2;
    String levelValue;


    MyAreaCustomDialog(final Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        setContentView(R.layout.myareacustomdialog);

        numberPicker1 = findViewById(R.id.numberPicker1);
        numberPicker2 = findViewById(R.id.numberPicker2);
        btnRegister = findViewById(R.id.btnRegister);



        numberPicker1.setMinValue(0);
        numberPicker1.setDisplayedValues(items1);
        numberPicker1.setMaxValue(17);
        numberPicker1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);     //수정할수없게 만드는설정
        numberPicker1.setWrapSelectorWheel(false);                                         //휠이 끝가지가면  처음부터 보이게하는것을 막는 설정
        numberPicker2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker2.setWrapSelectorWheel(false);




        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldval, int newval) {
                Log.d(TAG, "msg=======");
                int value = numberPicker.getValue();
                Log.d(TAG, "value : " + value);
                levelValue = items1[value];

                if (levelValue.equals("서울특별시")) {
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemSeoul);
                    numberPicker2.setMaxValue(24);
                    numberPicker2.setValue(0);
                } else if (levelValue.equals("부산광역시")) {
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemBusan);
                    numberPicker2.setMaxValue(15);
                    numberPicker2.setValue(0);
                } else if (levelValue.equals("대구광역시")) {
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemDeGu);
                    numberPicker2.setMaxValue(7);
                    numberPicker2.setValue(0);
                } else if (levelValue.equals("인천광역시")) {
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemInCheon);
                    numberPicker2.setMaxValue(9);
                    numberPicker2.setValue(0);
                } else if (levelValue.equals("광주광역시")) {
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemGwangJu);
                    numberPicker2.setMaxValue(4);
                    numberPicker2.setValue(0);
                } else if (levelValue.equals("대전광역시")) {
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemDeJeon);
                    numberPicker2.setMaxValue(4);
                    numberPicker2.setValue(0);
                } else if (levelValue.equals("울산광역시")) {
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemUlSan);
                    numberPicker2.setMaxValue(4);
                    numberPicker2.setValue(0);
                } else if (levelValue.equals("세종특별자치시")) {
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(item);
                    numberPicker2.setMaxValue(1);
                    numberPicker2.setValue(0);
                } else if (levelValue.equals("경기도")) {
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemGyeongGiDo);
                    numberPicker2.setMaxValue(30);
                    numberPicker2.setValue(0);
                } else if (levelValue.equals("강원도")) {
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemGangWonDo);
                    numberPicker2.setMaxValue(17);
                    numberPicker2.setValue(0);
                }
                else if(levelValue.equals("충청북도")){
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemChungCheongBukDo);
                    numberPicker2.setMaxValue(10);
                    numberPicker2.setValue(0);
                }
                else if(levelValue.equals("충청남도")){
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemChungCheongNamDo);
                    numberPicker2.setMaxValue(14);
                    numberPicker2.setValue(0);
                }
                else if(levelValue.equals("전라북도")){
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemJeolLaBukDo);
                    numberPicker2.setMaxValue(13);
                    numberPicker2.setValue(0);
                }
                else if(levelValue.equals("전라남도")){
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemJeolLaNamDo);
                    numberPicker2.setMaxValue(21);
                    numberPicker2.setValue(0);
                }
                else if(levelValue.equals("경상북도")){
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemGyeongSangBukDo);
                    numberPicker2.setMaxValue(22);
                    numberPicker2.setValue(0);
                }
                else if(levelValue.equals("경상남도")){
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemGyeongSangNamDo);
                    numberPicker2.setMaxValue(17);
                    numberPicker2.setValue(0);
                }
                else if(levelValue.equals("제주도")){
                    numberPicker2.setMinValue(0);
                    numberPicker2.setDisplayedValues(itemJeJuIsland);
                    numberPicker2.setMaxValue(1);
                    numberPicker2.setValue(0);
                }
            }
        });
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int value = numberPicker.getValue();
                Log.d(TAG, "value : " + value);
                if(levelValue.equals("서울특별시")){
                    levelValue2 = itemSeoul[value];
                    numberPicker2.scrollTo(0,1);
                } else if (levelValue.equals("부산광역시")) {
                    levelValue2 = itemBusan[value];
                } else if (levelValue.equals("대구광역시")) {
                    levelValue2 = itemDeGu[value];
                } else if (levelValue.equals("인천광역시")) {
                    levelValue2 = itemInCheon[value];
                } else if (levelValue.equals("광주광역시")) {
                    levelValue2 = itemGwangJu[value];
                } else if (levelValue.equals("대전광역시")) {
                    levelValue2 = itemDeJeon[value];
                } else if (levelValue.equals("울산광역시")) {
                    levelValue2 = itemUlSan[value];
                } else if (levelValue.equals("세종특별자치시")) {
                    levelValue2 = item[value];
                } else if (levelValue.equals("경기도")) {
                    levelValue2 = itemGyeongGiDo[value];
                } else if (levelValue.equals("강원도")) {
                    levelValue2 = itemGangWonDo[value];
                } else if (levelValue.equals("충청북도")) {
                    levelValue2 = itemChungCheongBukDo[value];
                } else if (levelValue.equals("충청남도")) {
                    levelValue2 = itemChungCheongNamDo[value];
                } else if (levelValue.equals("전라북도")) {
                    levelValue2 = itemJeolLaBukDo[value];
                } else if (levelValue.equals("전라남도")) {
                    levelValue2 = itemJeolLaNamDo[value];
                } else if (levelValue.equals("경상북도")) {
                    levelValue2 = itemGyeongSangBukDo[value];
                } else if (levelValue.equals("경상남도")) {
                    levelValue2 = itemGyeongSangNamDo[value];
                } else if (levelValue.equals("제주도")) {
                    levelValue2 = itemJeJuIsland[value];
                }
            }
        });



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {           //등록하기 버튼누르면 메인 액티비티의 메소드실행
                MainActivity mainActivity = new MainActivity();
                mainActivity.demiss(levelValue,levelValue2);
                Log.d(TAG,"특별시,도 : "+levelValue+" 시,구: "+levelValue2 );

            }
        });


    }
}
