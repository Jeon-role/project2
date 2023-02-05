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


public class StartGameCustomDialog extends Dialog {
    Button starte;



    public StartGameCustomDialog(final Context context){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        setContentView(R.layout.gamestartcustomdialog);
        starte = findViewById(R.id.btnstart);


        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==starte){
                    dismiss();
                }

            }
        };
        starte.setOnClickListener(click);



    }

}
