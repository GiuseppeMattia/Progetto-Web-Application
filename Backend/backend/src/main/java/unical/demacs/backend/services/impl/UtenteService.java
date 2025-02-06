package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.interfaces.UtenteDAO;
import unical.demacs.backend.services.interfaces.IUtenteService;

import java.util.List;
import java.util.NoSuchElementException;

public class UtenteService implements IUtenteService {

    private final UtenteDAO utenteDAO;

    public UtenteService(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    @Override
    public void ban(Utente utente) {

        if(utente.isBannato()){
            throw new IllegalArgumentException("L'utente è gia bannato");
        }

        utenteDAO.ban(utente);
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
    public void update(Utente utente){

        checkUtenteSeEsiste(utente.getUsername());
        utenteDAO.update(utente);
    }

    @Override
    public void delete(Utente utente) {

        checkUtenteSeEsiste(utente.getUsername());
        utenteDAO.delete(utente);
    }

    private void checkUtenteSeEsiste(String username){

        Utente utente = utenteDAO.findByUsername(username);
        if (utente == null) {
            throw new IllegalArgumentException("L'utente " + username + " non esiste.");
        }
    }
}
