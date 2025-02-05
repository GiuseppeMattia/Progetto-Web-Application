package unical.demacs.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Utente {

    protected String username;
    private boolean venditore;
    private boolean amministratore;
    private String password;
    private String email;
    private String telefono;
    private boolean bannato;


    public boolean isBannato() {
        return bannato;
    }

    public void setBannato(boolean bannato) {
        this.bannato = bannato;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //I SEGUENTI CAMPI SERVONO??

    // protected List<Recensione> recensioniEffettuate;
    @JsonIgnore
    protected List<Asta> asteAttiveDaVenditore;
    @JsonIgnore
    protected List<Asta> asteAttiveDaAcquirente;
    @JsonIgnore
    protected List<Annuncio> annunciAttivi;

    public List<Asta> getAsteAttiveDaAcquirente() {
        return asteAttiveDaAcquirente;
    }


    //I GETTER DELLE LISTE ANDEREBBERO FATTI ASTRATTI? PERCHE TANTO VENGONO UTILIZZATI SOLO NEI PROXY

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isVenditore() {
        return venditore;
    }

    public void setVenditore(boolean venditore) {
        this.venditore = venditore;
    }

    public boolean isAmministratore() {
        return amministratore;
    }

    public void setAmministratore(boolean amministratore) {
        this.amministratore = amministratore;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public List<Recensione> getRecensioniEffettuate() {
//        return recensioniEffettuate;
//    }

//    public void setRecensioniEffettuate(List<Recensione> recensioniEffettuate) {
//        this.recensioniEffettuate = recensioniEffettuate;
//    }

    public List<Asta> getAsteAttiveDaVenditore() {
        return asteAttiveDaVenditore;
    }

    public void setAsteAttiveDaVenditore(List<Asta> asteAttiveDaVenditore) {
        this.asteAttiveDaVenditore = asteAttiveDaVenditore;
    }

    public List<Annuncio> getAnnunciAttivi() {
        return annunciAttivi;
    }

    public void setAnnunciAttivi(List<Annuncio> annunciAttivi) {
        this.annunciAttivi = annunciAttivi;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "username='" + username + '\'' +
                ", venditore=" + venditore +
                ", amministratore=" + amministratore +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                // ", recensioniEffettuate=" + recensioniEffettuate +
                ", asteAttiveDaVenditore=" + asteAttiveDaVenditore +
                ", annunciAttivi=" + annunciAttivi +
                ", asteAttiveDaAcquirente=" + asteAttiveDaAcquirente +
                ", bannato=" + bannato +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
