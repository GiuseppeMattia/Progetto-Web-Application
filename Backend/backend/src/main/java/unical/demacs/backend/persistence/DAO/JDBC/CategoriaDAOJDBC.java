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

    private Connection connection;

    public CategoriaDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Categoria> getAll() {
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
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Categoria getByID(int id) {
        String query = "SELECT * FROM categoria WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
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
}
