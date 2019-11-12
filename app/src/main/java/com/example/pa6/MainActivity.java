package com.example.pa6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;


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
            arrayAdapter = new ArrayAdapter<Note>(
                    this,
                    android.R.layout.simple_list_item_1,
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
       /* Button newNoteButton = (Button) findViewById(R.id.newNoteButton);
        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_REQUEST_CODE);
            }
        });
        */
        //adapter for arrayList and listView
        noteListView = (ListView) findViewById(R.id.noteListView);
        arrayAdapter = new ArrayAdapter<Note>(
                this,
                android.R.layout.simple_list_item_1,
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
        /*
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
                                myNotes.remove(myNotes.get(position));
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null);
                alertBuilder.show();
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });
        */

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
                //needed for PA7
                //task: switch on menu item id... try to show(log or toast) the
                //indexes of the items that are checked
                switch (menuItem.getItemId()){
                    case R.id.deleteMenuItem:
                        //hint for pA7
                        String temp = noteListView.getCheckedItemPositions().toString();
                        Toast.makeText(MainActivity.this, temp, Toast.LENGTH_SHORT).show();
                        //myNotes.remove(myNotes.get(position)));
                        //arrayAdapter.notifyDataSetChanged();
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
        switch (id) {
            case R.id.addMenuItem:
                startEditItemActivity();
                return true; // we have consumed/handled this click event
            // task: finish remaining two cases
            case R.id.deleteMenuItem:
                Toast.makeText(this, "TODO: delete", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
