package com.example.akira.agendadb;

import java.sql.Struct;

/**
 * Created by Akira on 17/09/2016.
 */
public class CriarQuery {

    public String getDatabaseNome(){
        return "agenda";
    }

    public String getApiKey(){
        return "KI4CgYHpgGUzh7mPf7YMmx9wxVAFnrSl";
    }

    public String getBaseUrl(){
        return "https://api.mlab.com/api/1/databases/"+getDatabaseNome()+"/collections/";
    }

    public String fimApiKey(){
        return "?apiKey="+getApiKey();
    }

    /**
     * get usuario especifico
     */
    public String fimApiKey(String docid){
        return "/"+docid+"?apiKey="+getApiKey();
    }

    public String documentUsuarios(){
        return "usuarios";
    }

    public String construirUrlSalvar(){
        return getBaseUrl()+documentUsuarios()+fimApiKey();
    }

    public String contatosGetUrl(){
        return getBaseUrl()+documentUsuarios()+fimApiKey();
    }

    public String contatosAtualizaUrl(String cpf_id){
        return getBaseUrl()+documentUsuarios()+fimApiKey(cpf_id);
    }

    public String createUsuario(Usuario user){
        return String.format("{\"_id\": \"%s\", "
                        + "\"nome\": \"%s\", "
                        + "\"telefone\": \"%s\", "
                        + "\"endereco\": \"%s\"}, "
                        + "\"safe\" : true}",
                user.cpf_id, user.nome, user.telefone, user.endereco);
    }

    public String updateUsuario(Usuario user){
        return String.format("{\"$set\": "
                + "{\"_id\": \"%s\", "
                + "\"nome\": \"%s\", "
                + "\"telefone\": \"%s\", "
                + "\"endereco\": \"%s\"}" + "}",
                user.getCpf_id(), user.getNome(), user.getTelefone(), user.getEndereco());
    }
}
