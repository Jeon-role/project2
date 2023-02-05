package org.techtown.project;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import org.techtown.project.databinding.ActivityTestBinding;


public class TestActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    ActivityTestBinding testBinding;
    TestCustomDialog testCustomDialog;
    int score;
    DisplayMetrics dm;
    int width;
    int height;
    int i=0;
    int btn_check=0;
    String TAG  = "TestActivity";

    FragmentTransaction fragmentTransaction;
    String[] testMessage = {"1. 전화번호나 사람이름을 기억하기 힘들다", "2.어떤 일이 언제 일어났는지 기억하지 못할때가 있다", "3.며칠 전에 들었던 이야기를 잊는다", "4.오래전부터 들었던 이야기를 잊는다",
            "5.반복되는 일상생활에 변화가 생겼을 때 금방 적응하기가 힘들다", "6. 본인에게 중요한 사항을 잊을때가 있다(예를 들어 배우자 생일,결혼기념일 등)", "7.다른사람에게 같은 이야기를 반복 할때가 있다", "8.어떤 일을 해놓고 잊을 때가 있다",
            "9.약속을 해놓고 잊을 때가 있다", "10.이야기도중  자기가 무슨 이야기를 하고있었는지를 잊을때가 있다", "11.약먹는 시간을 놓치기도 한다", "12.여러 가지 물건을 사러 갔다가 한 두가지를 빠뜨리기도 한다", "13.가스불을 끄는 것을 잊어 버린적이 있다  또는 음식을 태운 적이 있다",
            "14.남에게 같은 질문을 반복한다", "15.어떤일을 해놓고도 했는지 안했는지 몰라 다시 확인해야 한다", "16.물건을 두고 다니거나 또는 가지고 갈 물건을 놓고 간다", "17.하고 싶은 말이나 표현이 금방 떠오르지 않는다", "18.물건 이름이 금방 생각 나지 않는다",
            "19.개인적인 편지나 사무적인 편지를 쓰기 힘들다", "20.갈수록 말수가 감소되는 경향이 있다"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testBinding = DataBindingUtil.setContentView(this,R.layout.activity_test);
        dm = getApplicationContext().getResources().getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction  = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.frameLayout,TestFragment.newInstance(testMessage[0]));
        fragmentTransaction.commit();





        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==testBinding.backImage){
                    finish();
                }
                else if (v == testBinding.btnTestAlways) {
                    btn_check = 1;
                    testBinding.btnTestAlways.setBackground(ContextCompat.getDrawable(TestActivity.this, R.drawable.custom_button_yellow));
                    testBinding.btnTestYes.setBackground(ContextCompat.getDrawable(TestActivity.this, R.drawable.custom_button));
                    testBinding.btnTestNo.setBackground(ContextCompat.getDrawable(TestActivity.this, R.drawable.custom_button));
                }
                else if(v==testBinding.btnTestYes){
                    btn_check=2;
                    testBinding.btnTestAlways.setBackground(ContextCompat.getDrawable(TestActivity.this,R.drawable.custom_button));
                    testBinding.btnTestYes.setBackground(ContextCompat.getDrawable(TestActivity.this,R.drawable.custom_button_yellow));
                    testBinding.btnTestNo.setBackground(ContextCompat.getDrawable(TestActivity.this,R.drawable.custom_button));

                }
                else if(v==testBinding.btnTestNo){
                    btn_check=3;
                    testBinding.btnTestAlways.setBackground(ContextCompat.getDrawable(TestActivity.this,R.drawable.custom_button));
                    testBinding.btnTestYes.setBackground(ContextCompat.getDrawable(TestActivity.this,R.drawable.custom_button));
                    testBinding.btnTestNo.setBackground(ContextCompat.getDrawable(TestActivity.this,R.drawable.custom_button_yellow));
                }
                else if (v == testBinding.btnResult) {
                    i++;
                    Log.d(TAG,"i : "+i);
                    testBinding.btnTestAlways.setBackground(ContextCompat.getDrawable(TestActivity.this,R.drawable.custom_button));
                    testBinding.btnTestYes.setBackground(ContextCompat.getDrawable(TestActivity.this,R.drawable.custom_button));
                    testBinding.btnTestNo.setBackground(ContextCompat.getDrawable(TestActivity.this,R.drawable.custom_button));
                    if(btn_check != 0){
                        if(btn_check==1){
                            score =score+2;
                        }
                        else if(btn_check==2){
                            score =score+1;
                        }
                        else if (btn_check==3) {
                            score =score+0;
                        }

                        btn_check=0;
                        if (i < testMessage.length) {
                            fragmentTransaction=fragmentManager.beginTransaction();
                            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right);
                            fragmentTransaction.replace(R.id.frameLayout, TestFragment.newInstance(testMessage[i]));
                            fragmentTransaction.commit();
                            if(i==testMessage.length-1){
                                testBinding.btnResult.setText("결과보기");
                            }
                            testBinding.textViewCount.setText(String.valueOf(i+1));
                        }
                        if(i==testMessage.length+1){
                            btnenable();
                        }
                        else if (i == testMessage.length) {
                            Toast.makeText(TestActivity.this, String.valueOf(score), Toast.LENGTH_LONG).show();
                            testCustomDialog = new TestCustomDialog(TestActivity.this, score);
                            WindowManager.LayoutParams vm = testCustomDialog.getWindow().getAttributes();
                            vm.copyFrom(testCustomDialog.getWindow().getAttributes());
                            vm.width = width / 2;
                            vm.height = height / 3;
                            testCustomDialog.show();
                            score=0;
                        }
                    }
                    else{
                        if(i==testMessage.length+1){
                            btnenable();
                        }
                        else{
                            i--;
                            Log.d(TAG,"i : "+i);
                            Toast.makeText(TestActivity.this, "아무것도 선택하지 않았습니다", Toast.LENGTH_LONG).show();
                        }
                    }

                }


            }
        };
        testBinding.backImage.setOnClickListener(click);
        testBinding.btnResult.setOnClickListener(click);
        testBinding.btnTestAlways.setOnClickListener(click);
        testBinding.btnTestYes.setOnClickListener(click);
        testBinding.btnTestNo.setOnClickListener(click);
    }

    public void btnenable(){           //버튼을 비활성화하는 메소드
        testBinding.btnTestAlways.setEnabled(false);
        testBinding.btnTestYes.setEnabled(false);
        testBinding.btnTestNo.setEnabled(false);
        testBinding.btnResult.setEnabled(false);
        Toast.makeText(TestActivity.this, "버튼 비활성화", Toast.LENGTH_LONG).show();
        Log.d(TAG,"버튼 비활성화");
    }

}
