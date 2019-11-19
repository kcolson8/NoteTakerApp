package com.example.pa6;

import androidx.annotation.NonNull;

public class Note {
    public int id;
    private String title;
    private String label;
    private String content;
    private int imageResourceId;

    public Note(){
        this.id = -1;
        this.title = "TITLE";
        this.label = "LABEL";
        this.content = "CONTENT";
        this.imageResourceId = -1;
    }

    //Note constructor sets id, title, label, and note content
    public Note(int id, String title, String label, String content) {
        this.id = id;
        this.title = title;
        this.label = label;
        this.content = content;
    }

    public Note(String title, String label, String content) {
        this.id = id;
        this.title = title;
        this.label = label;
        this.content = content;
    }

    public Note(String title, String label, String content, int imageResourceId) {
        this.title = title;
        this.label = label;
        this.content = content;
        this.imageResourceId = imageResourceId;
    }

    public Note(int id, String title, String label, String content, int imageResourceId){
        this.id = id;
        this.title = title;
        this.label = label;
        this.content = content;
        this.imageResourceId = imageResourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    //Overridden toString() method returns the title of a note
    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
