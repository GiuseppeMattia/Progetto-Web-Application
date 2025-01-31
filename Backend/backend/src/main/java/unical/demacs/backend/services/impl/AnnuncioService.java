package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.interfaces.AnnuncioDAO;
import unical.demacs.backend.persistence.DBManager;
import unical.demacs.backend.services.interfaces.IAnnuncioService;

import java.util.List;
import java.util.NoSuchElementException;

public class AnnuncioService implements IAnnuncioService {

    private final AnnuncioDAO annuncioDAO;

    public AnnuncioService(AnnuncioDAO annuncioDAO) {
        this.annuncioDAO = annuncioDAO;
    }

    @Override
    public List<Annuncio> findAll() {
        List<Annuncio> annunci = annuncioDAO.findAll();

        if(annunci == null){
            throw new NoSuchElementException("Lista vuota");
        }

        return annunci;
    }

    @Override
    public Annuncio findById(int id) {

        Annuncio annuncio = annuncioDAO.findById(id);
        if(annuncio == null){
            throw new IllegalArgumentException("L'annuncio con ID " + id + " non esiste.");
        }

        return annuncio;
    }

    @Override
    public List<Annuncio> findByUsernameUtente(String username) {

        Utente utente = DBManager.getInstance().getUtenteDAO().findByUsername(username);

        if(utente == null){
            throw new IllegalArgumentException("L'utente con username " + username + " non esiste.");
        }

        List<Annuncio> annunci = annuncioDAO.findByUsernameUtente(username);

        if(annunci == null){
            throw new NoSuchElementException("L'utente con username " + username + " non ha annunci attivi.");
        }

        return annunci;
    }

    @Override
    public List<Annuncio> findByCategoria(int idCategoria) {

        Categoria categoria = DBManager.getInstance().getCategoriaDAO().findById(idCategoria);

        if(categoria == null){
            throw new IllegalArgumentException("L'categoria con ID " + idCategoria + " non esiste.");
        }

        List<Annuncio> annunci = annuncioDAO.findByCategoria(idCategoria);
        if(annunci == null){
            throw new NoSuchElementException("Non esistono annunci che appartengono alla categoria con nome" + categoria.getNome());
        }
        return annunci;
    }

    @Override
    public List<Annuncio> findAnnuncioByTitolo(String titolo) {
        List<Annuncio> annunci = annuncioDAO.findAnnuncioByTitolo(titolo);

        if(annunci == null){
            throw new NoSuchElementException("Non esistono annunci con titolo " + titolo);
        }

        return annunci;
    }

    @Override
    public List<Annuncio> findAnnuncioByTitoloAndCategoria(String titolo, int idCategoria) {

        List<Annuncio> annunci = annuncioDAO.findAnnuncioByTitoloAndCategoria(titolo, idCategoria);

        if(annunci == null){
            throw new NoSuchElementException("Non esistono annunci con titolo " + titolo + " che appartengono alla categoria con id" + idCategoria);
        }

        return annunci;
    }

    @Override
    public void save(Annuncio annuncio) {
        annuncioDAO.save(annuncio);
    }

    @Override
    public void update(Annuncio annuncio, float prezzoScontato) {

        Annuncio daCercare = annuncioDAO.findById(annuncio.getID());
        if(daCercare == null){
            throw new IllegalArgumentException("L'annuncio che stai provando a modificare non esiste");
        }

        annuncioDAO.update(annuncio, prezzoScontato);
    }

    @Override
    public void delete(Annuncio annuncio) {

        Annuncio daCercare = annuncioDAO.findById(annuncio.getID());
        if(daCercare == null){
            throw new IllegalArgumentException("L'annuncio che stai provando ad eliminare non esiste");
        }
        annuncioDAO.delete(annuncio);
    }
}
