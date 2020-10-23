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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        btn=view.findViewById(R.id.btnIniciar);
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
        Bundle datosRecuperados = getArguments();

        if (datosRecuperados != null) {
            // Y ahora puedes recuperar usando get en lugar de put

            boolean toastFormulario = datosRecuperados.getBoolean("tForm");

            if (toastFormulario){
                Toast.makeText(getActivity(),"Su solicitud se ha almacenado correctamente",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getActivity(),"Ha habido un error con su solicitud. Vuelva a intentarlo mas tarde.",Toast.LENGTH_LONG).show();
            }
        }


        return view;

    }


}
