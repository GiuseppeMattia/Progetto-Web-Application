package unical.demacs.backend.services.impl;

import unical.demacs.backend.model.Categoria;
import unical.demacs.backend.persistence.DAO.interfaces.CategoriaDAO;
import unical.demacs.backend.services.interfaces.ICategoriaService;

import java.util.List;
import java.util.NoSuchElementException;

public class CategoriaService implements ICategoriaService {

    private final CategoriaDAO categoriaDAO;

    public CategoriaService(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public List<Categoria> findAll(){
        return categoriaDAO.findAll();
    }

    @Override
    public Categoria findById(int id){

        Categoria categoria = categoriaDAO.findById(id);
        if(categoria == null){
            throw new IllegalArgumentException("Non esiste una categoria con id " + id);
        }
        return  categoria;
    }

    @Override
    public Categoria findByName(String nome){

        Categoria categoria = categoriaDAO.findByName(nome);
        if(categoria == null){
            throw new IllegalArgumentException("Non esiste una categoria con nome " + nome);
        }

        return categoria;
    }
}
