package com.jacksonjao.jhon.who;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Ip extends AppCompatActivity {
    TextView ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ip= (TextView) findViewById(R.id.et_ip);

    }

    public void entrar(View v) {

        //  TareaAsincrona.setGetIp(ip.getText().toString());
        Conexion.getInstance().setIp(ip.getText().toString());

        Intent intent= new Intent(Ip.this,Login.class);
        startActivity(intent);
        finish();
    }
}