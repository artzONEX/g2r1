package com.txapuzalia.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class formularioFragment extends Fragment {



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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_formulario, container, false);

        tvOpcion = v.findViewById(R.id.tvOpcion);

        btnenviar = v.findViewById(R.id.btnenviar);
        editName  = v.findViewById(R.id.editName);
        editSurName  = v.findViewById(R.id.editSurName);
        editDireccion  = v.findViewById(R.id.editDireccion);
        editEmail  = v.findViewById(R.id.editEmail);
        editPhone  = v.findViewById(R.id.editPhone);
        checkBoxTC  = v.findViewById(R.id.checkBoxTC);
        etComments= v.findViewById(R.id.etComments);





        editName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editName.setHint("El Nombre es obligatorio");

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
                    Toast.makeText(getActivity().getApplicationContext(), "El Nombre es obligatorio.", Toast.LENGTH_SHORT).show();
                }else{
                    //VERIFICACION DEL CAMPO APELLIDO
                    if(editSurName.getText().toString().isEmpty()){
                        Toast.makeText(getActivity().getApplicationContext(), "El Apellido es obligatorio.", Toast.LENGTH_SHORT).show();
                    }else{
                        //VERIFICACION DEL CAMPO EMAIL
                        if(editEmail.getText().toString().isEmpty()){
                            Toast.makeText(getActivity().getApplicationContext(), "El correo electrónico es obligatorio.", Toast.LENGTH_SHORT).show();
                        }else{
                            String emailInput = editEmail.getText().toString().trim();
                            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                                Toast.makeText(getActivity().getApplicationContext(), "El correo electrónico es incorrecto.", Toast.LENGTH_SHORT).show();
                            }else{
                                //VERIFICACION DEL CAMPO DIRECCION
                                if(editDireccion.getText().toString().isEmpty()){
                                    Toast.makeText(getActivity().getApplicationContext(), "La dirreción del incidente  es obligatorio .", Toast.LENGTH_SHORT).show();
                                }else{
                                    //VERIFICACION DEL CAMPO NUM TELF
                                    if(editPhone.getText().toString().isEmpty()){
                                        Toast.makeText(getActivity().getApplicationContext(), "El número de teléfono es obligatorio.", Toast.LENGTH_SHORT).show();

                                    }else{
                                        if (editPhone.getText().toString().trim().length() < 9) {
                                            Toast.makeText(getActivity().getApplicationContext(), "El número de teléfono es incorrecto.", Toast.LENGTH_SHORT).show();

                                        }else{
                                            if(!checkBoxTC.isChecked()) {
                                                Toast.makeText(getActivity().getApplicationContext(), "No has aceptado los terminos y condiciones", Toast.LENGTH_SHORT).show();
                                            }else{
                                                /*Map<String, Object> updateMap = new HashMap();
                                                updateMap.put("Apellido", editSurName.getText().toString());
                                                updateMap.put("Nombre", editName.getText().toString());
                                                updateMap.put("Direccion", editDireccion.getText().toString());
                                                updateMap.put("Telefono", editPhone.getText().toString());
                                                updateMap.put("Comentario_Usuario", etComments.getText().toString());
                                                updateMap.put("Nombre_Tarea", tvOpcion.getText().toString());
                                                updateMap.put("E-mail", editEmail.getText().toString());

                                                String id= editDireccion.getText().toString()+ " - "+ tvOpcion.getText().toString();


                                                db.collection("Tareas")
                                                        .document(id)
                                                        .set(updateMap);
                                                //Toast.makeText(getApplicationContext(), "ENVIADO. ENVIADO ENVIADO", Toast.LENGTH_SHORT).show();
                                                FragmentManager fm = getFragmentManager();
                                                assert fm != null;
                                                FragmentTransaction ft = fm.beginTransaction();
                                                homeFragment llf = new homeFragment();
                                                ft.replace(R.id.fragment_container, llf);
                                                ft.commit();*/






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






        return v;
    }

}