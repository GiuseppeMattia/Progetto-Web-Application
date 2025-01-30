package unical.demacs.backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.JDBC.UtenteDAOJDBC;
import unical.demacs.backend.persistence.DBManager;
import unical.demacs.backend.services.impl.UtenteService;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

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

    @GetMapping("/validaUtente")
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

    @PostMapping("/creaUtente")
    public ResponseEntity<Boolean> creaUtente(@RequestBody Utente utente) {
        Utente daTrovare = utenteService.findByUsername(utente.getUsername());

        if(daTrovare == null) {
            //SE L'UTENTE CON QUELLO USERNAME NON ESISTE GIA, LO POSSO SALVARE
            utenteService.save(utente);
            return ResponseEntity.ok(true);
        }
        else{
            //ALTRIMENTI, DO UN ERRORE
            return ResponseEntity.status(401).body(false);
        }
    }


}
