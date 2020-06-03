package com.example.lab_6arango_terrones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {
    String correo, password, tp_user;
    TextView prueba, prueba1;
    EditText user, pass;
    Spinner sp_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.InicializarControles();
        this.AttachEventSpn();
    }

    public void InicializarControles(){
        prueba = (TextView)findViewById(R.id.pruebash);
        prueba1 = (TextView)findViewById(R.id.pruebashp);
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
        this.CargarPref();
        //VALIDATIONS AND LOGIN
        if(TextUtils.isEmpty(user.getText().toString()))
            user.setError("Introduzca su Correo Electronico");
        if(TextUtils.isEmpty(pass.getText().toString()))
            pass.setError("Introduzca la Contraseña");
        else{
            if(user.getText().toString().compareToIgnoreCase(correo)==0 && pass.getText().toString().equals(password)){
                if(sp_user.getSelectedItem().toString().equals("Administrador")){
                    Intent i = new Intent(this, WelcomeActivity.class);
                    startActivity(i);
                }else if(sp_user.getSelectedItem().toString().equals("Especial")){
                    Intent e = new Intent(this, WelcomeEActivity.class);
                    startActivity(e);
                }else{
                    Intent j = new Intent(this, Welcome2Activity.class);
                    j.putExtra("tipo",sp_user.getSelectedItem().toString());
                    startActivity(j);

                }
            }else
                Toast.makeText(this,"Email or Password incorrect.", Toast.LENGTH_LONG).show();
        }
        //VALIDATIONS AND LOGIN
    }
}

//Switch del Button
/*switch (view.getId()){
            case R.id.btnlog:
                GuardarPref();
                break;
            case R.id.btnu:
                Intent i = new Intent(this, WelcomeActivity.class);
                startActivity(i);
        }*/

