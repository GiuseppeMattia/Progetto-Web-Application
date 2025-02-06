package unical.demacs.backend.persistence.DAO.JDBC;

import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.persistence.DAO.interfaces.CategoriaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOJDBC implements CategoriaDAO {

    private final Connection connection;

    public CategoriaDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Categoria> findAll() {
        String query = "SELECT * FROM categoria";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            List<Categoria> categorie = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setID(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("nome"));
                categorie.add(categoria);
            }

            return categorie;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Categoria findById(int id) {
        String query = "SELECT * FROM categoria WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                Categoria categoria = new Categoria();

                categoria.setID(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("nome"));
                return categoria;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Categoria findByName(String nome) {

        String query = "SELECT * FROM categoria WHERE nome = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setID(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("nome"));
                return categoria;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
