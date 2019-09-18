package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    EditText et_title, et_text;
    Button bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String intentType = getIntent().getStringExtra("button type");

        et_title = findViewById(R.id.et_title);
        et_text = findViewById(R.id.et_text);
        bt_add = findViewById(R.id.btn_add);

        if (intentType.equals(("ADD"))) {

            bt_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title, text;
                    title = et_title.getText().toString();
                    text = et_text.getText().toString();
                    if (TextUtils.isEmpty(text)) {

                        Toast.makeText(SecondActivity.this, "Pls Enter some text.Empty Notes cannot be added!!!", Toast.LENGTH_LONG).show();
                    } else {

                        savetodb(title, text);
                    }
                }
            });
        } else if (intentType.equals("UPDATE")) {
          final    Notepad notepad = getIntent().getParcelableExtra("note");
            et_title.setText(notepad.getTitle());
            et_text.setText(notepad.getText());
            bt_add.setText("update");
            bt_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title,text;
                   notepad.setTitle(et_title.getText().toString());
                    notepad.setText(et_text.getText().toString());
                    Dbhelper db=new Dbhelper(SecondActivity.this);
                    db.updateNote( notepad);
                }
            });


        }
    }


    private void savetodb(String title, String text) {
        Dbhelper db = new Dbhelper(this);
        db.insertNote(title, text);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
