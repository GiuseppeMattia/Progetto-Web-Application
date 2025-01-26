package unical.demacs.backend.model;

import java.util.List;



public class Annuncio {

    private int ID;
    private Articolo articolo;
    private float prezzo;
    private String descrizione;
    private byte[] foto;
    private String titolo;
    private float prezzoScontato;
    private Utente venditore;
    private List<Recensione> recensioni = null;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    public Articolo getArticolo() {
        return articolo;
    }

    public void setArticolo(Articolo articolo) {
        this.articolo = articolo;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public float getPrezzoScontato() {
        return prezzoScontato;
    }

    public void setPrezzoScontato(float prezzoScontato) {
        this.prezzoScontato = prezzoScontato;
    }

    public Utente getVenditore() {
        return venditore;
    }

    public void setVenditore(Utente venditore) {
        this.venditore = venditore;
    }

}
