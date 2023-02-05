package org.techtown.project;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class TestCustomDialog extends Dialog {
    Button btnTestConfirm;
    TextView textViewScore;
    TextView testTextView;
    String testFalse = "치매가 아닐 확률이 94%";
    String testTrue = "치매 증상에 해달할 확률이 87% , 치매전문병원 검진 권장";
    public TestCustomDialog(Context context,int score){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        setContentView(R.layout.testcustomdialog);
        btnTestConfirm = findViewById(R.id.btnTestConfirm);
        textViewScore = findViewById(R.id.textViewScore);
        testTextView = findViewById(R.id.testTextView);
        if(score>=25){
            testTextView.setText(testTrue);
        }
        else{
            testTextView.setText(testFalse);
        }
        textViewScore.setText((String.valueOf(score))+"점");


        btnTestConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


    }
}
