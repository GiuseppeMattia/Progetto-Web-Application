package unical.demacs.backend.services.interfaces;

import unical.demacs.backend.model.Categoria;

import java.util.List;

public interface ICategoriaService {
    List<Categoria> findAll();
    Categoria findById(int id);
    Categoria findByName(String nome);
}
