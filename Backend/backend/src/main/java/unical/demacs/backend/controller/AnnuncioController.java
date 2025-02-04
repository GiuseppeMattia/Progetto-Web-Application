package unical.demacs.backend.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Recensione;
import unical.demacs.backend.persistence.DAO.JDBC.AnnuncioDAOJDBC;
import unical.demacs.backend.persistence.DAO.Proxy.AnnuncioProxy;
import unical.demacs.backend.persistence.DBManager;
import unical.demacs.backend.services.impl.AnnuncioService;

import java.util.List;

@RestController
@RequestMapping("/api/annuncio")
public class AnnuncioController {


    private final AnnuncioService annuncioService;

    public AnnuncioController() {
        annuncioService = new AnnuncioService(new AnnuncioDAOJDBC(DBManager.getInstance().getConnection()));
    }

    @GetMapping("/trovaTutti")
    public ResponseEntity<List<Annuncio>> trovaTuttiAnnunci() {

        try{
            List<Annuncio> annunci = annuncioService.findAll();
            return ResponseEntity.ok(annunci);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/trovaByID")
    public ResponseEntity<Annuncio> trovaAnnuncioByID(@RequestParam("id")int id) {

        try{
            Annuncio annuncio = annuncioService.findById(id);
            return ResponseEntity.ok(annuncio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/trovaByUtente")
    public ResponseEntity<List<Annuncio>> trovaAnnunciByUtente(@RequestParam String username) {

        try{
            List<Annuncio> annunci = annuncioService.findByUsernameUtente(username);
            return ResponseEntity.ok(annunci);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/trovaByCategoria")
    public ResponseEntity<List<Annuncio>> trovaAnnunciByCategoria(@RequestParam int IDCategoria) {

        try{
            List<Annuncio> annunci = annuncioService.findByCategoria(IDCategoria);
            return ResponseEntity.ok(annunci);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }


    @GetMapping("/trovaByTitolo")
    public ResponseEntity<List<Annuncio>> trovaAnnunciTitolo(@RequestParam String titolo) {

        try{
            List<Annuncio> annunci = annuncioService.findAnnuncioByTitolo(titolo);
            return ResponseEntity.ok(annunci);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/trovaByTitoloAndCategoria")
    public ResponseEntity<List<Annuncio>> trovaAnnunciByTitoloAndCategoria(@RequestParam String titolo, @RequestParam int IDCategoria) {

        try{
            List<Annuncio> annuncio = annuncioService.findAnnuncioByTitoloAndCategoria(titolo, IDCategoria);
            return ResponseEntity.ok(annuncio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }

    }

    @PostMapping("/crea")
    public ResponseEntity<Boolean> creaAnnuncio(@RequestBody Annuncio annuncio) {

        annuncioService.save(annuncio);
        return ResponseEntity.ok(true);

    }

    //NEL BODY, HA SENSO PASSARE TUTTO L'ANNUNCIO SE IO POI VADO AD UTILIZZARE SOLAMENTE L'ID??
    @PostMapping("/aggiornaPrezzo")
    public ResponseEntity<Boolean> aggiornaPrezzo(@RequestBody Annuncio annuncio, @RequestParam float prezzo) {

      try{
          annuncioService.update(annuncio, prezzo);
          return ResponseEntity.ok(true);
      } catch (Exception e) {
          System.out.println(e.getMessage());
          return ResponseEntity.status(404).body(null);
      }

    }

    @DeleteMapping("/elimina")
    public ResponseEntity<Boolean> eliminaAnnuncio(@RequestBody Annuncio annuncio) {

        try{
            annuncioService.delete(annuncio);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

}
