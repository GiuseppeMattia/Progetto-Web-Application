package unical.demacs.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
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
    protected List<Recensione> recensioni = null;


    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    @Override
    public String toString() {
        return "Annuncio{" +
                "ID=" + ID +
                ", categoria=" + categoria +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", prezzo=" + prezzo +
                ", descrizione='" + descrizione + '\'' +
                // ", foto=" + Arrays.toString(foto) +
                ", titolo='" + titolo + '\'' +
                ", prezzoScontato=" + prezzoScontato +
                ", venditore=" + venditore +
                '}';
    }

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

    public int getID() {
        return ID;
    }


    //QUESTA FUNZIONA VA TOLTA??
    //L'ID E' AUTOINCREMENT SULLA BASE DI DATI, QUINDI IN TEORIA ANDREBBE TOLTO
    public void setID(int ID) {
        this.ID = ID;
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
