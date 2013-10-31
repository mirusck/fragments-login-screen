package com.example.fragmentswork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        String[] listTitles = getResources().getStringArray(R.array.list_titles);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, listTitles);
        listView.setAdapter(adapter);
        return view;
//        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
//@todo: min height 48dip of each list item
