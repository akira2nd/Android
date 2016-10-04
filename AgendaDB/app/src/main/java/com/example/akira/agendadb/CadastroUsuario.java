package com.example.akira.agendadb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroUsuario extends AppCompatActivity {

    EditText editText_cpf_id;
    EditText editText_nome;
    EditText editText_phone;
    EditText editText_endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        editText_cpf_id = (EditText) findViewById(R.id.editTextCPF);
        editText_nome = (EditText) findViewById(R.id.editTextNome);
        editText_phone = (EditText) findViewById(R.id.editTextPhone);
        editText_endereco = (EditText) findViewById(R.id.editTextEndereco);

        Button save = (Button) findViewById(R.id.buttonSalvarNovo);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarNovo();
            }
        });



    }

    private void salvarNovo() {
        Usuario usuario = new Usuario();
        usuario.cpf_id = editText_cpf_id.getText().toString();
        usuario.nome = editText_nome.getText().toString();
        usuario.telefone = editText_phone.getText().toString();
        usuario.endereco = editText_endereco.getText().toString();

        Connection connection = new Connection();
        connection.execute(usuario);


        finish();
    }
}
