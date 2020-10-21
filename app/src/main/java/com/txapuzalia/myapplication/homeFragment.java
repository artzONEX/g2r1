package com.txapuzalia.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

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
    public Activity MainActivity;


    @SuppressLint("CutPasteId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        Fragment fragment;
        view = inflater.inflate(R.layout.fragment_home, container, false);

        flecha = view.findViewById(R.id.flecha);
        textView3 = view.findViewById(R.id.textView3);
        scrollView2 = view.findViewById(R.id.scrollView2);
        logo = view.findViewById(R.id.logo);
        VideoHome = view.findViewById(R.id.videoview);

        //getActivity().findViewById(R.id.drawer_layout);

        dl= view.findViewById(R.id.drawer_layout);
        nv= view.findViewById(R.id.navigation_informacion);

        toolbar=view.findViewById(R.id.main_toolbar);
        //toolbar.setTitleTextColor(Color.WHITE);
        //setSupportActionBar(toolbar);
        /*NavigationView navigationView = view.findViewById(R.id.navigation_informacion);
        navigationView.setNavigationItemSelectedListener(navigationView.this);


         */
        nv.setItemIconTintList(null);
        ActionBarDrawerToggle actionBarDrawerToggle= new ActionBarDrawerToggle(
                MainActivity,
                dl,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();



        //Añadimos el video

        /*PRUEBA 4
        MediaController mc= new MediaController(getContext());

        String path = "android.resource://";
        VideoHome.setVideoURI(Uri.parse(path));
        VideoHome.setMediaController(mc);
        VideoHome.start();


       /
      //PRUEBA 3
      /*



        VideoHome.requestFocus();


       String videopath = "https://archive.org/details/video3_202010";

       VideoHome.setVideoURI(Uri.parse(videopath));

       VideoHome.setMediaController(new MediaController(getActivity())); //error in (this)[enter image description here][1]

       VideoHome.requestFocus();
       VideoHome.start();*
        */

        //PRUEBA 2

        //String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.drawable.video;
        //Si el vídeo está detrás de una URL
        String videoPath = "https://archive.org/download/video3_202010/video3.mp4";
        Uri videoUri = Uri.parse(videoPath);
        VideoHome.setVideoURI(videoUri);


        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(VideoHome);
        VideoHome.setMediaController(mediaController);


        /*android.widget.MediaController mediaController = null;
        VideoHome.setMediaController(new MediaController(this));
        VideoHome.setMediaController(mediaController);
        mediaController.setAnchorView(VideoHome);



        /*
        VideoHome.setMediaController(new MediaController(getActivity()));


        MediaController mediaController = new MediaController(getActivity());
        VideoHome.setMediaController(mediaController);
        mediaController.setAnchorView(VideoHome);



        VideoHome.requestFocus();



        /*VideoHome.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d("VideoHome", "ready to play");
                //Si queremos auto-start
                VideoHome.start();
            }
        });*/

        /* PRUEBA !
        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.drawable.video;
        Uri uri = Uri.parse(videoPath);
        VideoHome.setVideoURI(uri);
        /*MediaController mediaController = new MediaController(getActivity());
        VideoHome.setMediaController(mediaController);
        mediaController.setAnchorView(VideoHome);*/

        VideoHome.start();


        /* ESTA ES LA FUNCION DEL SLIDER AUTOMÁTICO*/
        imageSwitcher = view.findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                return imageView;
            }
        });
        Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);


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



        btn=view.findViewById(R.id.btnIniciar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FragmentManager fm = getFragmentManager();
                assert fm != null;
                FragmentTransaction ft = fm.beginTransaction();
                formularioFragment llf = new formularioFragment();
                ft.replace(R.id.fragment_container, llf);
                ft.commit();*/

                /*ActionBarDrawerToggle actionBarDrawerToggle= new ActionBarDrawerToggle(
                        getActivity(),
                        dl,
                        toolbar,
                        R.string.openNavDrawer,
                        R.string.closeNavDrawer
                );
                dl.addDrawerListener(actionBarDrawerToggle);
                actionBarDrawerToggle.syncState();
                nv.setNavigationItemSelectedListener(MainActivity);*/

                /*getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new homeFragment()).commit();
                //navigationView.getContext().setCheckedItem(R.id.navigation_home);

                 */

                //dl.openDrawer(dl);

                dl.openDrawer(GravityCompat.START);


            }


        });
        return view;

    }

}
