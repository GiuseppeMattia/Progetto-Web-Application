package unical.demacs.backend;

import unical.demacs.backend.model.*;
import unical.demacs.backend.persistence.DAO.JDBC.*;

import unical.demacs.backend.persistence.DAO.Proxy.UtenteProxy;
import unical.demacs.backend.persistence.DAO.interfaces.*;
import unical.demacs.backend.persistence.DBManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Utente venditore = new UtenteProxy();
        venditore.setUsername("Ciccio");
        List<Asta> aste = venditore.getAsteAttiveDaVenditore();
        for (Asta asta : aste) {
            System.out.println(asta);
        }

        //TEST Annuncio DAO - findAnnuncioByTitoloAndCategoria

        /*
        Categoria categoria = new Categoria();
        categoria.setID(1);
        AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC(DBManager.getInstance().getConnection());
        List<Annuncio> annunci = annuncioDAO.findAnnuncioByTitoloAndCategoria("Apple iPhone 15", categoria);

        for (Annuncio annuncio : annunci) {
            System.out.println(annuncio);
        }

         */


        //TEST Annuncio DAO - findAnnuncioByCategoria

        /*
        Categoria categoria = new Categoria();
        categoria.setID(2);
        AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC(DBManager.getInstance().getConnection());
        List<Annuncio> annunci = annuncioDAO.findAnnuncioByCategoria(categoria);

        for (Annuncio annuncio : annunci) {
            System.out.println(annuncio);
        }
         */

        //TEST Annuncio DAO - findAnnuncioByTitolo

        /*
        AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC(DBManager.getInstance().getConnection());
        List<Annuncio> annunci = annuncioDAO.findAnnuncioByTitolo("Apple iPhone 15");

        for (Annuncio annuncio : annunci) {
            System.out.println(annuncio);
        }

         */

        //TEST Annuncio DAO - delete

        /*
        Annuncio annuncio = new Annuncio();
        annuncio.setID(1);
        AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC(DBManager.getInstance().getConnection());
        annuncioDAO.delete(annuncio);
         */

        //TEST Annuncio DAO - save

        /*
        Utente utente = new Utente();
        utente.setUsername("Ciccio");
        Categoria categoria = new Categoria();
        categoria.setID(1);
        byte[] b = {100, 100};
        Annuncio annuncio = new Annuncio();
        annuncio.setModello("iPhone 15");
        annuncio.setMarca("Apple");
        annuncio.setDescrizione("Apple iPhone 15");
        annuncio.setFoto(b);
        annuncio.setTitolo("Apple iPhone 15");
        annuncio.setVenditore(utente);
        annuncio.setPrezzo(1000);
        annuncio.setCategoria(categoria);

        AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC(DBManager.getInstance().getConnection());
        annuncioDAO.save(annuncio);
         */

        //TEST Annuncio DAO - update

        /*
        Annuncio annuncio = new Annuncio();
        annuncio.setID(1);
        AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC(DBManager.getInstance().getConnection());
        annuncioDAO.update(annuncio, 900);
         */


        //TEST Annuncio DAO - findByCategoria

        /*
        Categoria categoria = new Categoria();
        categoria.setID(2);
        AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC(DBManager.getInstance().getConnection());
        List<Annuncio> annunci = annuncioDAO.findByCategoria(categoria);

        for (Annuncio annuncio : annunci) {
            System.out.println(annuncio);
        }
         */

        //TEST Annuncio DAO - findByUsernameUtente

        /*
        AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC(DBManager.getInstance().getConnection());
        List<Annuncio> annunci = annuncioDAO.findByUsername("Ciccio");
        for (Annuncio annuncio : annunci) {
            System.out.println(annuncio);
        }
         */



        //TEST Annuncio DAO - findById

        /*
        AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC(DBManager.getInstance().getConnection());
        Annuncio annuncio = annuncioDAO.findById(2);
        System.out.println(annuncio);
         */

        //TEST Annuncio DAO - finAll

        /*
        AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC(DBManager.getInstance().getConnection());
        List<Annuncio> annunci = annuncioDAO.findAll();
        for (Annuncio annuncio : annunci) {
            System.out.println(annuncio);
        }
         */

        //TEST Asta DAO - findByUtenteVenditore

        /*
        Utente utente = new Utente();
        utente.setUsername("Ciccio");

        AstaDAO astaDAO = new AstaDAOJDBC(DBManager.getInstance().getConnection());
        List<Asta> aste = astaDAO.findBYUtenteVenditore(utente);
        for (Asta asta : aste) {
            System.out.println(asta);
        }
         */

        //TEST Asta DAO - findByAnnuncio

        /*
        Annuncio annuncio = new Annuncio();
        annuncio.setID(1);
        AstaDAO astaDAO = new AstaDAOJDBC(DBManager.getInstance().getConnection());
        Asta asta = astaDAO.findByAnnuncio(annuncio);

        System.out.println(asta);
         */


        //TEST Asta DAO - save

        /*
        Annuncio annuncio = new Annuncio();
        annuncio.setID(1);
        Utente utente = new Utente();
        utente.setUsername("Franco");

        Asta asta = new Asta();
        asta.setAcquirente(utente);
        asta.setTerminated(false);
        asta.setPrezzo(300);
        asta.setAnnuncio(annuncio);

        AstaDAO astaDAO = new AstaDAOJDBC(DBManager.getInstance().getConnection());
        astaDAO.save(asta);
        */

        //TEST Asta DAO - delete

        /*
        Asta asta = new Asta();
        asta.setID(1);
        AstaDAO astaDAO = new AstaDAOJDBC(DBManager.getInstance().getConnection());
        astaDAO.delete(asta);
         */

        //TEST Asta DAO - update

        /*
        Asta asta = new Asta();
        asta.setID(1);
        AstaDAO astaDAO = new AstaDAOJDBC(DBManager.getInstance().getConnection());
        astaDAO.update(asta, 200);
         */



        //TEST Asta DAO - findByUtenteAcquirente

        /*
        Utente utente = new Utente();
        utente.setUsername("Domenico");

        AstaDAO astaDAO = new AstaDAOJDBC(DBManager.getInstance().getConnection());
        List<Asta> aste = astaDAO.findByUtenteAcquirente(utente, true);

        for (Asta asta : aste) {
            System.out.println(asta);
        }
         */




        //TEST Asta DAO - findByCategoria

        /*
        Categoria categoria = new Categoria();
        categoria.setNome("PC");
        categoria.setID(2);
        AstaDAO astaDAO = new AstaDAOJDBC(DBManager.getInstance().getConnection());
        List<Asta> aste = astaDAO.findByCategoria(categoria);

        for (Asta asta : aste) {
            System.out.println(asta);
        }
         */

        //TEST Asta DAO - findById

        /*
        AstaDAO astaDAO = new AstaDAOJDBC(DBManager.getInstance().getConnection());
        Asta asta = astaDAO.findById(1);
        System.out.println(asta);
         */



        //TEST Asta DAO - findAll

        /*
        AstaDAO astaDAO = new AstaDAOJDBC(DBManager.getInstance().getConnection());
        List<Asta> aste = astaDAO.findAll();
        for (Asta asta : aste) {
            System.out.println(asta.toString());
        }
         */

        //TEST Categoria DAO - findByName

        /*
        CategoriaDAO categoriaDAO = new CategoriaDAOJDBC(DBManager.getInstance().getConnection());
        Categoria categoria = categoriaDAO.findByName("PC");
        System.out.println(categoria.toString());
         */


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
