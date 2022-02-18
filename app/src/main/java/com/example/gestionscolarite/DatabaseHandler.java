package com.example.gestionscolarite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ScolarityManager";
    //Programs
    private static final String TABLE_PROGRAMS = "programs";
    private static final String KEY_ID = "idProgram";
    private static final String KEY_NAME = "nameProgram";
    //Students
    private static final String TABLE_STUDENTS="students";
    private static final String KEY_CNE = "cne";
    private static final String KEY_NOM = "nom";
    private static final String KEY_PRENOM = "prenom";
    private static final String KEY_ID_PROGRAM = "idFiliere";
    private static final String KEY_NIVEAU = "niveau";
    //Modules/Planning
    private static final String TABLE_PLANNING="planning";
    private static final String KEY_ID_MODULE = "idModule";
    private static final String KEY_NAME_MODULE = "nameModule";




    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //PROGRAMS TABLE
        String Create_table_programs ="CREATE TABLE "+TABLE_PROGRAMS+" (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT"+ " )";
        db.execSQL(Create_table_programs);
        //STUDENTS TABLE
        String Create_table_students="CREATE TABLE "+TABLE_STUDENTS+" ("+ KEY_CNE+ " TEXT PRIMARY KEY," +
                " "+KEY_NOM+" TEXT," +KEY_PRENOM+" TEXT,"+KEY_ID_PROGRAM+" INTEGER,"+KEY_NIVEAU+" TEXT, FOREIGN KEY("+KEY_ID_PROGRAM+") REFERENCES "+
                TABLE_PROGRAMS+"("+KEY_ID+"), FOREIGN KEY("+KEY_NIVEAU+") REFERENCES "+TABLE_PLANNING+"("+KEY_NIVEAU+"))";
        db.execSQL(Create_table_students);

        //PLANNING /MODULES TABLE
        String Create_table_planning="CREATE TABLE "+TABLE_PLANNING+" ("+KEY_ID_MODULE+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME_MODULE+" TEXT,"+
                KEY_ID_PROGRAM+" INTEGER , "+KEY_NIVEAU+" TEXT, FOREIGN KEY("+KEY_ID_PROGRAM+") REFERENCES "+
                TABLE_PROGRAMS+"("+KEY_ID+"))";
        db.execSQL(Create_table_planning);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRAMS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PLANNING);

        // Create tables again
        onCreate(db);
    }

    // code to add the new program
    void addProgramTitle(Program program) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, program.getNameProgram()); // Program  Name

        // Inserting Row
        db.insert(TABLE_PROGRAMS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // Get all programs
    public List<Program> getAllPrograms() {
        List<Program> programList = new ArrayList<Program>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PROGRAMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Program program = new Program();
                program.setIdProgram(cursor.getString(0));
                program.setNameProgram(cursor.getString(1));
                // Adding program to list
                programList.add(program);
            } while (cursor.moveToNext());
        }

        // return contact list
        return programList;
    }
    //Get IdProgram with a given program name
    public Program getIdProgram(String ProgramName){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROGRAMS, new String[] { KEY_ID,
                        KEY_NAME}, KEY_NAME + "=?",
                new String[] { String.valueOf(ProgramName) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Program program = new Program(cursor.getString(0),
                cursor.getString(1));
        // return program
        return program;
    }



    // Add new Student
    void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CNE, student.getCne()); // Student CNE
        values.put(KEY_NOM, student.getNom()); // Student Lname
        values.put(KEY_PRENOM, student.getPrenom()); // Student Fname
        values.put(KEY_ID_PROGRAM, student.getIdFiliere()); // Student Program/Filiere
        values.put(KEY_NIVEAU, student.getNiveau()); // Student Level/Niveau

        // Inserting Row
        db.insert(TABLE_STUDENTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }
    // Get all Students
    public List<Student> getAllStudents() {
        List<Student> studentsList = new ArrayList<Student>();
        // Select All Query
        //String selectQuery = "SELECT " + KEY_NOM+","+KEY_PRENOM+" FROM " + TABLE_STUDENTS;
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS;
        //String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS +" WHERE "+ KEY_ID_PROGRAM +" = "+ Program_filter;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setCne(cursor.getString(0));
                student.setNom(cursor.getString(1));
                student.setPrenom(cursor.getString(2));
                student.setIdFiliere(cursor.getString(3));
                student.setNiveau(cursor.getString(4));



                // Adding contact to list
                studentsList.add(student);
            } while (cursor.moveToNext());
        }


        // return contact list
        return studentsList;
    }


    //Get Students with a given program id
    public List<Student> getStudents(String IdProgram,String Niveau) {
        List<Student> studentsList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS +" WHERE "+ KEY_ID_PROGRAM +" = "+IdProgram +" AND "+KEY_NIVEAU+" = "+ Niveau;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setCne(cursor.getString(0));
                student.setNom(cursor.getString(1));
                student.setPrenom(cursor.getString(2));
                student.setIdFiliere(cursor.getString(3));
                student.setNiveau(cursor.getString(4));




                // Adding contact to list
                studentsList.add(student);
            } while (cursor.moveToNext());
        }


        // return contact list
        return studentsList;
    }
    // code to add the new PLANNING/MODULE
    void addPlanning(Planning planning) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(KEY_ID_MODULE, planning.getIdModule()); // Module  ID
        values.put(KEY_NAME_MODULE, planning.getNameModule()); // Module  Name
        values.put(KEY_ID_PROGRAM, planning.getIdProgram()); // Program ID
        values.put(KEY_NIVEAU, planning.getNiveau()); // Niveau

        // Inserting Row
        db.insert(TABLE_PLANNING, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    // Get all Levels
    public List<Planning> getAllLevels() {
        List<Planning> levelsList = new ArrayList<Planning>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_NIVEAU+" FROM " + TABLE_PLANNING;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Planning planning = new Planning();
                planning.setNiveau(cursor.getString(0));

                // Adding contact to list
                levelsList.add(planning);
            } while (cursor.moveToNext());
        }


        // return contact list
        return levelsList;
    }



}
