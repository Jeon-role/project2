package org.techtown.project;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


public class CardRuleCustomDialog extends Dialog {
    Button btnStart;
    CheckBox checkTarot;
    CheckBox checkFruit;
    CheckBox checkFlag;
    CheckBox checkHigh;
    CheckBox checkMedium;
    CheckBox checkLow;
    Intent intent;
    int confirm=0;
    String googleName;


    public CardRuleCustomDialog(final Context context){                //카드의 카테고리와 난이도를 선택하는 다이얼로그
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        setContentView(R.layout.cardrulecustomdialog);
        btnStart = findViewById(R.id.btnStart);
        checkTarot = findViewById(R.id.checkTarot);
        checkFruit = findViewById(R.id.checkFruit);
        checkFlag = findViewById(R.id.checkFlag);
        checkHigh = findViewById(R.id.checkHigh);
        checkMedium = findViewById(R.id.checkMedium);
        checkLow = findViewById(R.id.checkLow);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==checkTarot){
                    checkFruit.setChecked(false);
                    checkFlag.setChecked(false);
                }
                else if(v==checkFruit){
                    checkTarot.setChecked(false);
                    checkFlag.setChecked(false);
                }
                else if(v==checkFlag){
                    checkTarot.setChecked(false);
                    checkFruit.setChecked(false);
                }
                else if(v==checkHigh){
                    checkMedium.setChecked(false);
                    checkLow.setChecked(false);
                }
                else if(v==checkMedium){
                    checkHigh.setChecked(false);
                    checkLow.setChecked(false);
                }
                else if(v==checkLow){
                    checkHigh.setChecked(false);
                    checkMedium.setChecked(false);
                }
            }
        };

        checkTarot.setOnClickListener(click);
        checkFruit.setOnClickListener(click);
        checkFlag.setOnClickListener(click);
        checkHigh.setOnClickListener(click);
        checkMedium.setOnClickListener(click);
        checkLow.setOnClickListener(click);







        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context,CardActivity.class);
                if(confirm==0){
                    intent.putExtra("이름",googleName);
                    if(checkTarot.isChecked()){
                        Toast.makeText(context,"카테고리 : 타로",Toast.LENGTH_SHORT).show();
                        intent.putExtra("카테고리","타로");
                    }
                    else if(checkFruit.isChecked()){
                        intent.putExtra("카테고리","동물");
                    }
                    else if(checkFlag.isChecked()){
                        intent.putExtra("카테고리","국기");
                    }
                    else{
                        confirm=1;
                        Toast.makeText(context,"카테고리 : 아무것도",Toast.LENGTH_SHORT).show();
                    }
                }
                if(confirm==0){
                    if(checkHigh.isChecked()){
                        Toast.makeText(context,"난이도 : 상",Toast.LENGTH_SHORT).show();
                        intent.putExtra("난이도","상");
                    }
                    else if(checkMedium.isChecked()){
                        intent.putExtra("난이도","중");
                    }
                    else if(checkLow.isChecked()){
                        intent.putExtra("난이도","하");
                    }
                    else {
                        confirm=1;
                        Toast.makeText(context,"난이도 : 아무것도",Toast.LENGTH_SHORT).show();
                    }
                }
                if(confirm==0){
                    context.startActivity(intent);
                }
                dismiss();
            }
        });


    }
    public void googleName(String name){
        googleName=name;
        Log.d("CardRuleCustom","googlename : "+googleName);
    }
}
