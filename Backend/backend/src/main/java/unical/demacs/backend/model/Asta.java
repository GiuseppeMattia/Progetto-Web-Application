package unical.demacs.backend.model;

import java.sql.Date;

public class Asta {

    private int ID;
    private Annuncio annuncio;
    private float prezzo;
    private Utente acquirente;
    private boolean terminated;
    private Date scadenza;


    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public Boolean getTerminated() {
        return terminated;
    }

    public void setTerminated(Boolean terminated) {
        this.terminated = terminated;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Annuncio getAnnuncio() {
        return annuncio;
    }

    public void setAnnuncio(Annuncio annuncio) {
        this.annuncio = annuncio;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Utente getAcquirente() {
        return acquirente;
    }

    public void setAcquirente(Utente acquirente) {
        this.acquirente = acquirente;
    }


    @Override
    public String toString() {
        return "Asta{" +
                "ID='" + ID + '\'' +
                //", annuncio=" + annuncio.getTitolo() +
                ", prezzo=" + prezzo +
                ", acquirente=" + acquirente.getUsername() +
                ", isTerminated=" + terminated +
                '}';
    }
}
