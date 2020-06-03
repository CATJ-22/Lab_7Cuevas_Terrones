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

    public void CargarPref(){
        /*tp_user = ;*/
        SharedPreferences admin = getSharedPreferences(sp_user.getSelectedItem().toString(), Context.MODE_PRIVATE);
        /*else if ()
            Sharep*/
        correo = admin.getString("correo", "zamora@mail.com");
        password = admin.getString("contra","Elinnombrable");

        prueba.setText(correo);
        prueba1.setText(password);
    }

    //Button Login
    public void onClick(View view){

        try{
            Lab7SQLiteHelper usuariosDb = new Lab7SQLiteHelper(getApplicationContext(),"usuarios",null,1);
            SQLiteDatabase db = usuariosDb.getWritableDatabase();

            if (db == null){
                ContentValues values = new ContentValues();
                values.put("usuario", "Juan");
                values.put("cedula","9-999-9999");
                values.put("correo","elseniordelastinieblas@hagosufrirestudiantes.com");
                values.put("tipo","Administrador");
                values.put("contra", "123");
                db.insert("usuario",null,values);
                Toast.makeText(getApplicationContext(),"En teoria, todo se inserto bien",Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Errorcito: "+e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(user.getText().toString()))
            user.setError("Introduzca su Correo Electronico");
        if(TextUtils.isEmpty(pass.getText().toString()))
            pass.setError("Introduzca la Contraseña");
        else{
        correo=user.toString();
        password=pass.toString();
            Lab7SQLiteHelper usuariosDb = new Lab7SQLiteHelper(getApplicationContext(),"usuarios",null,1);
        Toast.makeText(getApplicationContext(), "abro la clase bd", Toast.LENGTH_SHORT).show();
            SQLiteDatabase db = usuariosDb.getReadableDatabase();
            Toast.makeText(getApplicationContext(), "creo la sqlitedb", Toast.LENGTH_SHORT).show();
        String[] campos = new String[] {"correo","tipo", "contra"};
            Toast.makeText(getApplicationContext(), "Llegue a crear el arreglo", Toast.LENGTH_SHORT).show();
        Cursor c = db.query("usuarios",campos,null,null,null,null,null);
            Toast.makeText(getApplicationContext(), "Llegue a crear el cursor", Toast.LENGTH_SHORT).show();
            if (c.moveToFirst()) {
                do {
                    Toast.makeText(getApplicationContext(), "Llegue al if de C", Toast.LENGTH_SHORT).show();
                    tp_user = c.getString(1);
                    Toast.makeText(getApplicationContext(), "Llegue hasta leer bd", Toast.LENGTH_SHORT).show();
                    if (c.getString(0).compareToIgnoreCase(correo) == 0 && c.getString(2).equals(password)) {
                        Toast.makeText(getApplicationContext(), "COMPARE BIEN", Toast.LENGTH_SHORT).show();
                        if (sp_user.getSelectedItem().toString().equals(tp_user)) {
                            Intent i = new Intent(this, WelcomeActivity.class);
                            startActivity(i);
                        } else if (sp_user.getSelectedItem().toString().equals(tp_user)) {
                            Intent e = new Intent(this, WelcomeEActivity.class);
                            startActivity(e);
                        } else {
                            Intent j = new Intent(this, Welcome2Activity.class);
                            j.putExtra("tipo", sp_user.getSelectedItem().toString());
                            startActivity(j);
                        }

                    }
                } while (c.moveToNext() && c.getString(2) != correo && c.getString(4) != password);
                Toast.makeText(getApplicationContext(), "Me sali sin hacer nada", Toast.LENGTH_SHORT).show();
            }}

    }
}
