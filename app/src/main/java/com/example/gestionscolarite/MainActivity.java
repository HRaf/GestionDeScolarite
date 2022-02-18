package com.example.gestionscolarite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle mtoggle;
    Menu menu;
    private CardView calendarview,modulesview,programsview,studentsview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("School Management");
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);
        mtoggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();
        //CardViews
        studentsview=findViewById(R.id.studentsview);
        programsview=findViewById(R.id.programsview);
        modulesview=findViewById(R.id.modulesview);
        calendarview=findViewById(R.id.calendarview);
        //Programsview click
        programsview.setOnClickListener(v-> {
            Intent intent_program = new Intent(getApplicationContext(),AddProgram.class);
            startActivity(intent_program);

        });
        //Moduleview click
        modulesview.setOnClickListener(v->{
            Intent intent_module=new Intent(getApplicationContext(),AddModule.class);
            startActivity(intent_module);
        });

        //Studentview click
        studentsview.setOnClickListener(v-> {
            Intent intent_student=new Intent(getApplicationContext(),ListOfStudent.class);
            startActivity(intent_student);

        });
        //Calendar View
        calendarview.setOnClickListener(v->{Intent intent_calendar=new Intent(getApplicationContext(),Calendar.class);
            startActivity(intent_calendar);});
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;
        }
        /*if(item.getItemId()==R.id.switchpro){
            Intent intent=new Intent(MainActivity.this,Pl.class);
            startActivity(intent);

        }*/
        /*if(item.getItemId()==R.id.logout){
            FirebaseAuth.getInstance().signOut();
            Intent intlogout=new Intent(getApplicationContext(),Login.class);
            startActivity(intlogout);
        }*/
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}