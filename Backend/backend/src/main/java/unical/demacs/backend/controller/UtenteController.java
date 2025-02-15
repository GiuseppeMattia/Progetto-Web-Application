package unical.demacs.backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.JDBC.UtenteDAOJDBC;
import unical.demacs.backend.persistence.DBManager;
import unical.demacs.backend.services.impl.UtenteService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

    private final UtenteService utenteService;

    public UtenteController() {
        utenteService = new UtenteService(new UtenteDAOJDBC(DBManager.getInstance().getConnection()));
    }

    @PostMapping("/valida")
    public ResponseEntity<Boolean> validaUtente(@RequestBody Utente utente) {

        try{

            if(!Objects.equals(utenteService.findByUsername(utente.getUsername()).getPassword(), utente.getPassword())){
                return ResponseEntity.status(401).body(false);
            }
            return ResponseEntity.ok(true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(false);
        }

    }


    @PostMapping("/ban")
    public ResponseEntity<Boolean> banUtente(@RequestBody Utente utente) {

        try{
            utenteService.ban(utente);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(false);
        }
    }

    @GetMapping("/checkIfBanned")
    public ResponseEntity<Boolean> checkIfBanned(@RequestParam String username) {
        try{
            Utente utente = utenteService.findByUsername(username);
            if(utente.isBannato()){
                return ResponseEntity.ok(true);
            }
            return ResponseEntity.ok(false);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
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
    public ResponseEntity<Boolean> creaUtente(@RequestBody Utente utente){

        try{
            utenteService.save(utente);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(false);
        }

    }


    @PostMapping("/aggiorna")
    public ResponseEntity<Boolean> aggiornaUtente(@RequestBody Utente utente) {

        try{
            utenteService.update(utente);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(false);
        }
    }


    @DeleteMapping("/elimina")
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
