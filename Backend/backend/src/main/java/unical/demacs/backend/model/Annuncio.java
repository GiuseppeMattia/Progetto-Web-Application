package unical.demacs.backend.model;

import java.util.List;



public class Annuncio {

    protected int ID;   //
    private Categoria categoria;
    private String marca;   //
    private String modello; //
    private float prezzo;   //
    private String descrizione; //
    private byte[] foto;    //
    private String titolo;  //
    private Float prezzoScontato = null;    //
    private Utente venditore;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    protected List<Recensione> recensioni = null;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
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

    public Float getPrezzoScontato() {
        return prezzoScontato;
    }

    public void setPrezzoScontato(Float prezzoScontato) {
        this.prezzoScontato = prezzoScontato;
    }

    public Utente getVenditore() {
        return venditore;
    }

    public void setVenditore(Utente venditore) {
        this.venditore = venditore;
    }

}
