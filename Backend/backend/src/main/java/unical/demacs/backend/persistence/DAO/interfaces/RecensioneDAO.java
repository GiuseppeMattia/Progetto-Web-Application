package unical.demacs.backend.persistence.DAO.interfaces;

import unical.demacs.backend.model.Recensione;

import java.util.List;

public interface RecensioneDAO {

    List<Recensione> findByAnnuncio(int idAnnuncio);                  //TROVO TUTTE LE RECENSIONI DI UNO SECIFICO ANNUNCIO
    List<Recensione> findByUtente(String username);                   //TROVO TUTTE LE RECENSIONI DI UNO SPECIFICO UTENTE
    void save(Recensione recensione);
    void update(Recensione recensione, String testo);
    void delete(Recensione recensione);

}
