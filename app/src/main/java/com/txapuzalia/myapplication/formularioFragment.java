package com.txapuzalia.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
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

    // -- CREAMOS UNA VARIABLE PARA USAR EN LA BASE DE DATOS, CON ESTA VARIABLE PODEMOS HACER DIFERENTES SENTENCIAS (INSERT, SELECT...)
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

        // -- IF PARA COMPROBAR QUE EN EL CAMPO DE NOMBRE HA ESCRITO ALGO --
        editName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editName.setHint("El Nombre es obligatorio");

                }
            }
        });
        // -- IF PARA COMPROBAR QUE EN EL CAMPO DE APELLIDO HA ESCRITO ALGO --
        editSurName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editSurName.setHint("El Apellido es obligatorio");
                }
            }
        });
        // -- IF PARA COMPROBAR QUE EN EL CAMPO DE DIRECCIÓN HA ESCRITO ALGO --
        editDireccion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editDireccion.setHint("La dirección es obligatorio");
                }
            }
        });
        // -- IF PARA COMPROBAR QUE EN EL CAMPO DE EMAIL HA ESCRITO ALGO --
        editEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editEmail.setHint("El correo electrónico es obligatorio");
                }
            }
        });
        // -- IF PARA COMPROBAR QUE EN EL CAMPO DE TELEFONO HA ESCRITO ALGO --
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
                // -- AL CLICKAR EL BOTÓN DE ENVIAR, REVISARÁ QUE TODOS LOS CAMPOS CUMPLAN CON SUS RESPECTIVOS REQUISITOS --
                if (editName.getText().toString().isEmpty() || editSurName.getText().toString().isEmpty() ||
                        editDireccion.getText().toString().isEmpty() || editEmail.getText().toString().isEmpty() ||
                        editPhone.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Rellene los campos obligatorios", Toast.LENGTH_SHORT).show();

                } else {
                    if (comprobarDatos()) {
                        MiThread miThread = new MiThread();
                        miThread.execute();
                    }


                }
            }


        });


        return v;
    }

    private boolean comprobarDatos() {

        String emailInput = editEmail.getText().toString().trim();
        // -- COMPRUEBA QUE LOS CAMPOS DEL MÓVIL Y DEL EMAIL ESTÉN BIEN ESCRITOS (que tenga un "@", que termine en ".com" que sea todos números, nueve dígitos
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(getActivity().getApplicationContext(), "El correo electrónico es incorrecto.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (editPhone.getText().toString().trim().length() < 9) {
                Toast.makeText(getActivity().getApplicationContext(), "El número de teléfono es incorrecto.", Toast.LENGTH_SHORT).show();
                return false;
            }


        }
        return true;
    }

    public class MiThread extends AsyncTask<Integer, Integer, Integer> {
        private ProgressDialog progreso;

        @Override
        protected void onPreExecute(){
            // -- BARRA DE PROGRESO --
            progreso = new ProgressDialog(getActivity());
            progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progreso.setMessage("Tramitando solicitud. ESPERE...");
            progreso.setCancelable(false);
            progreso.setMax(100);
            progreso.setProgress(0);
            progreso.show();
        }

        @Override
        protected Integer doInBackground (Integer... n){

            // -- CREA UN OBJETO PARA GUARDAR TODOS LOS DATOS --
            Map<String, Object> updateMap = new HashMap();
            updateMap.put("Apellido", editSurName.getText().toString());
            updateMap.put("Nombre", editName.getText().toString());
            updateMap.put("Direccion", editDireccion.getText().toString());
            updateMap.put("Telefono", editPhone.getText().toString());
            updateMap.put("Comentario_Usuario", etComments.getText().toString());
            updateMap.put("Nombre_Tarea", tvOpcion.getText().toString());
            updateMap.put("E-mail", editEmail.getText().toString());


            // -- ESTE SERÁ EL ID QUE MANDAREMOS A LA BASE DE DATOS PARA DIFERENCIAR UN OBJETO DE OTRO --
            String id = editDireccion.getText().toString() + " - " + tvOpcion.getText().toString();

            int jumpTime = 0;
            int totalProgressTime = 100;
            while(jumpTime < totalProgressTime) {

                jumpTime += 10;
                progreso.setProgress(jumpTime);
                SystemClock.sleep(300);
            }
            db.collection("Tareas")
                    .document(id)
                    .set(updateMap);
            // -- CREAR BUNDLE, QUE SON LOS DATOS QUE PASAREMOS --
            Bundle datosAEnviar = new Bundle();


            // -- AQUÍ PON TOSDOS LOS DATOS QUE QUIERAS EN FORMATO CLAVE, VALOR --
            datosAEnviar.putBoolean("tForm",true);
            FragmentManager fm = getActivity().getSupportFragmentManager();
            assert fm != null;
            FragmentTransaction ft = fm.beginTransaction();
            homeFragment llf = new homeFragment();
            llf.setArguments(datosAEnviar);
            ft.replace(R.id.fragment_container, llf);
            ft.addToBackStack(null);
            ft.commit();

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... porc){

            progreso.setProgress(porc[0]);
        }

        @Override
        protected void onPostExecute(Integer res){

            progreso.dismiss();
        }
    }
}
