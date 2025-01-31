package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.persistence.DAO.interfaces.CategoriaDAO;
import unical.demacs.backend.services.interfaces.ICategoriaService;

import java.util.List;

public class CategoriaService implements ICategoriaService {

    private final CategoriaDAO categoriaDAO;

    public CategoriaService(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaDAO.findAll();
    }

    @Override
    public Categoria findById(int id){
        return categoriaDAO.findById(id);
    }

    @Override
    public Categoria findByName(String nome) {
        return categoriaDAO.findByName(nome);
    }
}
