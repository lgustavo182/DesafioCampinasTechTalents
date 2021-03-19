package DAO;

import java.sql.*;
import java.util.ArrayList;
import Model.Cadastro;

// Autor: Luis Gustavo de Almeida Monteiro
// CAMPINAS TECH TALENTS 2020 - TRILHA ASSERTIVA | JAVA
// 18-03-2021

public class CadastroDAO {


     // A classe DAO será a responsável por realizar o CRUD.

    private Connection con = null;


    public CadastroDAO() throws Exception {
        con = SQLConnection.createConnectionToMySQL();
    }

    public void add_Cadastro(Cadastro c) throws Exception {

        String sql = "INSERT INTO cadastro (nome, login, senha, data_cadastro) VALUES (?, ?, ?, ?)";

        try {
            con = SQLConnection.createConnectionToMySQL();

            PreparedStatement stmt = con.prepareStatement(sql);


            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getLogin());
            stmt.setString(3, c.getSenha());
            stmt.setString(4, String.valueOf((new Date(System.currentTimeMillis()))));

            stmt.execute();
            System.out.println("\nCadastro realizado com sucesso\n");

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
           con.close();
        }
    }

    public ArrayList<Cadastro> mostrarCadastros() throws Exception {

        //ArrayList que irei retornar
        ArrayList<Cadastro> cadastros = new ArrayList<>();

        //Query que irei lançar, padrão.
        String sql = "SELECT * FROM cadastro";

        try {
            con = SQLConnection.createConnectionToMySQL();
            //Passo a Query que vai ser preparada e executada.
            PreparedStatement stmt = con.prepareStatement(sql);

            //Executo essa Query e atribuo o resultado a rs. Onde ira guardar todo resultado que for pego no banco,
            //ele guarda o resultado de uma pesquisa numa estrutura de dados que pode ser percorrida.
            ResultSet rs = stmt.executeQuery();

            //Percorro o resultado enquanto tiver proximo.
            while (rs.next()) {

                //Instacio um tipo pessoa pra criar a pessoa e adicionar no ArrayList que irei retornar.
                Cadastro c = new Cadastro();
                //Chamo o Setters padrão da pessoa, e no parametro coloco o rs.getTipo("nome_da_coluna_igual_do_banco");
                c.setId(rs.getInt("id_usuario"));
                c.setNome(rs.getString("nome"));
                c.setLogin(rs.getString("login"));
                c.setSenha(rs.getString("senha"));
                c.setDataCadastro(rs.getDate("data_cadastro"));

                //Adiciono no ArrayList.
                cadastros.add(c);
                //Repete esse processo ate acabar o ResultSet, e no final o ARRAY vai ficar cheio com todas as pessoas.
            }

            rs.close();
            //Retorno o ARRAY com todas as pessoas
            return cadastros;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        } finally {
            con.close();
        }
    }

    public void deletaCadastro (int id) throws Exception {

        //Pra deletar a pessoa é necessario deletar todas as vendas que essa pessoa está
        //relacionada.
        String sql = "DELETE FROM cadastro WHERE id_usuario = ?";

        try {
            con = SQLConnection.createConnectionToMySQL();

            //Removendo realmente a pessoa
            PreparedStatement stmt = con.prepareStatement(sql);
            //Preparo sendo feito, digo que no 1º '?' ele vai ser trocado pelo id da pessoa que recebemos no parametro.
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("\nCadastro deletado com sucesso \n");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            con.close();
        }
    }

    public Cadastro acharCadastro(int id) throws Exception {

        Cadastro c = new Cadastro();
        String sql = "SELECT * FROM cadastro WHERE nome = ?";

        try {
            con = SQLConnection.createConnectionToMySQL();
            PreparedStatement stmt = con.prepareStatement(sql);
            //Preparo sendo feito, digo que no 1º '?' ele vai ser trocado pelo cpf da pessoa que recebemos no parametro.
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                //Chamo o Setters padrão de pessoa, e no parametro coloco o rs.getTipo("nome_da_coluna_igual_do_banco");
                c.setId(rs.getInt("id_usuario"));
                c.setNome(rs.getString("nome"));
                c.setLogin(rs.getString("login"));
                c.setSenha(rs.getString("senha"));
                c.setDataCadastro(rs.getDate("data_cadastro"));
            }
            return c;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        } finally {
            con.close();
         }
    }

    public void alterarCadastro(String nome, String login, String senha) throws Exception {

        String sql = "UPDATE cadastro SET nome = ?, login = ? senha = ? WHERE id_usuario = ?";

        try {
            con = SQLConnection.createConnectionToMySQL();
            PreparedStatement stmt = con.prepareStatement(sql);


            stmt.setString(1, nome);
            stmt.setString(2, login);
            stmt.setString(3, senha);
            stmt.executeUpdate();

            System.out.println("\nCadastro deletado com sucesso! \n");

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            con.close();

        }
    }



}
