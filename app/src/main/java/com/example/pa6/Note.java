package com.example.pa6;

import androidx.annotation.NonNull;

public class Note {
    private String title;
    private String label;
    private String content;

    //Note constructor sets title, label, and note content
    public Note(String title, String label, String content) {
        this.title = title;
        this.label = label;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //Overridden toString() method returns the title of a note
    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
