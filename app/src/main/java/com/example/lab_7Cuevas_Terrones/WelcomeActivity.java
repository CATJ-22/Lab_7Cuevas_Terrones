package com.example.lab_7Cuevas_Terrones;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
        this.CargarBD();
    }

    public void CargarBD(){
        Intent j = getIntent();
        String idd = j.getStringExtra("id");
            try {

                Lab7SQLiteHelper usuariosDb = new Lab7SQLiteHelper(getApplicationContext(),"usuarios",null,1);
                SQLiteDatabase db = usuariosDb.getReadableDatabase();
                String[] campos = new String[] {"usuario","cedula","correo","tipo"};
                String[] args = new String[]{idd};

                Cursor c = db.query("usuarios",campos,"cedula=?",args,null,null,null);
                if (c.moveToFirst()){
                    do {
                                nom=c.getString(0);
                                ced=c.getString(1);
                                correo=c.getString(2);
                                usertp=c.getString(3);
                    }while(c.moveToNext());
                }
        name.setText(nom);
        id.setText(ced);
        mail.setText(correo);
        user_id.setText(usertp);
    }
    catch (Exception e){
        Toast.makeText(getApplicationContext(),"Errorsote: "+e.getMessage().toString(),Toast.LENGTH_SHORT).show();
    }}

    public void Registrar(View view){
        Intent r = new Intent(this, RegistroActivity.class);
        startActivity(r);
    }


    public void Salir(View view){
        Intent s = new Intent(this, MainActivity.class);
        startActivity(s);
    }
}
