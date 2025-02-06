package unical.demacs.backend.services.interfaces;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Asta;
import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.model.Utente;

import java.util.List;

public interface IAstaService {
    List<Asta> findAll();
    Asta findById(int id);
    Asta findByAnnuncio(int idAnnuncio);
    List<Asta> findByCategoria(int idCategoria);
    List<Asta> findByUtenteAcquirente(String username, boolean terminata);
    List<Asta> findBYUtenteVenditore(String username);
    void save(Asta asta);
    void update(Asta asta);
    void delete(Asta asta);
}
