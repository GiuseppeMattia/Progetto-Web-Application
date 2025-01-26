package unical.demacs.backend.persistence.DAO.JDBC;

import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.UtenteDAO;
import unical.demacs.backend.persistence.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UtenteDAOJDBC implements UtenteDAO {

    private final Connection connection;

    public UtenteDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Utente findByUsername(String username) {

        String query = "SELECT * FROM utente WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Utente utente = new Utente();
                utente.setPassword(resultSet.getString("password"));
                utente.setUsername(resultSet.getString("username"));
                utente.setEmail(resultSet.getString("email"));
                utente.setAmministratore(resultSet.getBoolean("amministratore"));
                utente.setTipo(resultSet.getBoolean("tipo"));
                return utente;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Utente utente) {

    }

    @Override
    public void delete(Utente utente) {

    }
}
