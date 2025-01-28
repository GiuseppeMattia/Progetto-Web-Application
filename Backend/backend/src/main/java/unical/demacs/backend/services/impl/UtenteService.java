package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.interfaces.UtenteDAO;
import unical.demacs.backend.services.interfaces.IUtenteService;

import java.util.List;

public class UtenteService implements IUtenteService {

    private final UtenteDAO utenteDAO;

    public UtenteService(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    @Override
    public List<Utente> findAll() {
        return utenteDAO.findAll();
    }

    @Override
    public Utente findByUsername(String username) {
        return utenteDAO.findByUsername(username);
    }

    @Override
    public void save(Utente utente) {
        utenteDAO.save(utente);
    }

    @Override
    public void update(Utente utente, boolean amministratore) {
        utenteDAO.update(utente, amministratore);
    }

    @Override
    public void delete(Utente utente) {
        utenteDAO.delete(utente);
    }
}
