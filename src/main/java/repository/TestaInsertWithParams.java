package repository;

import config.ConnectionFactory;

import java.sql.*;

public class TestaInsertWithParams {

    public static void main(String[] args) throws SQLException {
        String nome = "MOUSE";
        String descricao = "MOUSE SEM FIO";
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.reConnect();

        //PASSAR A INFORMAÇÃO COMO UM PARAMETERS, EVITA SQL INJECTION
        PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES(?, ?)",
                Statement.RETURN_GENERATED_KEYS);

        stm.setString(1, nome);
        stm.setString(2, descricao);

        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();
        while (rst.next()) {
            Integer id = rst.getInt(1);
            System.out.println("ID criado foi: " + id);
        }
    }
}
