package unical.demacs.backend.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/trovaById")
    public ResponseEntity<Asta> trovaById(@RequestParam int id) {
        try{
            Asta asta = astaService.findById(id);
            return ResponseEntity.ok().body(asta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/trovaByAnnuncio")
    public ResponseEntity<Asta> trovaByAnnuncio(@RequestParam int idAnnuncio) {

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
    //SE terminata == false, TROVA TUTTE LE ASTE A CUI IO STO PARTECIOPANDO

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

}
