package com.jacksonjao.jhon.who;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import envio.Usuario;

public class Registro extends AppCompatActivity implements Observer {
    EditText mail, contrasena;
    String usuario;
    String pass;
    Conexion conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        conexion = Conexion.getInstance();
        // conexion.deleteObserver(this);
        conexion.addObserver(this);

        System.out.println(conexion.countObservers());
        mail = (EditText) findViewById(R.id.et_email);
        contrasena = (EditText) findViewById(R.id.et_id);
    }

    public void registrarse(View v) {

        usuario = mail.getText().toString();
        pass = contrasena.getText().toString();
        if ((usuario.matches("[a-zA-Z_0-9]+")) && (!(usuario.contains(" "))) && (pass.matches("[a-zA-Z_0-9]+")) && (!(pass.contains(" ")))) {
            if (usuario.length() > 3) {

                if (pass.length() > 3) {
                    Usuario user = new Usuario(usuario, pass, "registro");
                    try {
                        conexion.enviar(user);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast toast = Toast.makeText(this, "La contraseña debe tener más de 3 caracteres", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(this, "El usuario debe tener más de 3 caracteres", Toast.LENGTH_SHORT);
                toast.show();
            }


        }

        // finish();

    }

    @Override
    public void update(Observable observable, Object data) {
        System.out.println(data);

        final String mensaje = data.toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mensaje.toString().contains("aprovado")) {
                    Toast toast = Toast.makeText(Registro.this, "Usuario registrado", Toast.LENGTH_SHORT);
                    toast.show();
                    conexion.deleteObserver(Registro.this);
                    Intent intent = new Intent(Registro.this, Login.class);
                    startActivity(intent);
                    Registro.this.finish();
                }else{
                    Toast toast = Toast.makeText(Registro.this, "Este usuario ya existe", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Conexion.getInstance().deleteObservers();
        Intent intent = new Intent(Registro.this, Login.class);
        startActivity(intent);
        Registro.this.finish();
    }
}
