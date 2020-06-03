package com.example.lab_6arango_terrones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Welcome2Activity extends AppCompatActivity {
    TextView name, id, mail, user_id;
    String nom, ced, correo, usertp, spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);
        this.InicializarControles();
    }

    public void InicializarControles(){
        name = (TextView)findViewById(R.id.txtnom1);
        id = (TextView)findViewById(R.id.txtced1);
        mail = (TextView)findViewById(R.id.txtcorreo1);
        user_id = (TextView)findViewById(R.id.txtusertp1);
        this.CargarPref();
    }

    public void CargarPref(){
        Intent j = getIntent();
        spn = j.getStringExtra("tipo");

        SharedPreferences admin = getSharedPreferences(spn, Context.MODE_PRIVATE);
        nom = admin.getString("nombre", "Juan Zamora");
        ced = admin.getString("ced", "8-405-988");
        correo = admin.getString("correo", "zamora@mail.com");
        usertp = admin.getString("tipo", "No asignado");

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
        Intent o = new Intent(this, MainActivity.class);
        startActivity(o);
    }
}
