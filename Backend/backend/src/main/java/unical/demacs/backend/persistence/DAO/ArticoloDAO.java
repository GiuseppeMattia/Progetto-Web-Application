package unical.demacs.backend.persistence.DAO;

import unical.demacs.backend.model.Articolo;

public interface ArticoloDAO {

    void save(Articolo articolo);
    void delete(Articolo articolo);
}
