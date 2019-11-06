package com.example.pa6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoteActLayout noteLayout = new NoteActLayout(this);
        setContentView(noteLayout);

        Button doneButton = (Button) findViewById(R.id.doneButton);
        final EditText titleEditText = (EditText) findViewById(R.id.titleEditText);
        final Spinner labelSpinner = (Spinner) findViewById(R.id.labelSpinner);
        final EditText contentEditText = (EditText) findViewById(R.id.contentEditText);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                    intent.putExtra("title",titleEditText.getText().toString());
                    intent.putExtra("label", labelSpinner.getSelectedItem().toString());
                    intent.putExtra("noteContent", contentEditText.getText().toString());
                    setResult(Activity.RESULT_OK, intent);

                NoteActivity.this.finish();
            }
        });
    }
}
