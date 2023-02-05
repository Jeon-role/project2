package org.techtown.project;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TestFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    public TestFragment() {

    }
    public static TestFragment newInstance(String param1) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.linearLayoutImg);
        linearLayout.bringToFront();
        TextView textView = view.findViewById(R.id.testfInfo);
        textView.setBackgroundColor(Color.rgb(102,102,153));
        textView.setText("\n"+mParam1);
        return view;
    }







}
