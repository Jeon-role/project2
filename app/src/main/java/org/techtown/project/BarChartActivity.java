package org.techtown.project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import org.techtown.project.databinding.ActivityBarchartBinding;
import java.util.ArrayList;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;


public class BarChartActivity extends AppCompatActivity {
    ActivityBarchartBinding barchartBinding;
    Realm realm;
    TimerDB timerDB;
    String TAG="BarChartActivity";
    String[] items={" 하루   "," 일주일  "}; //한달보류
    ArrayList<Long> dbdaylist = new ArrayList<>();
    ArrayList<Long> dbmonthlist = new ArrayList<>();
    ArrayList<Long> dboneweeklist = new ArrayList<>();
    float  list2;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        barchartBinding = DataBindingUtil.setContentView(this,R.layout.activity_barchart);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        savedb();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,items);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        barchartBinding.spinner.setAdapter(adapter);
        ValueFormatter coutom = new MyValueFormatter("초");


//        Legend l =barchartBinding.barChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(true);
////        l.setTypeface(tfLight);
//        l.setYOffset(0f);
//        l.setXOffset(10f);
//        l.setYEntrySpace(0f);
//        l.setTextSize(8f);



        YAxis leftAx = barchartBinding.barChart.getAxisLeft();
//        leftAx.setTypeface(tfLight);
        leftAx.setLabelCount(8,false);
        leftAx.setValueFormatter(coutom);
        leftAx.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAx.setSpaceTop(15f);
        leftAx.setAxisMinimum(0f);

        YAxis rightAx = barchartBinding.barChart.getAxisRight();
        rightAx.setLabelCount(8,false);
        rightAx.setValueFormatter(coutom);
        rightAx.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAx.setSpaceTop(15f);
        rightAx.setAxisMinimum(0f);



        barchartBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                barchartBinding.barChart.invalidate();
                barchartBinding.barChart.notifyDataSetChanged();
                barchartBinding.barChart.animateY(3000);
                if(items[position].equals(" 하루   ")){
                    ValueFormatter xAxisFormatter = new CountAxisValueFormatter(barchartBinding.barChart);
                    XAxis xAxis = barchartBinding.barChart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setDrawGridLines(false);
                    xAxis.setGranularity(1f);
                    xAxis.setLabelCount(dbdaylist.size());
                    xAxis.setValueFormatter(xAxisFormatter);
                    setData(dbdaylist.size(),170,1);
                }
                else if(items[position].equals(" 일주일  ")){

                    ValueFormatter xAxisFormatter2 = new CountAxisValueFormatter2(barchartBinding.barChart);
                    XAxis xAxis = barchartBinding.barChart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setDrawGridLines(false);
                    xAxis.setGranularity(1f);
                    xAxis.setLabelCount(7);
                    xAxis.setValueFormatter(xAxisFormatter2);

                    setData(7,1,7);
                }
//                else if(items[position].equals(" 한달  ")){           //보류
//                    setData(30,120,30);
//                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void setData(int count, float range,int type) {


        ArrayList<BarEntry> values = new ArrayList<>();

        if(type==1){
            for(int i=0; i<dbdaylist.size(); i++){
                float list= (float)dbdaylist.get(i);
                values.add(new BarEntry(i+1,list));
            }
        }
        else if(type==7){
            for(int i=0; i<dbdaylist.size(); i++){
                float list= dbdaylist.get(i);
                list2 += list;

            }
            float list3=list2/dbdaylist.size();
            values.add(new BarEntry(1,0));
            values.add(new BarEntry(2,0));
            values.add(new BarEntry(3,0));
            values.add(new BarEntry(4,0));
            values.add(new BarEntry(5,list3));
            values.add(new BarEntry(6,0));
            values.add(new BarEntry(7,0));


        }
        else if(type==30){

        }



        BarDataSet set1;
        if (barchartBinding.barChart.getData() != null && barchartBinding.barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barchartBinding.barChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            barchartBinding.barChart.getData().notifyDataChanged();
            barchartBinding.barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "성적");
            set1.setDrawIcons(false);
            int startColor1 = (this).getResources().getColor(android.R.color.holo_orange_light);
            int startColor2 = (this).getResources().getColor( android.R.color.holo_blue_light);
            int startColor3 = (this).getResources().getColor( android.R.color.holo_orange_light);
            int startColor4 = (this).getResources().getColor( android.R.color.holo_green_light);
            int startColor5 = (this).getResources().getColor( android.R.color.holo_red_light);
            int endColor1 = (this).getResources().getColor(android.R.color.holo_blue_dark);
            int endColor2 = (this).getResources().getColor( android.R.color.holo_purple);
            int endColor3 = (this).getResources().getColor( android.R.color.holo_green_dark);
            int endColor4 = (this).getResources().getColor( android.R.color.holo_red_dark);
            int endColor5 = (this).getResources().getColor( android.R.color.holo_orange_dark);

            List<GradientColor> gradientColors = new ArrayList<>();
            gradientColors.add(new GradientColor(startColor1, endColor1));
            gradientColors.add(new GradientColor(startColor2, endColor2));
            gradientColors.add(new GradientColor(startColor3, endColor3));
            gradientColors.add(new GradientColor(startColor4, endColor4));
            gradientColors.add(new GradientColor(startColor5, endColor5));

            set1.setGradientColors(gradientColors);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(7f);
            data.setBarWidth(0.6f);
//            Description des =barchartBinding.barChart.getDescription();
//            des.setEnabled(false);
            barchartBinding.barChart.setDescription(null);
            barchartBinding.barChart.setData(data);
            barchartBinding.barChart.setScaleEnabled(false);
            barchartBinding.barChart.setFitBars(true);
        }
    }
    public void savedb() {
        RealmResults<TimerDB> List = realm.where(TimerDB.class).findAll();
        for (TimerDB timerDB : List) {
            dbdaylist.add(timerDB.getTimer());
            Log.d(TAG, "ddddddd" + timerDB.getTimer());
        }
    }

    public void savedb2() {
        RealmResults<OneWeekDB> List = realm.where(OneWeekDB.class).findAll();
        for (OneWeekDB oneWeekDB : List) {
            dboneweeklist.add(oneWeekDB.getOneWeekTimer());
            Log.d(TAG, "ddddddd" + oneWeekDB.getOneWeekTimer());
        }
    }
}
