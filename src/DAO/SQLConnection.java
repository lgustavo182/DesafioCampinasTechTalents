package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

// Autor: Luis Gustavo de Almeida Monteiro
// CAMPINAS TECH TALENTS 2020 - TRILHA ASSERTIVA | JAVA
// 18-03-2021

public class SQLConnection {

    //Nome de usuário do MySQL
    private static final String USERNAME = "root";

    //Senha do banco de dados
    private static final String PASSWORD = "ucrania123";

    // Caminho (URL) do Banco de Dados
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bancoctt?useTimezone=true&serverTimezone=UTC&useSSL=false";




    /*
     * Conexão com o banco de Dados
     */

    public static Connection createConnectionToMySQL() throws Exception {

        // Carregando a Classe com a JVM
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Criando a conexão com o BD
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return connection;
    }

    public static void main(String[] args) throws Exception {
        //Recuperando conexão com o BD
        Connection con = createConnectionToMySQL();

        //Testando se a conexão é nula
        if (con!=null) {
            System.out.println("Conexão realizada com sucesso!");
            con.close();
        }
    }

}
