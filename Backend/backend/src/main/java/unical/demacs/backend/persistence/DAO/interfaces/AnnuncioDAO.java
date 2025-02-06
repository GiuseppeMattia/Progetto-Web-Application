package unical.demacs.backend.persistence.DAO.interfaces;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Categoria;

import java.util.List;

public interface AnnuncioDAO {

    List<Annuncio> findAll();
    Annuncio findById(int id);
    List<Annuncio> findByUsernameUtente(String username);
    List<Annuncio> findByCategoria(int idCategoria);
    List<Annuncio> findAnnuncioByTitolo(String titolo);
    List<Annuncio> findAnnuncioByTitoloAndCategoria(String titolo, int idCategoria);
    void save(Annuncio annuncio);
    void update(Annuncio annuncio);
    void delete(Annuncio annuncio);

}
