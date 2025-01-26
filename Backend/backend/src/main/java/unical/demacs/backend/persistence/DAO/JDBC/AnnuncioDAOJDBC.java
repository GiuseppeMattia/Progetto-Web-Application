package unical.demacs.backend.persistence.DAO.JDBC;

import unical.demacs.backend.model.*;
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

                Annuncio annuncio = new Annuncio();
                annuncio.setID(resultSet.getInt("id"));
                annuncio.setPrezzo(resultSet.getFloat("prezzo"));
                annuncio.setDescrizione(resultSet.getString("descrizione"));
                annuncio.setTitolo(resultSet.getString("titolo"));
                annuncio.setPrezzoScontato(resultSet.getFloat("prezzo_scontato"));
                annuncio.setMarca(resultSet.getString("marca"));
                annuncio.setModello(resultSet.getString("modello"));
                annuncio.setFoto(resultSet.getBytes("foto"));

                // Cerco l'utente in base all'username
                Utente utente = DBManager.getInstance().getUtenteDAO().findByUsername(resultSet.getString("venditore"));
                annuncio.setVenditore(utente);

                //Categoria categoria = DBManager.getInstance().getCategoriaDAO().getByID(resultSet.getInt("id_categoria"));
                //annuncio.setCategoria(categoria);

                annunci.add(annuncio);
            }
            return annunci;
        }catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }

    @Override
    public Annuncio findById(String id) {
        return null;
    }

    @Override
    public List<Annuncio> findByUtenteID(String IDUtente) {
        return List.of();
    }

    @Override
    public List<Annuncio> findByCategoria(Categoria categoria) {
        return List.of();
    }


    @Override
    public void save(Annuncio annuncio) {

    }

    @Override
    public void update(Annuncio annuncio) {

    }

    @Override
    public void delete(Annuncio annuncio) {

    }
}
