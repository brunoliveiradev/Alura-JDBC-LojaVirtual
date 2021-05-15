package Java.repository;

import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "Bruno001.");

        Statement stm = con.createStatement();
        stm.execute("SELECT ID, nome, descricao FROM produto");

        ResultSet rst = stm.getResultSet();

        while (rst.next()) {
            Integer id = rst.getInt("ID");
            String nome = rst.getString("nome");
            String descricao = rst.getString("descricao");
            System.out.println(id + " " + nome + " " + descricao);
        }


        con.close();
    }

}
