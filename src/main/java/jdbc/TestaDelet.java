package jdbc;

import config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaDelet {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.reConnect();

        PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
        stm.setInt(1, 12);
        stm.execute();

        Integer linhasModificadas = stm.getUpdateCount();
        System.out.println("Quantidade de linhas modificadas: " + linhasModificadas);
    }
}
