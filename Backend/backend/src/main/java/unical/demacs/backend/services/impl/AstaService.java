package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Asta;
import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.interfaces.AstaDAO;
import unical.demacs.backend.persistence.DBManager;
import unical.demacs.backend.services.interfaces.IAstaService;

import java.util.List;
import java.util.NoSuchElementException;

public class AstaService implements IAstaService {

    private final AstaDAO astaDAO;

    public AstaService(AstaDAO astaDAO) {
        this.astaDAO = astaDAO;
    }

    @Override
    public List<Asta> findAll() {

        List<Asta> aste = astaDAO.findAll();
        if (aste == null) {
            throw new NoSuchElementException("Non ci sono state attive al momento");
        }
        return aste;
    }

    @Override
    public Asta findById(int id) {

        Asta asta = astaDAO.findById(id);
        if (asta == null) {
            throw new IllegalArgumentException("Non esiste un asta con id " + id);
        }

        return asta;
    }

    @Override
    public Asta findByAnnuncio(int idAnnuncio) {

        Asta asta = astaDAO.findByAnnuncio(idAnnuncio);

        if (asta == null) {
            throw new IllegalArgumentException("Non esiste un asta dell'annuncio che ha come id " + idAnnuncio);
        }

        return asta;
    }

    @Override
    public List<Asta> findByCategoria(int idCategoria) {

        Categoria categoria = DBManager.getInstance().getCategoriaDAO().findById(idCategoria);
        if (categoria == null) {
            throw new IllegalArgumentException("Non esiste una categoria con id " + idCategoria);
        }

        List<Asta> aste = astaDAO.findByCategoria(idCategoria);
        if (aste == null) {
            throw new NoSuchElementException("Non esiste aste appartenenti alla categoria " + categoria.getNome());
        }

        return aste;
    }

    @Override
    public List<Asta> findByUtenteAcquirente(String username, boolean terminata) {
        Utente utente = DBManager.getInstance().getUtenteDAO().findByUsername(username);
        if (utente == null) {
            throw new IllegalArgumentException("Non esiste un utente con username " + username);
        }

        List<Asta> aste = astaDAO.findByUtenteAcquirente(username, terminata);
        if (aste == null) {
            throw new NoSuchElementException("Questo utente non sta partecipando a nessuna asta ");
        }

        return aste;
    }

    @Override
    public List<Asta> findBYUtenteVenditore(String username){

        Utente utente = DBManager.getInstance().getUtenteDAO().findByUsername(username);
        if (utente == null) {
            throw new IllegalArgumentException("Non esiste un utente con username " + username);
        }

        return astaDAO.findBYUtenteVenditore(username);
    }

    @Override
    public void save(Asta asta) {

        Asta daCercare = astaDAO.findByAnnuncio(asta.getAnnuncio().getID());

        if(daCercare != null) {
            System.out.println("Non puoi salvare un'asta che si riferisce ad un annuncio che ha gia un asta");
            throw new IllegalArgumentException("Non puoi salvare un'asta che si riferisce ad un annuncio che ha gia un asta");
        }

        astaDAO.save(asta);
    }

    @Override
    public void update(Asta asta, float prezzo){

        Asta daCercare = astaDAO.findById(asta.getID());
        if(daCercare == null) {
            throw new IllegalArgumentException("L'asta che si vuole modificare con ID" + asta.getID() + " non esiste");
        }

        if(prezzo < asta.getPrezzo()) {
            throw new IllegalArgumentException("Non puoi proporre un prezzo inferiore");
        }

        astaDAO.update(asta, prezzo);
    }

    @Override
    public void delete(Asta asta){
        astaDAO.delete(asta);
    }
}
