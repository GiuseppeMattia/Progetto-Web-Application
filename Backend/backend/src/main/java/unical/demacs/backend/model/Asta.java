package unical.demacs.backend.model;

public class Asta {

    private int ID;
    private Annuncio annuncio;
    private float prezzo;
    private Utente acquirente;
    private boolean isTerminated;

    public Boolean getTerminated() {
        return isTerminated;
    }

    public void setTerminated(Boolean terminated) {
        isTerminated = terminated;
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
                ", isTerminated=" + isTerminated +
                '}';
    }
}
