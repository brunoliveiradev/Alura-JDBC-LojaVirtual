package jdbc;

import config.ConnectionFactory;

import java.sql.SQLException;

public class TestaPoolConnection {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        for (int i = 0; i < 20; i++){
            connectionFactory.reConnect();
            System.out.println("Conexão: " + i);
        }
    }
}
