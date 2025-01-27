package unical.demacs.backend;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.model.Recensione;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.JDBC.AnnuncioDAOJDBC;

import unical.demacs.backend.persistence.DAO.JDBC.CategoriaDAOJDBC;
import unical.demacs.backend.persistence.DAO.JDBC.RecensioneDAOJDBC;
import unical.demacs.backend.persistence.DAO.JDBC.UtenteDAOJDBC;
import unical.demacs.backend.persistence.DAO.Proxy.AnnuncioProxy;
import unical.demacs.backend.persistence.DAO.interfaces.CategoriaDAO;
import unical.demacs.backend.persistence.DAO.interfaces.RecensioneDAO;
import unical.demacs.backend.persistence.DAO.interfaces.UtenteDAO;
import unical.demacs.backend.persistence.DBManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        //TEST Categoria DAO - findByName

        CategoriaDAO categoriaDAO = new CategoriaDAOJDBC(DBManager.getInstance().getConnection());
        Categoria categoria = categoriaDAO.findByName("PC");
        System.out.println(categoria.toString());



        //TEST Categoria DAO - findById

        /*
        CategoriaDAO categoriaDAO = new CategoriaDAOJDBC(DBManager.getInstance().getConnection());
        Categoria categoria = categoriaDAO.findById(1);
        System.out.println(categoria.toString());
         */

        //TEST Categoria DAO - findAll

        /*
        CategoriaDAO categoriaDAO = new CategoriaDAOJDBC(DBManager.getInstance().getConnection());
        List<Categoria> categorie = categoriaDAO.findAll();
        for (Categoria c : categorie) {
            System.out.println(c.toString());
        }
         */


        //TEST Recensione DAO - delete

        /*
        Recensione recensione = new Recensione();
        recensione.setID(4);
        RecensioneDAO recensioneDAO = new RecensioneDAOJDBC(DBManager.getInstance().getConnection());
        recensioneDAO.delete(recensione);
         */

        //TEST Recensione DAO - update

        /*
        Recensione recensione = new Recensione();
        recensione.setID(1);

        RecensioneDAO recensioneDAO = new RecensioneDAOJDBC(DBManager.getInstance().getConnection());
        recensioneDAO.update(recensione, "HO MODIFICATO LA REC");
         */

        //TEST Recensione DAO - save

        /*
        Annuncio annuncio = new Annuncio();
        annuncio.setID(1);

        Utente utente = new Utente();
        utente.setUsername("Ciccio");

        Recensione recensione = new Recensione();
        recensione.setAutore(utente);
        recensione.setTesto("SPETTACOLO MAMMA MIA");
        recensione.setAnnuncio(annuncio);

        RecensioneDAO recensioneDAO = new RecensioneDAOJDBC(DBManager.getInstance().getConnection());
        recensioneDAO.save(recensione);
         */

        //TEST Recensione DAO - findByUsername

        /*
        RecensioneDAO recensioneDAO = new RecensioneDAOJDBC(DBManager.getInstance().getConnection());
        List<Recensione> recensioni = recensioneDAO.findByUtente("Ciccio");

        for (Recensione r : recensioni) {
            System.out.println(r.toString());
        }
         */

        //TEST Recensione DAO - findByAnnuncio

        /*
        RecensioneDAO recensioneDAO = new RecensioneDAOJDBC(DBManager.getInstance().getConnection());
        List<Recensione> recensioni = recensioneDAO.findByAnnuncio(1);

        for (Recensione r : recensioni) {
            System.out.println(r.toString());
        }
         */

        //TEST Utente DAO - save

        /*
        Utente utente = new Utente();
        utente.setUsername("Domenico");
        utente.setPassword("password");
        utente.setEmail("domenico@gmail.com");
        utente.setTipo(true);
        utente.setAmministratore(false);

        UtenteDAO utenteDAO = new UtenteDAOJDBC(DBManager.getInstance().getConnection());
        utenteDAO.save(utente);
         */

        //TEST Utente DAO - delete

        /*
        Utente utente = new Utente();
        utente.setUsername("Domenico");
        utente.setPassword("password");
        utente.setEmail("domenico@gmail.com");
        utente.setTipo(true);
        utente.setAmministratore(false);

        UtenteDAO utenteDAO = new UtenteDAOJDBC(DBManager.getInstance().getConnection());
        utenteDAO.delete(utente);
         */

        //TEST Utente DAO - findByUsername

        /*
        UtenteDAO utenteDAO = new UtenteDAOJDBC(DBManager.getInstance().getConnection());
        Utente utente = utenteDAO.findByUsername("admin");
        System.out.println(utente.getUsername() + " " + utente.getPassword());
         */

        //TEST Utente DAO - findAll

        /*
        UtenteDAO utenteDAO = new UtenteDAOJDBC(DBManager.getInstance().getConnection());
        List<Utente> utenti = utenteDAO.findAll();

        for(Utente utente : utenti) {
            System.out.println(utente);
        }
         */

        //TEST Utente DAO - update

        /*
        Utente utente = new Utente();
        utente.setUsername("Domenico");
        utente.setPassword("password");
        utente.setEmail("domenico@gmail.com");
        utente.setTipo(true);
        utente.setAmministratore(false);

        UtenteDAO utenteDAO = new UtenteDAOJDBC(DBManager.getInstance().getConnection());
        utenteDAO.update(utente,  true);
         */







    }
}
