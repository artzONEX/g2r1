package com.txapuzalia.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class formularioFragment extends Fragment {

    public CardView navigation_correo;
    public CardView navigation_telefono;
    public CardView navigation_movil;
    public Button btnenviar;
    public EditText editName;
    public EditText editSurName;
    public EditText editDireccion;
    public EditText editEmail;
    public EditText editPhone;
    public String opcion;
    public TextView tvOpcion;
    public EditText etComments;


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null){
            opcion = getArguments().getString("opcion");
        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_formulario, container, false);

        tvOpcion = v.findViewById(R.id.opcion);
        btnenviar = v.findViewById(R.id.btnenviar);
        editName = v.findViewById(R.id.editName);
        editSurName = v.findViewById(R.id.editSurName);
        editDireccion = v.findViewById(R.id.editDireccion);
        editEmail = v.findViewById(R.id.editEmail);
        editPhone = v.findViewById(R.id.editPhone);
        etComments = v.findViewById(R.id.etComments);
        navigation_correo=v.findViewById(R.id.navigation_correo);
        navigation_movil=v.findViewById(R.id.navigation_telefonoMovil);
        navigation_telefono=v.findViewById(R.id.navigation_telefonoFijo);

        tvOpcion.setText(opcion);
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
                    Toast.makeText(getActivity().getApplicationContext(), R.string.camposVacios, Toast.LENGTH_SHORT).show();

                } else {
                    if (comprobarDatos()) {
                        MiThread miThread = new MiThread();
                        miThread.execute();
                    }


                }
            }


        });


        navigation_correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inCorreo= new Intent(Intent.ACTION_SEND);
                inCorreo=contactanos.FUNCION_CORREO(inCorreo);
                startActivity(Intent.createChooser(inCorreo, "Escoja un correo"));

            }
        });

        navigation_movil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inPhone = new Intent(Intent.ACTION_DIAL);
                String numeroMovil = getString(R.string.numeroTel);
                inPhone=contactanos.FUNCION_MOVIL(numeroMovil, inPhone);
                startActivity(inPhone);

            }
        });

        navigation_telefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                String numeroFijo= getString(R.string.numeroFij);
                intent=contactanos.FUNCION_FIJO(numeroFijo, intent);
                startActivity(intent);

            }
        });

        return v;
    }

    private boolean comprobarDatos() {

        String emailInput = editEmail.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(getActivity().getApplicationContext(), R.string.correoMal, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (editPhone.getText().toString().trim().length() < 9) {
                Toast.makeText(getActivity().getApplicationContext(), R.string.telefonoMal, Toast.LENGTH_SHORT).show();
                return false;
            }


        }
        return true;
    }

    @SuppressLint("StaticFieldLeak")
    public class MiThread extends AsyncTask<Integer, Integer, Integer> {
        private ProgressDialog progreso;

        @Override
        protected void onPreExecute(){
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


            Map<String, Object> updateMap = new HashMap();
            updateMap.put("Apellido", editSurName.getText().toString());
            updateMap.put("Nombre", editName.getText().toString());
            updateMap.put("Direccion", editDireccion.getText().toString());
            updateMap.put("Telefono", editPhone.getText().toString());
            updateMap.put("Comentario_Usuario", etComments.getText().toString());
            updateMap.put("Nombre_Tarea", tvOpcion.getText().toString());
            updateMap.put("E-mail", editEmail.getText().toString());



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
            //Crear bundle, que son los datos que pasaremos
            Bundle datosAEnviar = new Bundle();
            // Aquí pon todos los datos que quieras en formato clave, valor
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
