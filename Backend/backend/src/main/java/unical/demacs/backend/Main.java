package unical.demacs.backend;

import unical.demacs.backend.model.*;
import unical.demacs.backend.persistence.DAO.JDBC.*;

import unical.demacs.backend.persistence.DAO.Proxy.AnnuncioProxy;
import unical.demacs.backend.persistence.DAO.Proxy.UtenteProxy;
import unical.demacs.backend.persistence.DAO.interfaces.*;
import unical.demacs.backend.persistence.DBManager;

import javax.sound.midi.SoundbankResource;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Annuncio proxy = new Annuncio();
        proxy.setID(1);
        System.out.println(proxy.getTitolo());
    }
}
