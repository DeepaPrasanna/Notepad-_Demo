package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {
    EditText et_title, et_text;
    Button bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        et_title = findViewById(R.id.et_title);
        et_text = findViewById(R.id.et_text);
        bt_add = findViewById(R.id.btn_add);
        final Intent intent=getIntent();
       // int data=intent.getIntExtra("add_note",-1);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title, text;
                title = et_title.getText().toString();
                text = et_text.getText().toString();

                savetodb(title, text);
                Intent intent1=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent1);





            }
        });
    }

    private void savetodb(String title,String text)
    {
       Dbhelper db=new Dbhelper(this);
       db.insertNote(title,text);
    }
}
