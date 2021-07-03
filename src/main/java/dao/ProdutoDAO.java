package dao;

import model.Categoria;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Produto product) throws SQLException {
        String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";

        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstm.setString(1, product.getNome());
            pstm.setString(2, product.getDescricao());
            pstm.execute();

            try(ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    product.setId(rst.getInt(1));
                }
            }
        }

    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();

        String sql = "SELECT * FROM PRODUTO";

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            try(ResultSet rst = pstm.getResultSet()){
                while (rst.next()) {
                    Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }

    public List<Produto> find(Categoria category) throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();
        System.out.println("Executando buscar por categoria");

        String sql = "SELECT * FROM PRODUTO WHERE CATEGORIA_ID = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, category.getId());
            pstm.execute();

            try(ResultSet rst = pstm.getResultSet()){
                while (rst.next()) {
                    Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }
}
