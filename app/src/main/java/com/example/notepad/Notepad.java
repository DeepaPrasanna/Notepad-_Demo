package com.example.notepad;

public class Notepad {
    public static final String TABLE_NAME = "Notepad";
    public static final String COLUMN_ID = "id";
    public static final String NOTE_TITLE = "title";
    public static final String NOTE_TEXT = "text";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOTE_TITLE + " TEXT, " +
            NOTE_TEXT + " TEXT)";

    public Notepad() {

    }
    public String title,text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
