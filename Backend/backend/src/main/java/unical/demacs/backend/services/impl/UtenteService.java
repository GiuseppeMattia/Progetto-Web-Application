package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.interfaces.UtenteDAO;
import unical.demacs.backend.persistence.DBManager;
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

        Utente toFind = DBManager.getInstance().getUtenteDAO().findByUsername(utente.getUsername());
        if (toFind != null) {
            System.out.println(utente.getUsername() + " esiste gia");
            throw new IllegalArgumentException("L'utente " + utente.getUsername() + " esiste gia.");
        }
        utenteDAO.save(utente);
    }

    @Override
    public void update(Utente utente, boolean amministratore) {
        if(utente.getAmministratore()){
            throw new IllegalArgumentException("L'utente " + utente.getUsername() + " è gia amministratore");
        }

        utenteDAO.update(utente, amministratore);
    }

    @Override
    public void delete(Utente utente) {
        // NON FACCIO NESSUN CONTROLLO RIGUARDO AL FATTO CHE UN UTENTE ABBIA O MENO ANNUNCI IN
        // VENDITA QUANDO VIENE ELIMINATO. CONTROLLO TUTTO NELLE CHIAMATE AL DB
        utenteDAO.delete(utente);
    }
}
