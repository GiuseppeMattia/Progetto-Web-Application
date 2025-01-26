package unical.demacs.backend.persistence.DAO.interfaces;

import unical.demacs.backend.model.Asta;
import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.model.Utente;

import java.util.List;

public interface AstaDAO {

    List<Asta> findAll();                                       //TROVA TUTTE LE ASTE ATTIVE
    Asta findById(String id);                                   //TROVA UNA SPECIFICA ASTA
    List<Asta> findByCategoria(Categoria categoria);            //TROVA TUTTE LE ASTE DI UNA CATEGORIA SPECIFICA
    List<Asta> attiva_findByUtente(Utente utente);              //TROVA TUTTE LE ASTE ATTIVE A CUI STA PARTECIPANDO UNO SPECIFICO UTENTE
    List<Asta> terminata_findByUtente(Utente utente);           //TROVA TUTTE LE ASTE TERMINATE(E VINTE) DA UNO SPECIFICO UTENTE (STORICO)
    void save(Asta asta);
    void update(Asta asta);
    void delete(Asta asta);
}
