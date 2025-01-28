package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Recensione;
import unical.demacs.backend.persistence.DAO.interfaces.RecensioneDAO;
import unical.demacs.backend.services.interfaces.IRecensioneService;

import java.util.List;

public class RecensioneService implements IRecensioneService {

    private final RecensioneDAO recensioneDAO;

    public RecensioneService(RecensioneDAO recensioneDAO) {
        this.recensioneDAO = recensioneDAO;
    }

    @Override
    public List<Recensione> findByAnnuncio(int idAnnuncio) {
        return recensioneDAO.findByAnnuncio(idAnnuncio);
    }

    @Override
    public List<Recensione> findByUtente(String username) {
        return recensioneDAO.findByUtente(username);
    }

    @Override
    public void save(Recensione recensione) {
        recensioneDAO.save(recensione);
    }

    @Override
    public void update(Recensione recensione, String testo) {
        recensioneDAO.update(recensione, testo);
    }

    @Override
    public void delete(Recensione recensione) {
        recensioneDAO.delete(recensione);
    }
}
