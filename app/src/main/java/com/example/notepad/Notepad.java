package com.example.notepad;

import android.os.Parcel;
import android.os.Parcelable;

public class Notepad implements Parcelable {
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
    protected Notepad(Parcel in) {
        id = in.readInt();
        title = in.readString();
        text = in.readString();
    }
    public String title,text;
    private int id;

    public static final Creator<Notepad> CREATOR = new Creator<Notepad>() {
        @Override
        public Notepad createFromParcel(Parcel in) {
            return new Notepad(in);
        }

        @Override
        public Notepad[] newArray(int size) {
            return new Notepad[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
     parcel.writeInt(id);
     parcel.writeString(title);
     parcel.writeString(text);
    }
}
