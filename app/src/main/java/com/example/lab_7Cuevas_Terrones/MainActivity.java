package com.example.lab_7Cuevas_Terrones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String correo, password, tp_user;
    TextView prueba, prueba1;
    EditText user, pass;
    Spinner sp_user;
//Cuevitas estuvo aqui.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.InicializarControles();
        this.AttachEventSpn();
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
        sp_user = (Spinner)findViewById(R.id.spn_usuario);
        this.LoadSpinner();
    }

    //SPINNER
    public void AttachEventSpn() {
        sp_user.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id){
                String opcion = adapterView.getItemAtPosition(pos).toString();
                Toast.makeText(getApplicationContext(), opcion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });
    }

    public void LoadSpinner(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spn_opciones, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sp_user.setAdapter(adapter);
    }
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

