package unical.demacs.backend.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unical.demacs.backend.model.Asta;
import unical.demacs.backend.persistence.DAO.JDBC.AstaDAOJDBC;
import unical.demacs.backend.persistence.DBManager;
import unical.demacs.backend.services.impl.AstaService;

import java.util.List;

@RestController
@RequestMapping("/api/asta")
public class AstaController {

    private final AstaService astaService;

    public AstaController() {
        astaService = new AstaService(new AstaDAOJDBC(DBManager.getInstance().getConnection()));
    }

    @GetMapping("/trovaTutte")
    public ResponseEntity<List<Asta>> trovaTutte() {

        try{
            List<Asta> aste = astaService.findAll();
            return ResponseEntity.ok().body(aste);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/trovaById")
    public ResponseEntity<Asta> trovaById(@RequestBody int id) {
        try{
            Asta asta = astaService.findById(id);
            return ResponseEntity.ok().body(asta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/trovaByAnnuncio")
    public ResponseEntity<Asta> trovaByAnnuncio(@RequestBody int idAnnuncio) {
        try{
            Asta asta = astaService.findByAnnuncio(idAnnuncio);
            return ResponseEntity.ok().body(asta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/trovaByCategoria")
    public ResponseEntity<List<Asta>> trovaByCategoria(@RequestParam int idCategoria) {

        try{
            List<Asta> aste = astaService.findByCategoria(idCategoria);
            return ResponseEntity.ok().body(aste);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }


    //SE terminata == true, TROVA TUTTE LE ASTE A CUI HO PARTECIPATO E CHE MI SONO PORTATO A CASA (lo storico)
    //SE terminata == false, TROVA TUTTE LE ASTE A CUI IO SONO IL PROBABILE FUTURO ACQUIRENTE

    @GetMapping("/trovaByUtenteAcquirente")
    public ResponseEntity<List<Asta>> trovaByUtenteAcquirente(@RequestParam String username, @RequestParam boolean terminata){

        try{
            List<Asta> aste = astaService.findByUtenteAcquirente(username, terminata);
            return ResponseEntity.ok().body(aste);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/trovaByUtenteVenditore")
    public ResponseEntity<List<Asta>> trovaByUtenteVenditore(@RequestParam String username){
        try{
            List<Asta> aste = astaService.findBYUtenteVenditore(username);
            return ResponseEntity.ok().body(aste);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/crea")
    public ResponseEntity<Boolean> crea(@RequestBody Asta asta) {
        System.out.println(asta.getID());
        try{
            astaService.save(asta);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(false);
        }
    }

    @PostMapping("/aggiorna")
    public ResponseEntity<Boolean> aggiorna(@RequestBody Asta asta) {
        try{
            System.out.println(asta.getID() + asta.getTerminated().toString());
            astaService.update(asta);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(false);
        }
    }

    @DeleteMapping("/elimina")
    public ResponseEntity<Boolean> elimina(@RequestBody Asta asta) {
        astaService.delete(asta);
        return ResponseEntity.ok(true);
    }



}
