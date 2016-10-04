package com.example.akira.agendadb;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListaUsuarios extends ListActivity {

    ArrayList<Usuario> dadosUsuario = new ArrayList<Usuario>();
    ArrayList<String> listItems = new ArrayList<String>();

    String update_cpf;
    String update_nome;
    String update_telefone;
    String update_endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        GetUsuarios getUsuarios = new GetUsuarios();
        try {
            dadosUsuario = getUsuarios.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Usuario u: dadosUsuario){
            listItems.add(u.getNome());
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                listItems));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);

        String valorSelecionado = (String) getListAdapter().getItem(position);
        Toast.makeText(this,valorSelecionado,Toast.LENGTH_SHORT).show();
        usuarioSelecionado(valorSelecionado);

        Bundle dataBundle = new Bundle();
        dataBundle.putString("_id", update_cpf);
        dataBundle.putString("nome", update_nome);
        dataBundle.putString("telefone", update_telefone);
        dataBundle.putString("endereco", update_endereco);
        Intent expandirUserIntent = new Intent(this,AtualizarUsuario.class);
        expandirUserIntent.putExtras(dataBundle);
        startActivity(expandirUserIntent);
        finish();
    }

    private void usuarioSelecionado(String valorSelecionado) {
        for (Usuario user: dadosUsuario){
            if (valorSelecionado.contains(user.getNome())){
                update_cpf = user.getCpf_id();
                update_nome = user.getNome();
                update_telefone = user.getTelefone();
                update_endereco = user.getEndereco();
            }
        }
    }

}
