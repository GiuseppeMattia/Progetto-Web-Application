package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.persistence.DAO.interfaces.AnnuncioDAO;
import unical.demacs.backend.services.interfaces.IAnnuncioService;

import java.util.List;

public class AnnuncioService implements IAnnuncioService {

    private final AnnuncioDAO annuncioDAO;

    public AnnuncioService(AnnuncioDAO annuncioDAO) {
        this.annuncioDAO = annuncioDAO;
    }

    @Override
    public List<Annuncio> findAll() {
        return annuncioDAO.findAll();
    }

    @Override
    public Annuncio findById(int id) {
        return annuncioDAO.findById(id);
    }

    @Override
    public List<Annuncio> findByUsernameUtente(String IDUtente) {
        return annuncioDAO.findByUsernameUtente(IDUtente);
    }

    @Override
    public List<Annuncio> findByCategoria(int idCategoria) {
        return annuncioDAO.findByCategoria(idCategoria);
    }

    @Override
    public List<Annuncio> findAnnuncioByTitolo(String titolo) {
        return annuncioDAO.findAnnuncioByTitolo(titolo);
    }

    @Override
    public List<Annuncio> findAnnuncioByTitoloAndCategoria(String titolo, int idCategoria) {
        return annuncioDAO.findAnnuncioByTitoloAndCategoria(titolo, idCategoria);
    }

    @Override
    public void save(Annuncio annuncio) {
        annuncioDAO.save(annuncio);
    }

    @Override
    public void update(Annuncio annuncio, float prezzoScontato) {
        annuncioDAO.update(annuncio, prezzoScontato);
    }

    @Override
    public void delete(Annuncio annuncio) {
        annuncioDAO.delete(annuncio);
    }
}
