package com.jacksonjao.jhon.who;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main extends AppCompatActivity {

    int contador;
    int segundos;
    boolean iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        contador=0;
        segundos=0;
        iniciar=true;
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(iniciar){

                    try {
                        Thread.sleep(1000);
                        contador++;
                        if(contador==3){

                            iniciar=false;
                            Intent intent= new Intent(Main.this,Ip.class);
                            startActivity(intent);
                            Main.this.finish();
                        }
                        System.out.println(contador);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }

                }
            }
        }).start();

        System.out.println("Holi");
    }


}
