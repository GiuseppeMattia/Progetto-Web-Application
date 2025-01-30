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

        try{
            //SE NON CATTURO L'ECCEZIONE, QUESTO USERNAME APPARTIENE AD UN UTENTE
            utenteService.findByUsername(username);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            //SE LA CATTURO, SIGNIFICA CHE NON ESISTE UN UTENTE CON UNO USERNAME
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(false);
        }

    }


    //ANCHE QUESTA FUNZIONE (validaUtente), HA SENSO FARLA?
    //IN TEORIA, LATO front-end, POTREI FARE UNA CHIAMATA ALLA FUNZIONE trovaUtenteByUsername
    //E, DOPO AVER RICEVUTO L'UTENTE, VERIFICARE SE LE CREDENZIALI SONO CORRETTE.
    //COSA è PIU CORRETTO?

    //UTILIZZO UNA @RequestBody ANCHE SE MI SERVONO SOLO username E password PER NON PASSARE
    // LA PASSWORD IN CHIARO NELL'URL
    @GetMapping("/valida")
    public ResponseEntity<Boolean> validaUtente(@RequestBody Utente utente) {

        try{

            if(utenteService.findByUsername(utente.getUsername()).getPassword() != utente.getPassword()) {
                return ResponseEntity.status(401).body(false);
            }

            return ResponseEntity.ok(true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(false);
        }

    }


    @GetMapping("/trovaByUsername")
    public ResponseEntity<Utente> trovaUtenteByUsername(@RequestParam String username) {

        try{
            Utente utente = utenteService.findByUsername(username);
            return ResponseEntity.ok(utente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/trovaTutti")
    public ResponseEntity<List<Utente>> trovaTuttiUtenti() {

        try{
            List<Utente> utenti = utenteService.findAll();
            return ResponseEntity.ok(utenti);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }

    }

    @PostMapping("/crea")
    public ResponseEntity<Boolean> creaUtente(@RequestBody Utente utente) {

        try{

            utenteService.save(utente);
            return ResponseEntity.ok(true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(false);
        }

    }


    //PRENDO UN UTENTE NEL BODY, E PER SEMPLICITA, PRENDO UN PARAMETRO NELL'URL CHE RAPPRESENTA IL Boolean amministratore
    @PostMapping("/aggiorna")
    public ResponseEntity<Boolean> aggiornaUtente(@RequestBody Utente utente, @RequestParam Boolean amministratore) {

        try{
            utenteService.update(utente, amministratore);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(false);
        }
    }


    @PostMapping("/elimina")
    public ResponseEntity<Boolean> eliminaUtente(@RequestBody Utente utente) {

        try{
            utenteService.delete(utente);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(false);
        }
    }

}
