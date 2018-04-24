package com.example.a1a1a1214.fragmenttransaction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    private static final String KEY_NUMBER = "KEY_NUMBER";
    private int mNumber = 0;
    //TODO 한번 더 볼것1
    private FragmentManager.OnBackStackChangedListener mListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            FragmentManager fragmentManager = getSupportFragmentManager();
            int count = 0;
            for (Fragment f: fragmentManager.getFragments()) {
                if (f != null) {
                    count++;
                }
            }
            mNumber = count;
            Log.d("MainActivity", "onBackStackChanged mNumber=" + mNumber);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //추가
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "추가 버튼 클릭", Toast.LENGTH_SHORT).show();
                //Import android.support.v4.app.FragmentManager
                FragmentManager fragmentManager = getSupportFragmentManager();
                //트랜잭션 생성
                fragmentManager.beginTransaction().add(R.id.fragment_container, MyFragment.getInstance(mNumber))
                        //프래그먼트 생성
                    .addToBackStack(null) //백스택에 추가
                .commit(); //적용
            }
        });

        //삭제
        findViewById(R.id.remove_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "삭제 버튼 클릭", Toast.LENGTH_SHORT).show();
                if(mNumber == 0)
                {
                    return;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStack(); //백스택에서 꺼내서 이전 상태로 돌아간다.
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(mListener);
        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        Log.d("MainActivity", "onCreate fragment=" + fragment + ", mNumber=" + mNumber);
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, MyFragment.getInstance(mNumber), FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit();
        }
    }

    //TODO 한번 더 볼것2
    //region Check
    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.removeOnBackStackChangedListener(mListener);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NUMBER, mNumber);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mNumber = savedInstanceState.getInt(KEY_NUMBER);
    }
    //endregion
}
