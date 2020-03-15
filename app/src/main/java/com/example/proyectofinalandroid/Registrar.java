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

public class Registrar extends AppCompatActivity implements View.OnClickListener {

    TextView textLogin;
    Button btnRegistrar, btnVaciar;
    EditText editUsuario, editContraseniaUno, editContraseniaDos;

    SQLHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        instancias();
        acciones();
    }

    private void instancias() {
        editUsuario = findViewById(R.id.editUsuarioRegistro);
        editContraseniaUno = findViewById(R.id.editContraseniaRegistroUno);
        editContraseniaDos = findViewById(R.id.editContraseniaRegistroDos);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnVaciar = findViewById(R.id.btnVaciar);
        textLogin = findViewById(R.id.textLogin);

        helper = new SQLHelper(this);
    }

    private void acciones() {
        btnVaciar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        textLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegistrar:
                if (editUsuario.equals("") || editContraseniaUno.equals("") || editContraseniaDos.equals("")){
                    Toast.makeText(this, "Por favor rellene todos los campos", Toast.LENGTH_LONG).show();
                }
                else if(editContraseniaUno.getText().toString().equals(editContraseniaDos.getText().toString())){
                    String usuarioRegistro = editUsuario.getText().toString();
                    String contraseniaRegistro = editContraseniaUno.getText().toString();
                    Boolean usuarioExistente = helper.comprobarUsuario(usuarioRegistro);
                    if(usuarioExistente==true){
                        Boolean insertarUsuario = helper.insertarUsuario(usuarioRegistro,contraseniaRegistro);
                        if(insertarUsuario==true){
                            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this, "Ya existe un usuario con ese nombre, vuelva a introducir otro", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "Las contraseñas no coinciden, vuelva a introducirlas", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnVaciar:
                editContraseniaUno.setText("");
                editContraseniaDos.setText("");
                editUsuario.setText("");
                Toast.makeText(this, "Campos vaciados", Toast.LENGTH_LONG).show();
                break;
            case R.id.textLogin:
                Intent intent = new Intent(Registrar.this,Login.class);
                startActivity(intent);
                break;
        }

    }
}
