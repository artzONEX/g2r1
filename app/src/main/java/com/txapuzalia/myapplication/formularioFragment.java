package com.txapuzalia.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class formularioFragment extends Fragment {


    public Button btnenviar;
    public EditText editName;
    public EditText editSurName;
    public EditText editDireccion;
    public EditText editEmail;
    public EditText editPhone;
    public TextView tvOpcion;
    public EditText etComments;



    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_formulario, container, false);

        tvOpcion = v.findViewById(R.id.tvOpcion);
        btnenviar = v.findViewById(R.id.btnenviar);
        editName = v.findViewById(R.id.editName);
        editSurName = v.findViewById(R.id.editSurName);
        editDireccion = v.findViewById(R.id.editDireccion);
        editEmail = v.findViewById(R.id.editEmail);
        editPhone = v.findViewById(R.id.editPhone);
        etComments = v.findViewById(R.id.etComments);




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
                    editDireccion.setHint("La dirección es obligatorio");
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
                if (editName.getText().toString().isEmpty() || editSurName.getText().toString().isEmpty() ||
                        editDireccion.getText().toString().isEmpty() || editEmail.getText().toString().isEmpty() ||
                        editPhone.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Rellene los campos obligatorios", Toast.LENGTH_SHORT).show();

                } else {
                    if (comprobarDatos()) {
                        Map<String, Object> updateMap = new HashMap();
                        updateMap.put("Apellido", editSurName.getText().toString());
                        updateMap.put("Nombre", editName.getText().toString());
                        updateMap.put("Direccion", editDireccion.getText().toString());
                        updateMap.put("Telefono", editPhone.getText().toString());
                        updateMap.put("Comentario_Usuario", etComments.getText().toString());
                        updateMap.put("Nombre_Tarea", tvOpcion.getText().toString());
                        updateMap.put("E-mail", editEmail.getText().toString());

                        String id = editDireccion.getText().toString() + " - " + tvOpcion.getText().toString();


                        db.collection("Tareas")
                                .document(id)
                                .set(updateMap);
                        Toast.makeText(getActivity(), "Su peticion se ha realizado correctamente", Toast.LENGTH_LONG).show();
                        FragmentManager fm = getFragmentManager();
                        assert fm != null;
                        FragmentTransaction ft = fm.beginTransaction();
                        homeFragment llf = new homeFragment();
                        ft.replace(R.id.fragment_container, llf);

                        ft.commit();

                    }


                }
            }


        });


        return v;
    }

    private boolean comprobarDatos() {

        String emailInput = editEmail.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(getActivity().getApplicationContext(), "El correo electrónico es incorrecto.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
                if (editPhone.getText().toString().trim().length() < 9 ) {
                    Toast.makeText(getActivity().getApplicationContext(), "El número de teléfono es incorrecto.", Toast.LENGTH_SHORT).show();
                    return false;
                }


        }
        return true;
    }
}
