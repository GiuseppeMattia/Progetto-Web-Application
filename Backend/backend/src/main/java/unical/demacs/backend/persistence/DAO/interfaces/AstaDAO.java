package unical.demacs.backend.persistence.DAO.interfaces;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Asta;
import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.model.Utente;

import java.util.List;

public interface AstaDAO {

    List<Asta> findAll();                                                 //TROVA TUTTE LE ASTE ATTIVE
    Asta findById(int id);                                               //TROVA UNA SPECIFICA ASTA
    Asta findByAnnuncio(Annuncio annuncio);
    List<Asta> findByCategoria(Categoria categoria);                     //TROVA TUTTE LE ASTE DI UNA CATEGORIA SPECIFICA
    List<Asta> findByUtenteAcquirente(Utente utente, boolean terminata);
    List<Asta> findBYUtenteVenditore(Utente venditore);
    void save(Asta asta);
    void update(Asta asta, float prezzo);
    void delete(Asta asta);
}
