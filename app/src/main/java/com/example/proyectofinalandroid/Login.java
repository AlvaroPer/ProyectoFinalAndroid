package com.example.proyectofinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinalandroid.database.SQLHelper;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText editUsuario, editContrasenia;

    SQLHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instancias();
        acciones();
    }

    private void instancias() {
        editUsuario = findViewById(R.id.editUsuarioLogin);
        editContrasenia = findViewById(R.id.editContraseniaLogin);
        btnLogin = findViewById(R.id.btnLogin);

        helper = new SQLHelper(this);
    }

    private void acciones() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (editUsuario.equals("") || editContrasenia.equals("")){
                    Toast.makeText(this, "Por favor rellene todos los campos", Toast.LENGTH_LONG).show();
                }else{
                    String nombreLogin = editUsuario.getText().toString();
                    String contraseniaLogin = editContrasenia.getText().toString();
                    boolean comprobarLogin = helper.comprobarLogin(nombreLogin,contraseniaLogin);
                    if(comprobarLogin==true){
                        Toast.makeText(this, "Sesión iniciada", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "El usuario o contraseña no son correctos, vuelva a introducirlos", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
}
