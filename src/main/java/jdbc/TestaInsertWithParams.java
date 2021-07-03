package jdbc;

import config.ConnectionFactory;

import java.sql.*;

public class TestaInsertWithParams {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();

        try(Connection connection = factory.reConnect()) {
            connection.setAutoCommit(false); //Controla as transações - false: ou vai todas as info, ou nenhuma

            try (//PASSAR A INFORMAÇÃO COMO UM PARAMETRO, EVITA SQL INJECTION
                              PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES(?, ?)",
                            Statement.RETURN_GENERATED_KEYS)
            ) {
                adicionarVariavel("SmartTV", "45 polegadas", stm);
                adicionarVariavel("PlayStation 5", "Console", stm);
                //Depois que tudo foi adicionado sem erros, é feito o commit para salvar
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rollback executado");
                // Executa um rollback caso de algum erro
                connection.rollback();
            }
        }
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);

        stm.execute();

        try(ResultSet rst = stm.getGeneratedKeys()) {
            while (rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("ID criado foi: " + id);
            }
        }
    }
}
