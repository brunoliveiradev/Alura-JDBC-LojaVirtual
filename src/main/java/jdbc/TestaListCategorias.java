package jdbc;

import config.ConnectionFactory;
import dao.CategoriaDAO;
import model.Categoria;
import model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListCategorias {

    public static void main(String[] args) throws SQLException {

        try(Connection connection = new ConnectionFactory().reConnect()) {
            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            List<Categoria> categorias = categoriaDAO.listarComProdutos();

            categorias.stream().forEach(category -> {
                System.out.println(category.getNome());

                for(Produto produto : category.getProdutos()) {
                    System.out.println(category.getNome() + " - " + produto.getNome());
                }
            });
        }
    }
}
