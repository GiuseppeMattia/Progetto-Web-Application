package unical.demacs.backend.model;

import java.util.List;

public class Utente {

    protected String username;
    private boolean tipo;
    private boolean amministratore;
    private String password;
    private String email;

    //I SEGUENTI CAMPI SERVONO??
    protected List<Recensione> recensioniEffettuate;
    protected List<Asta> asteAttiveDaVenditore;
    protected List<Asta> asteAttiveDaAcquirente;
    protected List<Annuncio> annunciAttivi;
    //

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

    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public boolean getAmministratore() {
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

    public List<Recensione> getRecensioniEffettuate() {
        return recensioniEffettuate;
    }

    public void setRecensioniEffettuate(List<Recensione> recensioniEffettuate) {
        this.recensioniEffettuate = recensioniEffettuate;
    }

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
                ", tipo=" + tipo +
                ", amministratore=" + amministratore +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", recensioniEffettuate=" + recensioniEffettuate +
                ", asteAttiveDaVenditore=" + asteAttiveDaVenditore +
                ", annunciAttivi=" + annunciAttivi +
                ", asteAttiveDaAcquirente=" + asteAttiveDaAcquirente +
                '}';
    }
}
