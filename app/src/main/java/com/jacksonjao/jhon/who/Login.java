package com.jacksonjao.jhon.who;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import envio.Usuario;

public class Login extends AppCompatActivity implements Observer {
    String usuario;
    String pass;
    EditText mail, contrasena;
    Conexion conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        conexion = Conexion.getInstance();
        conexion.addObserver(this);
        mail = (EditText) findViewById(R.id.et_email);
        contrasena = (EditText) findViewById(R.id.et_id);

    }


    public void entrar(View v) {
        usuario = mail.getText().toString();
        pass = contrasena.getText().toString();
        Usuario user = new Usuario(usuario, pass, "inicio");
        try {
            conexion.enviar(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

      /*  Intent intent = new Intent("descubrir");
        startActivity(intent);
        finish();*/

    }

    public void registrate(View v) {
        Conexion.getInstance().deleteObservers();
        Intent intent = new Intent(this, Registro.class);

        startActivity(intent);
        finish();
    }

    @Override
    public void update(Observable observable, Object data) {
        final String mensaje = data.toString();
        System.out.println(mensaje);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!(mensaje.toString().contains("denegado"))) {
                    Toast toast = Toast.makeText(Login.this, "Sesión iniciada", Toast.LENGTH_SHORT);
                    toast.show();
                    conexion.deleteObserver(Login.this);
                    Intent intent = new Intent(Login.this, Instrucciones.class);
                    intent.putExtra("usuario", mensaje);
                    startActivity(intent);
                    Login.this.finish();
                } else {
                    Toast toast = Toast.makeText(Login.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
     System.exit(0);
    }
}
