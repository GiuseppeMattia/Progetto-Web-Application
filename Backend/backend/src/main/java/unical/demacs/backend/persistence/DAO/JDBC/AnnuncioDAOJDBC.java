package unical.demacs.backend.persistence.DAO.JDBC;

import unical.demacs.backend.model.*;
import unical.demacs.backend.persistence.DAO.AnnuncioDAO;
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

        /*
        String query = "SELECT * FROM annuncio";

        try(PreparedStatement statement = connection.prepareStatement(query)){

            ResultSet resultSet = statement.executeQuery();
            List<Annuncio> annunci = new ArrayList<>();

            while(resultSet.next()){

                Annuncio annuncio = new Annuncio();
                annuncio.setID(resultSet.getInt("id"));
                annuncio.setPrezzo(resultSet.getFloat("prezzo"));
                annuncio.setDescrizione(resultSet.getString("descrizione"));
                annuncio.setTitolo(resultSet.getString("titolo"));
                annuncio.setPrezzoScontato(resultSet.getFloat("prezzoScontato"));



                //CERCO L'UTENTE
                String queryUtente = "SELECT * FROM utente WHERE username = ?";
                String username = resultSet.getString("username");

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(queryUtente);
                    preparedStatement.setString(1, username);
                    ResultSet resultSetUtente = preparedStatement.executeQuery();
                    if(resultSetUtente.next()){
                        Utente utente = new Utente();
                        utente.setUsername(resultSetUtente.getString("username"));
                        utente.setPassword(resultSetUtente.getString("password"));
                        utente.setEmail(resultSetUtente.getString("email"));
                        utente.setTipo(resultSetUtente.getBoolean("tipo"));
                        utente.setAmministratore(resultSetUtente.getBoolean("amministratore"));

                        annuncio.setVenditore(utente);      //VENDITORE SETTATO
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }



                //CERCO L'ARTIOLO
                String queryArticolo = "SELECT * FROM articolo WHERE id = ?";
                String idArticolo = resultSet.getString("id_articolo");

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(queryArticolo);
                    preparedStatement.setString(1, idArticolo);
                    ResultSet resultSetArticolo = preparedStatement.executeQuery();
                    while(resultSetArticolo.next()){
                        Articolo articolo = new Articolo();
                        articolo.setID(resultSetArticolo.getString("id"));
                        articolo.setColore(resultSetArticolo.getString("colore"));
                        articolo.setLunghezza(resultSetArticolo.getFloat("lunghezza"));
                        articolo.setLarghezza(resultSetArticolo.getFloat("larghezza"));
                        articolo.setProfondita(resultSetArticolo.getFloat("profondita"));
                        articolo.setMarca(resultSetArticolo.getString("marca"));
                        articolo.setModello(resultSetArticolo.getString("modello"));

                        //TROVO LA CATEGORIA

                        String queryCategoria = "SELECT * FROM categoria WHERE id = ?";
                        String idCategoria = resultSet.getString("id_categoria");

                        try {
                            PreparedStatement preparedStatement1 = connection.prepareStatement(queryCategoria);
                            preparedStatement1.setString(1, idCategoria);
                            ResultSet resultSetCategoria = preparedStatement1.executeQuery();
                            while(resultSetCategoria.next()){
                                switch (resultSetCategoria.getInt("id")) {
                                    case 1:
                                        articolo.setCategoria(Categoria.SMARTPHONE);
                                        break;
                                    case 2:
                                        articolo.setCategoria(Categoria.PC);
                                        break;
                                    case 3:
                                        articolo.setCategoria(Categoria.CONSOLE);
                                        break;
                                    case 4:
                                        articolo.setCategoria(Categoria.ACCESSORI);
                                        break;
                                    default:
                                        articolo.setCategoria(null);        //HA SENSO?
                                }

                            }

                            annuncio.setArticolo(articolo);     //ARTICOLO SETTATO

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                //CERCO LA LISTA DI RECENSIONI

                String queryRecensioni = "SELECT * FROM recensione WHERE id_annuncio = ?";
                String idAnnuncio = resultSet.getString("id_annuncio");

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(queryRecensioni);
                    preparedStatement.setString(1, idAnnuncio);
                    ResultSet resultSetRecensioni = preparedStatement.executeQuery();
                    while(resultSetRecensioni.next()){
                        Recensione recensione = new Recensione();
                        recensione.setID(resultSetRecensioni.getString("id"));
                        recensione.set
                    }
                }




            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

         */

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
    public List<Annuncio> findByArticolo(Articolo articolo) {
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
