package com.example.proyectofinalandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {

    public SQLHelper(@Nullable Context context) {
        super(context, "segunda.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table usuarios(nombreusuario text primary key,contraseniausuario text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuarios");

    }

    public boolean insertarUsuario(String nombreUsuario, String passwordUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombreusuario",nombreUsuario);
        contentValues.put("contraseniausuario",passwordUsuario);
        long insert = db.insert("usuarios", null, contentValues);

        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean comprobarLogin (String nombreUsuario, String contraseniaUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from usuarios where nombreusuario =? and contraseniausuario =?", new String[] {nombreUsuario, contraseniaUsuario});
        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean comprobarUsuario (String nombreUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select nombreusuario from usuarios where nombreusuario =?", new String[] {nombreUsuario});
        if (cursor.getCount()>0){
            return false;
        }else{
            return true;
        }
    }
}
