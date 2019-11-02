package com.example.pa6;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class NoteActLayout extends GridLayout {

    public NoteActLayout(final Context context){
        super(context);

        setColumnCount(2);

        EditText titleEditText = new EditText(context);
        GridLayout.LayoutParams titleLayoutParams = new GridLayout.LayoutParams();
        titleEditText.setHint("Note title");
        titleLayoutParams.width = LayoutParams.WRAP_CONTENT;
        titleLayoutParams.height = LayoutParams.WRAP_CONTENT;
        titleEditText.setLayoutParams(titleLayoutParams);
        addView(titleEditText);

        Spinner label = new Spinner(context);
        GridLayout.LayoutParams spinnerLayoutParams = new GridLayout.LayoutParams();
        spinnerLayoutParams.width = LayoutParams.WRAP_CONTENT;
        spinnerLayoutParams.height = LayoutParams.WRAP_CONTENT;
        label.setLayoutParams(spinnerLayoutParams);
        addView(label);

        List<String> spinnerOptions =  new ArrayList<String>();
        spinnerOptions.add("Personal");
        spinnerOptions.add("School");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                spinnerOptions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        label.setAdapter(adapter);


        EditText noteEditText = new EditText(context);
        GridLayout.LayoutParams noteLayoutParams = new GridLayout.LayoutParams();
        noteEditText.setHint("Note content");
        noteLayoutParams.width = LayoutParams.WRAP_CONTENT;
        noteLayoutParams.height = LayoutParams.WRAP_CONTENT;
        noteEditText.setLayoutParams(noteLayoutParams);
        addView(noteEditText);

    }
}
