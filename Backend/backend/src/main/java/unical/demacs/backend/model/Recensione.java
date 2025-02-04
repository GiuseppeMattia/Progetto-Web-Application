package unical.demacs.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Recensione {

    private int ID;
    private String testo;

    private Utente autore;

    private Annuncio annuncio;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Utente getAutore() {
        return autore;
    }

    public void setAutore(Utente autore) {
        this.autore = autore;
    }

    public Annuncio getAnnuncio() {
        return annuncio;
    }

    public void setAnnuncio(Annuncio annuncio) {
        this.annuncio = annuncio;
    }

    @Override
    public String toString() {
        return "Recensione{" +
                "ID=" + ID +
                ", testo='" + testo + '\'' +
                ", autore=" + autore.getUsername() +
                ", annuncio=" + annuncio +
                '}';
    }
}
