package com.example.pa6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class MainActivity extends AppCompatActivity {

static  final int NEW_NOTE_REQUEST_CODE = 1;
static final int EDIT_NOTE_REQUEST_CODE = 2;
SimpleCursorAdapter cursorAdapter;
ListView noteListView;

static final String TAG =  "MATag";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //adds note contents from NoteActivity to be displayed in ListView
        if(requestCode == NEW_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String label = data.getStringExtra("label");
            String noteContent = data.getStringExtra("noteContent");

            NoteOpenHelper openHelper = new NoteOpenHelper(this);
            openHelper.insertNote(new Note(title, label, noteContent));

            noteListView = (ListView) findViewById(R.id.noteListView);

            cursorAdapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_list_item_activated_1,
                    openHelper.getSelectAllNotesCursor(),
                    new String[] {NoteOpenHelper.TITLE}, //first column in database
                    new int[] {android.R.id.text1}, //id of text view to put data into
                    0
            );
            noteListView.setAdapter(cursorAdapter);
        } //updates an existing note that was edited
        else if(requestCode == EDIT_NOTE_REQUEST_CODE && resultCode == RESULT_OK){
            int noteId = data.getIntExtra("id",0);
            String title = data.getStringExtra("title");
            String label = data.getStringExtra("label");
            String noteContent = data.getStringExtra("noteContent");

            NoteOpenHelper openHelper = new NoteOpenHelper(this);
            openHelper.updateNoteById(noteId, new Note(title, label, noteContent));

            noteListView = (ListView) findViewById(R.id.noteListView);

            cursorAdapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_list_item_activated_1,
                    openHelper.getSelectAllNotesCursor(),
                    new String[] {NoteOpenHelper.TITLE}, //first column in database
                    new int[] {android.R.id.text1}, //id of text view to put data into
                    0
            );
            noteListView.setAdapter(cursorAdapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MainActLayout myGridLayout = new MainActLayout(this);
        setContentView(myGridLayout);

        noteListView = (ListView) findViewById(R.id.noteListView);
        final NoteOpenHelper openHelper = new NoteOpenHelper(this);

        //adapter for cursor and listView
        cursorAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_activated_1,
                openHelper.getSelectAllNotesCursor(),
                new String[] {NoteOpenHelper.TITLE}, //first column in database
                new int[] {android.R.id.text1}, //id of text view to put data into
                0
        );
        noteListView.setAdapter(cursorAdapter);

        //When item in listView is clicked, the app opens NoteActivity to exit the existing note
        //that the user clicked on
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int noteId = (int) noteListView.getItemIdAtPosition(position);
                Log.d(TAG, "id: " + noteId);

                Intent intent = new Intent(MainActivity.this, NoteActivity.class);

                Note selectedNote = openHelper.getSelectOneNoteCursor(id);

                intent.putExtra("id", noteId);
                intent.putExtra("title", selectedNote.getTitle());
                intent.putExtra("label", selectedNote.getLabel());
                intent.putExtra("content", selectedNote.getContent());
                //openHelper.deleteNote(noteId);
                startActivityForResult(intent, EDIT_NOTE_REQUEST_CODE);
            }
        });

        noteListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        noteListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                int numChecked = noteListView.getCheckedItemCount();
                actionMode.setTitle(numChecked + " selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.cam_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                //dont need this for PA7
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                //executes when user clicks a cam menu item
                noteListView = (ListView) findViewById(R.id.noteListView);

                switch (menuItem.getItemId()){
                    case R.id.deleteMenuItem:
                        SparseBooleanArray selectedItems = noteListView.getCheckedItemPositions();
                        Log.d(TAG, "selected items size: " + selectedItems.size());
                        for(int i = 0; i < selectedItems.size(); i++){
                            int noteId = (int) noteListView.getItemIdAtPosition(selectedItems.keyAt(i));
                            openHelper.deleteNote(noteId);
                        }
                        cursorAdapter = new SimpleCursorAdapter(
                                MainActivity.this,
                                android.R.layout.simple_list_item_activated_1,
                                openHelper.getSelectAllNotesCursor(),
                                new String[] {NoteOpenHelper.TITLE}, //first column in database
                                new int[] {android.R.id.text1}, //id of text view to put data into
                                0
                        );
                        noteListView.setAdapter(cursorAdapter);
                        cursorAdapter.notifyDataSetChanged();

                        actionMode.finish(); //exit cam
                        return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                //don't need this for PA7
            }
        });
    }

    private void startEditItemActivity() {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivityForResult(intent, NEW_NOTE_REQUEST_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // get a reference to the MenuInflater
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // override a callback that executes whenever an options menu action is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        noteListView = (ListView) findViewById(R.id.noteListView);
        NoteOpenHelper openHelper = new NoteOpenHelper(this);

        switch (id) {
            case R.id.addMenuItem:
                startEditItemActivity();
                return true;
            case R.id.deleteMenuItem:
                //delete all note items when main menu delete is hit

                final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setTitle("Delete all")
                        .setMessage("Are you sure you want to delete all notes?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                NoteOpenHelper openHelper = new NoteOpenHelper(MainActivity.this);
                                openHelper.deleteAllNotes();
                                cursorAdapter = new SimpleCursorAdapter(
                                        MainActivity.this,
                                        android.R.layout.simple_list_item_activated_1,
                                        openHelper.getSelectAllNotesCursor(),
                                        new String[] {NoteOpenHelper.TITLE}, //first column in database
                                        new int[] {android.R.id.text1}, //id of text view to put data into
                                        0
                                );
                                noteListView.setAdapter(cursorAdapter);
                                cursorAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null);
                alertBuilder.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

