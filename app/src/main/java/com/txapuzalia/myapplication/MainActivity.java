package com.txapuzalia.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static DrawerLayout dl;


    private TextView textViewModo;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
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

                    Toast.makeText(getApplicationContext(), R.string.modoOn, Toast.LENGTH_SHORT).show();

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



                    Toast.makeText(getApplicationContext(), R.string.modoOff, Toast.LENGTH_SHORT).show();

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

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        //toolbar.setTitleTextColor(Color.WHITE);
        //setSupportActionBar(toolbar);

        dl= findViewById(R.id.drawer_layout);
        NavigationView nv = findViewById(R.id.navigation_informacion);
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
            nv.setCheckedItem(R.id.navigation_home);
        }

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


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        switch (Item.getItemId())
        {
            case R.id.navigation_telefonoFijo:
                Intent inte = new Intent(Intent.ACTION_DIAL);
                inte=contactanos.FUNCION_FIJO(getString(R.string.numeroFij), inte);
                startActivity(inte);
                break;
            case R.id.navigation_telefonoMovil:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent = contactanos.FUNCION_MOVIL(getString(R.string.numeroTel), intent);
                startActivity(intent);
                break;
            case R.id.navigation_correo:
                Intent in = new Intent(Intent.ACTION_SEND);
                in=contactanos.FUNCION_CORREO(in);
                startActivity(Intent.createChooser(in, "Escoja un correo"));
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

    @SuppressLint("NonConstantResourceId")
    public void Botones(View View) {
        formularioFragment cf = new formularioFragment();
        Bundle bundle = new Bundle();
        switch (View.getId()) {
            case R.id.otros1:
                TextView otros1= findViewById(R.id.otros1Text);
                bundle.putString("opcion", otros1.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.otros2:
                TextView otros2= findViewById(R.id.otros2Text);
                bundle.putString("opcion", otros2.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.otros3:
                TextView otros3= findViewById(R.id.otros3Text);
                bundle.putString("opcion", otros3.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.carp1:
                TextView carp1= findViewById(R.id.carp1Text);
                bundle.putString("opcion", carp1.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.carp2:
                TextView carp2= findViewById(R.id.carp2Text);
                bundle.putString("opcion", carp2.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.carp3:
                TextView carp3= findViewById(R.id.carp3Text);
                bundle.putString("opcion", carp3.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.carp4:
                TextView carp4= findViewById(R.id.carp4Text);
                bundle.putString("opcion", carp4.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.carp5:
                TextView carp5= findViewById(R.id.carp5Text);
                bundle.putString("opcion", carp5.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.carp6:
                TextView carp6= findViewById(R.id.carp6Text);
                bundle.putString("opcion", carp6.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.carp7:
                TextView carp7= findViewById(R.id.carp7Text);
                bundle.putString("opcion", carp7.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.carp8:
                TextView carp8= findViewById(R.id.carp8Text);
                bundle.putString("opcion", carp8.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.elec1:
                TextView elec1= findViewById(R.id.elec1Text);
                bundle.putString("opcion", elec1.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.elec2:
                TextView elec2= findViewById(R.id.elec2Text);
                bundle.putString("opcion", elec2.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.elec3:
                TextView elec3= findViewById(R.id.elec3Text);
                bundle.putString("opcion", elec3.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.elec4:
                TextView elec4= findViewById(R.id.elec4Text);
                bundle.putString("opcion", elec4.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.elec5:
                TextView elec5= findViewById(R.id.elec5Text);
                bundle.putString("opcion", elec5.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.elec6:
                TextView elec6= findViewById(R.id.elec6Text);
                bundle.putString("opcion", elec6.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.pint1:
                TextView pint1= findViewById(R.id.pint1Text);
                bundle.putString("opcion", pint1.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.pint2:
                TextView pint2= findViewById(R.id.pint2Text);
                bundle.putString("opcion", pint2.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.pint3:
                TextView pint3= findViewById(R.id.pint3Text);
                bundle.putString("opcion", pint3.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.pint4:
                TextView pint4= findViewById(R.id.pint4Text);
                bundle.putString("opcion", pint4.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.pint5:
                TextView pint5= findViewById(R.id.pint5Text);
                bundle.putString("opcion", pint5.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.font1:
                TextView font1= findViewById(R.id.font1Text);
                bundle.putString("opcion", font1.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.font2:
                TextView font2= findViewById(R.id.font2Text);
                bundle.putString("opcion", font2.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.font3:
                TextView font3= findViewById(R.id.font3Text);
                bundle.putString("opcion", font3.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.font4:
                TextView font4= findViewById(R.id.font4Text);
                bundle.putString("opcion", font4.getText().toString() );
                cf.setArguments(bundle);
                break;
            case R.id.font5:
                TextView font5= findViewById(R.id.font5Text);
                bundle.putString("opcion", font5.getText().toString() );
                cf.setArguments(bundle);
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cf).commit();

    }

    public static void botonInicio(){
        dl.openDrawer(GravityCompat.START);
    }


}