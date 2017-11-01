package com.jacksonjao.jhon.who;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

public class Interaccion extends AppCompatActivity implements Observer{
    TextView time, usuarios, perfil;
String ganador;
    ImageView[] imagen = new ImageView[12];
    Integer[] carta = new Integer[7];
    ArrayList<Item> items;
    Bitmap bm;
    String usuarioPerdedor;
    int turno, id1, id2;
    boolean[] encontrada = new boolean[12];
    int pos1, pos2;


long millis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaccion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        items = new ArrayList<Item>();
        imagen[0] = (ImageView) findViewById(R.id.iv_cardUno);
        imagen[1] = (ImageView) findViewById(R.id.iv_cardDos);
        imagen[2] = (ImageView) findViewById(R.id.iv_cardTres);
        imagen[3] = (ImageView) findViewById(R.id.iv_cardCuatro);
        imagen[4] = (ImageView) findViewById(R.id.iv_cardCinco);
        imagen[5] = (ImageView) findViewById(R.id.iv_cardSeis);
        imagen[6] = (ImageView) findViewById(R.id.iv_cardSiete);
        imagen[7] = (ImageView) findViewById(R.id.iv_cardOcho);
        imagen[8] = (ImageView) findViewById(R.id.iv_cardNueve);
        imagen[9] = (ImageView) findViewById(R.id.iv_cardDiez);
        imagen[10] = (ImageView) findViewById(R.id.iv_cardOnce);
        imagen[11] = (ImageView) findViewById(R.id.iv_cardDoce);
        perfil = (TextView) findViewById(R.id.tv_perfil);

        perfil.setText(getIntent().getStringExtra("usuario"));
Conexion.getInstance().addObserver(this);
        time = (TextView) findViewById(R.id.tv_time);
        usuarios = (TextView) findViewById(R.id.tv_users);
        perfil = (TextView) findViewById(R.id.tv_perfil);

usuarios.setText(getIntent().getStringExtra("usuariosTotal"));
        carta[0] = R.drawable.carta0;
        carta[1] = R.drawable.carta1;
        carta[2] = R.drawable.carta2;
        carta[3] = R.drawable.carta3;
        carta[4] = R.drawable.carta4;
        carta[5] = R.drawable.carta5;
        carta[6] = R.drawable.card;//tapada
        for (int i = 0; i < carta.length - 1; i++) {
            items.add(new Item(i, carta[i]));
            items.add(new Item(i, carta[i]));
        }

        Collections.shuffle(items);


    }

    public void evaluar(int pos1, int pos2, int id1, int id2) {


        if (id1 == id2) {

            encontrada[pos1] = true;
            encontrada[pos2] = true;
            if (encontrada[0] == true && encontrada[1] == true && encontrada[2] == true && encontrada[3] == true && encontrada[4] == true && encontrada[5] == true && encontrada[6] == true && encontrada[7] == true && encontrada[8] == true && encontrada[9] == true && encontrada[10] == true && encontrada[11] == true) {
                Toast.makeText(Interaccion.this, "Terminaste!, esperando a los otros usuarios...", Toast.LENGTH_SHORT).show();

                try {
                    Conexion.getInstance().enviar("termino");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } else {


            encontrada[pos1] = false;
            encontrada[pos2] = false;
            imagen[pos1].setBackgroundResource(R.drawable.card);
            imagen[pos2].setBackgroundResource(R.drawable.card);

        }

        turno = 0;


    }


    public void uno(View v) {

        if (encontrada[0] == false) {
            imagen[0].setBackgroundResource(items.get(0).getImagen());
            encontrada[0] = true;
            turno += 1;

            if (turno == 1) {
                pos1 = 0;
                id1 = items.get(pos1).getId();
            }
            if (turno == 2) {
                pos2 = 0;
                id2 = items.get(pos2).getId();
                evaluar(pos1, pos2, id1, id2);
            }
        }


    }

    public void dos(View v) {
        if (encontrada[1] == false) {
            imagen[1].setBackgroundResource(items.get(1).getImagen());
            encontrada[1] = true;
            turno += 1;

            if (turno == 1) {
                pos1 = 1;
                id1 = items.get(pos1).getId();
            }
            if (turno == 2) {
                pos2 = 1;
                id2 = items.get(pos2).getId();
                evaluar(pos1, pos2, id1, id2);
            }
        }
    }

    public void tres(View v) {
        if (encontrada[2] == false) {

            imagen[2].setBackgroundResource(items.get(2).getImagen());
            encontrada[2] = true;
            turno += 1;

            if (turno == 1) {
                pos1 = 2;
                id1 = items.get(pos1).getId();
            }
            if (turno == 2) {
                pos2 = 2;
                id2 = items.get(pos2).getId();
                evaluar(pos1, pos2, id1, id2);
            }
        }
    }

    public void cuatro(View v) {
        if (encontrada[3] == false) {
            imagen[3].setBackgroundResource(items.get(3).getImagen());
            encontrada[3] = true;
            turno += 1;

            if (turno == 1) {
                pos1 = 3;
                id1 = items.get(pos1).getId();
            }
            if (turno == 2) {
                pos2 = 3;
                id2 = items.get(pos2).getId();
                evaluar(pos1, pos2, id1, id2);
            }

        }
    }

    public void cinco(View v) {
        if (encontrada[4] == false) {
            imagen[4].setBackgroundResource(items.get(4).getImagen());
            encontrada[4] = true;
            turno += 1;

            if (turno == 1) {
                pos1 = 4;
                id1 = items.get(pos1).getId();
            }
            if (turno == 2) {
                pos2 = 4;
                id2 = items.get(pos2).getId();
                evaluar(pos1, pos2, id1, id2);
            }
        }
    }

    public void seis(View v) {
        if (encontrada[5] == false) {
            imagen[5].setBackgroundResource(items.get(5).getImagen());
            encontrada[5] = true;
            turno += 1;

            if (turno == 1) {
                pos1 = 5;
                id1 = items.get(pos1).getId();
            }
            if (turno == 2) {
                pos2 = 5;
                id2 = items.get(pos2).getId();
                evaluar(pos1, pos2, id1, id2);
            }
        }
    }

    public void siete(View v) {
        if (encontrada[6] == false) {
            imagen[6].setBackgroundResource(items.get(6).getImagen());
            encontrada[6] = true;
            turno += 1;

            if (turno == 1) {
                pos1 = 6;
                id1 = items.get(pos1).getId();
            }
            if (turno == 2) {
                pos2 = 6;
                id2 = items.get(pos2).getId();
                evaluar(pos1, pos2, id1, id2);
            }
        }
    }

    public void ocho(View v) {
        if (encontrada[7] == false) {
            imagen[7].setBackgroundResource(items.get(7).getImagen());
            encontrada[7] = true;
            turno += 1;

            if (turno == 1) {
                pos1 = 7;
                id1 = items.get(pos1).getId();
            }
            if (turno == 2) {
                pos2 = 7;
                id2 = items.get(pos2).getId();
                evaluar(pos1, pos2, id1, id2);
            }
        }
    }

    public void nueve(View v) {
        if (encontrada[8] == false) {
            imagen[8].setBackgroundResource(items.get(8).getImagen());
            encontrada[8] = true;
            turno += 1;

            if (turno == 1) {
                pos1 = 8;
                id1 = items.get(pos1).getId();
            }
            if (turno == 2) {
                pos2 = 8;
                id2 = items.get(pos2).getId();
                evaluar(pos1, pos2, id1, id2);
            }
        }
    }

    public void diez(View v) {
        if (encontrada[9] == false) {
            imagen[9].setBackgroundResource(items.get(9).getImagen());
            encontrada[9] = true;
            turno += 1;

            if (turno == 1) {
                pos1 = 9;
                id1 = items.get(pos1).getId();
            }
            if (turno == 2) {
                pos2 = 9;
                id2 = items.get(pos2).getId();
                evaluar(pos1, pos2, id1, id2);
            }
        }
    }

    public void once(View v) {
        if (encontrada[10] == false) {
            imagen[10].setBackgroundResource(items.get(10).getImagen());
            encontrada[10] = true;
            turno += 1;

            if (turno == 1) {
                pos1 = 10;
                id1 = items.get(pos1).getId();
            }
            if (turno == 2) {
                pos2 = 10;
                id2 = items.get(pos2).getId();
                evaluar(pos1, pos2, id1, id2);
            }
        }
    }

    public void doce(View v) {
        if (encontrada[11] == false) {
            imagen[11].setBackgroundResource(items.get(11).getImagen());
            encontrada[11] = true;
            turno += 1;

            if (turno == 1) {
                pos1 = 11;
                id1 = items.get(pos1).getId();
            }
            if (turno == 2) {
                pos2 = 11;
                id2 = items.get(pos2).getId();
                evaluar(pos1, pos2, id1, id2);
            }
        }
    }




    @Override
    public void update(Observable observable, Object data) {




        if(data instanceof String){
        if(data.equals("terminaronTodos")){
            Intent intent= new Intent(Interaccion.this,Ganador.class);
            intent.putExtra("ranking",time.getText());
            intent.putExtra("ganador",ganador);
            intent.putExtra("yo",perfil.getText());
            Conexion.getInstance().deleteObservers();
            finish();
            startActivity(intent);
        }

       if(data.equals("ganaste")){
ganador="ganaste";
       }

        if(data.equals("perdiste")){
            ganador="perdiste";

        }

        if(!data.equals("terminaronTodos")&&!data.equals("ganaste")&&!data.equals("perdiste")){
        final String tiempo = (String) data;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//stuff that updates ui
                time.setText(tiempo);
           time.setTextColor(Color.WHITE);

            }
        });
    }}}


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }

}
