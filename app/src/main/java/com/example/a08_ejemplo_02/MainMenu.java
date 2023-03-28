package com.example.a08_ejemplo_02;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;

public class MainMenu extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        iv = findViewById(R.id.imageView);

        animar(iv);

    }

    public void comenzar(View view)
    {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void Facil(View view)
    {

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("dificultad","Facil");
        startActivity(i);


    }

    public void Medio(View view)
    {

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("dificultad","Medio");
        startActivity(i);



    }

    public void Dificil(View view)
    {

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("dificultad","Dificil");
        startActivity(i);


    }

    public void animar(View v)
    {

        final Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                android.R.interpolator.fast_out_slow_in);


        v.animate()
                .scaleX(0)
                .scaleY(0)
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
                        v.animate()
                                .scaleX(1)
                                .scaleY(1)
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
}