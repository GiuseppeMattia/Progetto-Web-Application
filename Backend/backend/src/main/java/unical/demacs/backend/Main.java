package unical.demacs.backend;

import unical.demacs.backend.model.Recensione;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.JDBC.RecensioneDAOJDBC;
import unical.demacs.backend.persistence.DAO.RecensioneDAO;
import unical.demacs.backend.persistence.DBManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        //TEST RECENSIONE DAO - findByUtente

        /*
        Utente autore = new Utente();
        autore.setUsername("giovanna_verdi");

        RecensioneDAO recensioneDAO = new RecensioneDAOJDBC(DBManager.getInstance().getConnection());
        List<Recensione> recensione = recensioneDAO.findByUtente(autore.getUsername());

        for (Recensione rec : recensione) {
            System.out.println(rec.getAutore().getUsername());
            System.out.println(rec.getTesto());
            System.out.println(rec.getID());
        }
        */

        //TEST RECENSIONE DAO - findByAnnuncio

        /*
        RecensioneDAO recensioneDAO = new RecensioneDAOJDBC(DBManager.getInstance().getConnection());
        List<Recensione> recensioni = recensioneDAO.findByAnnuncio(1);

        for (Recensione rec : recensioni) {
            System.out.println(rec.getAutore().getUsername());
            System.out.println(rec.getTesto());
            System.out.println(rec.getID());
        }
         */


        //TEST RECENSIONE DAO - save

        Utente autore = new Utente();
        autore.setUsername("giupeppe");
        Recensione recensione = new Recensione();
        recensione.setAutore(autore);
        recensione.setTesto("SPETTACOLARE MAMMA MIA ");
        recensione.





    }
}
