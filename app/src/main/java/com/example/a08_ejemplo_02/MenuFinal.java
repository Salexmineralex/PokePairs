package com.example.a08_ejemplo_02;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;

public class MenuFinal extends AppCompatActivity {

    ImageView iv;

    ImageView iv2;

    String dificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_final);

        iv = findViewById(R.id.imageView2);

        iv2 = findViewById(R.id.imageView3);

        animar(iv);

        animar(iv2);

        dificultad = getIntent().getStringExtra("dificultad");

    }

    public void Volver(View view)
    {

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("dificultad",dificultad);
        startActivity(i);
    }

    public void MenuPrincipal(View view)
    {
        Intent i = new Intent(this, MainMenu.class);
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