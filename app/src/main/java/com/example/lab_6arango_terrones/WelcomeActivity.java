package com.example.lab_6arango_terrones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    TextView name, id, mail, user_id;
    String nom, ced, correo, usertp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.InicializarControles();
    }

    public void InicializarControles(){
        name = (TextView)findViewById(R.id.txtnom);
        id = (TextView)findViewById(R.id.txtced);
        mail = (TextView)findViewById(R.id.txtcorreo);
        user_id = (TextView)findViewById(R.id.txtusertp);
        this.CargarPref();
    }

    public void CargarPref(){
        SharedPreferences admin = getSharedPreferences("Administrador", Context.MODE_PRIVATE);
        nom = admin.getString("nombre", "Juan Zamora");
        ced = admin.getString("ced", "8-405-988");
        correo = admin.getString("correo", "zamora@mail.com");
        usertp = admin.getString("tipo", "Administrador");

        name.setText(nom);
        id.setText(ced);
        mail.setText(correo);
        user_id.setText(usertp);
    }

    public void Registrar(View view){
        Intent r = new Intent(this, RegistroActivity.class);
        startActivity(r);
    }


    public void Salir(View view){
        Intent s = new Intent(this, MainActivity.class);
        startActivity(s);
    }
}
