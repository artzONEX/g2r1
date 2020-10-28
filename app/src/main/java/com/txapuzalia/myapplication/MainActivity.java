package com.txapuzalia.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.IOError;
import java.io.IOException;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout dl;
    private NavigationView nv;

    private TextView textViewModo;
    public Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewModo = findViewById(R.id.textViewModo);
        switch1 = findViewById(R.id.switch1);
        cargarPreferencias();

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switch1.isChecked()) {

                    //ModoOscuro();

                    Toast.makeText(getApplicationContext(), "Activando Modo Oscuro", Toast.LENGTH_SHORT).show();

                    SharedPreferences MiPreferences = getSharedPreferences("guardado", Context.MODE_PRIVATE);
                    String texto = "Oscuro";

                    textViewModo.setText(texto);




                    SharedPreferences.Editor editor = MiPreferences.edit();
                    editor.putString("text", texto);




                   /* getDelegate().setLocalNightMode(
                            AppCompatDelegate.MODE_NIGHT_YES);*/

                    /*AppCompatDelegate
                            .setDefaultNightMode(
                                    AppCompatDelegate
                                            .MODE_NIGHT_YES);*/


                    ModoOscuro();



                    //ModoClaro();

                    editor.apply();
                }
                else if(!switch1.isChecked()) {



                    Toast.makeText(getApplicationContext(), "Activando Modo Claro", Toast.LENGTH_SHORT).show();

                    SharedPreferences MiPreferences = getSharedPreferences("guardado", Context.MODE_PRIVATE);
                    String texto = "Claro";


                    textViewModo.setText(texto);

                    SharedPreferences.Editor editor = MiPreferences.edit();
                    editor.putString("text", texto);

                    /*getDelegate().setLocalNightMode(
                            AppCompatDelegate.MODE_NIGHT_NO);*/



                    /*AppCompatDelegate
                            .setDefaultNightMode(
                                    AppCompatDelegate
                                            .MODE_NIGHT_NO);*/

                    ModoClaro();




                    //ModoOscuro();

                    editor.apply();


                }
            }
        });

        toolbar=findViewById(R.id.main_toolbar);
        //toolbar.setTitleTextColor(Color.WHITE);
        //setSupportActionBar(toolbar);

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

    /*public void cargarPreferencias() {
        SharedPreferences MiPreferences = getSharedPreferences("guardado", Context.MODE_PRIVATE);
        String text = MiPreferences.getString("text","Ninguno");

        textViewModo.setText(text);

        String textoC = "Claro";
        String textoO = "Oscuro";


       if(textViewModo.getText().equals(textoC)){


           ModoClaro();


           switch1.isChecked();
            AppCompatDelegate
                    .setDefaultNightMode(
                            AppCompatDelegate
                                    .MODE_NIGHT_NO);



           /*return  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            getApplicationContext().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
           return  ((AppCompatActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        }else if(textViewModo.getText().equals(textoO)){


            ModoOscuro();

            switch1.toggle();


           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            AppCompatDelegate
                    .setDefaultNightMode(
                            AppCompatDelegate
                                    .MODE_NIGHT_YES);
            /*((AppCompatActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        }else if(!switch1.isChecked()) {
            ModoClaro();
        }
    }

    private void ModoOscuro() {


       AppCompatDelegate
                .setDefaultNightMode(
                        AppCompatDelegate
                                .MODE_NIGHT_YES);
        /*getDelegate().setLocalNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);

        recreate();*
    }

    public void ModoClaro() {
       AppCompatDelegate
                .setDefaultNightMode(
                        AppCompatDelegate
                                .MODE_NIGHT_NO);

        getDelegate().setLocalNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);

        recreate();
    }*/


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        switch (Item.getItemId())
        {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
                break;
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

    public void openDrawer() {
        //dl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }





    private void cargarPreferencias(){
        SharedPreferences MiPreferences = getSharedPreferences("guardado", Context.MODE_PRIVATE);
        String text = MiPreferences.getString("text","Ninguno");

        textViewModo.setText(text);

        String textoC = "Claro";
        String textoO = "Oscuro";


        if(textViewModo.getText().equals(textoC)){


            ModoClaro();

            switch1.isChecked();

        }else if(textViewModo.getText().equals(textoO)){


            ModoOscuro();

            switch1.toggle();

        }else if(!switch1.isChecked()) {
            ModoClaro();
        }

    }

    private void ModoOscuro() {
        AppCompatDelegate
                .setDefaultNightMode(
                        AppCompatDelegate
                                .MODE_NIGHT_YES);
    }

    private void ModoClaro() {
        AppCompatDelegate
                .setDefaultNightMode(
                        AppCompatDelegate
                                .MODE_NIGHT_NO);
    }
}