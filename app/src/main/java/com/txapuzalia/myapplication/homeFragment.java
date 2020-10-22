package com.txapuzalia.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class homeFragment extends Fragment {
    Button btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        Fragment fragment;
        view = inflater.inflate(R.layout.fragment_home, container, false);
        btn=view.findViewById(R.id.btnIniciar);

        // -- UNA FUNCION QUE SE EJECUTA CUANDO SE CLICKA EL BOTÓN DE ABAJO, EL CUAL ABRE EL MENÚ LATERAL --
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                assert fm != null;
                FragmentTransaction ft = fm.beginTransaction();
                formularioFragment llf = new formularioFragment();
                ft.replace(R.id.fragment_container, llf);
                ft.commit();
            }


        });

        // -- UN CONTAINER QUE TRAE TODA LA INFORMACIÓN DEL FORMULARIO PARA SABER SI SE HA INSERTADO LA INFORMACIÓN EN LA BASE DE DATOS --
        Bundle datosRecuperados = getArguments();

        if (datosRecuperados != null) {
            // -- Y AHORA PUEDES RECUPERAR USANDO GET EN LUGAR DE PUT --

            boolean toastFormulario = datosRecuperados.getBoolean("tForm");
            // -- EN CASO DE QUE SE HAYA INSERTADO LA INFORMACIÓN EN LA BASE DE DATOS, ENSEÑARÁ UN MENSAJE EN EL QUE SE DICE QUE SE HA INSERTADO EN LA BASE DE DATOS --
            if (toastFormulario){
                Toast.makeText(getActivity(),"Su solicitud se ha almacenado correctamente",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getActivity(),"Ha habido un error con su solicitud. Vuelva a intentarlo mas tarde.",Toast.LENGTH_LONG).show();
            }
        }


        return view;

    }


}
