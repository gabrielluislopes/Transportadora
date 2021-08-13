package conexão;

import java.sql.*;

/**
 *
 * @author Gabs
 */
public class ConectaBD {
    public Connection connection = null;
    private final String DRIVER = "org.postgresql.Driver";
    private final String BDNOME = "BDTransportadora";
    private final String URL = "jdbc:postgresql://localhost:5432/" + BDNOME;
    private final String LOGIN = "postgres";
    private final String SENHA = "postgres";
    
    public boolean getConnection(){
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, SENHA);
            System.out.println("Conectou");
            return true;
        }
        catch (ClassNotFoundException erro){
            System.out.println("Driver não encontrado " + erro.toString());
            return false;
        }
        catch (SQLException erro){
            System.out.println("Falha ao conectar " + erro.toString());
            return false;
        }
    }
    
    public void close(){
        try{
            connection.close();
            System.out.println("Desconectou");
        }
        catch (SQLException erro) {}
    }
}
