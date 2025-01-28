package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Asta;
import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.interfaces.AstaDAO;
import unical.demacs.backend.services.interfaces.IAstaService;

import java.util.List;

public class AstaService implements IAstaService {

    private final AstaDAO astaDAO;

    public AstaService(AstaDAO astaDAO) {
        this.astaDAO = astaDAO;
    }

    @Override
    public List<Asta> findAll() {
        return astaDAO.findAll();
    }

    @Override
    public Asta findById(int id) {
        return astaDAO.findById(id);
    }

    @Override
    public Asta findByAnnuncio(Annuncio annuncio) {
        return astaDAO.findByAnnuncio(annuncio);
    }

    @Override
    public List<Asta> findByCategoria(Categoria categoria) {
        return astaDAO.findByCategoria(categoria);
    }

    @Override
    public List<Asta> findByUtenteAcquirente(Utente utente, boolean terminata) {
        return astaDAO.findByUtenteAcquirente(utente, terminata);
    }

    @Override
    public List<Asta> findBYUtenteVenditore(Utente venditore) {
        return astaDAO.findBYUtenteVenditore(venditore);
    }

    @Override
    public void save(Asta asta) {
        astaDAO.save(asta);
    }

    @Override
    public void update(Asta asta, float prezzo) {
        astaDAO.update(asta, prezzo);
    }

    @Override
    public void delete(Asta asta) {
        astaDAO.delete(asta);
    }
}
