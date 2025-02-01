package unical.demacs.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unical.demacs.backend.model.Recensione;
import unical.demacs.backend.persistence.DAO.JDBC.RecensioneDAOJDBC;
import unical.demacs.backend.persistence.DBManager;
import unical.demacs.backend.services.impl.RecensioneService;

import java.util.List;

@RestController
@RequestMapping("/api/recensioni")
public class RecensioniController {

    private final RecensioneService recensioneService;

    public RecensioniController() {
        recensioneService = new RecensioneService(new RecensioneDAOJDBC(DBManager.getInstance().getConnection()));
    }

    @GetMapping("/trovaByAnnuncio")
    public ResponseEntity<List<Recensione>> trovaByAnnuncio(@RequestParam int idAnnuncio){

        try{
            List<Recensione> recensioni = recensioneService.findByAnnuncio(idAnnuncio);
            return ResponseEntity.ok(recensioni);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/trovaByUtente")
    public ResponseEntity<List<Recensione>> trovaByUtente(@RequestParam String username){
        try{
            List<Recensione> recensioni = recensioneService.findByUtente(username);
            return ResponseEntity.ok(recensioni);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/crea")
    public ResponseEntity<Recensione> crea(@RequestBody Recensione recensione){
        try{
            recensioneService.save(recensione);
            return ResponseEntity.ok(recensione);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/aggiorna")
    public ResponseEntity<Recensione> aggiorna(@RequestBody Recensione recensione){
        recensioneService.save(recensione);
        return ResponseEntity.ok(recensione);
    }

    @DeleteMapping("/elimina")
    public ResponseEntity<Recensione> elimina(@RequestBody Recensione recensione){
        recensioneService.delete(recensione);
        return ResponseEntity.ok(recensione);
    }

}
