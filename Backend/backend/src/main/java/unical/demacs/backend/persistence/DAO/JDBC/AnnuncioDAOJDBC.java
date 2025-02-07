package unical.demacs.backend.persistence.DAO.JDBC;

import unical.demacs.backend.model.*;
import unical.demacs.backend.persistence.DAO.Proxy.AnnuncioProxy;
import unical.demacs.backend.persistence.DAO.interfaces.AnnuncioDAO;
import unical.demacs.backend.persistence.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnnuncioDAOJDBC implements AnnuncioDAO {

    private final Connection connection;

    public AnnuncioDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Annuncio> findAll() {

        String query = "SELECT * FROM annuncio";

        try(PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            List<Annuncio> annunci = new ArrayList<>();

            while (resultSet.next()) {
                annunci.add(buildAnnuncioFromResultSet(resultSet));
            }

            return annunci;

        }catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }

    @Override
    public Annuncio findById(int id) {

        String query = "SELECT * FROM annuncio WHERE id=?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return buildAnnuncioFromResultSet(resultSet);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Annuncio> findByUsernameUtente(String username) {

        String query = "SELECT * FROM annuncio WHERE venditore=?";
        List<Annuncio> annunci = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                annunci.add(buildAnnuncioFromResultSet(resultSet));
            }

            return annunci;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Annuncio> findByCategoria(int idCategoria) {
        String query = "SELECT * FROM annuncio WHERE id_categoria=?";
        List<Annuncio> annunci = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCategoria);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                annunci.add(buildAnnuncioFromResultSet(resultSet));
            }

            return annunci;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Annuncio> findAnnuncioByTitolo(String titolo) {

        String query = "SELECT * FROM annuncio WHERE titolo=?";
        List<Annuncio> annunci = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, titolo);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                annunci.add(buildAnnuncioFromResultSet(resultSet));
            }

            return annunci;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Annuncio> findAnnuncioByTitoloAndCategoria(String titolo, int idCategoria) {
        String query = "SELECT * FROM annuncio WHERE titolo=? AND id_categoria=?";
        List<Annuncio> annunci = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, titolo);
            statement.setInt(2, idCategoria);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                annunci.add(buildAnnuncioFromResultSet(resultSet));
            }

            return annunci;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void save(Annuncio annuncio) {

        String query = "INSERT INTO annuncio(prezzo, descrizione, foto, titolo, venditore, marca, modello, id_categoria) VALUES(?,?,?,?,?,?,?,?)";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, annuncio.getPrezzo());
            statement.setString(2, annuncio.getDescrizione());
            statement.setBytes(3, annuncio.getFoto());
            statement.setString(4, annuncio.getTitolo());
            statement.setString(5, annuncio.getVenditore().getUsername());
            statement.setString(6, annuncio.getMarca());
            statement.setString(7, annuncio.getModello());
            statement.setInt(8, annuncio.getCategoria().getID());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void update(Annuncio annuncio) {

        String query = "UPDATE annuncio SET prezzo=?, descrizione=?," +
                         " titolo=?, prezzo_scontato=?, marca=?, modello=? WHERE id=?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, annuncio.getPrezzo());
            statement.setString(2, annuncio.getDescrizione());
            statement.setString(3, annuncio.getTitolo());
            statement.setFloat(4, annuncio.getPrezzoScontato());
            statement.setString(5, annuncio.getMarca());
            statement.setString(6, annuncio.getModello());
            statement.setInt(7, annuncio.getID());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Annuncio annuncio) {


        Asta astaDaEliminare = DBManager.getInstance().getAstaDAO().findByAnnuncio(annuncio.getID());
        if(astaDaEliminare != null) {
            DBManager.getInstance().getAstaDAO().delete(astaDaEliminare);
        }

        List<Recensione> recensioniDaELiminare = DBManager.getInstance().getRecensioneDAO().findByAnnuncio(annuncio.getID());
        if(recensioniDaELiminare != null) {
            for(Recensione r : recensioniDaELiminare) {
                DBManager.getInstance().getRecensioneDAO().delete(r);
            }
        }


        String query = "DELETE FROM annuncio WHERE id=?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, annuncio.getID());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Annuncio buildAnnuncioFromResultSet(ResultSet resultSet) throws SQLException{

        Annuncio annuncio = new AnnuncioProxy();
        Utente utente = DBManager.getInstance().getUtenteDAO().findByUsername(resultSet.getString("venditore"));
        annuncio.setVenditore(utente);
        annuncio.setID(resultSet.getInt("id"));
        annuncio.setPrezzo(resultSet.getFloat("prezzo"));
        annuncio.setDescrizione(resultSet.getString("descrizione"));
        annuncio.setTitolo(resultSet.getString("titolo"));
        annuncio.setMarca(resultSet.getString("marca"));
        annuncio.setModello(resultSet.getString("modello"));
        annuncio.setFoto(resultSet.getBytes("foto"));
        annuncio.setPrezzoScontato(resultSet.getFloat("prezzo_scontato"));
        annuncio.setCategoria(DBManager.getInstance().getCategoriaDAO().findById(resultSet.getInt("id_categoria")));

        return annuncio;
    }
}
