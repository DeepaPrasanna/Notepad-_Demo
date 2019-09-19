package com.example.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class Dbhelper extends SQLiteOpenHelper {

    Context context;
    public static final String DATABASE_NAME = "Note.db";
    public static final int DATABASE_VERSION = 1;
//    public static final String TABLE_NAME = "Notes";
//    public static final String COLUMN_ID = "id";
//    public static final String NOTE_TITLE = "title";
//    public static final String NOTE_DESC = "content";
//
//    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
//            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            NOTE_TITLE + " TEXT," +
//            NOTE_DESC + " TEXT)";


    public Dbhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Notepad.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertNote(String title, String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Notepad.NOTE_TITLE, title);
        cv.put(Notepad.NOTE_DESC, text);

        long id = db.insert(Notepad.TABLE_NAME, null, cv);
        db.close();
        Toast.makeText(context, "Note added successfully", Toast.LENGTH_SHORT).show();
        return id;
    }

    public List<Notepad> getNotesList() {

        SQLiteDatabase db = this.getWritableDatabase();
        String GET_LIST = "SELECT * FROM " + Notepad.TABLE_NAME;

        Cursor cursor = db.rawQuery(GET_LIST, null);
        List<Notepad> list = new ArrayList<>();

        if (cursor.moveToNext()) {
            do {
                Notepad notepad = new Notepad();
                notepad.id = cursor.getString(cursor.getColumnIndex(Notepad.COLUMN_ID));
                notepad.title = cursor.getString(cursor.getColumnIndex(Notepad.NOTE_TITLE));
                notepad.text = cursor.getString(cursor.getColumnIndex(Notepad.NOTE_DESC));
                list.add(notepad);
            } while (cursor.moveToNext());


        }
        cursor.close();
        db.close();

        return list;


    }

    public int updateNote(String id, String title, String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Notepad.COLUMN_ID, id);
        cv.put(Notepad.NOTE_TITLE, title);
        cv.put(Notepad.NOTE_DESC, title);


        Toast.makeText(context, "Note Updated Successfully", Toast.LENGTH_LONG).show();
        db.close();
        return db.update(Notepad.TABLE_NAME, cv, Notepad.COLUMN_ID + " = ?", new String[]{Notepad.COLUMN_ID});


    }


    public void deleteNote(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Notepad.TABLE_NAME, Notepad.COLUMN_ID + " = ?", new String[]{Notepad.COLUMN_ID});
        db.close();

    }

}



















