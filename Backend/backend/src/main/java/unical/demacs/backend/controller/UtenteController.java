package unical.demacs.backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.JDBC.UtenteDAOJDBC;
import unical.demacs.backend.persistence.DBManager;
import unical.demacs.backend.services.impl.UtenteService;

import java.util.List;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

    //NOTA BENE
    //DECIDERE CON CRITERIO QUALI CODICI DI ERRORE RESTITUIRE

    private final UtenteService utenteService;

    public UtenteController() {
        utenteService = new UtenteService(new UtenteDAOJDBC(DBManager.getInstance().getConnection()));
    }


    // HA SENSO FARE UNA FUNZIONE CHE VERIFICA SOLAMENTE SE UNO USERNAME ESISTE GIA?
    //QUESTO CONTROLLO LO FACCIO QUANDO PROVO A SALVARE UN UTENTE
    @GetMapping("/verificaUsername")
    public ResponseEntity<Boolean> verificaUsername(@RequestParam String username) {


        //RITORNA OK SE E' STATO TROVATO UN UTENTE CON QUELLO USERNAME
        Utente utente = utenteService.findByUsername(username);
        return ResponseEntity.ok(utente != null);

    }


    //ANCHE QUESTA FUNZIONE (validaUtente), HA SENSO FARLA?
    //IN TEORIA, LATO front-end, POTREI FARE UNA CHIAMATA ALLA FUNZIONE trovaUtenteByUsername
    //E, DOPO AVER RICEVUTO L'UTENTE, VERIFICARE SE LE CREDENZIALI SONO CORRETTE.
    //COSA E' PIU CORRETTO???

    //DA MODIFICARE
    //NON PASSARE (NON PASSARE) LA PASSWORD COME PARAMETRO.             MODIFICA MODIFICA
    @GetMapping("/valida")
    public ResponseEntity<Boolean> validaUtente(@RequestParam String username, @RequestParam String password) {

        Utente utente = utenteService.findByUsername(username);
        if(utente == null) {
            //SE L'UTENTE NON ESISTE PROPRIO, C'E' UN ERRORE
            return ResponseEntity.status(401).body(false);
        } else{
            //SE L'UTENTE ESISTE, E LA PASSWORD E' QUELLA, VA BENE
            //ALTRIMENTI, C'E' UN ERRORE
            if(utente.getPassword().equals(password)) {
                return ResponseEntity.ok(true);
            } else{
                return ResponseEntity.status(401).body(false);
            }
        }

    }

    @GetMapping("/trovaByUsername")
    public ResponseEntity<Utente> trovaUtenteByUsername(@RequestParam String username) {

        Utente utente = utenteService.findByUsername(username);

        if(utente == null) {
            return ResponseEntity.status(401).body(null);
        }

        return ResponseEntity.ok(utente);
    }

    @GetMapping("/trovaTutti")
    public ResponseEntity<List<Utente>> trovaTuttiUtenti() {
        List<Utente> utenti = utenteService.findAll();

        if(utenti.isEmpty()) {
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.ok(utenti);
    }

    @PostMapping("/crea")
    public ResponseEntity<Boolean> creaUtente(@RequestBody Utente utente) {
        Utente daTrovare = utenteService.findByUsername(utente.getUsername());

        if(daTrovare == null) {
            //SE L'UTENTE CON QUELLO USERNAME NON ESISTE GIA, LO POSSO SALVARE
            utenteService.save(utente);
            return ResponseEntity.ok(true);
        }
        //ALTRIMENTI, DO UN ERRORE
        return ResponseEntity.status(401).body(false);

    }


    //PRENDO UN UTENTE NEL BODY, E PER SEMPLICITA, PRENDO UN PARAMETRO NELL'URL CHE RAPPRESENTA IL Boolean amministratore
    @PostMapping("/aggiorna")
    public ResponseEntity<Boolean> aggiornaUtente(@RequestBody Utente utente, @RequestParam Boolean amministratore) {
        Utente daTrovare = utenteService.findByUsername(utente.getUsername());

        if(daTrovare == null) {
            //SE NON TROVO L'UTENTE DA AGGIORNARE, DO UN 404 NOT FOUND
            return ResponseEntity.status(404).body(false);
        }

        if(daTrovare.getAmministratore() == amministratore) {
            //IL CODICE DI ERRORE 409 INDICA CHE LA RICHIESTA E' IN CONFLITTO
            return ResponseEntity.status(409).body(false);
        }

        utenteService.update(utente, amministratore);
        return ResponseEntity.ok(true);
    }


    @PostMapping("/elimina")
    public ResponseEntity<Boolean> eliminaUtente(@RequestBody Utente utente) {
        Utente daTrovare = utenteService.findByUsername(utente.getUsername());
        if(daTrovare == null) {
            //NON POSSO ELIMINARE UN UTENTE CHE NON ESISTE
            return ResponseEntity.status(404).body(false);
        }

        utenteService.delete(utente);
        return ResponseEntity.ok(true);
    }

}
