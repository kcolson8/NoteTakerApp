package com.example.pa6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

static  final int NOTE_REQUEST_CODE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(MainActivity.this, "onActivityResult called", Toast.LENGTH_SHORT).show();
        if(requestCode == NOTE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String title = data.getStringExtra("title");
            String label = data.getStringExtra("label");
            String noteContent = data.getStringExtra("noteContent");

            Toast.makeText(MainActivity.this, "title:" + title, Toast.LENGTH_SHORT).show();

            List<Note> myNotes = new ArrayList<Note>();
            myNotes.add(new Note(title, label, noteContent));

            ListView noteListView = (ListView) findViewById(R.id.noteListView);
            ArrayAdapter<Note> arrayAdapter = new ArrayAdapter<Note>(
                    this,
                    android.R.layout.simple_list_item_1, // the view for each item as a row in the listview
                    myNotes // data source
            );
            noteListView.setAdapter(arrayAdapter);

       } else {
            //Toast.makeText(MainActivity.this, "no content", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActLayout myGridLayout = new MainActLayout(this);
        setContentView(myGridLayout);

        Button newNoteButton = (Button) findViewById(R.id.newNoteButton);
        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(intent);
            }
        });


    }


}
