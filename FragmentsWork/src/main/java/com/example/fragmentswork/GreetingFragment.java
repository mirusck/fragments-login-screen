package com.example.fragmentswork;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GreetingFragment extends Fragment {
    FragmentTransaction fTrans;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_greeting, null); //null only for dialogs, because there are no parent elem
        return inflater.inflate(R.layout.fragment_greeting, container, false); //@todo: replace others!
    }
    // other stuff to onActivityCreate !
}
