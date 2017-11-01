package com.jacksonjao.jhon.who;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import envio.UserPerdedor;

public class Ganador extends AppCompatActivity implements Observer {
RelativeLayout relativeLayout;
    TextView yo, el;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganador);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Conexion.getInstance().addObserver(this);
        relativeLayout=(RelativeLayout) findViewById(R.id.rl_ganador);
yo = (TextView)findViewById(R.id.tv_yo);
        el=(TextView) findViewById(R.id.tv_el);
        if(getIntent().getStringExtra("ganador").equals("ganaste")){
            relativeLayout.setBackground(getDrawable(R.drawable.ganaste));
yo.setText(getIntent().getStringExtra("yo"));

        }
        if(getIntent().getStringExtra("ganador").equals("perdiste")){
            relativeLayout.setBackground(getDrawable(R.drawable.perdiste));
            el.setText("");
            yo.setText("Perdiste");
            UserPerdedor userPerdedor= new UserPerdedor(getIntent().getStringExtra("yo"));
            try {
                Conexion.getInstance().enviar(userPerdedor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void ranking(View v){

        Intent intent=new Intent(this, Ranking.class);
        intent.putExtra("ranking",getIntent().getStringExtra("ranking"));
        intent.putExtra("yo",getIntent().getStringExtra("yo"));
        Conexion.getInstance().deleteObservers();
        finish();

        startActivity(intent);


    }

    @Override
    public void update(Observable observable, Object data) {

        if (data instanceof UserPerdedor) {


final UserPerdedor userPerdedor= (UserPerdedor) data;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(getIntent().getStringExtra("ganador").equals("ganaste")){
                    el.setText(el.getText()+"\n"+"\n"+userPerdedor.getName());

                }
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
