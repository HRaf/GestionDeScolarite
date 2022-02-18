package com.example.gestionscolarite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProgram extends AppCompatActivity {
    Button addbtn;
    EditText programtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Program");
        programtitle=findViewById(R.id.programtitle);
        //Sqlite handler
        DatabaseHandler db = new DatabaseHandler(this);
        //Add program button
        addbtn=findViewById(R.id.addbtn);
        addbtn.setOnClickListener(v ->{
            String ProgramTitle=programtitle.getText().toString();
            db.addProgramTitle(new Program(ProgramTitle));
            Toast.makeText(this, "Program added successfully", Toast.LENGTH_SHORT).show();
            programtitle.setText("");

        });
    }
}