package com.example.akira.agendadb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botaoCadastro = (Button) findViewById(R.id.buttonCadastrar);
        Button botaoEntrar = (Button) findViewById(R.id.buttonListar);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrar();
            }
        });

        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

    }

    private void entrar() {
        Intent intent = new Intent(this, ListaUsuarios.class);
        startActivity(intent);
    }

    private void cadastrar() {
        Intent intent = new Intent(this, CadastroUsuario.class);
        startActivity(intent);
    }


}
