package unical.demacs.backend.persistence.DAO.interfaces;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Categoria;

import java.util.List;

public interface AnnuncioDAO {

    List<Annuncio> findAll();                               //TROVO TUTTI GLI ANNUNCI
    Annuncio findById(String id);                           //TROVO UNO SPECIFICO ANNUNCIO
    List<Annuncio> findByUtenteID(String IDUtente);         //TROVO TUTTI GLI ANNUNCI DI UNO SPECIFICO UTENTE
    List<Annuncio> findByCategoria(Categoria categoria);    //TROVO TUTTI GLI ANNUNCI DI UNA SPECIFICA CATEGORIA
    void save(Annuncio annuncio);
    void update(Annuncio annuncio);
    void delete(Annuncio annuncio);

}
