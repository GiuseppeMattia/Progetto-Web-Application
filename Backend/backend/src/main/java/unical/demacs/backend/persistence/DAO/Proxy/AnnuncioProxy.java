package unical.demacs.backend.persistence.DAO.Proxy;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Recensione;
import unical.demacs.backend.persistence.DBManager;

import java.util.List;

public class AnnuncioProxy extends Annuncio {

    public List<Recensione> getRecensioni(){
        recensioni = DBManager.getInstance().getRecensioneDAO().findByAnnuncio(this.ID);
        return recensioni;
    }
}
