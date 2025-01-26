package unical.demacs.backend.persistence.DAO.JDBC;

import unical.demacs.backend.model.Recensione;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.interfaces.RecensioneDAO;
import unical.demacs.backend.persistence.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAOJDBC implements RecensioneDAO {

    private final Connection connection;

    public RecensioneDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    //TESTATA E FUNZIONA
    @Override
    public List<Recensione> findByAnnuncio(int idAnnuncio) {

        String query = "SELECT * FROM recensione WHERE id_annuncio = ?";
        List<Recensione> recensioni = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idAnnuncio);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Recensione recensione = new Recensione();
                Utente utente = DBManager.getInstance().getUtenteDAO().findByUsername(resultSet.getString("autore"));
                recensione.setID(resultSet.getInt("id"));
                recensione.setTesto(resultSet.getString("testo"));
                recensione.setAutore(utente);
                recensioni.add(recensione);
            }

            return recensioni;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //TESTATA E FUNZIONA
    @Override
    public List<Recensione> findByUtente(String username) {

        String query = "SELECT * FROM recensione WHERE autore = ?";
        List<Recensione> recensioni = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Recensione recensione = new Recensione();
                Utente autore = DBManager.getInstance().getUtenteDAO().findByUsername(resultSet.getString("autore"));
                recensione.setAutore(autore);
                recensione.setID(resultSet.getInt("id"));
                recensione.setTesto(resultSet.getString("testo"));
                recensioni.add(recensione);
            }

            return recensioni;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Recensione recensione) {

        String query = "INSERT INTO recensione (id_annuncio, testo, autore) VALUES (?, ?, ?)";


        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, recensione.getAnnuncio().getID());
            statement.setString(2, recensione.getTesto());
            statement.setString(3, recensione.getAutore().getUsername());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        query = "SELECT * FROM annuncio WHERE id_annuncio = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, recensione.getID());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
