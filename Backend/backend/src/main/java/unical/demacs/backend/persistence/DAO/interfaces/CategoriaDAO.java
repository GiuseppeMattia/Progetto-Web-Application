package unical.demacs.backend.persistence.DAO.interfaces;

import unical.demacs.backend.model.Categoria;

import java.util.List;

public interface CategoriaDAO {

    List<Categoria> findAll();
    Categoria findById(int id);
    Categoria findByName(String nome);
}
