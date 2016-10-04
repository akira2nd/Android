package com.example.akira.agendadb;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class AtualizarUsuario extends Activity {

    EditText editText_cpf;
    EditText editText_nome;
    EditText editText_telefone;
    EditText editText_endereco;

    String _id;
    String nome;
    String telefone;
    String endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_usuario);

        editText_cpf = (EditText) findViewById(R.id.editTextUcpf);
        editText_nome = (EditText) findViewById(R.id.editTextUnome);
        editText_telefone = (EditText) findViewById(R.id.editTextUtelefone);
        editText_endereco = (EditText) findViewById(R.id.editTextUendereco);

        Bundle getBundle = null;
        getBundle = this.getIntent().getExtras();
        _id = getBundle.getString("_id");
        nome = getBundle.getString("nome");
        telefone = getBundle.getString("telefone");
        endereco = getBundle.getString("endereco");

        editText_cpf.setText(_id);
        editText_nome.setText(nome);
        editText_telefone.setText(telefone);
        editText_endereco.setText(endereco);

        final Button atualizaSalvar = (Button) findViewById(R.id.buttonUsalvar);
        atualizaSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    atualizaUsuario(v);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void atualizaUsuario(View v) throws UnknownHostException {
        Usuario user = new Usuario();

        user.setCpf(editText_cpf.getText().toString());
        user.setNome(editText_nome.getText().toString());
        user.setTelefone(editText_telefone.getText().toString());
        user.setEndereco(editText_endereco.getText().toString());

        MongoLabUpdateContact tsk = new MongoLabUpdateContact();
        tsk.execute(user);
        Intent i = new Intent(this, ListaUsuarios.class);
        startActivity(i);
        Toast.makeText(this,"Atualizado",Toast.LENGTH_SHORT).show();
        finish();
    }


    final class MongoLabUpdateContact extends AsyncTask<Object, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Object... params) {
            Usuario usuario = (Usuario) params[0];

            try {

                CriarQuery qb = new CriarQuery();
                URL url = new URL(qb.contatosAtualizaUrl(usuario.getCpf_id()));
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setRequestMethod("PUT");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type",
                        "application/json");
                connection.setRequestProperty("Accept", "application/json");

                OutputStreamWriter osw = new OutputStreamWriter(
                        connection.getOutputStream());

                osw.write(qb.updateUsuario(usuario));
                osw.flush();
                osw.close();
                if (connection.getResponseCode() < 205) {

                    return true;
                } else {
                    return false;

                }

            } catch (Exception e) {
                e.getMessage();
                return false;

            }
        }

    }
}