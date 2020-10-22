package com.txapuzalia.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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
import android.widget.Toast;
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

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Random;
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



        //String EDteamImage = "https://ed.team/sites/default/files/styles/16_9_medium/public/2018-04/guia-de-estilos.jpg?itok=73JysFzx";
        //Glide.with(getApplicationContext()).load(EDteamImage).into(flecha);





        Picasso.get()
                .load(URL1)

                .into(flecha);
                /*.placeholder(R.drawable.flecha)
                .error(R.drawable.flecha)*/
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
    


        /*try {
            flecha = view.findViewById(R.id.flecha);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL("https://ia601500.us.archive.org/31/items/flecha_20201022/flecha.png").getContent());
            flecha.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*private Target picassoImageTarget(Context context, final String imageDir, final String imageName) {
            Log.d("picassoImageTarget", " picassoImageTarget");
            ContextWrapper cw = new ContextWrapper(context);
            final File directory = cw.getDir(imageDir, Context.MODE_PRIVATE); // path to /data/data/yourapp/app_imageDir
            return new Target() {
                @Override
                public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final File myImageFile = new File(directory, imageName); // Create image file
                            FileOutputStream fos = null;
                            try {
                                fos = new FileOutputStream(myImageFile);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    fos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            Log.i("image", "image saved to >>>" + myImageFile.getAbsolutePath());

                        }
                    }).start();
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                }
                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    if (placeHolderDrawable != null) {}
                }
            };
        }*/








        //Añadimos el video

        //Si el vídeo está en local
        //String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.drawable.video;

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

                //dl.openDrawer(GravityCompat.START);

                //dl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);

                //dl.openDrawer(Your View, Usually a ListView);

                //dl.openDrawer(nv);

                //dl.openDrawer(Gravity.START);

                ((MainActivity) getActivity()).openDrawer();




            }


        });


        return view;

    }




}


