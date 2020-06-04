package com.example.lab_7Cuevas_Terrones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    private int a=0;
    private String tp_user;
    EditText nom, ced, mail, pass;
    Spinner spn_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        this.InicializarControles();
        this.AttachEventSpn();
    }

    public void InicializarControles(){
        nom = (EditText)findViewById(R.id.regnom);
        ced = (EditText)findViewById(R.id.regcedu);
        mail = (EditText)findViewById(R.id.regcorreo);
        spn_user = (Spinner)findViewById(R.id.spn_opcion);
        pass = (EditText)findViewById(R.id.regcontra);
        this.LoadSpinner();
    }

    //SPINNER
    public void AttachEventSpn() {
        spn_user.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
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
        spn_user.setAdapter(adapter);
    }
    //SPINNER

    public void ValidarDatos (View view){
        //Validaciones para los datos de entrada.
        if(TextUtils.isEmpty(nom.getText().toString()))
            nom.setError("No puede estar vacio.");
        else
            a++;
        if(TextUtils.isEmpty(ced.getText().toString()))
            ced.setError("No puede estar vacio.");
        else
            a++;
        if(TextUtils.isEmpty(mail.getText().toString()))
            mail.setError("No puede estar vacio.");
        else
            a++;
        if(TextUtils.isEmpty(pass.getText().toString()))
            pass.setError("No puede estar vacio.");
        else
            a++;

        if(a==4)
            GuardarDatos();
        else
            a=0;
    }

    public void GuardarDatos(){
        tp_user = spn_user.getSelectedItem().toString();
        try{
            Lab7SQLiteHelper usuariosDb = new Lab7SQLiteHelper(getApplicationContext(),"usuarios",null,1);
            SQLiteDatabase db = usuariosDb.getWritableDatabase();

            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("usuario", nom.getText().toString());
                values.put("cedula", ced.getText().toString());
                values.put("correo", mail.getText().toString());
                values.put("tipo", tp_user);
                values.put("contra", pass.getText().toString());
                db.insert("usuarios", null, values);
                Toast.makeText(getApplicationContext(), "En teoria, todo se inserto bien", Toast.LENGTH_SHORT).show();

            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Errorcito: "+e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
