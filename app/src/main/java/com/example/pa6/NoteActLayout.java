package com.example.pa6;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class NoteActLayout extends GridLayout {

    public NoteActLayout(final Context context){
        super(context);

        setColumnCount(2);

        //Title EditText component
        EditText titleEditText = new EditText(context);
        titleEditText.setHint("Note title");
        titleEditText.setId(R.id.titleEditText);
        GridLayout.Spec titleRowSpec = GridLayout.spec(0,1,1);
        GridLayout.Spec titleColSpec = GridLayout.spec(0,1,5);
        GridLayout.LayoutParams titleLayoutParams = new GridLayout.LayoutParams(titleRowSpec, titleColSpec);
        titleEditText.setLayoutParams(titleLayoutParams);
        addView(titleEditText);

        //Spinner component
        Spinner label = new Spinner(context);
        label.setId(R.id.labelSpinner);
        GridLayout.Spec labelRowSpec = GridLayout.spec(0,1,1);
        GridLayout.Spec labelColSpec = GridLayout.spec(1,1,1);
        GridLayout.LayoutParams spinnerLayoutParams = new GridLayout.LayoutParams(labelRowSpec, labelColSpec);
        label.setLayoutParams(spinnerLayoutParams);
        addView(label);

        List<String> spinnerOptions =  new ArrayList<String>();
        spinnerOptions.add("Personal");
        spinnerOptions.add("School");
        spinnerOptions.add("Work");
        spinnerOptions.add("Other");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                spinnerOptions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        label.setAdapter(adapter);

        //Content EditText component
        EditText noteEditText = new EditText(context);
        noteEditText.setHint("Note content");
        noteEditText.setGravity(getTop());
        noteEditText.setId(R.id.contentEditText);
        GridLayout.Spec noteRowSpec = GridLayout.spec(1,1,10);
        GridLayout.Spec noteColSpec = GridLayout.spec(0,2, 1);
        GridLayout.LayoutParams noteLayoutParams = new GridLayout.LayoutParams(noteRowSpec, noteColSpec);

        noteEditText.setLayoutParams(noteLayoutParams);

        addView(noteEditText);

        //Button component
        Button doneButton = new Button(context);
        doneButton.setId(R.id.doneButton);
        doneButton.setText("Done");
        GridLayout.Spec buttonRowSpec = GridLayout.spec(2,1,0);
        GridLayout.Spec buttonColSpec = GridLayout.spec(0,2,1);
        GridLayout.LayoutParams buttonLayoutPararms = new GridLayout.LayoutParams(buttonRowSpec, buttonColSpec);
        doneButton.setLayoutParams(buttonLayoutPararms);

        addView(doneButton);
    }
}
