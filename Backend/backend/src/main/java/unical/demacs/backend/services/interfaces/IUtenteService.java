package unical.demacs.backend.services.interfaces;

import unical.demacs.backend.model.Utente;

import java.util.List;

public interface IUtenteService {
    List<Utente> findAll();
    Utente findByUsername(String username);
    void save(Utente utente);
    void update(Utente utente, boolean amministratore);
    void delete(Utente utente);
    void ban(Utente utente);
}
