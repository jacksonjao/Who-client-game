package com.jacksonjao.jhon.who;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import envio.UserRanking;
import envio.Usuario;

public class Ranking extends AppCompatActivity implements Observer {
    TextView ranking, rankingTotal,tiempo;

    ArrayList<UserRanking> userRankings;
    String usuarios, usuariosTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        userRankings = new ArrayList<UserRanking>();
        Conexion.getInstance().deleteObservers();
        Conexion.getInstance().addObserver(this);
        usuarios = "";
        usuariosTotal = "";
        String[] partes = getIntent().getStringExtra("ranking").toString().split(":");

        UserRanking userRanking = new UserRanking(getIntent().getStringExtra("yo"), getIntent().getStringExtra("ranking"), partes[0] + partes[1] + partes[2]);
        try {
            Conexion.getInstance().enviar(userRanking);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //   userRankings.add( new UserRanking(getIntent().getStringExtra("yo"),getIntent().getStringExtra("ranking"),partes[0]+partes[1]+partes[2]));


        tiempo = (TextView) findViewById(R.id.tv_tiempo);
        rankingTotal = (TextView) findViewById(R.id.tv_ranking_user);
        ranking = (TextView) findViewById(R.id.tv_ranking);
        // ranking.setText(userRanking.getUsuario()+"; "+userRanking.getTiempo()+"; "+userRanking.getTiempoTotal());

tiempo.setText(getIntent().getStringExtra("ranking"));
    }


    @Override
    public void update(Observable observable, Object data) {

        if (data instanceof Collection) {
            userRankings.clear();
            usuariosTotal="";
                    usuarios="";
            userRankings.addAll((Collection<? extends UserRanking>) data);

            Collections.sort(userRankings, new CompararRanking());

            for (int i = 0; i < userRankings.size(); i++) {
                if (i < 9) {
                    usuarios += "00" + (i + 1) + " - "+userRankings.get(i).getUsuario() + "\n";
                    usuariosTotal += userRankings.get(i).getTiempo() + "\n";
                }
                if (i > 9 && i < 99) {
                    usuarios += "0" + (i + 1) + " - "+userRankings.get(i).getUsuario() + "\n";
                    usuariosTotal += userRankings.get(i).getTiempo() + "\n";
                }
                if (i > 99) {
                    usuarios += (i + 1) + " - "+userRankings.get(i).getUsuario() + "\n";
                    usuariosTotal += userRankings.get(i).getTiempo() + "\n";
                }

            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ranking.setText(usuarios);
                    rankingTotal.setText(usuariosTotal);
                }
            });




        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}