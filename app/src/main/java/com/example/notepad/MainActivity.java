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
    List<Notepad> list;
    private static int RES_CODE = 10;
    TextView mtv_No_items_found;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == RES_CODE) && (resultCode == RESULT_OK)) {
            assert data != null;
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
        list = new ArrayList<>();
        mtv_No_items_found = findViewById(R.id.tv_no_item_found);

        setupNoteAdapter();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("button type","ADD");
                startActivityForResult(intent, RES_CODE);


            }
        });
    }

    private void setupNoteAdapter() {

        Dbhelper db = new Dbhelper(MainActivity.this);
        list = db.getNotesList();
        if (list.size() > 0) {
            mtv_No_items_found.setVisibility(View.GONE);

            NoteAdapter obj = new NoteAdapter(MainActivity.this, (ArrayList<Notepad>) list);
            rec.setAdapter(obj);
        } else {
            rec.setVisibility(View.GONE);
            mtv_No_items_found.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void update(Notepad notepad) {
        Intent updateIntent=new Intent(MainActivity.this,SecondActivity.class);
        updateIntent.putExtra("button type","UPDATE");
        updateIntent.putExtra("note",notepad);

    }

    @Override
    public void delete(Notepad notepad) {
        Dbhelper db=new Dbhelper(MainActivity.this);
       db.deleteNote(notepad);

    }
}
