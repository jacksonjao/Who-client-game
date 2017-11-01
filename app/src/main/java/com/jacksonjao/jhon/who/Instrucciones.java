package com.jacksonjao.jhon.who;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import envio.NumeroDeUsuarios;

public class Instrucciones extends AppCompatActivity implements Observer {
TextView user, esperando;
    boolean agregarUsuario;
    Button boton;
    EditText numJugadores;
    NumeroDeUsuarios numeroDeUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
Conexion.getInstance().addObserver(this);
        boton= (Button) findViewById(R.id.btn_ready);
        user=(TextView)findViewById(R.id.tv_users);
        user.setText(getIntent().getStringExtra("usuario"));
        esperando=(TextView)findViewById(R.id.esperando);
        agregarUsuario=true;
numJugadores=(EditText)findViewById(R.id.et_numJugadores);
    }

    public void ready(View v){

        if ((numJugadores.getText().toString().matches("[a-zA-Z_0-9]+")) && (!(numJugadores.getText().toString().contains(" ")))&&numJugadores.getText()!=null) {
            numeroDeUsuarios= new NumeroDeUsuarios(Integer.parseInt(numJugadores.getText().toString()));

            if(agregarUsuario) {
            try {
                Conexion.getInstance().enviar(numeroDeUsuarios);
                Conexion.getInstance().enviar("listo");
                boton.setText("Esperando...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }else{
            Toast.makeText(this,"Agregue un número de jugadores",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void update(Observable observable, Object data) {
final String datos= data.toString();

        if(data instanceof String){


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(agregarUsuario==true) {
                        esperando.setText("Usuario: "+datos+"/"+numeroDeUsuarios.getCantidadUsuarios());
                        agregarUsuario=false;
                    }
                }
            });


            if(data.equals(Integer.toString(numeroDeUsuarios.getCantidadUsuarios()))){
                Intent intent= new Intent(this, Interaccion.class);
                intent.putExtra("usuario",user.getText().toString());
                intent.putExtra("usuariosTotal",data.toString() );
                Conexion.getInstance().deleteObservers();
                startActivity(intent);
                finish();
            }

        }


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }

}
