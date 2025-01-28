package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Asta;
import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.interfaces.AstaDAO;
import unical.demacs.backend.persistence.DBManager;
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
    public Asta findByAnnuncio(int idAnnuncio) {

        return astaDAO.findByAnnuncio(idAnnuncio);
    }

    @Override
    public List<Asta> findByCategoria(int idCategoria) {
        return astaDAO.findByCategoria(idCategoria);
    }

    @Override
    public List<Asta> findByUtenteAcquirente(String username, boolean terminata) {
        return astaDAO.findByUtenteAcquirente(username, terminata);
    }

    @Override
    public List<Asta> findBYUtenteVenditore(String username) {
        return astaDAO.findBYUtenteVenditore(username);
    }

    @Override
    public void save(Asta asta) {
        Annuncio annuncio = DBManager.getInstance().getAnnuncioDAO().findById(asta.getAnnuncio().getID());
        if(annuncio != null) {
            System.out.println("Non puoi salvare un'asta che si riferisce ad un annuncio che ha gia un asta");
            throw new IllegalArgumentException("Non puoi salvare un'asta che si riferisce ad un annuncio che ha gia un asta");
        }
        astaDAO.save(asta);
    }

    @Override
    public void update(Asta asta, float prezzo) {
        if(asta.getPrezzo() > prezzo) {
            throw new IllegalArgumentException("Non puoi proporre un prezzo inferiore");
        }

        astaDAO.update(asta, prezzo);
    }

    @Override
    public void delete(Asta asta) {
        astaDAO.delete(asta);
    }
}
