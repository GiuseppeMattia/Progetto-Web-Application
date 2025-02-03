package unical.demacs.backend;

import unical.demacs.backend.model.*;
import unical.demacs.backend.persistence.DAO.JDBC.*;

import unical.demacs.backend.persistence.DAO.Proxy.AnnuncioProxy;
import unical.demacs.backend.persistence.DAO.Proxy.UtenteProxy;
import unical.demacs.backend.persistence.DAO.interfaces.*;
import unical.demacs.backend.persistence.DBManager;

import javax.sound.midi.SoundbankResource;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UtenteDAO utenteDAO = new UtenteDAOJDBC(DBManager.getInstance().getConnection());
        Utente utente = new Utente();
        utente.setUsername("provaNuovoDb");
        utente.setPassword("provaNuovoDb");
        utente.setEmail("provaNuovoDb@gmail.com");
        utente.setBannato(true);
        utente.setTelefono("123456789");
        utente.setVenditore(true);
        utente.setAmministratore(true);
        utenteDAO.save(utente);

    }
}
