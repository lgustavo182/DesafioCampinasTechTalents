package Main;


import DAO.CadastroDAO;
import Model.Cadastro;

import java.util.Scanner;


// Autor: Luis Gustavo de Almeida Monteiro
// CAMPINAS TECH TALENTS 2020 - TRILHA ASSERTIVA | JAVA
// 18-03-2021

public class Main {

    public static void main(String[] args) throws Exception {

        Main m = new Main();
        m.menu();
    }

    public Scanner getScanner() {
        return new Scanner(System.in);
    }

    public void menu() throws Exception {

        System.out.println("1) Cadastrar");
        System.out.println("2) Ver Cadastros");
        System.out.println("3) Excluir Cadastros");
        System.out.println("4) Alterar Cadastros");
        System.out.println("5) Sair");
        System.out.print("Escolha uma opção: ");
        int op = getScanner().nextInt();

        switch (op) {

            case 1:
                addCadastro();
            case 2:
                listarCadastro();
            case 3:
                excluirCadastro();
            case 4:
                alterarCadastro();
            case 5:
                System.exit(0);
            default:
                System.out.println("Opção inválida. Digite uma opção válida!");
                menu();
        }
    }


    public void addCadastro() throws Exception {

        Scanner rd = new Scanner(System.in);
        Cadastro c = new Cadastro();

        System.out.print("\nDigite o nome do usuário: ");
        String nome = rd.nextLine();
        c.setNome(nome);
        System.out.print("Digite o login do usuário: ");
        String login = rd.nextLine();
        c.setLogin(login);
        System.out.print("Digite a senha do usuário: ");
        String senha = rd.nextLine();
        c.setSenha(senha);



        CadastroDAO cdao = new CadastroDAO();
        cdao.add_Cadastro(c);
        menu();
    }

    public void listarCadastro() throws Exception {

        //Instanciando a classe DAO de Pessoa
        CadastroDAO cdao = new CadastroDAO ();

        System.out.println("\t\n--- LISTA DE CADASTROS ---\n");

        //Passando um for no arraylist que o metodo mostrar_pessoas retorna
        for (Cadastro c : cdao.mostrarCadastros()) {
            System.out.println("Nome: " + c.getNome());
            System.out.println("Login: " + c.getLogin());
            System.out.println("Senha: " + c.getSenha() + "\n");
            System.out.println("Data de Cadastro: " + c.getDataCadastro() + "\n");

        }
        menu();
    }

    public void excluirCadastro() throws Exception {

        CadastroDAO cdao = new CadastroDAO();
        System.out.print("\nDigite o ID do cadastro para remove-lo: ");

        int id = getScanner().nextInt();

        cdao.deletaCadastro(id);
        System.out.println("Cadastro deletado com sucesso!");
        menu();
    }

    public void alterarCadastro() throws Exception {
        //Tem varias forma de fazer essa alteração, escolhi encontrar a pessoa chamando o metodo achar_pessoa da classe DAO,
        //mostrar ela, e dar a liberdade de alterar todas as informações, em seguida passando essas novas informaçoes
        //pro metodos altera_pessoa da classe PessoaDAO

        CadastroDAO cdao = new CadastroDAO();

        System.out.print("\nDigite o ID do cadastro para alterar: ");
        int id = getScanner().nextInt();

        Cadastro c = cdao.acharCadastro(id);

        System.out.println("\nAlterando Informações do Cadastro: \n");
        System.out.println("Nome: " + c.getNome());
        System.out.println("Login: " + c.getLogin());
        System.out.println("Senha: " + c.getSenha() + "\n");


        System.out.println("Digite as novas informações: \n");

        System.out.print("Nome: ");
        String nome = getScanner().nextLine();
        System.out.print("Login: ");
        String login = getScanner().nextLine();
        System.out.print("Senha: ");
        String senha = getScanner().nextLine();

        //Passando como parametro as informações e o CPF da pessoa que foi digitado e posteriormente encontrado
        cdao.alterarCadastro(c.getNome(), login, senha);
        menu();
    }


}