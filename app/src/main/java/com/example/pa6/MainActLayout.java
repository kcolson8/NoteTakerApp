package com.example.pa6;

import android.content.Context;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActLayout extends GridLayout {

    public MainActLayout(final Context context){
        super(context);

        setColumnCount(1);


        //ListView component
        ListView noteListView = new ListView(context);
        noteListView.setId(R.id.noteListView);

        //listview layout
        GridLayout.LayoutParams listViewParams = new GridLayout.LayoutParams();
        listViewParams.height = LayoutParams.MATCH_PARENT;
        listViewParams.width = LayoutParams.MATCH_PARENT;
        noteListView.setLayoutParams(listViewParams);
        addView(noteListView);
    }
}
