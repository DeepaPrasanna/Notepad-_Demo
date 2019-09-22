package com.example.notepad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnItemClickInterface {
    RecyclerView rec;
    Button fab;
    TextView mtv_No_items_found;


    private static int RES_CODE_ADD = 10;
    private static int RES_CODE_UPDATE = 11;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == RES_CODE_ADD) && (resultCode == RESULT_OK)) {
            assert data != null;
            setupNoteAdapter();
        } else if ((requestCode == RES_CODE_UPDATE) && (resultCode == RESULT_OK)) {
            setupNoteAdapter();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rec = findViewById(R.id.rec);
        fab = findViewById(R.id.btn_fab);
        rec.setLayoutManager(new LinearLayoutManager(this));

        mtv_No_items_found = findViewById(R.id.tv_no_item_found);

        setupNoteAdapter();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("button_type", "ADD");
                startActivityForResult(intent, RES_CODE_ADD);
                // setupNoteAdapter();


            }
        });
    }

    private void setupNoteAdapter() {

        List<Notepad> list;
        Dbhelper db = new Dbhelper(MainActivity.this);
        list = db.getNotesList();
        if (list.size() > 0) {
            mtv_No_items_found.setVisibility(View.GONE);
            rec.setVisibility(View.VISIBLE);
            NoteAdapter obj = new NoteAdapter(MainActivity.this, (ArrayList<Notepad>) list);
            obj.setListener(this);
            rec.setAdapter(obj);
            obj.notifyDataSetChanged();


        } else {
            rec.setVisibility(View.GONE);
            mtv_No_items_found.setVisibility(View.VISIBLE);
        }
    }


    public void OnUpdate(String id, String title, String text) {

        Intent updateIntent = new Intent(MainActivity.this, SecondActivity.class);
        updateIntent.putExtra("button_type", "UPDATE");
        updateIntent.putExtra("id", id);
        updateIntent.putExtra("title", title);
        updateIntent.putExtra("text", text);
        startActivityForResult(updateIntent, RES_CODE_UPDATE);
        setupNoteAdapter();

    }

    @Override
    public void OnDelete(String id) {
        Dbhelper db = new Dbhelper(MainActivity.this);
        db.deleteNote(id);
        setupNoteAdapter();

    }
}
