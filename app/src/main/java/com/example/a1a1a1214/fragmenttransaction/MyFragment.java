package com.example.a1a1a1214.fragmenttransaction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by a1a1a1214 on 2018-04-24.
 */

public class MyFragment extends Fragment {
    private static final String ARG_NO = "ARG_NO";

    public MyFragment()
    {

    }

    public static MyFragment getInstance(int no)
    {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NO,no);
        //초깃값을 설정한 Bundle을 setArguments()로 설정
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int no = getArguments().getInt(ARG_NO, 0);
        String text = "" + no + "번째 프래그먼트";
        Log.d("MyFragment", "onCreate " + text);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView) view.findViewById(R.id.text);
        //getArguments()를 통해 초깃값을 구함
        int no = getArguments().getInt(ARG_NO, 0);
        String text = "" + no + "번째 프래그먼트";
        Log.d("MyFragment", text);
        textView.setText(text);
    }
}
