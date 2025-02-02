package unical.demacs.backend.persistence.DAO.Proxy;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Asta;
import unical.demacs.backend.model.Recensione;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DBManager;

import java.util.List;

public class UtenteProxy extends Utente {

//    public List<Recensione> getRecensioni() {
//        recensioniEffettuate = DBManager.getInstance().getRecensioneDAO().findByUtente(username);
//        return recensioniEffettuate;
//    }

    public List<Asta> getAsteAttiveDaAcquirente(){
        asteAttiveDaAcquirente = DBManager.getInstance().getAstaDAO().findByUtenteAcquirente(username, false);
        return asteAttiveDaAcquirente;
    }

    public List<Asta> getAsteAttiveDaVenditore(){
        asteAttiveDaVenditore = DBManager.getInstance().getAstaDAO().findBYUtenteVenditore(username);
        return asteAttiveDaVenditore;
    }

    public List<Annuncio> geAnnunciAttivi(){
        annunciAttivi = DBManager.getInstance().getAnnuncioDAO().findByUsernameUtente(username);
        return annunciAttivi;
    }

}
