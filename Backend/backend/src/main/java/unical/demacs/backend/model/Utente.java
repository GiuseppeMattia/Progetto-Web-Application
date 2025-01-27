package unical.demacs.backend.model;

import java.util.List;

public class Utente {

    private String username;
    private boolean tipo;
    private boolean amministratore;
    private String password;
    private String email;

    //I SEGUENTI CAMPI SERVONO??
    private List<Recensione> recensioniEffettuate;
    private List<Asta> asteAttive;
    private List<Annuncio> annunciAttivi;
    private List<Annuncio> annunciEffettuati;
    //

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

    public List<Asta> getAsteAttive() {
        return asteAttive;
    }

    public void setAsteAttive(List<Asta> asteAttive) {
        this.asteAttive = asteAttive;
    }

    public List<Annuncio> getAnnunciAttivi() {
        return annunciAttivi;
    }

    public void setAnnunciAttivi(List<Annuncio> annunciAttivi) {
        this.annunciAttivi = annunciAttivi;
    }

    public List<Annuncio> getAnnunciEffettuati() {
        return annunciEffettuati;
    }

    public void setAnnunciEffettuati(List<Annuncio> annunciEffettuati) {
        this.annunciEffettuati = annunciEffettuati;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "username='" + username + '\'' +
                ", tipo=" + tipo +
                ", amministratore=" + amministratore +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
