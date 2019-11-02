package com.example.pa6;

import android.content.Context;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActLayout extends GridLayout {

    public MainActLayout(final Context context){
        super(context);

        setColumnCount(1);

        Button addNoteButton = new Button(context);
        addNoteButton.setId(R.id.newNoteButton);
        addNoteButton.setText("Add new note");

        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.height = LayoutParams.WRAP_CONTENT;
        layoutParams.width = LayoutParams.MATCH_PARENT;
        addNoteButton.setLayoutParams(layoutParams);

        addView(addNoteButton);
    }
}
