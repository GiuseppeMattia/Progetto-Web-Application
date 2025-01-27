package unical.demacs.backend;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Recensione;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.JDBC.AnnuncioDAOJDBC;

import unical.demacs.backend.persistence.DAO.JDBC.UtenteDAOJDBC;
import unical.demacs.backend.persistence.DAO.Proxy.AnnuncioProxy;
import unical.demacs.backend.persistence.DAO.interfaces.UtenteDAO;
import unical.demacs.backend.persistence.DBManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {

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





    }
}
