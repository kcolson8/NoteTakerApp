package com.example.pa6;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NoteOpenHelper extends  SQLiteOpenHelper {

        static final String DATABASE_NAME = "notesDatabase";
        static final int DATABASE_VERSION = 1;
        static final String TAG =  "PA7Tag";

        static final String TABLE_NOTES = "tableNotes";
        static final String ID  = "_id";
        static final String TITLE = "title";
        static final String LABEL = "label";
        static final String CONTENT = "content";
        static final String IMAGE_RESOURCE = "imageResource";

        public NoteOpenHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sqlCreate = "CREATE TABLE " + TABLE_NOTES + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TITLE + " TEXT, " +
                    LABEL + " TEXT, " +
                    CONTENT + " TEXT, " +
                    IMAGE_RESOURCE + " INTEGER)";
            Log.d(TAG, "onCreate: " + sqlCreate);
            db.execSQL(sqlCreate);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public void insertNote(Note note) {
            String sqlInsert = "INSERT INTO " + TABLE_NOTES + " VALUES(null, '" +
                    note.getTitle() + "', '" + note.getLabel() +
                    "', '" + note.getContent() + "', '" + note.getImageResourceId() + "')";
            SQLiteDatabase db = getWritableDatabase();
            Log.d(TAG, "insertNote: " + sqlInsert);
            db.execSQL(sqlInsert);
            db.close();
        }

        public Cursor getSelectAllNotesCursor(){
            String sqlSelect = "SELECT * FROM " + TABLE_NOTES;

            SQLiteDatabase db = getReadableDatabase();
            Log.d(TAG, "getSelectAllNotesCursor: " + sqlSelect);

            Cursor cursor = db.rawQuery(sqlSelect, null);
            return cursor;
        }

        public Note getSelectOneNoteCursor(long id){
            String sqlSelect = "SELECT * FROM " + TABLE_NOTES +
                    " WHERE " + ID + "=" + id;

            SQLiteDatabase db = getReadableDatabase();
            Log.d(TAG, "getSelectOneNoteCursor: " + sqlSelect);

            Cursor cursor = db.rawQuery(sqlSelect, null);

            Note note = new Note();
            while(cursor.moveToNext()) {
                String title = cursor.getString(1);
                String label = cursor.getString(2);
                String content = cursor.getString(3);
                int imageResource = cursor.getInt(4);
                note = new Note(title, label, content, imageResource);
            }
            return note;
        }

        public void updateNoteById(int id, Note newNote){
            String sqlUpdate = "UPDATE " + TABLE_NOTES + " SET " + TITLE + "='" +
                    newNote.getTitle() + "', " + LABEL + "='" +
                    newNote.getLabel() + "', " + CONTENT + "='" +
                    newNote.getContent() + "' WHERE " + ID + "=" + id;
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sqlUpdate);
            db.close();
        }

        public void deleteNote(int id){
            String sqlDelete = "DELETE FROM " + TABLE_NOTES +
                    " WHERE " + ID + "=" + id;
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sqlDelete);
            db.close();
        }

        public void deleteAllNotes(){
            String sqlDelete = "DELETE FROM " + TABLE_NOTES;
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sqlDelete);
            db.close();
        }
}
