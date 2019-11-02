package com.example.pa6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoteActLayout noteLayout = new NoteActLayout(this);
        setContentView(noteLayout);
    }
}
