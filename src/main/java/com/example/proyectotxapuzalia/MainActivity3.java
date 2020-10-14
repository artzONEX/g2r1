package com.example.proyectotxapuzalia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {



    public Button btnAtras;
    public Button btnenviar;
    public EditText editName;
    public EditText editSurName;
    public EditText editDireccion;
    public EditText editEmail;
    public EditText editPhone;
    public CheckBox checkBoxTC;
    public TextView tvOpcion;
    public EditText etComments;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvOpcion = findViewById(R.id.tvOpcion);
        btnAtras = findViewById(R.id.btnAtras);
        btnenviar = findViewById(R.id.btnenviar);
        editName  = findViewById(R.id.editName);
        editSurName  = findViewById(R.id.editSurName);
        editDireccion  = findViewById(R.id.editDireccion);
        editEmail  = findViewById(R.id.editEmail);
        editPhone  = findViewById(R.id.editPhone);
        checkBoxTC  = findViewById(R.id.checkBoxTC);
        etComments=findViewById(R.id.etComments);


        //Función del boton atras para retroceder a la activity 2
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        editName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editName.setHint("El Nombre es obligatorio");
                    Toast toast2 =
                            Toast.makeText(getApplicationContext(),
                                    "Toast con gravity", Toast.LENGTH_SHORT);

                    toast2.setGravity(Gravity.TOP| Gravity.LEFT,0,0);

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

        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //VERIFICACION DEL CAMPO NOMBRE
                if(editName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "El Nombre es obligatorio.", Toast.LENGTH_SHORT).show();
                }else{
                    //VERIFICACION DEL CAMPO APELLIDO
                    if(editSurName.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "El Apellido es obligatorio.", Toast.LENGTH_SHORT).show();
                    }else{
                        //VERIFICACION DEL CAMPO EMAIL
                        if(editEmail.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "El correo electrónico es obligatorio.", Toast.LENGTH_SHORT).show();
                        }else{
                            String emailInput = editEmail.getText().toString().trim();
                            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                                Toast.makeText(getApplicationContext(), "El correo electrónico es incorrecto.", Toast.LENGTH_SHORT).show();
                            }else{
                                //VERIFICACION DEL CAMPO DIRECCION
                                if(editDireccion.getText().toString().isEmpty()){
                                    Toast.makeText(getApplicationContext(), "La dirreción del incidente  es obligatorio .", Toast.LENGTH_SHORT).show();
                                }else{
                                    //VERIFICACION DEL CAMPO NUM TELF
                                    if(editPhone.getText().toString().isEmpty()){
                                        Toast.makeText(getApplicationContext(), "El número de teléfono es obligatorio.", Toast.LENGTH_SHORT).show();

                                    }else{
                                        if (editPhone.getText().toString().trim().length() < 9) {
                                            Toast.makeText(getApplicationContext(), "El número de teléfono es incorrecto.", Toast.LENGTH_SHORT).show();

                                        }else{
                                            if(!checkBoxTC.isChecked()) {
                                                Toast.makeText(getApplicationContext(), "No has aceptado los terminos y condiciones", Toast.LENGTH_SHORT).show();
                                            }else{
                                                Map<String, Object> updateMap = new HashMap();
                                                updateMap.put("Apellido", editSurName.getText().toString());
                                                updateMap.put("Nombre", editName.getText().toString());
                                                updateMap.put("Direccion", editDireccion.getText().toString());
                                                updateMap.put("Telefono", editPhone.getText().toString());
                                                updateMap.put("Comentario_Usuario", etComments.getText().toString());
                                                updateMap.put("Nombre_Tarea", tvOpcion.getText().toString());
                                                updateMap.put("E-mail", editEmail.getText().toString());




                                                db.collection("Tareas")
                                                        .document(editSurName.getText().toString())
                                                        .set(updateMap);
                                                //Toast.makeText(getApplicationContext(), "ENVIADO. ENVIADO ENVIADO", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(MainActivity3.this, MainActivity.class);

                                                startActivity(intent);
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }

        });





    }
}