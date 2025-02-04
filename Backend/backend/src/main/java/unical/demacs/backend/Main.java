package unical.demacs.backend;

import unical.demacs.backend.model.*;
import unical.demacs.backend.persistence.DAO.JDBC.*;

import unical.demacs.backend.persistence.DAO.Proxy.AnnuncioProxy;
import unical.demacs.backend.persistence.DAO.Proxy.UtenteProxy;
import unical.demacs.backend.persistence.DAO.interfaces.*;
import unical.demacs.backend.persistence.DBManager;
import unical.demacs.backend.services.impl.UtenteService;

import javax.sound.midi.SoundbankResource;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UtenteDAO utenteDAO = new UtenteDAOJDBC(DBManager.getInstance().getConnection());
        Utente utente = utenteDAO.findByUsername("Ciccio");
        UtenteService utenteService = new UtenteService(utenteDAO);
        utenteService.ban(utente);

    }
}
