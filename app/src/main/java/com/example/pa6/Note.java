package com.example.pa6;

public class Note {
    private String title;
    private String label;
    private String content;

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
}
