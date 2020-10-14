package com.txapuzalia.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout dl;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.main_toolbar);
        //toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.navigation_informacion);
        navigationView.setNavigationItemSelectedListener(this);

        dl= findViewById(R.id.drawer_layout);
        nv= findViewById(R.id.navigation_informacion);
        nv.setItemIconTintList(null);
        ActionBarDrawerToggle actionBarDrawerToggle= new ActionBarDrawerToggle(
                this,
                dl,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        nv.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new homeFragment()).commit();
            navigationView.setCheckedItem(R.id.navigation_home);
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        switch (Item.getItemId())
        {
            case R.id.navigation_informacion:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InformacionFragment()).commit();
                break;
            case R.id.navigation_pintura:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new pinturaFragment()).commit();
                break;
            case R.id.navigation_electricista:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new electricidadFragment()).commit();
                break;
            case R.id.navigation_otros:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new otrosFragment()).commit();
                break;
            case R.id.navigation_fontaneria:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fontaneriaFragment()).commit();
                break;
            case R.id.navigation_carpinteria:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new carpinteriaFragment()).commit();
                break;
        }


            dl.closeDrawer(GravityCompat.START);

        return true;
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}