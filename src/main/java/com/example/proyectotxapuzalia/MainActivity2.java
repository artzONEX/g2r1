package com.example.proyectotxapuzalia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private TextView tvOpcion;
    public Button btnAtras;
    public Button btnenviar;
    public EditText editName;
    public EditText editSurName;
    public EditText editDireccion;
    public EditText editEmail;
    public EditText editPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOpcion = findViewById(R.id.tvopcion);
        btnAtras = findViewById(R.id.btnatras);
        btnenviar = findViewById(R.id.btnEnviar);
        editName = findViewById(R.id.editname);
        editSurName = findViewById(R.id.editSurname);
        editDireccion = findViewById(R.id.editdireccion);
        editEmail = findViewById(R.id.editemail);
        editPhone = findViewById(R.id.editphone);

        tvOpcion.setText(getIntent().getStringExtra(Values.CLAVE_INTENT));


        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                //intent.putExtra("mensaje", getText(R.string.helloworld));

                //intent.putExtra("mensaje", CajaTexto.getText().toString());

                //intent.putExtra(Values.CLAVE_INTENT, btnAtras.getText().toString());

                startActivity(intent);


                // startActivityForResult(intent, Values.REQ_ACT_2);
            }
        });

        editName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editName.setHint("El Nombre es obligatorio");
                    Toast toast2 =
                            Toast.makeText(getApplicationContext(),
                                    "Toast con gravity", Toast.LENGTH_SHORT);

                    toast2.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);

                    toast2.show();
                }
            }
        });

        editSurName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editSurName.setHint("El Apellido es obligatorio");
                }
            }
        });

        editDireccion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editDireccion.setHint("El dirreción del incidente es obligatorio");
                }
            }
        });
        editEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editEmail.setHint("El correo electrónico es obligatorio");
                }
            }
        });

        editPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editPhone.setHint("El número de teléfono es obligatorio");
                }
            }
        });

        editEmail.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });


        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //VERIFICACION DEL CAMPO NOMBRE
                if (editName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El Nombre es obligatorio.", Toast.LENGTH_SHORT).show();
                } else {
                    //VERIFICACION DEL CAMPO APELLIDO
                    if (editSurName.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "El Apellido es obligatorio.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (editEmail.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "El correo electrónico es obligatorio.", Toast.LENGTH_SHORT).show();
                        } else {
                            String emailInput = editEmail.getText().toString().trim();
                            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                                Toast.makeText(getApplicationContext(), "El correo electrónico es incorrecto.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (editDireccion.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "La dirreción del incidente  es obligatorio .", Toast.LENGTH_SHORT).show();
                        } else {
                            if (editPhone.getText().toString().isEmpty()) {
                                Toast.makeText(getApplicationContext(), "El número de teléfono es obligatorio.", Toast.LENGTH_SHORT).show();

                            }
                            if (editPhone.getText().toString().trim().length() < 9) {
                                Toast.makeText(getApplicationContext(), "El número de teléfono es incorrecto.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }
            }

        });

    }


}