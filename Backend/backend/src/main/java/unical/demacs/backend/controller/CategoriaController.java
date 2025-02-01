package unical.demacs.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.persistence.DAO.JDBC.CategoriaDAOJDBC;
import unical.demacs.backend.persistence.DBManager;
import unical.demacs.backend.services.impl.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController() {
        categoriaService = new CategoriaService(new CategoriaDAOJDBC(DBManager.getInstance().getConnection()));
    }

    @GetMapping("/trovaTutte")
    public ResponseEntity<List<Categoria>> trovaTutte() {
        List<Categoria> categorie = categoriaService.findAll();
        return ResponseEntity.ok(categorie);
    }

    @GetMapping("/trovaById")
    public ResponseEntity<Categoria> trovaById(@RequestParam int id) {
        try{
            Categoria categoria = categoriaService.findById(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/trovaByNome")
    public ResponseEntity<Categoria> trovaByNome(@RequestParam String nome){

        try{
            Categoria categoria = categoriaService.findByName(nome);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }


}
