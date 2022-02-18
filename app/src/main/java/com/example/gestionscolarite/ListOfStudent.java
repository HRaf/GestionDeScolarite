package com.example.gestionscolarite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class ListOfStudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spinner_program,spinner_level;
    RecyclerView rv_student;
    RecyclerView.LayoutManager layoutManager;
    StudentAdapter studentAdapter;
    List<Student> studentsList=new ArrayList<>();;
    DatabaseHandler databaseHandler;
    Button addstudentbtn,filterbtn;
    @Override
    public void onResume() {
        super.onResume();
        studentAdapter.notifyDataSetChanged();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_student);
        //Filter Button
        filterbtn=findViewById(R.id.filterbtn);
        // Add Student Button
        addstudentbtn = findViewById(R.id.addstudentbtn);
        addstudentbtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddStudent.class);
            startActivity(intent);
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Students");
        databaseHandler = new DatabaseHandler(this);
        //Spinner Level
        spinner_level=findViewById(R.id.spinner_level);
        List<Planning> planningList=databaseHandler.getAllLevels();
        //List<String> levels = new ArrayList<String>();
        //levels.add("ALL");
        String[] levels = { "TC 1", "TC 2", "Licence", "Master 1", "Master 2","CI 1","CI 2","CI 3"};

        //for(Planning planning:planningList){
         //   levels.add(planning.getNiveau());
        //}
        spinner_level.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the levels/programs list
        ArrayAdapter aalevels = new ArrayAdapter(this, android.R.layout.simple_spinner_item, levels);
        aalevels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner_level
        spinner_level.setAdapter(aalevels);


        //Spinner Program
        spinner_program = findViewById(R.id.spinner_program);
        List<Program> programsList = databaseHandler.getAllPrograms();
        List<String> programs = new ArrayList<String>();
        programs.add("ALL");
        for (Program pg : programsList) {
            programs.add(pg.getNameProgram());

        }
        spinner_program.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the levels/programs list
        ArrayAdapter aaprograms = new ArrayAdapter(this, android.R.layout.simple_spinner_item, programs);
        aaprograms.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spinner_program.setAdapter(aaprograms);
        rv_student = findViewById(R.id.rv_student);
        rv_student.setHasFixedSize(true);
        studentsList = databaseHandler.getAllStudents();

        filterbtn.setOnClickListener(v->{
            String Program_filter = spinner_program.getSelectedItem().toString();
            String Level_filter=spinner_level.getSelectedItem().toString();

            if (Program_filter.equals("ALL") && Level_filter.equals("ALL")) {
                studentsList.clear();
                List<Student> studentsList_new;
                studentsList_new = databaseHandler.getAllStudents();
                studentsList.addAll(studentsList_new);
                // notify adapter
                studentAdapter.notifyDataSetChanged();

            } else {
                Program P=databaseHandler.getIdProgram(Program_filter);
                String IdProgram=P.getIdProgram();
                studentsList.clear();
                List<Student> studentsList_new;
                try {
                    studentsList_new = databaseHandler.getStudents(String.valueOf(IdProgram), "Master 2");
                    studentsList.addAll(studentsList_new);
                    // notify adapter
                    studentAdapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(this, "No Student found!", Toast.LENGTH_SHORT).show();
                }



            }

        });

        /*spinner_program.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
                String Program_filter = spinner_program.getSelectedItem().toString();
                if (Program_filter.equals("ALL")) {
                    studentsList = databaseHandler.getAllStudents();

                } else if(Program_filter.equals("Sim")) {

                    Toast.makeText(ListOfStudent.this, "ok", Toast.LENGTH_SHORT).show();
                    studentsList.clear();
                    List<Student> studentsList_new;
                    studentsList_new=databaseHandler.getStudents(2);
                    studentsList.addAll(studentsList_new);
                    // notify adapter
                    studentAdapter.notifyDataSetChanged();


                }

            }

            @Override
            public void onNothingSelected (AdapterView < ? > parent){

            }
        });*/
        layoutManager = new LinearLayoutManager(this);
        rv_student.setLayoutManager(layoutManager);
        studentAdapter = new StudentAdapter(this, studentsList, rv_student);
        rv_student.setAdapter(studentAdapter);
        studentAdapter.notifyDataSetChanged();







    }
        @Override
        public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){

        }

        @Override
        public void onNothingSelected (AdapterView < ? > parent){

        }

}