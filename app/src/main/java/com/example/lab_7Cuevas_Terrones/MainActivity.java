package com.example.lab_7Cuevas_Terrones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    String correo, password, tp_user;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.InicializarControles();

        try{
            Lab7SQLiteHelper usuariosDb = new Lab7SQLiteHelper(getApplicationContext(),"usuarios",null,1);
            SQLiteDatabase db = usuariosDb.getWritableDatabase();

            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("usuario", "Juan");
                values.put("cedula", "9-999-9999");
                values.put("correo", "elseniordelastinieblas@hagosufrirestudiantes.com");
                values.put("tipo", "Administrador");
                values.put("contra", "123");
                db.insert("usuarios", null, values);
                Toast.makeText(getApplicationContext(), "En teoria, todo se inserto bien", Toast.LENGTH_SHORT).show();

            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Errorcito: "+e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }

    }




    public void InicializarControles(){
        user = (EditText)findViewById(R.id.txtemail);
        pass = (EditText)findViewById(R.id.txtpw);


    }

    //SPINNER

    //SPINNER



    //Button Login
    public void onClick(View view){
        if(TextUtils.isEmpty(user.getText().toString()))
            user.setError("Introduzca su Correo Electronico");
        if(TextUtils.isEmpty(pass.getText().toString()))
            pass.setError("Introduzca la Contraseña");
        else{
        correo=user.getText().toString();
        password=pass.getText().toString();
            Lab7SQLiteHelper usuariosDb = new Lab7SQLiteHelper(getApplicationContext(),"usuarios",null,1);
            SQLiteDatabase db = usuariosDb.getReadableDatabase();
        String[] campos = new String[] {"correo","tipo", "contra", "cedula"};
        Cursor c = db.query("usuarios",campos,null,null,null,null,null);
            if (c.moveToFirst()) {
                do {

                    String correobd = c.getString(0);
                    tp_user = c.getString(1);
                    String passbd = c.getString(2);
                    String id = c.getString(3);
                    if ( correobd.equals(correo) && passbd.equals(password)) {

                        if (tp_user.equals("Administrador")) {
                            Intent i = new Intent(this, WelcomeActivity.class);
                            i.putExtra("id", id);
                            startActivity(i);
                        } else if (tp_user.equals("Especial")) {
                            Intent e = new Intent(this, Welcome2Activity.class);
                            e.putExtra("id", id);
                            startActivity(e);
                        } else {
                            Intent j = new Intent(this, WelcomeEActivity.class);
                            j.putExtra("id", id);
                            startActivity(j);
                        }
//
                    }
                } while (c.moveToNext());
            }}

    }
}

