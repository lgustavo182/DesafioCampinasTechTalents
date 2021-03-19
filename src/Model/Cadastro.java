package Model;

import java.util.Date;

// Autor: Luis Gustavo de Almeida Monteiro
// CAMPINAS TECH TALENTS 2020 - TRILHA ASSERTIVA | JAVA
// 18-03-2021

public class Cadastro {

    private static int id;
    private static String nome;
    private static String login;
    private static String senha;
    private static Date dataCadastro;


    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getNome() {
        return nome;
    }

    public  void setNome(String nome) {
        this.nome = nome;
    }

    public static String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public static String getSenha() {
        return senha;
    }

    public  void setSenha(String senha) {
        this.senha = senha;
    }

    public static String getDataCadastro() {
        return String.valueOf(dataCadastro);
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }


}
