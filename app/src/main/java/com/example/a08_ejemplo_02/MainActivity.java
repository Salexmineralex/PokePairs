package com.example.a08_ejemplo_02;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout linearLayout;
    int numColumnas;
    int numFilas;
    ImageButton imageButton1;
    ImageButton imageButton2;

    ArrayList<Integer> ImagenesAlt;

    ArrayList<Integer> aleatoriosP;


    ArrayList<Integer> imagenes = new ArrayList<Integer>(Arrays.asList(R.drawable.f1,R.drawable.f2,R.drawable.f3,R.drawable.f4,R.drawable.f5,
            R.drawable.f6,R.drawable.f7,R.drawable.f8,R.drawable.f9,R.drawable.f10,R.drawable.f11,R.drawable.f12,R.drawable.f13,R.drawable.f14,
            R.drawable.f15,R.drawable.f16,R.drawable.f17,R.drawable.f18));


    GridLayout gridLayout ;

    Boolean ArrayPulsados[];

    Boolean Marcados[];

    String dificultad;

    int Filas;

    int numClicks = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);


        dificultad = getIntent().getStringExtra("dificultad");

        System.out.println(dificultad);

        if(dificultad.equals("Facil"))
        {
            Filas = 2;


        }

        if(dificultad.equals("Medio"))
        {

            Filas = 4;



        }

        if(dificultad.equals("Dificil"))
        {

            Filas = 6;



        }

        gridLayout.setColumnCount(Filas);

        gridLayout.setRowCount(Filas);





        buscarimagenes();
        //clickStart();
        crearpulsados();
        añadeBotones();




    }






    public void añadeBotones()
    {



        System.out.println("********************LLEGO AL METODO");

        int numColumnas = gridLayout.getColumnCount();
        int numFilas = gridLayout.getRowCount();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        ImageButton imageButton;


        for (int i = 0; i < numColumnas*numFilas; i++) {
            imageButton = new ImageButton(this);
            //Hay que dimensionar la imagen al tamaño que pueda caber en el layout

            imageButton.setLayoutParams(new ViewGroup.LayoutParams(displayMetrics.widthPixels / numColumnas,
                    displayMetrics.heightPixels / numFilas));
            //He puesto estas imágenes para probar

            System.out.println("ID QUE PONGO "+i);
            imageButton.setImageResource(R.drawable.pokebola);
            imageButton.setId(i);
            imageButton.setBackgroundColor(Color.WHITE);
            imageButton.setAdjustViewBounds(Boolean.parseBoolean("true"));
            imageButton.setOnClickListener(this);
            gridLayout.addView(imageButton);
            animar(imageButton);

        }
    }
    //En este ejemplo implement la interfaz View.OnClickListener
//En el siguiente implement un método propio de cada imagen

    public void onClick (View v)
    {


        ImageButton iv = (ImageButton) v;

        if(ArrayPulsados[iv.getId()] == false)
        {



            numClicks++;

        if (numClicks == 1)
        {
            imageButton1 = (ImageButton) v;

            if(ArrayPulsados[imageButton1.getId()] == false)
            {

                System.out.println("ID "+imageButton1.getId());

                imageButton1.setImageResource(ImagenesAlt.get(imageButton1.getId()));



                ArrayPulsados[imageButton1.getId()] = true;
                animar2(imageButton1);
            }




        } else if(numClicks <= 2)
        {
            imageButton2 = (ImageButton) v;

            if(ArrayPulsados[imageButton2.getId()] == false)
            {


                System.out.println("ID2 "+imageButton2.getId());
            imageButton2.setImageResource(ImagenesAlt.get(imageButton2.getId()));

            animar2(imageButton2);

            ArrayPulsados[imageButton1.getId()] = true;

            if(!ImagenesAlt.get(imageButton1.getId()).equals(ImagenesAlt.get(imageButton2.getId())) && Marcados[imageButton1.getId()]==false && Marcados[imageButton2.getId()]==false)
            {

                System.out.println("Imagen1"+ ImagenesAlt.get(imageButton1.getId())+" Imagen2"+ImagenesAlt.get(imageButton2.getId()));




            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageButton2.setImageResource(R.drawable.pokebola);
                    imageButton1.setImageResource(R.drawable.pokebola);
                    animar2(imageButton1);
                    animar2(imageButton2);
                    ArrayPulsados[imageButton1.getId()] = false;
                    ArrayPulsados[imageButton1.getId()] = false;
                    numClicks = 0;
                }
            }, 2000);
            }else
                {
                    Marcados[imageButton1.getId()]= true;
                    Marcados[imageButton2.getId()]=true;
                    ArrayPulsados[imageButton1.getId()] = true;
                    ArrayPulsados[imageButton2.getId()] = true;
                    numClicks = 0;
                    comprobarganar();

                }
        }
        }
        }
    }


    public void animar(ImageButton ImageButton)
    {


        final Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                android.R.interpolator.fast_out_slow_in);


        ImageButton.animate()
                .rotationX(180)
                .setInterpolator(interpolador)
                .setDuration(300)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //Se repite una vez por cada uno de los 600 arranques de animación
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //Le devuelve a su tamaño original
                        ImageButton.animate()
                                .rotationX(360)
                                .setInterpolator(interpolador)
                                .setDuration(300)                        ;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        //Se repite una vez por cada uno de los 600 paros de animación
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });



    }

    public void animar2(ImageButton ImageButton)
    {


        final Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                android.R.interpolator.fast_out_slow_in);


        ImageButton.animate()
                .rotationY(180)
                .setInterpolator(interpolador)
                .setDuration(300)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //Se repite una vez por cada uno de los 600 arranques de animación
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //Le devuelve a su tamaño original
                        ImageButton.animate()
                                .rotationY(360)
                                .setInterpolator(interpolador)
                                .setDuration(300)                        ;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        //Se repite una vez por cada uno de los 600 paros de animación
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });



    }

    public void crearpulsados()
    {

        ArrayPulsados = new Boolean[gridLayout.getColumnCount()*gridLayout.getRowCount()];

        Marcados = new Boolean[gridLayout.getColumnCount()*gridLayout.getRowCount()];

        for (int i = 0; i <ArrayPulsados.length ; i++)
        {

            ArrayPulsados[i] = false;

            Marcados[i] = false;


        }


    }


    public  void Aleatorios(int arraya[])
    {

        int num;

        for( int j = 0 ; j < arraya.length; j++)
        {

            do
            {


                num = (int) (Math.random()*gridLayout.getRowCount()*gridLayout.getColumnCount());
                //System.out.println("NUM:"+num+" "+arraya.length+" j"+ j);





            }while(norepetir(arraya,num));




            System.out.println(num);
            arraya[j] = num;

            for (int i = 0; i < arraya.length ; i++)
            {

                System.out.println(arraya[i]);

            }

        }




    }

    public  boolean norepetir(int[] arraya, int num)
    {

        boolean repetir = false;

        for (int i = 0; i < arraya.length ; i++)
        {

            if(arraya[i] == num)
            {
                repetir = true;

                //System.out.println(repetir+" "+num+" "+arraya[i]);

            }

        }

        return repetir;

    }

    public void buscarimagenes()
    {



        ImagenesAlt = new ArrayList<>();

        Collections.shuffle(imagenes);

        int pos = 0;

        for (int i = 0; i <(gridLayout.getRowCount()*gridLayout.getColumnCount())/2 ; i++)
        {


            System.out.println(i);
            ImagenesAlt.add(imagenes.get(i));




        }

        for (int i = 0; i <(gridLayout.getRowCount()*gridLayout.getColumnCount())/2 ; i++)
        {

            ImagenesAlt.add(imagenes.get(i));



        }

        System.out.println("IMAGENES" +ImagenesAlt);



    }

    public void comprobarganar()
    {
        Boolean ganar = true;

        for(int i = 0; i < Marcados.length; i++)
        {

            if(Marcados[i] == false)
            {

                ganar = false;

            }




        }


        if(ganar)
        {

            


            Intent i = new Intent(this, MenuFinal.class);
            i.putExtra("dificultad",dificultad);
            startActivity(i);

            ViewGroup v = (ViewGroup) ((ViewGroup) this
                    .findViewById(android.R.id.content)).getChildAt(0);
            Snackbar.make(v, "Has Ganado", Snackbar.LENGTH_LONG).show();
            System.out.println("Has ganado");

        }



    }

}

