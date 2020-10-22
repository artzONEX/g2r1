package com.txapuzalia.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

    public void Botones(View View) {
        formularioFragment cf = new formularioFragment();
        Bundle bundle = new Bundle();
        switch (View.getId()) {
            case R.id.otros1:
                TextView otros1= findViewById(R.id.otros1Text);
                bundle.putString("opcion", otros1.getText().toString() );
                cf.setArguments(bundle);
            case R.id.otros2:
                TextView otros2= findViewById(R.id.otros2Text);
                bundle.putString("opcion", otros2.getText().toString() );
                cf.setArguments(bundle);
            case R.id.otros3:
                TextView otros3= findViewById(R.id.otros3Text);
                bundle.putString("opcion", otros3.getText().toString() );
                cf.setArguments(bundle);
            case R.id.carp1:
                TextView carp1= findViewById(R.id.carp1Text);
                bundle.putString("opcion", carp1.getText().toString() );
                cf.setArguments(bundle);
            case R.id.carp2:
                TextView carp2= findViewById(R.id.carp2Text);
                bundle.putString("opcion", carp2.getText().toString() );
                cf.setArguments(bundle);
            case R.id.carp3:
                TextView carp3= findViewById(R.id.carp3Text);
                bundle.putString("opcion", carp3.getText().toString() );
                cf.setArguments(bundle);
            case R.id.carp4:
                TextView carp4= findViewById(R.id.carp4Text);
                bundle.putString("opcion", carp4.getText().toString() );
                cf.setArguments(bundle);
            case R.id.carp5:
                TextView carp5= findViewById(R.id.carp5Text);
                bundle.putString("opcion", carp5.getText().toString() );
                cf.setArguments(bundle);
            case R.id.carp6:
                TextView carp6= findViewById(R.id.carp6Text);
                bundle.putString("opcion", carp6.getText().toString() );
                cf.setArguments(bundle);
            case R.id.carp7:
                TextView carp7= findViewById(R.id.carp7Text);
                bundle.putString("opcion", carp7.getText().toString() );
                cf.setArguments(bundle);
            case R.id.carp8:
                TextView carp8= findViewById(R.id.carp8Text);
                bundle.putString("opcion", carp8.getText().toString() );
                cf.setArguments(bundle);
            case R.id.elec1:
                TextView elec1= findViewById(R.id.elec1Text);
                bundle.putString("opcion", elec1.getText().toString() );
                cf.setArguments(bundle);
            case R.id.elec2:
                TextView elec2= findViewById(R.id.elec2Text);
                bundle.putString("opcion", elec2.getText().toString() );
                cf.setArguments(bundle);
            case R.id.elec3:
                TextView elec3= findViewById(R.id.elec3Text);
                bundle.putString("opcion", elec3.getText().toString() );
                cf.setArguments(bundle);
            case R.id.elec4:
                TextView elec4= findViewById(R.id.elec4Text);
                bundle.putString("opcion", elec4.getText().toString() );
                cf.setArguments(bundle);
            case R.id.elec5:
                TextView elec5= findViewById(R.id.elec5Text);
                bundle.putString("opcion", elec5.getText().toString() );
                cf.setArguments(bundle);
            case R.id.elec6:
                TextView elec6= findViewById(R.id.elec6Text);
                bundle.putString("opcion", elec6.getText().toString() );
                cf.setArguments(bundle);
            case R.id.elec7:
                TextView elec7= findViewById(R.id.elec7Text);
                bundle.putString("opcion", elec7.getText().toString() );
                cf.setArguments(bundle);
            case R.id.pint1:
                TextView pint1= findViewById(R.id.pint1Text);
                bundle.putString("opcion", pint1.getText().toString() );
                cf.setArguments(bundle);
            case R.id.pint2:
                TextView pint2= findViewById(R.id.pint2Text);
                bundle.putString("opcion", pint2.getText().toString() );
                cf.setArguments(bundle);
            case R.id.pint3:
                TextView pint3= findViewById(R.id.pint3Text);
                bundle.putString("opcion", pint3.getText().toString() );
                cf.setArguments(bundle);
            case R.id.pint4:
                TextView pint4= findViewById(R.id.pint4Text);
                bundle.putString("opcion", pint4.getText().toString() );
                cf.setArguments(bundle);
            case R.id.pint5:
                TextView pint5= findViewById(R.id.pint5Text);
                bundle.putString("opcion", pint5.getText().toString() );
                cf.setArguments(bundle);
            case R.id.font1:
                TextView font1= findViewById(R.id.font1Text);
                bundle.putString("opcion", font1.getText().toString() );
                cf.setArguments(bundle);
            case R.id.font2:
                TextView font2= findViewById(R.id.font2Text);
                bundle.putString("opcion", font2.getText().toString() );
                cf.setArguments(bundle);
            case R.id.font3:
                TextView font3= findViewById(R.id.font3Text);
                bundle.putString("opcion", font3.getText().toString() );
                cf.setArguments(bundle);
            case R.id.font4:
                TextView font4= findViewById(R.id.font4Text);
                bundle.putString("opcion", font4.getText().toString() );
                cf.setArguments(bundle);
            case R.id.font5:
                TextView font5= findViewById(R.id.font5Text);
                bundle.putString("opcion", font5.getText().toString() );
                cf.setArguments(bundle);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cf).commit();

    }

}