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

    //TESTATA E FUNZIONA
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


    //TESTATA E FUNZIONA
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

    //TESTATA E FUNZIONA
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


    //TESTATA E FUNZIONA
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



    //TESTATA E FUNZIONA
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

    //TESTATA E FUNZIONA
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


    //TESTATA E FUNZIONA
    @Override
    public void save(Asta asta) {
        // NOTA BENE

        //LA save NON CONTROLLA SE L'ASTA CHE STO ANDANDO A SALVARE ESISTE GIA
        //LA save SALVA E BASTA

        //PER RIMEDIARE, LATO front, ANDREBBE FATTA UNA CHIAMATA A QUESTO DAO SULLA FUNZIONE findByAnnuncio,
        //IN MODO TALE CHE, SE ESISTE GIA UN ASTA APPARTENENTE AD UN ANNUNCIO, NON E' POSSIBILE CREARNE UNA NUOVA

        String query = "INSERT INTO asta( prezzo, terminata,  id_annuncio) VALUES(?, ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, asta.getPrezzo());
            statement.setBoolean(2, asta.getTerminated());
            statement.setInt(3, asta.getAnnuncio().getID());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //TESTATA E FUNZIONA
    @Override
    public void update(Asta asta, float prezzo) {
        //NOTA BENE. LA update NON CONTROLLA IN NESSUNO MODO CHE IL NUOVO PREZZO PROPOSTO
        //SIA INFERIORE AL PREZZO ATTUALE DELL'ASTA.

        //LA update AGGIORNA E BASTA

        String query = "UPDATE asta SET prezzo = ? WHERE id = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, prezzo);
            statement.setInt(2, asta.getID());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Asta asta) {

        //ANCHE QUI, LA delete NON CONTROLLA NULLA. NON CONTROLLA SE L'ASTA E' ATTIVA O MENO
        //ANCHE PERCHE, IDEALMENTE, POSSO ELIMINARE ANCHE UN'ASTA ATTIVA

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
        return asta;
    }
}
