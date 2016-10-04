package com.example.akira.agendadb;

/**
 * Created by Akira on 17/09/2016.
 */
public class Usuario {

    public String cpf_id;
    public String nome;
    public String telefone;
    public String endereco;

    public String getCpf_id() {
        return cpf_id;
    }

    public void setCpf(String cpf) {
        this.cpf_id = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
