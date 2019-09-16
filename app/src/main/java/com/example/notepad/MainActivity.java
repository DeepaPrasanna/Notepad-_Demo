package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rec;
    Button fab;
    List<Notepad>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rec = findViewById(R.id.rec);
        fab = findViewById(R.id.btn_fab);
        rec.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // intent.putExtra("add_note","");
                startActivity(intent);
                final Intent intent1=getIntent();

                Dbhelper db = new Dbhelper(MainActivity.this);
                list=db.getNotesList();
                NoteAdapter obj=new NoteAdapter(MainActivity.this,(ArrayList<Notepad>)list);
                rec.setAdapter(obj);
            }
        });
    }
}
