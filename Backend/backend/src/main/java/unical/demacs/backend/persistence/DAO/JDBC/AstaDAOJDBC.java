package unical.demacs.backend.persistence.DAO.JDBC;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Asta;
import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.interfaces.AstaDAO;
import unical.demacs.backend.persistence.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AstaDAOJDBC implements AstaDAO {

    private final Connection connection;

    public AstaDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Asta> findAll() {
        String query = "SELECT * FROM Asta";
        List<Asta> aste = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                aste.add(buildAstaFromResultSet(resultSet));
            }

            return aste;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Asta findById(int id) {

        String query = "SELECT * FROM Asta WHERE id = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return buildAstaFromResultSet(resultSet);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Asta findByAnnuncio(int idAnnuncio) {
        String query = "SELECT * FROM asta WHERE id_annuncio = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idAnnuncio);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return buildAstaFromResultSet(resultSet);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Asta> findByCategoria(int idCategoria) {

        String query = "SELECT * FROM asta, annuncio WHERE asta.id_annuncio = annuncio.id AND annuncio.id_categoria = ?";
        List<Asta> aste = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCategoria);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                aste.add(buildAstaFromResultSet(resultSet));
            }

            return aste;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Asta> findByUtenteAcquirente(String username, boolean terminata) {

        String query = "SELECT * FROM Asta WHERE acquirente = ? AND terminata = ?";
        List<Asta> aste = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setBoolean(2, terminata);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                aste.add(buildAstaFromResultSet(resultSet));
            }

            return aste;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Asta> findBYUtenteVenditore(String username) {
        String query = "SELECT * FROM asta, annuncio WHERE asta.id_annuncio = annuncio.id AND annuncio.venditore = ?";
        List<Asta> aste = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                aste.add(buildAstaFromResultSet(resultSet));
            }

            return aste;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void save(Asta asta) {

        String query = "INSERT INTO asta( prezzo, terminata,  id_annuncio, scadenza) VALUES(?, ?, ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, asta.getPrezzo());
            statement.setBoolean(2, asta.getTerminated());
            statement.setInt(3, asta.getAnnuncio().getID());
            statement.setDate(4, asta.getScadenza());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Asta asta) {

        String query = "UPDATE asta SET prezzo = ?, terminata = ?, acquirente = ?, scadenza = ? WHERE id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, asta.getPrezzo());
            statement.setBoolean(2, asta.getTerminated());
            statement.setString(3, asta.getAcquirente().getUsername());
            statement.setDate(4, asta.getScadenza());
            statement.setInt(5, asta.getID());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Asta asta) {

        String query = "DELETE FROM asta WHERE id = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, asta.getID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Asta buildAstaFromResultSet(ResultSet resultSet) throws SQLException {
        Asta asta = new Asta();
        Utente utente = DBManager.getInstance().getUtenteDAO().findByUsername(resultSet.getString("acquirente"));
        Annuncio annuncio = DBManager.getInstance().getAnnuncioDAO().findById(resultSet.getInt("id_annuncio"));
        asta.setAcquirente(utente);
        asta.setAnnuncio(annuncio);
        asta.setPrezzo(resultSet.getInt("prezzo"));
        asta.setTerminated(resultSet.getBoolean("terminata"));
        asta.setID(resultSet.getInt("id"));
        asta.setScadenza(resultSet.getDate("scadenza"));
        return asta;
    }
}
