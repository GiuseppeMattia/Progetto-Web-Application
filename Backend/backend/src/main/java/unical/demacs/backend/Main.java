package unical.demacs.backend;

import org.springframework.aop.aspectj.AspectJAdviceParameterNameDiscoverer;
import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Recensione;
import unical.demacs.backend.persistence.DAO.JDBC.AnnuncioDAOJDBC;
import unical.demacs.backend.persistence.DAO.JDBC.AnnuncioProxy;
import unical.demacs.backend.persistence.DBManager;

import javax.sound.midi.SysexMessage;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnuncioDAOJDBC annuncioDAOJDBC = new AnnuncioDAOJDBC(DBManager.getInstance().getConnection());

        List<Annuncio> annunci = annuncioDAOJDBC.findAll();

        for (Annuncio annuncio : annunci) {
            AnnuncioProxy annuncioProxy = new AnnuncioProxy();

            annuncioProxy.setID(annuncio.getID());
            annuncioProxy.setFoto(annuncio.getFoto());
            annuncioProxy.setCategoria(annuncio.getCategoria());
            annuncioProxy.setMarca(annuncio.getMarca());
            annuncioProxy.setModello(annuncio.getModello());
            annuncioProxy.setPrezzo(annuncio.getPrezzo());
            annuncioProxy.setDescrizione(annuncio.getDescrizione());
            annuncioProxy.setTitolo(annuncio.getTitolo());
            annuncioProxy.setPrezzoScontato(annuncio.getPrezzoScontato());
            annuncioProxy.setVenditore(annuncio.getVenditore());

            List<Recensione> recensioni = annuncioProxy.getRecensioni();

            for (Recensione recensione : recensioni) {
                System.out.println("Scritta da: " + recensione.getAutore().getUsername());
                System.out.println("E dice: " + recensione.getTesto());
            }
        }
    }
}
