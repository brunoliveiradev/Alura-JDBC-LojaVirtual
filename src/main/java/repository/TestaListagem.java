package repository;

import config.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.reConnect();

        Statement stm = connection.createStatement();
        stm.execute("SELECT ID, nome, descricao FROM produto");

        ResultSet rst = stm.getResultSet();

        while (rst.next()) {
            Integer id = rst.getInt("ID");
            String nome = rst.getString("nome");
            String descricao = rst.getString("descricao");
            System.out.println(id + " " + nome + " " + descricao);
        }
        connection.close();
    }

}
