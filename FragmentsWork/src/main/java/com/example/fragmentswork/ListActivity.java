package com.example.fragmentswork;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class ListActivity extends FragmentActivity {

    FragmentTransaction fTrans;
    MainFragment fList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        fList = new MainFragment();
        if ( savedInstanceState == null ) {
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.add(R.id.container, fList);
            fTrans.commit();
        }


    }


}
