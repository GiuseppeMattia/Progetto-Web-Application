package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Recensione;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.interfaces.RecensioneDAO;
import unical.demacs.backend.persistence.DBManager;
import unical.demacs.backend.services.interfaces.IRecensioneService;

import java.util.List;

public class RecensioneService implements IRecensioneService {

    private final RecensioneDAO recensioneDAO;

    public RecensioneService(RecensioneDAO recensioneDAO) {
        this.recensioneDAO = recensioneDAO;
    }

    @Override
    public List<Recensione> findByAnnuncio(int idAnnuncio){

        Annuncio annuncio = DBManager.getInstance().getAnnuncioDAO().findById(idAnnuncio);
        if(annuncio == null){
            throw new IllegalArgumentException("Non esiste un annuncio con id " + idAnnuncio);
        }

        return recensioneDAO.findByAnnuncio(idAnnuncio);
    }

    @Override
    public List<Recensione> findByUtente(String username) {

        Utente utente = DBManager.getInstance().getUtenteDAO().findByUsername(username);
        if(utente == null){
            throw new IllegalArgumentException("Non esiste un utente con username " + username);
        }

        return recensioneDAO.findByUtente(username);
    }

    @Override
    public void save(Recensione recensione){

        Annuncio annuncio = DBManager.getInstance().getAnnuncioDAO().findById(recensione.getAnnuncio().getID());
        if(annuncio == null){
            System.out.println("Non puoi lasciare una recensione per un annuncio che non esiste");
            throw new IllegalArgumentException("Annuncio non trovato");
        }

        recensioneDAO.save(recensione);
    }

    @Override
    public void update(Recensione recensione, String testo){
        recensioneDAO.update(recensione, testo);
    }

    @Override
    public void delete(Recensione recensione) {
        recensioneDAO.delete(recensione);
    }
}
