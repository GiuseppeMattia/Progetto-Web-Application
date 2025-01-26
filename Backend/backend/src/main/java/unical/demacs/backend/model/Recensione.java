package unical.demacs.backend.model;

public class Recensione {

    private int ID;
    private String testo;
    private Utente autore;

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
}
