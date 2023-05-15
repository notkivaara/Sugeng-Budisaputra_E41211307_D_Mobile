package com.example.acara18_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class  MainActivity extends AppCompatActivity {
    Button btnFirstFragment, btnSecondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFirstFragment = (Button) findViewById(R.id.firstFragment);
        btnSecondFragment = (Button) findViewById(R.id.secondFragment);
        btnFirstFragment.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                loadFragment(new FirstFragment());
            }
        });
        btnSecondFragment.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                loadFragment(new SecondFragment());
            }
        });
    }

    private void loadFragment(Fragment fragment){
//        create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();

//        create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

//      replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit(); // save the changes
    }
}