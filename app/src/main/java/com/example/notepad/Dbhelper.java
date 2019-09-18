package com.example.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.example.notepad.Notepad.NOTE_TEXT;
import static com.example.notepad.Notepad.TABLE_NAME;

public class Dbhelper extends SQLiteOpenHelper {
    Context context;
    private static final String DATABASE_NAME = "Note.db";
    private static final int DATABASE_VERSION = 1;

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
        cv.put(Notepad.NOTE_TEXT, text);

        long id = db.insert(TABLE_NAME, null, cv);
        db.close();
        Toast.makeText(context, "Note added successfully", Toast.LENGTH_SHORT).show();
        return id;
    }

    public List<Notepad> getNotesList() {
        List<Notepad> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String GET_LIST = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(GET_LIST, null);

        if (cursor.moveToNext()) {
            do {
                Notepad notepad = new Notepad();
                notepad.setTitle(cursor.getString(cursor.getColumnIndex(Notepad.NOTE_TITLE)));
                notepad.setTitle(cursor.getString(cursor.getColumnIndex(NOTE_TEXT)));
                list.add(notepad);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();

        }
        return list;


    }

    public int updateNote(Notepad notepad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Notepad.NOTE_TITLE, notepad.getTitle());
        cv.put(NOTE_TEXT, notepad.getText());

        Toast.makeText(context,"Note Updated Successfully",Toast.LENGTH_LONG).show();
        return db.update(Notepad.TABLE_NAME, cv, Notepad.COLUMN_ID + "= ?", new String[]{String.valueOf(Notepad.COLUMN_ID)});

    }


    public  void deleteNote(Notepad notepad)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Notepad.TABLE_NAME,Notepad.COLUMN_ID +" =? ",new String[]{String.valueOf(Notepad.COLUMN_ID)});
        db.close();

    }

}



