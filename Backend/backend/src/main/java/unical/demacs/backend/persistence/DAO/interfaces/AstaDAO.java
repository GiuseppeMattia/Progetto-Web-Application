package unical.demacs.backend.persistence.DAO.interfaces;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Asta;
import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.model.Utente;

import java.util.List;

public interface AstaDAO {

    List<Asta> findAll();                                                 //TROVA TUTTE LE ASTE ATTIVE
    Asta findById(int id);                                               //TROVA UNA SPECIFICA ASTA
    Asta findByAnnuncio(int idAnnuncio);
    List<Asta> findByCategoria(int idCategoria);                     //TROVA TUTTE LE ASTE DI UNA CATEGORIA SPECIFICA
    List<Asta> findByUtenteAcquirente(String username, boolean terminata);
    List<Asta> findBYUtenteVenditore(String username);
    void save(Asta asta);
    void update(Asta asta);
    void delete(Asta asta);
}
