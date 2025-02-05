package unical.demacs.backend.persistence.DAO.interfaces;

import unical.demacs.backend.model.Utente;

import java.util.List;

public interface UtenteDAO {

    List<Utente> findAll();
    Utente findByUsername(String username);
    void save(Utente utente);
    void update(Utente utente);
    void delete(Utente utente);
    void ban(Utente utente);

}
