package unical.demacs.backend.persistence.DAO.JDBC;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.Proxy.UtenteProxy;
import unical.demacs.backend.persistence.DAO.interfaces.UtenteDAO;
import unical.demacs.backend.persistence.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAOJDBC implements UtenteDAO {

    private final Connection connection;

    public UtenteDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Utente> findAll() {
        String query = "SELECT * FROM utente";
        List<Utente> utenti = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Utente utente = new UtenteProxy();
                utente.setAmministratore(resultSet.getBoolean("amministratore"));
                utente.setEmail(resultSet.getString("email"));
                utente.setPassword(resultSet.getString("password"));
                utente.setUsername(resultSet.getString("username"));
                utente.setVenditore(resultSet.getBoolean("venditore"));
                utente.setTelefono(resultSet.getString("telefono"));
                utente.setBannato(resultSet.getBoolean("bannato"));
                utenti.add(utente);
            }

            return utenti;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Utente findByUsername(String username) {

        String query = "SELECT * FROM utente WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Utente utente = new UtenteProxy();
                utente.setPassword(resultSet.getString("password"));
                utente.setUsername(resultSet.getString("username"));
                utente.setEmail(resultSet.getString("email"));
                utente.setAmministratore(resultSet.getBoolean("amministratore"));
                utente.setVenditore(resultSet.getBoolean("venditore"));
                utente.setTelefono(resultSet.getString("telefono"));
                utente.setBannato(resultSet.getBoolean("bannato"));
                return utente;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void save(Utente utente) {

        String query = "INSERT INTO utente (venditore, username, password, email, amministratore, bannato, telefono) VALUES (?, ?, ?, ?, ?,?,?)";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, utente.isVenditore());
            statement.setString(2, utente.getUsername());
            statement.setString(3, utente.getPassword());
            statement.setString(4, utente.getEmail());
            statement.setBoolean(5, utente.isAmministratore());
            statement.setBoolean(6, utente.isBannato());
            statement.setString(7, utente.getTelefono());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Utente utente) {

        String query = "UPDATE utente SET amministratore = ? WHERE username = ?";
        System.out.println("update: " + utente.isAmministratore());
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, utente.isAmministratore());
            preparedStatement.setString(2, utente.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void delete(Utente utente) {

        String query = "DELETE FROM utente WHERE username = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, utente.getUsername());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Annuncio> annunciDaEliminare = DBManager.getInstance().getAnnuncioDAO().findByUsernameUtente(utente.getUsername());

        for(Annuncio annuncio : annunciDaEliminare){
            DBManager.getInstance().getAnnuncioDAO().delete(annuncio);
        }

    }

    @Override
    public void ban(Utente utente) {
        String query = "UPDATE utente SET bannato = ? WHERE username = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setString(2, utente.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
