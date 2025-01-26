package unical.demacs.backend.persistence.DAO.interfaces;

import unical.demacs.backend.model.Utente;

public interface UtenteDAO {

    Utente findByUsername(String username);
    void save(Utente utente);
    void delete(Utente utente);

}
