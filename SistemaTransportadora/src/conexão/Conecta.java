package conexão;

import java.sql.*;
import javax.swing.*;

public class Conecta {

    public static void main(String args[]) {

        final String DRIVER = "org.postgresql.Driver";
        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        
        System.setProperty("jdbc.Drivers", DRIVER);

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, "postgres", "postgres");
            JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso");
            connection.close();
        } catch (ClassNotFoundException erro) {
            JOptionPane.showMessageDialog(null, "Driver JDBC nao encontrado!\n" + erro.toString());
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Problemas na conexao com a fonte de dados!\n" + erro.toString());
        }
    }
}