package com.txapuzalia.myapplication;

import android.content.Intent;
import android.net.Uri;

public class contactanos {

    public static Intent FUNCION_FIJO(String numerofijo, Intent inte){

        inte.setData(Uri.parse("tel:" + numerofijo));
        return inte;

    }

    public static Intent FUNCION_MOVIL(String numeroMovil, Intent intent){
        intent.setData(Uri.parse("tel:" + numeroMovil));
        return intent;
    }

    public static Intent FUNCION_CORREO(Intent in){
        in.putExtra(Intent.EXTRA_EMAIL, new String[] { "txapuzalia@gmail.com" });
        in.putExtra(Intent.EXTRA_SUBJECT, "Duda/Queja");
        in.setType("message/rfc822");

        return in;
    }
}
