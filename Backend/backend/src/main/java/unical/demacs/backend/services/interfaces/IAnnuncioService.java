package unical.demacs.backend.services.interfaces;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Categoria;

import java.util.List;

public interface IAnnuncioService {

    List<Annuncio> findAll();                                     //TROVO TUTTI GLI ANNUNCI
    Annuncio findById(int id);                                    //TROVO UNO SPECIFICO ANNUNCIO
    List<Annuncio> findByUsernameUtente(String IDUtente);         //TROVO TUTTI GLI ANNUNCI DI UNO SPECIFICO UTENTE
    List<Annuncio> findByCategoria(Categoria categoria);          //TROVO TUTTI GLI ANNUNCI DI UNA SPECIFICA CATEGORIA
    List<Annuncio> findAnnuncioByTitolo(String titolo);
    List<Annuncio> findAnnuncioByTitoloAndCategoria(String titolo, Categoria categoria);
    void save(Annuncio annuncio);
    void update(Annuncio annuncio, float prezzoScontato);
    void delete(Annuncio annuncio);
}
