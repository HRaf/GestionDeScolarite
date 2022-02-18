package com.example.gestionscolarite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddModule extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner,spinner_program;
    EditText moduletitle;
    Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_module);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Add Module");
        spinner=findViewById(R.id.spinner);
        spinner_program=findViewById(R.id.spinner_program);
        moduletitle=findViewById(R.id.moduletitle);
        addbtn=findViewById(R.id.addbtn);
        //handle spinner
        String[] levels = { "TC 1", "TC 2", "Licence", "Master 1", "Master 2","CI 1","CI 2","CI 3"};
        DatabaseHandler db = new DatabaseHandler(this);
        List<Program> programsList = db.getAllPrograms();
        List<String> programs=new ArrayList<String>();
        for(Program pg:programsList){
            programs.add(pg.getNameProgram());

        }

        spinner.setOnItemSelectedListener(this);
        spinner_program.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the levels/programs list
        ArrayAdapter aalevels = new ArrayAdapter(this,android.R.layout.simple_spinner_item,levels);
        aalevels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter aaprograms = new ArrayAdapter(this,android.R.layout.simple_spinner_item,programs);
        aaprograms.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aalevels);
        spinner_program.setAdapter(aaprograms);
        //Add module Button
        addbtn.setOnClickListener(v->{
           String moduleTitle=moduletitle.getText().toString();
           String nameProgram=spinner_program.getSelectedItem().toString();
           String niveau=spinner.getSelectedItem().toString();
           Program P=db.getIdProgram(nameProgram);
           String IdProgam=P.getIdProgram();
           db.addPlanning(new Planning(moduleTitle,IdProgam,niveau));
           moduletitle.setText("");
           Toast.makeText(this, "Module Added Successfully", Toast.LENGTH_SHORT).show();


        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}