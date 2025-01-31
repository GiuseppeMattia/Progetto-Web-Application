package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.interfaces.UtenteDAO;
import unical.demacs.backend.persistence.DBManager;
import unical.demacs.backend.services.interfaces.IUtenteService;

import java.util.List;
import java.util.NoSuchElementException;

public class UtenteService implements IUtenteService {

    private final UtenteDAO utenteDAO;

    public UtenteService(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    @Override
    public List<Utente> findAll(){

        List<Utente> utenti =  utenteDAO.findAll();

        if (utenti.isEmpty()) {
            throw new NoSuchElementException("Lista vuota");
        }

        return utenti;

    }

    @Override
    public Utente findByUsername(String username){

        return utenteDAO.findByUsername(username);
    }

    @Override
    public void save(Utente utente){

        Utente daTrovare = utenteDAO.findByUsername(utente.getUsername());

        if (daTrovare != null) {
            throw new IllegalArgumentException("L'utente " + utente.getUsername() + " esiste gia.");
        }

        utenteDAO.save(utente);
    }

    @Override
    public void update(Utente utente, boolean amministratore){

        checkUtenteSeEsiste(utente.getUsername());

        if(utente.getAmministratore()){
            throw new IllegalArgumentException("L'utente " + utente.getUsername() + " è gia amministratore");
        }

        utenteDAO.update(utente, amministratore);
    }

    @Override
    public void delete(Utente utente) {

        checkUtenteSeEsiste(utente.getUsername());

        // NON FACCIO NESSUN CONTROLLO RIGUARDO AL FATTO CHE UN UTENTE ABBIA O MENO ANNUNCI IN
        // VENDITA QUANDO VIENE ELIMINATO. CONTROLLO TUTTO NELLE CHIAMATE AL DB
        utenteDAO.delete(utente);
    }

    private void checkUtenteSeEsiste(String username){

        Utente utente = utenteDAO.findByUsername(username);
        if (utente == null) {
            throw new IllegalArgumentException("L'utente " + username + " non esiste.");
        }
    }
}
