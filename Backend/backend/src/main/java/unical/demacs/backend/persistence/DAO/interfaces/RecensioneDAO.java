package unical.demacs.backend.persistence.DAO.interfaces;

import unical.demacs.backend.model.Recensione;

import java.util.List;

public interface RecensioneDAO {

    List<Recensione> findByAnnuncio(int idAnnuncio);
    List<Recensione> findByUtente(String username);
    void save(Recensione recensione);
    void update(Recensione recensione, String testo);
    void delete(Recensione recensione);

}
