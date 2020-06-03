package com.example.lab_7Cuevas_Terrones;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Lab7SQLiteHelper extends SQLiteOpenHelper {
String sqlCreate = "CREATE TABLE usuarios (usuario TEXT, cedula TEXT, correo TEXT, tipo TEXT, contra TEXT)";



public Lab7SQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version){
    super(contexto, nombre, factory, version);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


}



}
