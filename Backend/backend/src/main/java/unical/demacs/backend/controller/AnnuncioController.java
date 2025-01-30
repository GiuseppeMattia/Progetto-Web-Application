package unical.demacs.backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.persistence.DAO.JDBC.AnnuncioDAOJDBC;
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

        List<Annuncio> annunci = annuncioService.findAll();

        if(annunci.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(annunci);
    }

    @GetMapping("/trovaByID")
    public ResponseEntity<Annuncio> trovaAnnuncioByID(@RequestBody int id) {

        Annuncio annuncio = annuncioService.findById(id);

        if(annuncio == null) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(annuncio);
    }

    @GetMapping("/trovaByUtente")
    public ResponseEntity<List<Annuncio>> trovaAnnunciByUtente(@RequestBody String username) {

        //DEVO VERIFICARE SE MI VIENE PASSATO UN UTENTE CHE NON ESISTE?

        List<Annuncio> annunci = annuncioService.findByUsernameUtente(username);

        if(annunci.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(annunci);
    }

    @GetMapping("/trovaByCategoria")
    public ResponseEntity<List<Annuncio>> trovaAnnunciByCategoria(@RequestBody int IDCategoria) {

        List<Annuncio> annunci = annuncioService.findByCategoria(IDCategoria);

        if(annunci.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(annunci);
    }

    @GetMapping("/trovaByTitolo")
    public ResponseEntity<List<Annuncio>> trovaAnnunciTitolo(@RequestBody String titolo) {

        List<Annuncio> annunci = annuncioService.findAnnuncioByTitolo(titolo);

        if(annunci.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(annunci);
    }

    @GetMapping("/trovaByTitoloAndCategoria")
    public ResponseEntity<List<Annuncio>> trovaAnnunciByTitoloAndCategoria(@RequestParam String titolo, @RequestParam int IDCategoria) {

        List<Annuncio> annunci = annuncioService.findAnnuncioByTitoloAndCategoria(titolo, IDCategoria);

        if(annunci.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(annunci);
    }

    @PostMapping("/crea")
    public ResponseEntity<Boolean> creaAnnuncio(@RequestBody Annuncio annuncio) {

        annuncioService.save(annuncio);
        return ResponseEntity.ok(true);

    }


    //NEL BODY, HA SENSO PASSARE TUTTO L'ANNUNCIO SE IO POI VADO AD UTILIZZARE SOLAMENTE L'ID??
    @PostMapping("/aggiornaPrezzo")
    public ResponseEntity<Boolean> aggiornaPrezzo(@RequestBody Annuncio annuncio, @RequestParam float prezzo) {

        Annuncio daCercare = annuncioService.findById(annuncio.getID());
        if(daCercare == null) {
            return ResponseEntity.status(404).build();
        }

        annuncioService.update(annuncio, prezzo);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/elimina")
    public ResponseEntity<Boolean> eliminaAnnuncio(@RequestBody Annuncio annuncio) {
        Annuncio daCercare = annuncioService.findById(annuncio.getID());
        if(daCercare == null) {
            return ResponseEntity.status(404).build();
        }
        annuncioService.delete(annuncio);
        return ResponseEntity.ok(true);
    }







}
