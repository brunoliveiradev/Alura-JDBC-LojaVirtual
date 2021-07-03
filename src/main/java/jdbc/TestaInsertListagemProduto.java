package jdbc;

import config.ConnectionFactory;
import dao.ProdutoDAO;
import model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaInsertListagemProduto {

    public static void main(String[] args) throws SQLException {

        Produto comoda = new Produto("Produto", "Descrição Produto");

        try (Connection connection = new ConnectionFactory().reConnect()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.save(comoda);

            List<Produto> listaDeProdutos = produtoDAO.listar();
            listaDeProdutos.stream().forEach(System.out::println);
        }
    }
}
