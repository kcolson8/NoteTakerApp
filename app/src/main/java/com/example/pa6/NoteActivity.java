package com.example.pa6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    int id;
    static final String TAG = "NATag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoteActLayout noteLayout = new NoteActLayout(this);
        setContentView(noteLayout);

        //gets reference to layout components
        Button doneButton = (Button) findViewById(R.id.doneButton);
        final EditText titleEditText = (EditText) findViewById(R.id.titleEditText);
        final Spinner labelSpinner = (Spinner) findViewById(R.id.labelSpinner);
        final EditText contentEditText = (EditText) findViewById(R.id.contentEditText);

        //grabs intent when editing a note to set title, label, and content as user last set it
        Intent intent = getIntent();
        if(intent != null){
            id = intent.getIntExtra("id",0);
            String title = intent.getStringExtra("title");
            String label = intent.getStringExtra("label");
            String content = intent.getStringExtra("content");
            titleEditText.setText(title);
            ArrayAdapter myAdap = (ArrayAdapter) labelSpinner.getAdapter(); //cast to an ArrayAdapter
            labelSpinner.setSelection(myAdap.getPosition(label));
            contentEditText.setText(content);
        }

        //Sends note content back to MainActivity when done button is pressed
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks if note doesn't have a title set and notifies user via Toast message
                if(titleEditText.getText().toString().equals("")){
                    Toast.makeText(NoteActivity.this, "Missing title", Toast.LENGTH_SHORT).show();
                }
                //Checks if note doesn't have note content entered and notifies user via Toast message
                else if (contentEditText.getText().toString().equals("")){
                    Toast.makeText(NoteActivity.this, "Missing note content", Toast.LENGTH_SHORT).show();
                } else { //both the note title and content have been entered
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    Log.d(TAG, "returning id: " + id);
                    intent.putExtra("title", titleEditText.getText().toString());
                    intent.putExtra("label", labelSpinner.getSelectedItem().toString());
                    intent.putExtra("noteContent", contentEditText.getText().toString());
                    setResult(Activity.RESULT_OK, intent);
                    NoteActivity.this.finish();
                }
            }
        });
    }
}
