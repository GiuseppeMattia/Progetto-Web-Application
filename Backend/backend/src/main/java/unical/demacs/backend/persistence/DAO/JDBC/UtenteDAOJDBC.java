package unical.demacs.backend.persistence.DAO.JDBC;

import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.interfaces.UtenteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAOJDBC implements UtenteDAO {

    private final Connection connection;

    public UtenteDAOJDBC(Connection connection) {
        this.connection = connection;
    }


    //TESTATA E FUNZIONA
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


    //TESTATA E FUNZIONA
    @Override
    public void save(Utente utente) {

        String query = "INSERT INTO utente (tipo, username, password, email, amministratore) VALUES (?, ?, ?, ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, utente.getTipo());
            statement.setString(2, utente.getUsername());
            statement.setString(3, utente.getPassword());
            statement.setString(4, utente.getEmail());
            statement.setBoolean(5, utente.getAmministratore());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    //TESTATA E FUNZIONA
    @Override
    public void delete(Utente utente) {

        //PER ELIMINARE, UTILIZZO SOLAMENTE username DATO CHE E' CHIAVE PRIMARIA
        String query = "DELETE FROM utente WHERE username = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, utente.getUsername());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
