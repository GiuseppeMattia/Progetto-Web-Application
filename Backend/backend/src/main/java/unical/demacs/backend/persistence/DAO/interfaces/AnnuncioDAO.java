package unical.demacs.backend.persistence.DAO.interfaces;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Categoria;

import java.util.List;

public interface AnnuncioDAO {

    List<Annuncio> findAll();                                     //TROVO TUTTI GLI ANNUNCI
    Annuncio findById(int id);                                    //TROVO UNO SPECIFICO ANNUNCIO
    List<Annuncio> findByUsernameUtente(String username);         //TROVO TUTTI GLI ANNUNCI DI UNO SPECIFICO UTENTE
    List<Annuncio> findByCategoria(int idCategoria);          //TROVO TUTTI GLI ANNUNCI DI UNA SPECIFICA CATEGORIA
    List<Annuncio> findAnnuncioByTitolo(String titolo);
    List<Annuncio> findAnnuncioByTitoloAndCategoria(String titolo, int idCategoria);
    void save(Annuncio annuncio);
    void update(Annuncio annuncio);
    void delete(Annuncio annuncio);

}
