package com.example.pa6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

static  final int NEW_NOTE_REQUEST_CODE = 1;
static final int EDIT_NOTE_REQUEST_CODE = 2;
List <Note> myNotes = new ArrayList<Note>();
ArrayAdapter<Note> arrayAdapter;
ListView noteListView;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //adds note contents from NoteActivity to be displayed in ListView
        if((requestCode == NEW_NOTE_REQUEST_CODE || requestCode == EDIT_NOTE_REQUEST_CODE) && resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String label = data.getStringExtra("label");
            String noteContent = data.getStringExtra("noteContent");

            myNotes.add(new Note(title, label, noteContent));

            noteListView = (ListView) findViewById(R.id.noteListView);
            ArrayAdapter<Note> arrayAdapter = new ArrayAdapter<Note>(
                    this,
                    android.R.layout.simple_list_item_1, // the view for each item as a row in the listview
                    myNotes // data source
            );
            noteListView.setAdapter(arrayAdapter);
       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MainActLayout myGridLayout = new MainActLayout(this);
        setContentView(myGridLayout);

        //Click listener for button, if clicked, takes user to new screen to create a note
        Button newNoteButton = (Button) findViewById(R.id.newNoteButton);
        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_REQUEST_CODE);
            }
        });

        //adapter for arrayList and listView
        noteListView = (ListView) findViewById(R.id.noteListView);
        arrayAdapter = new ArrayAdapter<Note>(
                this,
                android.R.layout.simple_list_item_1, // the view for each item as a row in the listview
                myNotes // data source
        );
        noteListView.setAdapter(arrayAdapter);

        //When item in listView is clicked, the app opens NoteActivity to exit the existing note
        //that the user clicked on
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("title", selection);
                intent.putExtra("label", myNotes.get(position).getLabel());
                intent.putExtra("content", myNotes.get(position).getContent());
                myNotes.remove(myNotes.get(position));
                startActivityForResult(intent, EDIT_NOTE_REQUEST_CODE);
            }
        });

        //When item in listView is long clicked, alert dialog pops up to ask user if they would like
        //to delete the selected item or not
        noteListView.setLongClickable(true);
        noteListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                final String selection = parent.getItemAtPosition(position).toString();
                final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setTitle("Delete a note")
                        .setMessage("Would you like to delete your note titled '" + selection + "'?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO
                                //myNotes.remove(which); //crashes app
                                myNotes.remove(myNotes.get(position));
                                //arrayAdapter.remove(myNotes.get(position)); //crashes app
                                arrayAdapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "removed note titled '" + selection + "'", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", null);
                alertBuilder.show();
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });
        arrayAdapter.notifyDataSetChanged();

    }
}
