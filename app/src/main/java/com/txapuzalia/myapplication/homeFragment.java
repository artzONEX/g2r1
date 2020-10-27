package com.txapuzalia.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class homeFragment extends Fragment {
    Button btn;

    public ImageView flecha;
    public TextView textView3;
    public ScrollView scrollView2;
    public ImageView logo;
    private ImageSwitcher imageSwitcher;
    private int[] galeria = {R.drawable.portada, R.drawable.portada2, R.drawable.portada3};
    private int posicion;
    private static final int DURACION = 2000;

    public VideoView VideoHome;


    private Toolbar toolbar;
    private DrawerLayout dl;
    private NavigationView nv;

    String URL1="https://ia601500.us.archive.org/31/items/flecha_20201022/flecha.png";

    @SuppressLint("CutPasteId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        view = inflater.inflate(R.layout.fragment_home, container, false);
        btn=view.findViewById(R.id.btnIniciar);


        flecha = view.findViewById(R.id.flecha);
        textView3 = view.findViewById(R.id.textView3);
        scrollView2 = view.findViewById(R.id.scrollView2);
        logo = view.findViewById(R.id.logo);
        VideoHome = view.findViewById(R.id.videoview);

        //getActivity().findViewById(R.id.drawer_layout);

        dl= view.findViewById(R.id.drawer_layout);
        nv= view.findViewById(R.id.navigation_informacion);

        toolbar=view.findViewById(R.id.main_toolbar);

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



        //Si el vídeo está detrás de una URL
        String videoPath = "https://archive.org/download/video3_202010/video3.mp4";
        Uri videoUri = Uri.parse(videoPath);
        VideoHome.setVideoURI(videoUri);

        //AÑADIMOS LOS CONTROLES DE REPRODUCIR VIDEO
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(VideoHome);
        VideoHome.setMediaController(mediaController);

        //INICIAMOS EL VIDEO AUTOMATICAMENTE
        VideoHome.start();






        /* ESTE PROCESO ES EL DE HACER DESAPARECER O APARECER EL MENSAJE DESLIZA*/
        scrollView2.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int maxDistance = logo.getHeight();
                int movement = scrollView2.getScrollY();
                float alphaFactor = ((movement * 1.0f) / (maxDistance - flecha.getHeight()));
                if (alphaFactor > 0.14 ) {
                    textView3.setAlpha(0f);
                    flecha.setAlpha(0f);
                }else if( alphaFactor <= 0.2){

                    textView3.setAlpha(1f);
                    flecha.setAlpha(1f);
                }
            }
        });



        Picasso.get()
                .load(URL1)

                .into(flecha);
                //.placeholder(R.drawable.flecha)
              //  .error(R.drawable.flecha)
        ;



        Picasso.get()
                .load(URL1)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                        try {
                            File data = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() );
                            if (!data.exists()) {
                                data.mkdirs();
                            }
                            FileOutputStream fileOutputStream = new FileOutputStream(new File(data, new Date().toString() + ".png"));
                            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            Log.d("GUARDADO", "GUARDADO GUARDADO GUARDADO");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Log.d("FALLO", "FALLO FALLO FALLO");
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            Log.d("FALLO", "FALLO FALLO FALLO");
                        }
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }


                });





        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        imageSwitcher.setImageResource(galeria[posicion]);
                        posicion++;
                        if (posicion == galeria.length)
                            posicion = 0;
                    }
                });
            }
        }, 0, DURACION);




        return view;

    }


}
