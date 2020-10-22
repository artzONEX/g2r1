package com.txapuzalia.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.squareup.okhttp.HttpUrl;


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
        // -- MEDIANTE ESTE SWITCH PODEMOS AVERIGUAR EN QUÉ BOTÓN HA CLICKADO --
        switch (Item.getItemId())
        {
            case R.id.navigation_telefono:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getString(R.string.numeroTel)));
                System.out.println(getString(R.string.numeroTel));
                startActivity(intent);
                break;
            case R.id.navigation_correo:
                Intent in = new Intent(Intent.ACTION_SEND);
                in.putExtra(Intent.EXTRA_EMAIL, new String[] { "txapuzalia@gmail.com" });
                in.putExtra(Intent.EXTRA_SUBJECT, "Duda/Queja");
                in.setType("message/rfc822");
                startActivity(Intent.createChooser(in, "Escriba un email"));
                break;
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
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