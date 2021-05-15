package repository;

import config.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.reConnect();

        System.out.println("Testando conexão");
        connection.close();
    }
}
